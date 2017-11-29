package com.util.pojo;

import java.util.HashMap;

/**
 * 扩展HashMap
 * 统一响应类 
 * 返回时 response 与code同时存在
 */
@SuppressWarnings("serial")
public class Answer extends HashMap<String,Object> {
    public static final String
            SUCCESS = "success",
            LOGIN_TIMEOUT="login_timeout",
            ERROR = "error",
            EXCEPTION = "exception";
    public static final String
		    SUCCESS_CODE = "200",//请求成功
		    LOGIN_TIMEOUT_CODE = "300",//请求时登录失效
		    ERROR_CODE = "400",//请求失败
		    EXCEPTION_CODE = "500";//请求异常
		    
		    

    public Answer(){
        super();
    }

    public Answer(String response, String code) {
        super();
        put("response",response);
        put("code",code);
    }
    /**
     * <p>Description:不带返回结果 </p>
     * <p>@param response
     * <p>@param code
     * <p>@param msg</p>
     */
    public Answer(String response,String code,String msg) {
    	super();
    	put("response",response);
    	put("code",code);
    	put("msg",msg);
    }
    /**
     * <p>Description:带返回结果 </p>
     * <p>@param response
     * <p>@param code
     * <p>@param msg
     * <p>@param result 返回结果</p>
     */
    public Answer(String response,String code,String msg,Object result) {
    	super();
    	put("response",response);
    	put("code",code);
    	put("msg",msg);
    	put("result",result);
    }
	public Object getResponse() {
		return get("response");
	}
	public void setResponse(String response) {
		put("response",response);
	}
	public Object getCode() {
		return get("code");
	}
	public void setCode(String code) {
		put("code",code);
	}
	public Object getMsg() {
		return get("msg");
	}
	public void setMsg(String msg) {
		put("msg",msg);
	}
}
