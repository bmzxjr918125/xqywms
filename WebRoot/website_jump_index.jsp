<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"><%--网站跳转首页引导页面--%>
    <title>獭猫科技</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="description" content="成都獭猫科技有限公司官方网站" />
    <meta name="keywords" content="成都獭猫科技有限公司,邻邻" />
	<meta http-equiv='refresh'   content='0;url=index.html'>
  </head>
  
  <body>
  </body>
  <%--<script type="text/javascript">
     if (navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i) ) {
    	 location.href="http://m.xxxx.com";
     }
  </script>
  
--%></html>
