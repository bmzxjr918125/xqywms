package com.util.aop;  
import java.lang.reflect.Method;  


import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;  
/** 
 * @author BianMingZhou 处理Action层的异常  
 *         ExceptionLog.java 
 */  
public class ExceptionLog implements ThrowsAdvice {  
	protected static final Logger log = Logger.getLogger(ExceptionLog.class);
    /** 
     * 参数解释 Method method 执行的方法  
     * Object[] args 方法参数 
     *  Object target 代理的目标对象 
     * Throwable throwable 产生的异常  
     */  
    public void afterThrowing(Method method, Object[] args, Object target,  
            RuntimeException  throwable) {  
    	    log.error("=========产生异常的方法名称：  " + method.getName());  
          
        for(Object o:args){  
           log.error("方法的参数：   " + o.toString());  
        }  
          
           log.error("代理对象：   " + target.getClass().getName());  
           log.error("抛出的异常:    " + throwable.getMessage()+">>>>>>>"  
                + throwable.getCause());  
           log.error("异常详细信息：　　　"+throwable.fillInStackTrace()+"==========");  
    }  
}