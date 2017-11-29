package com.util.pojo;

import java.io.Serializable;
import java.util.Properties;

/**
 * <p>ClassName: MailInfo</p>
 * <p>@Description: 邮件属性实体</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-7下午12:49:03</p>
 */
public class MailInfo implements Serializable {
	private static final long serialVersionUID = 5735351669370947621L;
	private String mailServerHost;// 服务器ip
	private String mailServerPort;// 端口
	private String fromAddress;// 发送者的邮件地址
	private String toAddress;// 邮件接收者地址
	private String username;// 登录邮件发送服务器的用户名
	private String password;// 登录邮件发送服务器的密码
	private boolean validateAuth = true;// 是否需要身份验证
	// 是否启用ssl 
    private boolean validateSSL = true; 
	private String subject;// 邮件主题
	private String content;// 邮件内容
	private String[] attachFileNames;// 附件名称

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validateAuth ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public boolean isValidateAuth() {
		return validateAuth;
	}

	public void setValidateAuth(boolean validateAuth) {
		this.validateAuth = validateAuth;
	}

	public boolean isValidateSSL() {
		return validateSSL;
	}

	public void setValidateSSL(boolean validateSSL) {
		this.validateSSL = validateSSL;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}
