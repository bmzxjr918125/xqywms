/**
 * =======常用 时间 价格 转换方法=========
 * =======封装 ajax 请求=========
 *        BianMingZhou
 * 
 */  

/**              
    * 时间戳转换日期              
    * @param <int> unixTime    待时间戳(秒)              
    * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
    * @param <int>  timeZone   时区              
    */
    function UnixToDate(unixTime, isFull, timeZone) {
    	if(unixTime==null){
    		return "";
    	}else{
    	
      // if (typeof (timeZone) == 'number')
      // {
           unixTime = parseInt(unixTime) + 28800000;//东8区时间偏移量为28800000毫秒
      // }
       var time = new Date(unixTime);
       var ymdhis = "";
       ymdhis += time.getUTCFullYear() + "-";
       ymdhis += (time.getUTCMonth()+1) + "-";
       ymdhis += time.getUTCDate();
       if (isFull == true)
       {
           ymdhis += " " + time.getUTCHours() + ":";
           ymdhis += time.getUTCMinutes() + ":";
           ymdhis += time.getUTCSeconds();
       }
       return ymdhis;
   	
    	}
   }
    
    function UnixToDate2(unixTime, isFull, timeZone) {
		if(unixTime==null){
    		return "";
    	}else{
    	
		unixTime = parseInt(unixTime) + 28800000;// 东8区时间偏移量为28800000毫秒
		var time = new Date(unixTime);
		var ymdhis = "";
		ymdhis += time.getUTCFullYear() + "-";
		ymdhis += (time.getUTCMonth()+1) + "-";
		ymdhis += time.getUTCDate();
       
		return ymdhis;
   	
    	}
   }
    
    /**
     * 获取指定时间与当前时间的差,转换为xx天xx小时xx分
     * @param startTime
     * @returns {String}
     */
    function surplusTime(endTime) {
	if (endTime == null) {
		
		return "";
	} else {
		
		var startTime = new Date();
		endTime = parseInt(endTime);
		var time = endTime - startTime.getTime();
		if (time <= 0) {
			return "<span style='color:red;'>已超时</span>";
		}else {
			var day = Math.floor(time / (24 * 60 * 60 * 1000));
			var hour = Math.floor(time / (60 * 60 * 1000) - day * 24);
			var min = Math.floor(time / (60 * 1000) - day * 24 * 60 - hour * 60);
			
			if (day <= 0 && hour > 0) {
				return hour + "小时" + min + "分钟";
			}else if (day <= 0 && hour <= 0) {
				return min + "分钟";
			}else {
				return day + "天" + hour + "小时" + min + "分钟";
			}
		}
	}
}
    
    /**
     * 获取指定时间与当前时间的差,转换为xx天xx小时xx分
     * @param startTime
     * @param howDay
     * @returns {String}
     */
    function surplusTimeByHowDay(startTime,howDay) {
    	
    	var endTime= startTime + 24*howDay*60*60*1000;  //往后推howDay天
	if (endTime == null) {
		
		return "";
	} else {
		
		var startTime = new Date();
		endTime = parseInt(endTime);
		var time = endTime - startTime.getTime();
		if (time <= 0) {
			return "<span style='color:red;'>已到期</span>";
		}else {
			var day = Math.floor(time / (24 * 60 * 60 * 1000));
			var hour = Math.floor(time / (60 * 60 * 1000) - day * 24);
			var min = Math.floor(time / (60 * 1000) - day * 24 * 60 - hour * 60);
			
			if (day <= 0 && hour > 0) {
				return "<span style='color:red;'>"+hour + "小时" + min + "分钟"+"</span>";
			}else if (day <= 0 && hour <= 0) {
				return "<span style='color:red;'>"+min + "分钟"+"</span>";
			}else {
				if(day < 2){
					return "<span style='color:red;'>"+day + "天" + hour + "小时" + min + "分钟"+"</span>";
				}else{
					return day + "天" + hour + "小时" + min + "分钟";
				}
			}
		}
	}
}
	
	/**
	 * 数据加载或页面跳转等待的遮挡层 打开（gif图片）
	 */
	function loadWait(msg) {
		if(msg==null){
			msg="加载中. . .";
		}
		//alert("调用loadWait");
		/*jQuery(".loadWait").remove();
		var divcss = "style='width:100%;height:100%;position:fixed;background-color:#000;opacity:0.3;top: 0;left: 0;text-align:center;z-index:900;'";
		var div = "<div id='loadWaitId' class='loadWait' align='center' " + divcss
				+ "><img  src='apppage/images/loading.gif'  alt='' /></div>";
		var loadWait = jQuery(".loadWait");
			jQuery("body").append(div);
		jQuery(".loadWait img").css("margin-top", jQuery(".loadWait").height() / 3);
		jQuery(".loadWait").css("display", "block");*/
		jQuery(".loadWait").remove();
		var divcss = "style='width:100%;height:100%;position:fixed;background-color:#000;opacity:0.3;top: 0;left: 0;text-align:center;z-index:99999;'";
		var div = "<div id='loadWaitId' class='loadWait' align='center' " + divcss+">" +
					 "<div class='load-container load8'>"+
					    "<div class='loader'></div>"+
					    "<div>"+msg+"</div>"+
				     "</div>" +
			      "</div>";
		//var loadWait = jQuery(".loadWait");
		jQuery("body").append(div);
		jQuery(".load-container").css("margin-top", jQuery(".loadWait").height() /2);
		jQuery(".loadWait").css("display", "block");
		//alert(1);
	}
	/**
	 * 数据加载或页面跳转等待的遮挡层  打开(文字提示)
	 */
	function loadWait_Span(msg) {
		//alert("调用loadWait");
		jQuery(".loadWait").remove();
		var divcss = "style='width:100%;height:100%;position:fixed;background-color:#000;opacity:0.3;top:0;left: 0;z-index:99999;'";
		var div = "<div id='loadWaitId' class='loadWait' align='center' " + divcss
		+ "><div style='position:fixed;top:45%;width: 100%;text-align:center;'><span height=''>"+msg+"</span></div></div>";
		var loadWait = jQuery(".loadWait");
		jQuery("body").append(div);
		jQuery(".loadWait").css("display", "block");
		jQuery(".loadWait span").css("color","white");

		//alert(1);
	}
	/**
	 * 数据加载或页面跳转等待的遮挡层  关闭
	 */
	function removeLoadWait() {
		setTimeout(function(){
			jQuery(".loadWait").remove();
		},200);

	}
   
	/*****************************************************************
	    jQuery Ajax封装通用类      
	*****************************************************************/
	jQuery(function() {
	/**
	 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),
	 * "state": 1} async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
	 * 注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。 type 请求方式("POST" 或 "GET")， 默认为 "GET"
	 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text successfn 成功回调函数 errorfn
	 * 失败回调函数
	 */
	jQuery.ax = function(url, data, async, type, dataType,loadMsg,successfn, errorfn) {
		async = (async == null) ? "true"
				: async;
		type = (type == null ) ? "post"
				: type;
		dataType = (dataType == null) ? "json"
				: dataType;
		data = (data == null ) ? {
			"date" : new Date().getTime()
		} : data;
		loadWait(loadMsg);
		jQuery.ajax({
			type : type,
			async : async,
			data : data,
			url : url,
			dataType : dataType,
			success : function(d) {
				removeLoadWait();
				successfn(d);
			},
			error : function(e) {
				removeLoadWait();
				errorfn(e);
			}
		});
	};

	/**
	 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),
	 * "state": 1} successfn 成功回调函数
	 */
	jQuery.axs = function(url, data,loadMsg, successfn) {
		data = (data == null || data == "" || typeof (data) == "undefined") ? {
			"date" : new Date().getTime()
		} : data;
		loadWait(loadMsg);
		jQuery.ajax({
			type : "post",
			data : data,
			url : url,
			dataType : "json",
			success : function(d) {
				removeLoadWait();
				successfn(d);
			}
		});
	};

	/**
	 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),
	 * "state": 1} dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text successfn
	 * 成功回调函数 errorfn 失败回调函数
	 */
	jQuery.axse = function(url, data,loadMsg,successfn, errorfn) {
		data = (data == null || data == "" || typeof (data) == "undefined") ? {
			"date" : new Date().getTime()
		} : data;
		if( loadMsg != null){
			loadWait(loadMsg);
		}
		
		jQuery.ajax({
			type : "post",
			data : data,
			url : url,
			dataType : "json",
			success : function(d) {
				removeLoadWait();
				successfn(d);
			},
			error : function(e) {
				removeLoadWait();
				errorfn(e);
			}
		});
	};
	});
	/***************************************
	 * 封装ajax调用实例
	 **************************************/
/*	jQuery(function(){
		jQuery.ax(
	                "http://127.0.0.1:8080/tmms/tmu/apiv1/clientUserApiResetMemberPwd",
	                null,
	                null,
	                null,
	                null, 
	                function(data){
	                    alert(data.msg);
	                }, 
	                function(data){
	                	alert(data.msg);
	                }
	            );
	            
	            jQuery.axs("http://127.0.0.1:8080/tmms/tmu/apiv1/clientUserApiResetMemberPwd", null,"asdas", function(a){
	            	alert(a.msg);
	            });
	        
	            jQuery.axse("http://127.0.0.1:8080/tmms/tmu/apiv1/clientUserApiResetMemberPwd",
	                null, 
	                "asda",
	                function(data){
	            	alert(data.msg);
	                },
	                function(data){
	                	alert(data.msg);
	            });
	        });*/
	
	/** 
	 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
	 * 
	 * @param num 数值(Number或者String) 
	 * @return 金额格式的字符串,如'1,234,567.45' 
	 * @type String 
	 */  
	function formatCurrency(num) {  
	    num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))  
	        num = "0.00";  
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num*100+0.50000000001);  
	    cents = num%100;  
	    num = Math.floor(num/100).toString();  
	    if(cents<10)  
	    cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	    num = num.substring(0,num.length-(4*i+3))+','+  
	    num.substring(num.length-(4*i+3));  
	   
	    return (((sign)?'':'-') + num + '.' + cents);  
	}
	
	/**
	 * 保留4位小数)后格式化成金额形式 
	 * @param num
	 * @returns {String}
	 */
	function formatAmount(num) {  
	    num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))  
	        num = "0.00";  
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num*10000+0.50000000001);  
	    cents = num%10000;  
	    num = Math.floor(num/10000).toString();  
	    
	    if(cents<1000)  
	        cents = "0" + cents;  
	    if(cents<100)  
	    	cents = "0" + cents;  
	    if(cents<10)  
	    	cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	    num = num.substring(0,num.length-(4*i+3))+','+  
	    num.substring(num.length-(4*i+3));  
	   
	    return (((sign)?'':'-') + num + '.' + cents);  
	}
	
	/**
     *验证手机号码
	 */
	function checkPhone(phone){
		var pattern = /^1[34578]\d{9}$/;  
	    if (pattern.test(phone)) {  
	        return true;  
	    }  
	    return false;  
	}
	/**
	 *验证密码
	 *为6-12位，只能是字母、数字和下划线
	 */
	function checkPwd(pwd){
		var pattern = /^[\w]{6,12}$/;  
		if (pattern.test(pwd)) {  
			return true;  
		}  
		return false;  
	}
	/**
	 * 转换为大图片地址
	 * @param path
	 * @returns
	 */
	function exBigImage(path){
		
		return path.replace("_s","");
	}
	
	
	/**
	 * 用户详情
	 */
	function memberInfo(memberId){
		
		jQuery.axse("tmg/tradeGetMemberInfo?memberId="+memberId,null,"数据获取中...",function(data) {
			
			var html1 = "<div class='centercontent'  style='margin-left:0px;'>"+
						"<div id='contentwrapper' class='contentwrapper'>"+
						"<div id='validation' class='subcontent'>"+
						"<div class='contenttitle2'>"+
			            "<h3>用户基本信息</h3></div>"+
						"<table cellpadding='0' border='0' class='stdtable stdtablecb' style='border-top:1px solid #ddd'>"+
			            "<tbody><tr>"+
			    		"<td style='width:240px;'>用户电话：<span>"+data.result.phoneNumber+"</span></td>"+
			    		"<td style='width:240px;'>獭猫账户：<span class='field'>"+data.result.userName+"</span></td>"+
			    		"</tr><tr>";
			
			var	html2 = "";
			if (data.result.memberType == 4) {
				html2 = "<td style='width:240px;'>代理人：<span>"+data.result.nickname+"</span></td>";
			}else {
				html2 = "<td style='width:240px;'>用户昵称：<span>"+data.result.nickname+"</span></td>";
			}
			    		
			var	html3 = "<td style='width:240px;'>用户状态：<span>"+data.result.status+"</span></td>"+
			    		"</tr><tr>"+
			    		"<td style='width:240px;'>创建时间：<span>"+data.result.createDate+"</span></td>"+
			    		"<td style='width:240px;'>登录时间：<span>"+data.result.lastLoginDate+"</span></td>"+
			    		"</tr></tbody></table><br />"+
						"<div class='contenttitle2'>"+
						"<h3>用户钱包信息</h3></div>"+
						"<table cellpadding='0' cellspacing='0' border='0' class='stdtable stdtablecb' style='border-top:1px solid #ddd'>"+
			    		"<tbody><tr>"+
			    	    "<td style='width:240px;'>钱包余额：<span>"+data.result.walletAmount+"</span></td>"+
			    		"<td style='width:240px;'>钱包冻结金额：<span>"+data.result.frozenAmount+"</span></td>"+
			    		"</tr><tr>"+
			    	    "<td style='width:240px;'>代扣金余额：<span>"+data.result.bondAmount+"</span></td>"+
			    		"<td style='width:240px;'>代扣金冻结金额：<span>"+data.result.frozenBondAmount+"</span></td>"+
			    		"</tr><tr>"+
			    		"<td style='width:240px;'>獭猫币：<span>"+data.result.returnAmount+"</span></td>"+
			    		"<td style='width:240px;'>冻结獭猫币：<span>"+data.result.frozenReturnAmount+"</span></td>"+
			    		"</tr></tbody></table><br/></div></div></div>";
			
			var html = html1 + html2 + html3;
			jBmzAlert(html, "用户信息及钱包信息", function(r) {
				
			});
				
			jQuery("#popup_ok").css("display", "none");
			//jQuery("#popup_container").css("min-width", "800px");
			jQuery("#popup_cancel").val("关闭");
			
		});
		
	}
	
	/**
	 *	用户提现规则
	 */
	jQuery('#rule_withdraw_btn').click(function(){
		var html = 	"<div style='font-size:16px;'><span class='rule_span'>1.单笔提现最低20元，单笔提现限额500元，单日提现限额2000元。</span><br/><br/>"+
					"<span class='rule_span'>2.提现收取1%手续费,手续费不足2元时按2元收取。</span><br/><br/>"+
					"<span class='rule_span'>3.提现手续费将从我的钱包余额中额外扣除。</span><br/><br/>"+
					"<span class='rule_span'>4.当我的钱包余额不足以支付手续费时，则从提现金额中扣除手续费。</span><br/><br/>"+
					"<span class='rule_span'>5.提现“T+1”天到账，当日提现，第二天到账，遇国家法定假日顺延。</span><br/><br/></div>";
		
		jBmzAlert(html, "提现规则", function(r) {});
		jQuery("#popup_ok").css("display", "none");
		jQuery("#popup_cancel").val("关闭");
		
	});
	
	/**
	 *	渠道商结算规则
	 */
	jQuery('#rule_operator_withdraw_btn').click(function(){
		var html = 	"<div style='font-size:16px;'><span class='rule_span'>1.单笔结算最低100元，单笔结算限额2000元，单日结算限额5000元。</span><br/><br/>"+
					"<span class='rule_span'>2.结算收取10%手续费。</span><br/><br/>"+
					"<span class='rule_span'>3.结算手续费将从我的钱包余额中额外扣除。</span><br/><br/>"+
					"<span class='rule_span'>4.当我的钱包余额不足以支付手续费时，则从结算金额中扣除手续费。</span><br/><br/>"+
					"<span class='rule_span'>5.结算“T+1”天到账，当日结算，第二天到账，遇国家法定假日顺延。</span><br/><br/></div>";
		
		jBmzAlert(html, "渠道商结算规则", function(r) {});
		jQuery("#popup_ok").css("display", "none");
		jQuery("#popup_cancel").val("关闭");
		
	});
	
	/**
	 * 根据出生日期算出年龄
	 */  
	function jsGetAge(strBirthday){      
		
	    var strBirthdayArr = strBirthday.split("-");
	    var date = new Date();  
	    
	    var year = date.getFullYear() - strBirthdayArr[0];
	    var month = (date.getMonth() + 1) - strBirthdayArr[1];
	    var day = date.getDate() - strBirthdayArr[2];
	    
	    if (year >= 0 && month >= 0 && day >= 0) {
			
	    	return year + "岁" + month + "个月" + day + "天";
		}else if(year >= 0 && month < 0 && day >= 0){
			
			return (year-1) + "岁" + (month + 12) + "个月" + day + "天";
		}else if(year >= 0 && month >= 0 && day < 0){
			
			return (year-1) + "岁" + (month - 1) + "个月" + (day+30) + "天";
		}else if(year >= 0 && month < 0 && day < 0){
			
			return (year-1) + "岁" + (month + 11) + "个月" + (day+30) + "天";
		}
	    
	    return 0;
	}  
	  
	/**
	 *	商家给客户发送短信
	 */
	function sendMemberSMS(memberId){
		
		var html = 	"<div class='show_gap'>"+
		            "<div class='show_gap_content'>"+
		 			"<div class='gap_content'>"+
					"<p>短信内容<span style='font-size:12px;color:red;'>(限字100)</span>：</p><div>"+
       				"<textarea rows='10' cols='60' id='sms_content' placeholder='短信字数不超过100字' maxlength='100'></textarea>"+
        			"</div></div></div></div>";
	
		jBmzAlert(html, "发送短信", function(r) {
			if (r) {
				
				var content = jQuery("#sms_content").val();
				var memberIds = "";
				if (memberId == 0) {
					var obj = jQuery(".check");
					for ( var i = 0; i < obj.length; i++) {
						if (obj[i].checked) {
							memberIds += obj[i].value + ",";
						}
					}
				}else{
					memberIds = memberId + ",";
				}
				
				if (memberIds == "") {
					jAlertErrorMsg("请选择需要发送短信的客户");
					return false;
				}else if (content == "") {
					jAlertErrorMsg("短信内容不能为空");
					return false;
				} else {
					var url = "merchant/memberSendMemberSMS?memberIds=" + memberIds + 
							"&content=" + content;
					jQuery.axse(
						url,
						null,
						"请求发送中...",
						function(data) {
							if (data.response == "success") {
								jAlertSuccessMsg(data.msg);
								jAlertHide();
								jQuery("#merchant_smsNumber").text(data.result.smsNumber);
								oTable.fnDraw();
							} else {
								jAlertErrorMsg(data.msg);
							}
						}, function(data) {
							jAlertErrorMsg("请求错误");
					});
				}
			}
		});
	}
	
	/**
	 * 商家购买短信
	 */
	function chargeMerchantSms(oldSmsNumber){
		
		var html = 	"<div class='show_gap'>"+
					"<div class='show_gap_content'>"+
					"<div class='money_show'>"+
					"<p style='color: #ff9900;font-size:24px;'>"+ oldSmsNumber +"</p>"+
					"<p style='color: #999999;'>剩余短信数</p>"+
					"</div><div class='gap_content'>"+
					"<p>购买短信数量：</p><div class='inp'>"+
					"<input type='text' class='data-number' maxlength='6' placeholder='单次购买数量不低于1000条(短信2角一条)' id='sms_number'/>"+
					"</div></div></div></div>";

		jBmzAlert(html, "商家短信购买", function(result){
		
			if (result) {
			
				var smsNumber = $("#sms_number").val();
				if (smsNumber == "" || smsNumber == null) {
					jAlertErrorMsg("请输入购买短信数量");
					return false;
				}else if (smsNumber <= 0) {
					jAlertErrorMsg("短信数量错误");
					return false;
				}else {
				
					var url = "merchant/memberRechargeMerchantSms?smsNumber=" + smsNumber;
					jQuery.axse(url, null, "请求发送中...",function(data){
						
					   if(data.response == "success"){
						   
						   jQuery("#recharge_sms_form").html(data.result.sHtmlText);
						   loadWait("充值跳转中...");
					   }else{
						   
						   jAlertErrorMsg(data.msg);
					   }
					},
					function(data){
						
						jAlertErrorMsg("请求错误");
					});
				}
			}
		});
	}
	//jQuery mobile
	

	//打开loading组件
	//text(string): 加载提示文字
	//str(string): load的背景颜色样式(取值:a,b,c,d)
	//flag(boolean): 背景是否透明(取值:true透明,false不透明)
	function loadStart(text,str,flag){
	    if(!text){
	        text = "加载中...";
	    }
	    jQuery(".ui-loader h1").html(text);
	    var _width = window.innerWidth;
	    var _height = window.innerHeight;
	    var htmlstr = '<div style="width:'+_width+'px;height:'+_height+'px;position:fixed;top:0px;left:0px;opacity:0.1;z-index:99999" class="loader-bg"></div>';
	    jQuery("body").append(htmlstr);
	    if(flag){
	        jQuery(".ui-loader").removeClass("ui-loader-verbose").addClass("ui-loader-default");
	    }
	    else{
	        jQuery(".ui-loader").removeClass("ui-loader-default").addClass("ui-loader-verbose");
	    }
	    var cla = "ui-body-"+str;
	    jQuery("html").addClass("ui-loading");
	    var arr = jQuery(".ui-loader").attr("class").split(" ");
	    var reg = /ui-body-[a-f]/;
	    for(var i in arr){
	        if(reg.test(arr[i])){
	            jQuery(".ui-loader").removeClass(arr[i]);
	        }
	    }
	    jQuery(".ui-loader").addClass(cla);
	}
	//结束loading组件
	function loadStop(){
	    jQuery("html").removeClass("ui-loading");
	    jQuery(".loader-bg").remove();
	}
	//提示信息
	function tips(msg){
		loadStart(msg,"a",false);
		jQuery(".ui-icon-loading").remove();
		jQuery(".loader-bg").remove();
		setTimeout(function(){  
			loadStop();  
		},1500);  
	}
