<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>交易后台管理系统</title>

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
           <div class="contenttitle2">
                        <h3>密码修改</h3>
           </div><!--contenttitle-->

                    <form class="stdform stdform2" onsubmit="return validate();"  enctype="multipart/form-data" id="adminUpdateForm">
                        <p> 	
                        	<label>管理员帐号<span class="inputrequiredflag">*</span></label>
                            <span class="field">
                            <input type="text" name="username"  id="username" value="${admin.username}"  class="smallinput" />
                            </span>
                        </p>
                        <p> 	
                        	<label>原密码<span class="inputrequiredflag">*</span></label>
                            <span class="field">
                            <input type="text" name="oldPass" id="oldPass" value=""  class="smallinput" />
                            </span>
                        </p>
                        <p> 	
                        	<label>新密码<span class="inputrequiredflag">*</span></label>
                            <span class="field">
                            <input type="text" name="newPass" id="newPass" value=""  class="smallinput" />
                            </span>
                        </p>
                       
                        <p class="stdformbutton">
                        	<button class="submit radius2">确定修改</button>
                        	<input class="reset radius2" type="reset" onclick="location.href='admin/adminMenuShow'" value="取消修改">
                        </p> 
                        <input type="hidden" value="${admin.id}" id="adminId"  name="adminId"/>
                    </form>
           </div>
        </div>
	</div><!-- centercontent -->
    
    
</div><!--bodywrapper-->

</body>
<script type="text/javascript" src="js/validateMethod.js"></script>
<script type="text/javascript">
  
   //表单验证
   jQuery("#adminUpdateForm").validate({
		rules: {
			"admin.username":{    
				required: true    
			}, 
	       "oldPass":{    
		       required: true,
	       }, 
	       "newPass":{    
		       required: true,
		       minlength:8,
		       isPass:true
	       }   
	
		},
		messages: {
			"admin.username":{
				required:"请填写正确的帐号名称",
			},
			
			"oldPass":{
				required:"请填写原始密码",
			},
			"newPass":{
				required:"请填写新密码",
				minlength:"请填写不小于8个字符的密码",
				 isPass:"只能输入8-16个字母、数字、下划线"
			}
		
		/*lastname: "Please enter your last name",
			email: "Please enter a valid email address",
			location: "Please enter your location"*/
		}
	});
	
   function validate(){
	   var username=jQuery("#username").val();
	   var oldPass=jQuery("#oldPass").val();
	   var newPass=jQuery("#newPass").val();
	   var adminId=jQuery("#adminId").val();
	   
	   if(username == null || username.trim() == "" ){
		     jAlertErrorMsg("请填写账号");
			return false;
	   }
	   if(oldPass == null || oldPass.trim() == "" ){
		     jAlertErrorMsg("请填写原始密码");
			return false;
	   }
	   if(newPass == null || newPass.trim() == "" ){
		     jAlertErrorMsg("请填写新密码");
			return false;
	   }
	   var qdata={};
	    qdata.username=username;
	    qdata.oldPass=oldPass;
	    qdata.newPass=newPass;
	    qdata.adminId=adminId;
		var url = "admin/adminUpdateSave";
		jQuery.axse(url,qdata, "密码修改中...",function(data){
   	   if(data.response=="success"){
   		   jAlertSuccessMsg(data.msg);
   	   }else{
   		   jAlertErrorMsg(data.msg);
   	   }
       },
       function(data){
       	alert("请求错误");
   	   });
	   return false;
   }
   
   
</script>

</html>
