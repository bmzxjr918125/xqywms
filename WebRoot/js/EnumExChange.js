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
     
     if(data == "REPAIR_WAIT_ASK"){
        
         return "<span title='用户或系统提交维修申请'>待派单</span>";
     }else if(data == "REPAIR_SEND"){
         
         return "<span title='系统派单，待人员响应'>待响应</span>";
     }else if(data == "REPAIR_ACCEPT"){

         return "<span title='工作人员接受工单，正在维修中'>维修中</span>";
     }else if(data == "REPAIR_WAIT_CHECK"){
        
         return "<span title='工作人员完成订单提交后台等待审核'>待审核</span>";
     }else if(data == "REPAIR_FINISH"){
         
         return "<span title='后台审核通过订单已完成'>已通过</span>";
     }else if(data == "REPAIR_SEND_FAIL"){
         
         return "<span title='工作人员拒绝接受派单，等待重新派单'>重新派单</span>";
     }else if(data == "REPAIR_CHECK_FAIL"){

         return "<span title='订单完成审核不通过，返回维修状态'>重维修中</span>";
     }else if(data == "REPAIR_FAIL"){

         return "<span title='系统直接取消工单，等待重新派单状态'>重新派单</span>";
     }else if(data == "WORKER_ABLE"){

         return "<span style='color:#78ce07;'>正常</span>";
     }else if(data == "WORKER_DISABLE"){

         return "<span style='color:red;'>冻结</span>";
     }else if(data == "USER_ABLE"){

         return "<span style='color:#78ce07;'>正常</span>";
     }else if(data == "USER_DISABLE"){

         return "<span style='color:red;'>冻结</span>";
     }else if(data == "DEVICE_NORMAL"){

         return "<span style='color:#78ce07;'>正常</span>";
     }else if(data == "DEVICE_REPAIR_ASK"){

         return "<span style='color:#fb9337;'>报修中</span>";
     }else if(data == "DEVICE_REPAIRING"){

         return "<span style='color:#f6e4a5;'>维修中</span>";
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
 
 
 
 
 
 