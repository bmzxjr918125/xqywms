package com.util.interceptor;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import com.exception.BizException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.util.MailUtil;
import com.util.pojo.Answer;
/**
 * <p>ClassName: ExceptionInterceptor</p>
 * <p>@Description: 全局异常拦截器</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-20下午1:06:31</p>
 */
@SuppressWarnings("serial")
public class ExceptionInterceptor extends AbstractInterceptor  {
	//不能用jsonObject
    private Answer map;
	protected static final Logger log = Logger.getLogger("logException"); 
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		 
		 HttpServletRequest request = (HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		 
		 String url = request.getRequestURI(); 
		 StringBuffer params = new StringBuffer();
		 
		 Map<String, String[]> paramsMap = request.getParameterMap();
		 map=new Answer();
			
			 try{
				 
					String result=invocation.invoke();
					return result;
				}catch (Exception e) {
					
				    //抛出异常 返回map 异常数据
	     			for (String key : paramsMap.keySet()) {
	     				
	     				String[] getParamVals = paramsMap.get(key);
	     				for (int n = 0; n < getParamVals.length; n++) {
	     					params.append(key + "=" + getParamVals[n] + "&");
	     				}
	     			}
	     			
	     	        Date date = new Date();
	     	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	     	        String rightnow=sdf.format(date);
	     	        ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
	     	        e.printStackTrace(new java.io.PrintWriter(buf, true));
	     	        String  expMessage = buf.toString();
	     	        buf.close(); 
	     	        
	     	       
	     		    
					map=new Answer(Answer.EXCEPTION,Answer.ERROR_CODE);
					
					
					if(e instanceof BizException){
						
						map.put("msg",e.getMessage());
						log.error("【"+e.getMessage()+"请求的URL:" + url +"】");
						
						//错误控制台日志输出
		     	        System.out.println("\n\n【"+rightnow+"xxxx执行了"+invocation.getProxy().getActionName()+"中"+invocation.getProxy().getMethod()+"方法发生异常xxxx】");
		     	        System.out.println("【请求的方式:" + request.getMethod() +"】");
		     	        System.out.println("【请求的URL:" + url +"】");
		     	        System.out.println("【请求的参数:" + params.toString()+"】");
		     	        System.out.println("【抛出的异常名称:"+e+"】"); 
		     	        System.out.println("【抛出的异常详细:"+expMessage+"】");
		     		    System.out.println("【xxxxxxxxxxxxxxxxxx方法发生异常结束xxxxxxxxxxxxxxxxxx】\n\n");
					
						
					}else{
						
						map.put("msg","系统异常,请稍后再试");
						//错误日志记录
		     	        log.error("【"+rightnow+"xxxx执行了"+invocation.getProxy().getActionName()+"中"+invocation.getProxy().getMethod()+"方法发生异常xxxx】");
		     	        log.error("【请求的方式:" + request.getMethod() +"】");
		     	        log.error("【请求的URL:" + url +"】");
		     	        log.error("【请求的参数:" + params.toString()+"】");
		     	        log.error("【抛出的异常名称:"+e+"】");
		     	        log.error("【抛出的异常详细:"+expMessage+"】");
		     	        log.error("【xxxxxxxxxxxxxxxxxx方法发生异常结束xxxxxxxxxxxxxxxxxx】\n\n");
		     	        //错误控制台日志输出
		     	        System.out.println("\n\n【"+rightnow+"xxxx执行了"+invocation.getProxy().getActionName()+"中"+invocation.getProxy().getMethod()+"方法发生异常xxxx】");
		     	        System.out.println("【请求的方式:" + request.getMethod() +"】");
		     	        System.out.println("【请求的URL:" + url +"】");
		     	        System.out.println("【请求的参数:" + params.toString()+"】");
		     	        System.out.println("【抛出的异常名称:"+e+"】"); 
		     	        System.out.println("【抛出的异常详细:"+expMessage+"】");
		     		    System.out.println("【xxxxxxxxxxxxxxxxxx方法发生异常结束xxxxxxxxxxxxxxxxxx】\n\n");
					
		     		    //邮件发送系统异常信息
		     		    final String mailContent="【"+rightnow+"xxxx执行了<strong>"+invocation.getProxy().getActionName()+"</strong>中<strong>"+invocation.getProxy().getMethod()+"</strong>方法发生异常xxxx】"
		     		    		+"</br>【请求的方式:<strong>" + request.getMethod() +"</strong>】"
		     		    		+"</br>【请求的URL:<strong>" + url +"</strong>】"
		     		    		+"</br>【请求的参数:<strong>" + params.toString()+"</strong>】"
		     		    		+"</br>【抛出的异常名称:<strong>"+e+"</strong>】"
		     		    		+"</br>【抛出的异常详细:<p>"+expMessage.replaceAll("\\)",")</br>")+"</p>】"
		     		    		+"</br>【xxxxxxxxxxxxxxxxxx方法发生异常结束xxxxxxxxxxxxxxxxxx】";
		     		 
		     		    final String serverName= request.getServerName();
		     		    Thread thread=new Thread(new Runnable() {
							public void run() {
								 MailUtil.sendSysExceptionMail(mailContent,serverName +"系统异常信息");
							}
						});
		     		    thread.start();
					}
				} 
		 
		ActionContext actionContext = invocation.getInvocationContext();
	    actionContext.put("result",map);
	    
		if( isReturnJSon(request) ){
			
			return "EXCEPTION_JSON";
		}else{
			
			return "EXCEPTION_HTML";
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
