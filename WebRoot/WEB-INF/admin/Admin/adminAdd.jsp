<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
	<title>驰享家后台管理系统</title>
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
						<li>新增管理员</li>
					</ul>

					<br />
					<div class="contenttitle2">
						<h3>新增管理员</h3>
					</div>
					<!--contenttitle-->

					<br /><br />

					<form action="admin/adminAddSave" method="post" class="stdform stdform2" id="adminAddSave_Form">

						<p style="border:1px solid #ddd">
							<label>用户名</label>
							<span class="field">
								<input type="text"
									   name="admin.username"
									   id="admin_username"
									   maxlength="24"
									   style="width:200px;"
									   value=""/>
							</span>
						</p>
						<p>
							<label>密码</label>
							<span class="field">
								<input type="password"
									   name="admin.password"
									   id="admin_password"
									   maxlength="24"
									   style="width:200px;height:30px;"
									   value=""/>
							</span>
						</p>
						<p>
							<label>确认密码</label>
							<span class="field">
								<input type="password"
									   name="eachPass"
									   id="eachPass"
									   maxlength="24"
									   style="width:200px;height:30px;"
									   value=""/>
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
									   value=""/>
							</span>
						</p>
					
                        <p class="stdformbutton">
                        	<button type="submit" class="submit radius2" onclick="submit()">新增保存</button>
                        </p>
					</form>
				</div>
			</div>
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->
</body>
<script type="text/javascript">

	//选中的权限
	function selectPowers(ele){
		var hi_power = jQuery("#admin_powers").val();
		var val = jQuery(ele).val();
		if(jQuery(ele).is(":checked")){
			jQuery("#admin_powers").val(hi_power + val + ",");
		}else{
			jQuery("#admin_powers").val(hi_power.replace(","+val+",",","));
		}
	}

	//验证数据
	jQuery("#adminAddSave_Form").validate({
		rules:{
			'admin.username':{
				required: true,
				remote:'tmg/adminCheckUnique',
				minlength: 6
			},
			'admin.password':{
				required: true,
				minlength: 6
			},
			'eachPass':{
				required: true,
				minlength: 6,
				equalTo:'#admin_password'
			}
		},
		messages:{
			'admin.username':{
				required:'用户名不能为空!',
				minlength:'用户名最小长度为6个!',
				remote:'用户名重复，请重新输入!'
			},
			'admin.password':{
				required:'密码不能为空!',
				minlength:'密码最小长度为6个!'
			},
			'eachPass':{
				required:'重复密码不能为空!',
				minlength:'重复密码最小长度为6个!',
				equalTo:'两次密码不相同!'
			}
		}
	});

</script>
</html>
