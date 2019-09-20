package com.wjh.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        String encode = bCryptPasswordEncoder.encode(password);

        return encode;
    }

    public static void main(String[] args) {
        String password="jianhao";
        String s = encodePassword(password);
        System.out.println(s);
/*
UUID uuid =new UUID(100L,50L);
        String s = uuid.toString();
        System.out.println(uuid);
        System.out.println(s);
*/
    }
}
