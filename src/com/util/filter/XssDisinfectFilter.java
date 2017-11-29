package com.util.filter;
  
import java.io.IOException;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;
  /**
   * 
   * @ClassName: XssDisinfectFilter
   * @Description: xss消毒过滤器
   * @author BianMingZhou
   * @date 2015-11-20 上午9:27:20
   */
public class XssDisinfectFilter implements Filter {  

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);  
    }

	public void destroy() {
	}
}  