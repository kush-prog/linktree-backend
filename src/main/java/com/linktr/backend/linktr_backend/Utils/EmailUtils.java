package com.linktr.backend.linktr_backend.Utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
        return "Hello " + name + "," + "\n\nYour new account has been created. Please click the" +
                " link below to verify your account. \n\n" + getVerificationUrl(host, token);
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/auth?token=" + token;
    }

}
