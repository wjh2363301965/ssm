package com.wjh.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        String encode = bCryptPasswordEncoder.encode(password);

        return encode;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<DateUtils> dateUtilsClass = DateUtils.class;
        Class<BCryptPasswordEncoderUtils> b = BCryptPasswordEncoderUtils.class;

        DateUtils cast = dateUtilsClass.cast(dateUtilsClass);

    }
}
