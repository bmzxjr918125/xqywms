<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
	<title>獭猫后台管理系统</title>
	<style type="text/css">
		table tr td{
			border: 1px solid #ccc;
			padding: 10px;
		}
		table tr td span{
			margin-left: 10px;
		}

	</style>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<!--topheader引入-->
		<jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
		<!--header引入-->
		<jsp:include page="/WEB-INF/admin/Include/header.jsp" />
		<!--vaernav2iconmenu引入-->
		<jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />


		<!--右部类容-->
		<div class="centercontent">
			<div id="contentwrapper" class="contentwrapper">
				<div id="validation" class="subcontent">
					<ul class="breadcrumbs">
						<li><a href="javascript:history.go(-1);">返回</a></li>
						<li>编辑管理员</li>
					</ul>

					<br />

					<div class="contenttitle2">
						<h3>编辑管理员</h3>
					</div>
					<!--contenttitle-->

					<br /><br />

					<form action="admin/adminEditSave" method="post" class="stdform stdform2" id="adminEditSave_Form">
						<input type="hidden" name="admin.id" value="${admin.id}" />
						<p style="border:1px solid #ddd">
							<label>用户名</label>
							<span class="field">
								<input type="text"
									   name="admin.username"
									   id="admin_username"
									   maxlength="24"
									   style="width:200px;"
									   value="${admin.username}"
										readonly="readonly"
										/>
							</span>
						</p>
						
						<p>
							<label>角色名称</label>
							<span class="field">
								<input type="text"
									   name="admin.role_name"
									   id="admin_role_name"
									   maxlength="64"
									   style="width:300px;"
									   value="${admin.role_name}"/>
							</span>
						</p>
						
                        <p class="stdformbutton">
                        	<button type="submit" class="submit radius2" onclick="submit()">编辑提交</button>
                        </p>
					</form>
				</div>
			</div>
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->
</body>

</html>
