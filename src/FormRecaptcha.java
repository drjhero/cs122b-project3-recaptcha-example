import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "FormReCaptcha", urlPatterns = "/form-recaptcha")
public class FormRecaptcha extends HttpServlet {
    public String getServletInfo() {
        return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);

        // Verify the ReCAPTCHA succeeded
        if (!RecaptchaVerifyUtils.verify(gRecaptchaResponse)) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Error</title></head>");
                out.println("<body>");
                out.println("<p>recaptcha verification error</p>");
                out.println("</body>");
                out.println("</html>");
                out.close();
                return;
            }
        }

        String loginUser = "mytestuser";
        String loginPasswd = "My6Password";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedbexample";
        response.setContentType("text/html"); // Response mime type

        // Create a new connection to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter out = response.getWriter();
             Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     String.format("SELECT * from stars where name like '%s'", request.getParameter("name")))) {

            out.println("<html><head><title>MovieDB: Found Records</title></head>");
            out.println("<body><h1>MovieDB: Found Records</h1>");
            out.println("<table border>");
            out.println("<tr><td>ID</td><td>Name</td></tr>");

            while (resultSet.next()) {
                String m_ID = resultSet.getString("ID");
                String m_Name = resultSet.getString("name");
                out.println(String.format("<tr><td>%s</td><td>%s</td></tr>", m_ID, m_Name));
            }
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head><title>Error</title></head>");
                out.println("<body>");
                out.println("<p>error:</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
