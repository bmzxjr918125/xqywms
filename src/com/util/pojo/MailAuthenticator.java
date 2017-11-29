package com.util.pojo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * <p>ClassName: MailAuthenticator</p>
 * <p>@Description: 邮件验证类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-7下午12:47:32</p>
 */
public class MailAuthenticator  extends Authenticator {
    
	 	private String username = null;  
	    private String password = null;  
	  
	    public MailAuthenticator() {  
	    
	    };  
	  
	    public MailAuthenticator(String username, String password) {  
	        this.username = username;  
	        this.password = password;  
	    }  
	  
	    protected PasswordAuthentication getPasswordAuthentication() {  
	        
	    	return new PasswordAuthentication(username, password);  
	    }  
}
