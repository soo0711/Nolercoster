package com.Nolercoster.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {
	
	// salt 생성
    public static String createSalt(String plainText) throws NoSuchAlgorithmException {
    	// SHA1PRNG알고리즘으로 난수 생성
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte [] bytes = new byte[16];
        random.nextBytes(bytes);

        //SALT 생성
        String salt = new String(Base64.getEncoder().encode(bytes));

        return salt;
    }
    
    public static String SHA256 (String plainText, String salt) {
        try {
            // 1
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String rawAndSalt = plainText + salt;

            // 2
            md.update(rawAndSalt.getBytes());

            // 3
            byte[] byteData = md.digest(); // 1byte = 8bit

            // 4
            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i < byteData.length; ++i) {
                String hex = Integer.toHexString(255 & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');// 각 byteData당 두 자리 수 16진수로 변환
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}