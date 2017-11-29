package com.util.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.util.pojo.Answer;
/**
 * <p>ClassName: MerchantInterceptor</p>
 * <p>@Description: 店员管理登录拦截</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-26上午10:51:32</p>
 */
public class ClientInterceptor extends MethodFilterInterceptor{
	private static final long serialVersionUID = 7856884269836969375L;
	private Answer map;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		// 验证是否登录
		if (invocation.getInvocationContext().getSession()
				.get("userInfo") != null) {
			
			return invocation.invoke();
		}
      if(isReturnJSon(request)){
    	     map=new Answer(Answer.LOGIN_TIMEOUT,Answer.LOGIN_TIMEOUT_CODE,"当前登录已失效，请重新登录");
    	     ActionContext actionContext = invocation.getInvocationContext();
    		 actionContext.put("result",map);
    	     return "EXCEPTION_JSON";
		}else{
			
			return "userLogin";
		}
	}
	
	
	/**
	  * <p>@Description:判断是否是请求返回json</p>
	  * <p>@param @param request
	  * <p>@param @return</p>   
	  * <p>@return boolean</p> 
	  * <p>@throws</p>
	  * <p>@author BianMingZhou</p>
	  * <p>@date 2016-6-20下午12:45:36</p>
	  */
	private boolean isReturnJSon(HttpServletRequest request) { 
	    String header = request.getHeader("X-Requested-With"); 
	    if ((header != null && "XMLHttpRequest".equals(header))||request.getRequestURI().contains("api")){
	        
	    	return true; 
	    }else{ 
	        
	    	return false; 
	    }
	}
		
	

}
