package com.xworkz.project.util;

import java.util.Random;

public class GenerateOTPUtil {
    private GenerateOTPUtil()
    {

    }
    public static int generateOTP() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // always between 1000 and 9999
    }


}
