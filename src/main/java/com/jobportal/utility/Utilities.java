package com.jobportal.utility;

import java.util.Random;

public class Utilities {
    public static String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
}
