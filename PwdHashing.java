package com.emrsys.medmatrix.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdHashing {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String pwdEnCode(String pwd) {
        return bCryptPasswordEncoder.encode(pwd);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}

