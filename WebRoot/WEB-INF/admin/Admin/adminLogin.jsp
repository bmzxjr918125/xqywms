<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>雪球能源运维后台登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="管理员登录">

	<script type="text/javascript" src="<%=basePath%>js/custom/index.js"></script>
  </head>

	<body class="loginpage">
	    <div class="loginbox">
	    	<div class="loginboxinner">
	            <div class="logo">
	            	<h1 class="logo">XQNY.<span>Admin</span></h1>
					<span class="slogan">雪球能源运维后台管理系统</span>
	            </div><!--logo-->

	            	<div class="nousername" style="display: none;">
						<div class="loginmsg"  id="loginmsg"></div>
		            </div><!--nousername-->
	            

	                <div class="username">
	                	<div class="usernameinner">
	                    	<input type="text"  id="username" />
	                    </div>
	                </div>

	                <div class="password">
	                	<div class="passwordinner">
	                    	<input type="password"  id="password" />
	                    </div>
	                </div>
	                <button onclick="login();" style="cursor: pointer;">登录</button>
	                
	               <!--  <div class="keep"><input type="checkbox" /> 记住密码</div> -->
	        </div>
	    </div>
	</body>
</html>
<script type="text/javascript">


	function login(){
		var username=jQuery("#username").val();
		var password=jQuery("#password").val();
		if(username == '' && password == '') {
			 jAlertErrorMsg("请填写用户名");
			return false;
		}
		if(username !='' && password =='') {
			 jAlertErrorMsg("请填写登录密码");
			return false;;
		}
		//清除菜单cookie
	    jQuery.cookie("current_top_li","", { expires: -1 });
	    jQuery.cookie("current_left_li","", { expires: -1 });

	    password=jQuery.md5(jQuery.md5(password).toUpperCase()).toUpperCase();
		
	    var qdata={};
	    qdata.username=username;
	    qdata.password=password;
		var url = "admin/adminLoginin";
		jQuery.axse(url,qdata, "登录验证中...",function(data){
    	   if(data.response=="success"){
    		   jAlertSuccessMsg(data.msg);
    		   location.href="admin/adminMenuShow";
    	   }else{
    		   jAlertErrorMsg(data.msg);
    	   }
        },
        function(data){
        	alert("请求错误");
    	});
	}
</script>