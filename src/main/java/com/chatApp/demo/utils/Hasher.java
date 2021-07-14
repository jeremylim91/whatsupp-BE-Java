package com.chatApp.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;

public class Hasher {
//    Create a salt. In production, this should be a ENV variable
    private String salt = "My secret salt";

//    private MessageDigest

    public byte[] getHashedString (String stringToHash){
//    Create a md instance
        try {
           MessageDigest md = MessageDigest.getInstance("SHA-512");
           byte [] hashedString = md.digest((stringToHash+salt).getBytes(StandardCharsets.UTF_8));
           return hashedString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
