package com.util;

import java.io.UnsupportedEncodingException;

import com.exception.BizException;


/**
 * <p>ClassName: CharacterEncoUtils</p>
 * <p>@Description: 字符串编码转换</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:03:03</p>
 */
public class CharacterEncoUtils {
      public static String Iso8859ToUtf8(String str){
    	 try {
    		 if(str!=null){
    			 str=new String(str.getBytes("iso-8859-1"),"utf-8");
    		 }else{
    			 str="";
    		 }
		} catch (UnsupportedEncodingException e) {
			throw new BizException("编码转换异常");
		}
    	 return str;
      }
}
