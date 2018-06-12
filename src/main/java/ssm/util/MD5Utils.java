package ssm.util;

import java.security.MessageDigest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @ClassName: MD5Utils
 * @Description: md5加密
 * @author 王建洪
 * @date 2018年4月12日
 *
 */
public class MD5Utils {
	
	/**
	 * 日志操作
	 */
	private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

	/**
	 * salt
	 */
	private static final String MD5_SALT = "";
	
	/**
	 * md5 加盐加密
	 * @param str 要加密的字符串
	 * @return md5加密后的字符串
	 */
	public static String MD5(String str) {
		
		return encrypt(encrypt(str + MD5_SALT) + MD5_SALT);
	}
	
    /** 
     * 对字符串md5加密(大写+数字) 
     * 
     * @param s 传入要加密的字符串 
     * @return  MD5加密后的字符串 
     */
    private static String encrypt(String s) {  
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
  
        try { 
            byte[] btInput = s.getBytes();  
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(btInput);  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }
            return new String(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
}
