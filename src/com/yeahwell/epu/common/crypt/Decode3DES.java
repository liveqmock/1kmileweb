package com.yeahwell.epu.common.crypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

/**
 * 
 * 功能描述: 3DES加密
 *
 */
@SuppressWarnings("restriction")
public class Decode3DES {
	
	private static final Decode3DES dse = new Decode3DES();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Decode3DES.class);

	private Decode3DES() {

	}

	public static Decode3DES getInstance() {

		return dse;
	}
	
	private static final String Algorithm = "DESede";
	
	//24字节的密钥
	private final byte[] keyBytes = {21,12,11,7,8,10,40,38,28,25,79,51,54,23,55,66,77,29,4,98,1,40,36,23};
	
	/**
	 * 功能描述: 加密
	 *
	 * @param src 加密的文本
	 * @return  
	 * @author huyiming 10120359@cnsuning.com
	 * @date 2012-3-19 下午09:16:33
	 */
	public byte[] encryptMode(byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
		    LOGGER.error(e1.getMessage(), e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
		    LOGGER.error(e2.getMessage(), e2);
		} catch (java.lang.Exception e3) {
		    LOGGER.error(e3.getMessage(), e3);
		}
		return null;
	}
	
	/**
	 * 功能描述: 解密
	 *
	 * @param src 密文
	 * @return  
	 */
	public byte[] decryptMode(byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
		    LOGGER.error(e1.getMessage(), e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
		    LOGGER.error(e2.getMessage(), e2);
		} catch (java.lang.Exception e3) {
		    LOGGER.error(e3.getMessage(), e3);
		}
		return null;
	}
	
	/**
	 * 功能描述: 字符串的编码
	 *
	 * @param s
	 * @return  
	 */
	public String getBASE64(byte[] s) {
        if (s == null) return null;

        String tempValue=(new sun.misc.BASE64Encoder()).encodeBuffer(s);
        Pattern  pattern= Pattern.compile("(&#13;)|\\s*|\\t|\\r|\\n");
        Matcher  matcher=pattern.matcher(tempValue);
        tempValue= matcher.replaceAll("");

        return  tempValue;
    }

	/**
	 * 功能描述: 字符串解码
	 *
	 * @param s
	 * @return  
	 */
	public byte[] getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return b;
		} catch (Exception e) {
		    LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
}
