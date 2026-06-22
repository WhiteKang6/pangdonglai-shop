package com.database.homework.pangdonglaishop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    public static String encode(String rawPassword){
        return encoder.encode(rawPassword);
    }
    public static Boolean matches(String rawPassword,String encodePassword){
        return encoder.matches(rawPassword,encodePassword);
    }


}
