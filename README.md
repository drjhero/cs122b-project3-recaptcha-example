## CS 122B Project 3 reCAPTCHA example

This example shows how to add reCAPTCHA to your frontend and backend.

### To run this example: 
1. Clone this repository.
2. Open IntelliJ -> Open -> Choose the project you just cloned (The root path must contain the pom.xml!) -> The IntelliJ will load automatically. 
3. Get a [reCAPTCHA](https://www.google.com/recaptcha/intro/v3.html) from Google. 
   `v3 Admin Console` -> `Register a new site` -> Choose `reCAPTCHA v2` -> Enter both your AWS public IP and "localhost" in "Domains" section -> Click "Submit"
4. In [src/RecaptchaConstants.java](src/RecaptchaConstants.java), replace `YOUR_SECRET_KEY;` with your own reCAPTCHA `secret key`. 
5. In [WebContent/index.html](WebContent/index.html), replace `data-sitekey="YOUR_SITE_KEY"` with your own reCAPTCHA `site key`. 
6. In [src/FormRecaptcha.java](src/FormRecaptcha.java), change the mysql username and password if you don't want to use `mytestuser`. 
7. You can run this project on Tomcat (both local machine and AWS).
