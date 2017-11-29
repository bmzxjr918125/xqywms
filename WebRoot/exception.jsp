<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>异常错误页面</title>

<link type="text/css" rel="stylesheet" href="../css/exception.css" />

<!--[if IE 6]>
<script src="../js/exception.js"></script>
<script>DD_belatedPNG.fix('*')</script>
<![endif]-->

</head>
<body>

<div id="wrap">
	<div>
		<img src="../images/404/404.png" alt="404" />
	</div>
	<div id="text">
		<strong>
			<span class="tip_img"></span>
			<a href="javascript:void(0)" onclick="history.back()">首页</a>
			<a href="javascript:void(0)" onclick="history.back()">返回上一页</a>
		</strong>
	</div>
	<div class="msg" style="text-align: center;padding: 15px;">
	    <span style="color: #fff;font-size: 12px;">-->::${result.msg}::<--</span>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>

</body>
</html>
