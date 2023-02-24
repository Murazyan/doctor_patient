package com.doctor_patinet.doctor_patient_project.util;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

public class AppUtil {

    public static String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


    @SneakyThrows
    public static Properties loadProperties(){
        Properties properties = new Properties();
        properties.load(new FileInputStream(
                "C:\\Users\\Margarita_Murazyan\\Desktop\\second\\doctor_patient\\src\\main\\resources\\application.properties"));
        return properties;
    }
}
