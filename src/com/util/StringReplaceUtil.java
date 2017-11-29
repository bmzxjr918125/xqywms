package com.util;


import org.apache.commons.lang.StringUtils;

public class StringReplaceUtil {
	/**
	 * 将证件号保留前三 后四 其余替换为****
	 * @param str
	 * @return
	 */
    public static String replaceToStar(String str) {
    	if(str==null){
    		return null;
    	}else{
            int len=str.length();

            if(len>7){
            	String ss=str.substring(3, len-4);
            	String star="";
            	for(int i=0;i<ss.length();i++){
            		star=star+"*";
            	}
            	str=str.substring(0,3)+star+str.substring(len-4,len);
            }
            return str;
    	}
    }

	/**
	 * <p>Description : 剔除空对象</p>
	 * <p>Author : XQL</p>
	 * <p>Date : 2016/3/25 14:47</p>
	 */
	public static String replaceNull(String str){
		return StringUtils.isEmpty(str) ? "" : str;
	}

	/**
	 * <p>Description : 将时间[xx:xx]转为秒数</p>
	 * <p>Author : XQL</p>
	 * <p>Date : 2016/5/19 9:38</p>
	 *
	 * @param time xx:xx 时间
	 * @return
     */
	public static int timeFormatInt(String time){
		if(StringUtils.isEmpty(time)) return 0;

		String times[] = time.split(":");
		int ts = 0;
		if(times.length > 1){
			if(!StringUtils.isEmpty(times[0])){
				ts += Integer.parseInt(times[0]) * 3600;
			}
			if(!StringUtils.isEmpty(times[1])){
				ts += Integer.parseInt(times[1]) * 60;
			}
		}
		return ts;
	}


	public static void main(String arg[]){
    	System.out.println(replaceToStar("510182199108070635"));
		//System.out.println(timeFormatInt("02:36:00"));
	}

}
