<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String remotePath ="http://localhost:8080/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>店员登录</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<body>
<!--登陆页面-->
<div data-role="page" id="pageLogin">
      <span><a href="<%=basePath%>admin/adminLogin">驰享家后台登录>>></a></span>
</div>

</body>
<script src="<%=basePath%>js/jquery.js"></script>

</html>
