package com.util.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class UeStrutsFilter extends StrutsPrepareAndExecuteFilter{
  
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("utf-8");
        //HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();        
       // System.out.println(url);        
        if (url.contains("ueditor/jsp/")) {            
           // System.out.println("使用自定义过滤器");            

            chain.doFilter(req, res);        

        }else{
        	 // System.out.println("使用默认过滤器");     
        	
        	/*Properties prop=new Properties();
			InputStream in = getClass().getResourceAsStream("/stygconfig.properties");
			prop.load(in);
			in.close();
			
			String stygdata=prop.getProperty("stygdata");
			Long date=Long.parseLong(stygdata);
        	
			Date date1=new Date(date);
			Date nowdate=new Date();*/
			
			//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//如果当前时间小于限制时间过期
        	/*if(nowdate.after(date1)){
        		response.sendRedirect("/stygguoqi/error.html");
        	}else{*/
    			
           	 super.doFilter(req, res, chain);        
        		
        	/*}*/
			
        }

    }

}
