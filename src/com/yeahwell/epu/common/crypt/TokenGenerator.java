package com.yeahwell.epu.common.crypt;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yeahwell
 */
public class TokenGenerator {
	
    
    private static final String ENCODING         = "UTF-8";
    private static final String ALGORITHM        = "DESede";
    private static final String ALGORITHM_CIPHER = "DESede/ECB/PKCS5Padding";
    private static final String HEXSTR           = "0123456789ABCDEF";
    private static final String RANDOMSTR         = "abcdefghijklmnopqrstuvwxyz0123456789";
    
    private static final Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
    
    private TokenGenerator(){}
    
    /**
     * 生成TokenNumber，根据当前时间，IP信息;
     * 
     * @param busiChannel 业务渠道；P为POS，W为WEB，M为手机移动APP
     * @param dateStr 日期串，格式为yyyyMMddHHmmssSSS
     * @param ip 请求的IP地址
     * @param encrKey 密钥
     * @return
     */
    public static String genToken(String busiChannel, String dateStr, String ip, String encrKey) {
        //业务渠道 + 日期串 + IP地址 + 6位随机数(字母+数字)
        String tokenNum = busiChannel + dateStr + ip + getRandomString(6);
        try {
            return bytes2HexStr(encrypt(tokenNum, encrKey));
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return tokenNum;
    }

    private static byte[] encrypt(String message, String key) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(new DESedeKeySpec(key.getBytes(ENCODING)));
        Cipher cipher = Cipher.getInstance(ALGORITHM_CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message.getBytes(ENCODING));
    }

    private static byte[] decrypt(String message, String key) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(new DESedeKeySpec(key.getBytes(ENCODING)));
        Cipher cipher = Cipher.getInstance(ALGORITHM_CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(TokenGenerator.hexStr2Bytes(message));
    }

    /**
     * 转成十六进制.
     * 
     * @param src
     * @return
     */
    public static String bytes2HexStr(byte[] src) {
        StringBuilder result = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            String str = Integer.toHexString(src[i] & 0xFF);
            if (str.length() == 1) {
                result.append(0);
            }
            result.append(str);
        }
        return result.toString();
    }

    /**
     * 获取字节数组.
     * 
     * @param sourece
     * @return
     */
    public static byte[] hexStr2Bytes(String sourece) {
        if (sourece == null || sourece.equals("")) {
            return null;
        }
        sourece = sourece.toUpperCase();
        int length = sourece.length() / 2;
        char[] hexChars = sourece.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /** 
     * Convert char to byte 
     * @param c char 
     * @return byte 
     */
    private static byte charToByte(char c) {
        return (byte) HEXSTR.indexOf(c);
    }

    /**
     * 生成随机字符串.
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(RANDOMSTR.length());
            sb.append(RANDOMSTR.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //密钥
        String key = "iloveChina0123456789ilove";
        //随机数
        String token = TokenGenerator.genToken("W", "20131227101352789", "192.168.0.1", key);
        System.out.println(token);
        //解密
        try {
            System.out.print(new String(TokenGenerator.decrypt(token, key), ENCODING));
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
