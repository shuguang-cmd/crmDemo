package com.crm.utils;
import java.util.Base64;

public class UserIDBase64 {
    public static String encoderUserID(Integer userId) {
        return Base64.getEncoder().encodeToString(String.valueOf(userId).getBytes());
    }
}