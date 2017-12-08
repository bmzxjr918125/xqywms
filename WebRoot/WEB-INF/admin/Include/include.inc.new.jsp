<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//外网测试
//String imagePath="http://114.55.53.48:8080/";
//外网生产
String imagePath="http://119.29.143.200:80/";
//本地
//String imagePath="http://192.168.0.103:8080/";
%>

<head>
    <base href="<%=basePath%>">

    <title>雪球能源运维后台管理系统</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<%=basePath%>css/style.default.css" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>fonts/roboto.css" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.uniform.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.flot.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.flot.resize.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.slimscroll.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/custom/general.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.dataTables.1.10.8.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.alerts.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/custom/tables.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/custom/forms.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.jgrowl.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/method.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/menu.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/jquery.colorbox-min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/colorpicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/custom/elements.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.md5.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/EnumExChange.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/plugins/chosen.jquery.min.js"></script>
    <!-- <script type="text/javascript" src="js/custom/dashboard.js"></script> -->
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/plugins/excanvas.min.js"></script><![endif]-->
	<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
	<![endif]-->
	<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
	<![endif]-->
	<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
	<![endif]-->
  </head>
