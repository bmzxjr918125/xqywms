///
/// 枚举 转换
///
/**
 * 会员等级
 * @param data
 * @returns {String}
 */
function grade(data){
	if (data == "ONE") {
		return "普通会员";
	}else if (data == "TWO") {
		return "金卡会员";
	}else if (data == "THREE") {
		return "钻石会员";
	}else {
		return "----";
	}
} 
/**
 * 性别
 * @param data
 * @returns {String}
 */
 function sex(data){
	 if (data == "BOY") {
		 return "<span style='color:#f6e4a5;'>男</span>";
	 }else if (data == "GIRL") {
		 return "<span style='color:#78ce07'>女</span>";
	 }else if(data == "NONE"){
		 return "<span style='color:#fb9337'>其它</span>";
	 }else{
		 return "----";
	 }
 }
 
 /**
  * 状态
  */
 function status(data){
     
     if(data == "AUTHEN_WAIT_CHECK"){
        
         return "<span style='color:#f6e4a5;'>待审核</span>";
     }else if(data == "AUTHEN_PASS"){
         
         return "<span style='color:#78ce07'>已通过</span>";
     }else if(data == "AUTHEN_NOT_PASS"){

         return "<span style='color:#fb9337'>未通过</span>";
     }else if(data == "GRADE_WAIT_CHECK"){
        
         return "<span style='color:#f6e4a5;'>待审核</span>";
     }else if(data == "GRADE_PASS"){
         
         return "<span style='color:#78ce07'>已通过</span>";
     }else if(data == "GRADE_NOT_PASS"){

         return "<span style='color:#fb9337'>未通过</span>";
     }
 }
 
 
 
 /**
  * 会员认证状态
  */
 function authen(data,phoneNumber){
     var html ="<label>认证账号：</label><span>"+phoneNumber+"</span><br/>";
         html+="<label>身份证号：</label><span>"+data.IDCard+"</span><br/>";
         html+="<label>真实姓名：</label><span>"+data.realName+"</span><br/>";
         html+="<label>提交时间：</label><span>"+(data.createDate != null ? data.createDate.replace(/\T/g,' ') : "-.-.-")+"</span><br/>";
         html+="<label>审核时间：</label><span>"+(data.updateDate != null ? data.updateDate.replace(/\T/g,' ') : "-.-.-")+"</span><br/>";
         
     
     if(data.status == "AUTHEN_WAIT_CHECK"){
         html+="<label>认证状态：</label><span style=\"color:#f6e4a5;cursor: pointer;\">认证中</span><br/>";
        return "<span onclick='jAlert(&apos;"+html+"&apos;, \"会员认证信息详细\");' style=\"color:#f6e4a5;cursor: pointer;\">认证中</span>";
     }else if(data.status == "AUTHEN_PASS"){
         html+="<label>认证状态：</label><span style=\"color:#78ce07;cursor: pointer;\">已认证</span><br/>";
         return "<span onclick='jAlert(&apos;"+html+"&apos;, \"会员认证信息详细\");' style=\"color:#78ce07;cursor: pointer;\">已认证</span>";
     }else if(data.status == "AUTHEN_NOT_PASS"){
         html+="<label>认证状态：</label><span style=\"color:red;cursor: pointer;\">认证未通过</span><br/>";
         return "<span onclick='jAlert(&apos;"+html+"&apos;, \"会员认证信息详细\");' style=\"color:red;cursor: pointer;\">认证未通过</span>";
     }
 }
 
 
 
 
 
 