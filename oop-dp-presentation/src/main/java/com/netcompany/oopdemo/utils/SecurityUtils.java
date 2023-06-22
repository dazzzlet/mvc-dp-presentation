package com.netcompany.oopdemo.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static boolean checkPassword(String userPassword, String hashedPassword) {
        String userHash = getHashedPassword(userPassword);
        return userHash.equals(hashedPassword);
    }

    public static String getHashedPassword(String userPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userPassword.getBytes());
            byte[] digest = md.digest();
            String userHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            return userHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}