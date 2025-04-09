package com.jobportal.utility;


public class Data {

    public static String getMessageBody(String otp, String name) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Your OTP Code</title>" +
                "<style>" +
                "    body {" +
                "        font-family: Arial, sans-serif;" +
                "        background-color: #f4f4f4;" +
                "        margin: 0;" +
                "        padding: 0;" +
                "    }" +
                "    .container {" +
                "        width: 100%;" +
                "        max-width: 600px;" +
                "        margin: 0 auto;" +
                "        background-color: #ffffff;" +
                "        border-radius: 8px;" +
                "        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);" +
                "        padding: 20px;" +
                "        text-align: center;" +
                "    }" +
                "    .header {" +
                "        font-size: 24px;" +
                "        font-weight: bold;" +
                "        color: #333;" +
                "        margin-bottom: 20px;" +
                "    }" +
                "    .otp-code {" +
                "        font-size: 40px;" +
                "        font-weight: bold;" +
                "        color: #4CAF50;" +
                "        padding: 10px;" +
                "        border-radius: 5px;" +
                "        background-color: #f0f8f1;" +
                "        margin: 20px 0;" +
                "    }" +
                "    .footer {" +
                "        font-size: 14px;" +
                "        color: #666;" +
                "        margin-top: 20px;" +
                "    }" +
                "    .footer a {" +
                "        color: #4CAF50;" +
                "        text-decoration: none;" +
                "    }" +
                "</style>" +
                "</head>" +
                "<body>" +

                "<div class=\"container\">" +
                "    <div class=\"header\">" +
                "        Hello, " + name + "!" +
                "    </div>" +
                "    <div>" +
                "        <p>We received a request to verify your identity. Below is your One-Time Password (OTP):</p>" +
                "        <div class=\"otp-code\">" +
                "            " + otp + "</div>" +
                "        <p>This OTP will expire in 5 minutes. Please enter it to complete the verification process.</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>If you did not request this, please ignore this email.</p>" +
                "    </div>" +
                "</div>" +

                "</body>" +
                "</html>";
    }
}
