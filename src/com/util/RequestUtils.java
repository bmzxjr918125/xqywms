package com.util;
import org.apache.struts2.ServletActionContext;
/**
 * <p>ClassName: RequestUtils</p>
 * <p>@Description: 封装Struts2 request请求</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:08:38</p>
 */
public class RequestUtils {
	public static String getStrParamter(String name){
		String str =ServletActionContext.getRequest().getParameter(name);
		
		if(("loca_x".equals(name)||"loca_y".equals(name))&&(str==null||"".equals(str))){//控制经纬度获取时不能为null或""
			return "0";
		}else{//正常获取参数
			if (str != null) {
				str = str.trim();
			}
			return XssUtil.stripXSS(str);
		}
	}

	public static int getIntParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static boolean getBooleanParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if ("true".equals(str) || "1".equals(str) || "on".equals(str)) {
			return true;
		}
		return false;
	}

	public static float getFloatParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}

		return Float.parseFloat(str);
	}
	public static double getDoubleParamter(String name) {
		String str = ServletActionContext.getRequest().getParameter(name);
		if (str == null || str.trim().equals("")) {
			return 0;
		}
		
		return Double.parseDouble(str);
	}

}
