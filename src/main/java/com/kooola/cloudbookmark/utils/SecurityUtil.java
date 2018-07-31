package com.kooola.cloudbookmark.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by march on 2018/7/26.
 */
public final class SecurityUtil {

    /**
     * md5加密
     *
     * @param source 数据源
     * @return 加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte anEncode : encode) {
            String hex = Integer.toHexString(0xff & anEncode);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    /**
     * AES加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encryptedBytes);
    }

    /**
     * AES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptAes(String data, String key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }
}
