 /**
   * 文件格式验证
   */
jQuery.validator.addMethod("isImage", function(value, element,param) {  
    	    var fileType = value.substring(value.lastIndexOf(".") + 1).toLowerCase(); 
    	    return this.optional(element) || jQuery.inArray(fileType, param) != -1;
    	},jQuery.validator.format("验证文件格式"));
  

/**
 * 药品添加或修改时判断类型是否选择
 */
jQuery.validator.addMethod("isSelect", function(value,element,param) {  
	
	return this.optional(element) || value != 0;
},jQuery.validator.format("验证是否选择"));

/**
 * 验证管理员密码合法性
 */
jQuery.validator.addMethod("isPass", function(value,element,param) {  

	//校验密码：只能输入8-16个字母、数字、下划线   
	var patrn=/^(\w){8,16}$/;  
	if (!patrn.exec(value)) {
		return false;
	}else{
		return true 
	}
	
},jQuery.validator.format("验证管理员密码合法性"));

/**
 * 验证电话合法性
 */
jQuery.validator.addMethod("isPhoneNumber", function(value,element,param) {  

	var patrn = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;  
	if (!patrn.exec(value)) {
		return false;
	}else{
		return true 
	}
	
},jQuery.validator.format("验证电话号码合法性"));

/**
 * 验证电话合法性
 */
jQuery.validator.addMethod("isAccount", function(value,element,param) {  
	
	var patrn = /^([a-zA-Z]*?)((?:[0-9]+(?:[0-9]|[a-zA-Z])*)*)$/;  
	if (!patrn.exec(value)) {
		return false;
	}else{
		return true 
	}
	
},jQuery.validator.format("验证账号合法性"));

