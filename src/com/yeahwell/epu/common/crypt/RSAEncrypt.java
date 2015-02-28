package com.yeahwell.epu.common.crypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 功能描述： RSA加密与解密
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RSAEncrypt {
	
	private static Logger logger = LoggerFactory.getLogger(RSAEncrypt.class);
	
	private static final RSAEncrypt rsa = new RSAEncrypt();
	
	private RSAEncrypt(){
		
	}
	
	public static RSAEncrypt getInstance(){
		return rsa;
	}
	
	/**
	  * @Description: 加密
	  * @param encryptText 加密内容(推荐英文和数字)
	  * @return Map key=cipherText密文,key=privateKey私钥 ,key=publicKey公钥
	  */
	public Map encrypt(String encryptText){
		Map map = new HashMap();
		
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			
			RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
			
			byte[] e = encrypt(publicKey, encryptText.getBytes());
			
			map.put("cipherText", e);
			map.put("privateKey", privateKey);
			map.put("publicKey",publicKey);
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("RSAEncrypt.encrypt({}): {}", e.getMessage(), encryptText);
		}
		
		return map;
	}
	
	/**
	 * 功能描述：加密
	 *
	 * @param publicKey
	 * @param obj
	 * @return
	 */
	private byte[] encrypt(RSAPublicKey publicKey,byte[] obj){
		if(null != publicKey){
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				
				return cipher.doFinal(obj);
			} catch (Exception e) {
				logger.error("RSAEncrypt.encrypt(): {}", e.getMessage());
			}
		}
		
		return null;
	}
	
	
	/**
	 * 功能描述：解密
	 *
	 * @param privateKey 私钥
	 * @param obj 密文
	 * @return 明文
	 */
	public String decrypt(RSAPrivateKey privateKey,byte[] obj){
		if(null != privateKey){
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				
				return bytesToString(cipher.doFinal(obj));
			} catch (Exception e) {
				logger.error("RSAEncrypt.decrypt(): {}", e.getMessage());
			}
		}
		
		return null;
	}
	
	/**
	  * @Description: 将字节转换成字符串
	  * @param encrytpByte 字节
	  * @return String 
	  */
	public String bytesToString(byte[] encrytpByte){
	    final int size = 1024;
		StringBuffer result = new StringBuffer(size);
		for(Byte bytes:encrytpByte){
		    result.append((char)bytes.intValue());
		}
		return result.toString();
	}

	
	public static void main(String[] arge){
		Map map = RSAEncrypt.getInstance().encrypt("sssssssssssssssss");
		RSAPrivateKey privateKey = (RSAPrivateKey)map.get("privateKey");
		System.out.println("私钥=" + privateKey);
		System.out.println("公钥=" + (RSAPublicKey)map.get("publicKey"));
		String ss = RSAEncrypt.getInstance().decrypt(privateKey, (byte[])map.get("cipherText"));
		System.out.println("解密后="+ss);
	}
}
