package com.base.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.base.action.datatables.DataTables;
import com.opensymphony.xwork2.ActionSupport;
import com.util.pojo.Answer;


public class BaseAction extends ActionSupport  implements ServletRequestAware,ServletResponseAware, ServletContextAware{
	private static final long serialVersionUID = -7588102526595752037L;


	public final String ANSWER = "answer";
	public final String AJAXSHOW = "AjaxShow";
	public final String SHOW = "Show";
	public Answer answer;

    public  HttpServletRequest request;

	public HttpServletResponse response;

	public ServletContext  context;

	private DataTables dtJson;

	public DataTables getDtJson() {
		return dtJson;
	}
	public void setDtJson(DataTables dtJson) {
		this.dtJson = dtJson;
	}


	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		  this.response = response;
	}
	public void setServletContext(ServletContext context) {
	   this.context = context;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
}
