package com.chatApp.demo.utils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hasher {
//    Create a salt. In production, this should be a ENV variable
    private static String salt = "secret";
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

//================Methods===========================

    private static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }


    public static String createHashedString (String stringToHash){
        MessageDigest md= null;

        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
//            Print the error
            e.printStackTrace();
        }
//       Add the salt and predefined string to the md object
        String saltedStringToHash= salt+stringToHash;
        md.update(saltedStringToHash.getBytes(StandardCharsets.UTF_8));
//       Convert it to a hashed string
        byte[] digest = md.digest();

        return bytesToHex(digest);
//        try {
//            //    Create a md instance
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
////           Use md instance to hash the (salt+password)
//            String hashedString = Arrays.toString(md.digest((stringToHash+salt).getBytes(StandardCharsets.UTF_8)));
//           return hashedString;
//        } catch (NoSuchAlgorithmException e) {
////          Print the error
//            e.printStackTrace();
//        }
//        return null;
    }
}
