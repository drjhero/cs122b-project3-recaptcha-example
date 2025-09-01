## CS 122B Project 3 reCAPTCHA example

This example shows how to add reCAPTCHA to your frontend and backend.

### To run this example: 
1. Clone this repository.
2. Open IntelliJ -> Open -> Choose the project you just cloned (The root path must contain the pom.xml!) -> The IntelliJ will load automatically. 
3. Get a [reCAPTCHA](https://www.google.com/recaptcha/intro/v3.html) from Google. Click on the `Get Started` button. Fill out a label. Select "Challenge (v2). 
   Select "I'm not a robot" Checkbox. Enter "localhost" in Domains and click the Submit button.
4. In [src/RecaptchaConstants.java](src/RecaptchaConstants.java), replace `YOUR_SECRET_KEY;` with your own reCAPTCHA `secret key`. 
5. In [WebContent/index.html](WebContent/index.html), replace `data-sitekey="YOUR_SITE_KEY"` with your own reCAPTCHA `site key`. 
6. In [src/FormRecaptcha.java](src/FormRecaptcha.java), change the mysql username and password if you don't want to use `mytestuser`. 
7. Run the project locally on Tomcat. It will present you with a ReCaptcha challenge that you can interact with. 
