package com.util.filter;





import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;

import com.util.XssUtil;


  
/**
 * <p>ClassName: XssHttpServletRequestWrapper</p>
 * <p>@Description: spring 包 进行html 标签 转义</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-15下午1:45:21</p>
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		    super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = XssUtil.stripXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		//System.out.println(XssUtil.stripXSS(value));
		return XssUtil.stripXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return XssUtil.stripXSS(value);
	}

	
	/*public static void main(String[] arg){
		System.out.println(stripXSS("<script>asd</script><p>asd</p>"));
	}*/
	
	}  
