package com.xworkz.project.util;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class EncryptUtil {
    private EncryptUtil(){

    }
    public static String encrypt(String algorithm, String input
                                 ) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey key=generateKey(128);
        System.out.println("key E:"+ Arrays.toString(key.getEncoded()));
        GCMParameterSpec iv=generateIv();
        System.out.println(Arrays.toString(iv.getIV()));
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        String en= Base64.getEncoder()
                .encodeToString(cipherText);
        System.out.println("==============en==============");
        System.out.println(en);
        System.out.println(decrypt("AES/GCM/NoPadding",en,key,iv));

        return en;
    }
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
    public static GCMParameterSpec generateIv() {
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        return new GCMParameterSpec(128, iv);
    }
    public static String decrypt(String algorithm, String cipherText,SecretKey key, GCMParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
      //  SecretKey key=generateKey(128);
        System.out.println("key D:"+ Arrays.toString(key.getEncoded()));

        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
