package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.exception.SignException;

import sun.misc.BASE64Encoder;


/**
 * <p>ClassName: Sign</p>
 * <p>@Description: 客户端接口请求sign生成加密算法</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-29下午4:29:28</p>
 */
public class Sign{
	
	
	/**
	 * <p>@Description: 根据请求的url
	 * （不包含sign,timestamp,account三个验证参数）
	 * 获取生成sign的参数1（sign=MD5(参数1+时间戳+加密token)）</p>
	 * <p>@param @param url
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-6-29下午8:00:09</p>
	 */
	public static String Encrypt(String url){
		
		String params="";
		String key="";
		String urlMethod="";
		String[] urls;
		if(url!=null && !"".equals(url.trim())){
			//分离url接口名称与参数值
			urls=url.split("\\?");
			if(urls.length>1){
				//url 排除特殊参数sign,timestamp,account
				String[] paramList=url.split("\\?")[1].split("&");
				url="";
				for(String str:paramList){
					
					if(!str.split("=")[0].equals("sign") && !str.split("=")[0].equals("account") 
							&& !str.split("=")[0].equals("timestamp")){
						
						url=url+str+"&";
					}
				}
				if(url.endsWith("&")){
					url=url.substring(0,url.lastIndexOf("&"));
				}
			}
		
			
			//分离url接口名称与参数值
			urls=url.split("\\?");
			if(urls.length>1){
				
				urlMethod=urls[0];
				params=urls[1];
			}else{
				
				urlMethod=urls[0];
			}
			
			//判断有无参数
			if(params!=null && !"".equals(params.trim())){
				
				String param[]=params.split("&");
				//根据param的长度判断参数重组循序 基数则先基数后偶数
				if(param.length%2==0){//偶数
					
					param=Order(param,2);
				}else{//奇数
					
					param=Order(param,1);
				}
				params="";
				for(int i=0;i<param.length;i++){
					
					params=params+param[i];
				}
				key=params+key;
			}else{
				
				key=urlMethod;
			}
			if(key.length()%2==0){
				
				key=md5_low(key);
			}else{
				
				key=md5_low(md5_low(key));
			}
		   return key;
		}else{
			
			throw new SignException("请求url为空");
		}
   }
	/**
	 * @Title: Order
	 * @Description:  按奇偶排序
	 * @author BianMingZhou
	 * @date 2015-12-10 下午3:30:20
	 * @param @param param
	 * @param @param flag 1奇数前 2偶数前
	 * @param @return 
	 * @return String[] 
	 * @throws
	 */
	private static String[] Order(String[] param,int flag){
	    List<String> list1=new ArrayList<String>();
	    List<String> list2=new ArrayList<String>();
		
		for(int i=0;i<param.length;i++){
			
			if(i%2==0){//偶
				
				list1.add(param[i]);
			}else{
				
				list2.add(param[i]);
			}
		}
		if(flag==1){// 先奇数
			
			 list2.addAll(list1);
			 int size =  list2.size();
			 param = (String[])list2.toArray(new String[size]);
		}else{
			
			list1.addAll(list2);
			int size =  list1.size();
			param = (String[])list1.toArray(new String[size]);
		}
		return param;
	}
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
	/**
	 * 32位大写
	 * @param s
	 * @return
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
	 * 32位小写
	 * @param s
	 * @return
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
	

	public static void main(String[] args) {
		
		String mili=System.currentTimeMillis()+"";
		System.out.println("mili="+mili);
		System.out.println("sign="+md5(Encrypt("/tmms/tmu/apiv1/clientUserApiLogin?x=30.122122&y=103.54566&phoneNum=15802861658&pwd=123456&type=1")+mili+"60DB9DE82709E3796470E40502955724"));
	
	}
	
}