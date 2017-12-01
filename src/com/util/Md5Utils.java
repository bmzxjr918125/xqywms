package com.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.exception.BizException;

import sun.misc.BASE64Encoder;
/**
 * <p>ClassName: Md5Utils</p>
 * <p>@Description: MD5加密公共方法类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:04:08</p>
 */
public class Md5Utils {
	public static String md5_(String str){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		String newstr = null;
		try {
			newstr = encoder.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}
	
	public static void main(String[] args) {
		//System.out.println(md5_low("0测试活动000000-00-00 00:00:000.0000.00-11aa54d315a4f2cab2d7a09470f0bf1cf"));
		System.out.println(md5(md5("018312")));
	}
	
	/**
	 * <p>@Description: 32位大写MD5加密</p>
	 * <p>@param @param s 加密字符窜
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午5:04:38</p>
	 */
	public final static String md5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes("UTF-8");
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
	
	/**
	 * <p>@Description: 32位小写MD5加密</p>
	 * <p>@param @param s 加密字符窜
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-13下午5:05:08</p>
	 */
	public final static String md5_low(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       

        try {
            byte[] btInput = s.getBytes("UTF-8");
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
	
	
	public static String md5ForPwd(String str){
    	return Md5Utils.md5(Md5Utils.md5(str));
    }
	public static String md5ForDefaultPwd(String phoneNumber){
	    if(phoneNumber == null || phoneNumber.trim().equals("") ||phoneNumber.length() != 11){
	      throw new BizException("生成默认密码手机号不正确，请检测是否为11位手机号码。");  
	    }
	    return Md5Utils.md5(Md5Utils.md5(phoneNumber.substring(5,11)));
	}
	
	
}
