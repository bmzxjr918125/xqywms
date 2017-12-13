<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
	<title>獭猫商户后台管理系统-钱包管理</title>
</head>
<style type="text/css">
.none_input {
    border: none;
}
	.popup_prompt {
	    background: #f7f7f7 none repeat scroll 0 0;
	    border: 1px solid #ccc;
	    border-radius: 2px;
	    box-shadow: 1px 1px 2px #eee inset;
	    color: #666;
	    margin: 5px 0;
	    padding: 7px 5px;
	}}
	
	#withdraw_forum {
	    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	    border: medium none;
	    height: 31px;
	    opacity: 0;
	    position: absolute;
	}
	
	#withdraw_forum {
	    border: 1px solid #fff;
	    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	    font-size: 12px;
	    width: 80%;
	}
	
	#withdraw_forum {
	    background: #fcfcfc none repeat scroll 0 0;
	    border: 1px solid #ccc;
	    border-radius: 2px;
	    box-shadow: 1px 1px 2px #ddd inset;
	    color: #666;
	    min-width: 40%;
	    padding: 5px 2px;
	}
	
	.rule_span{
		font-size: 14px;
	}

</style>

<body class="withvernav">
	<div class="bodywrapper">
		<!--topheader引入-->
     <jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/header.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />

		<!--右部类容-->
		<div class="centercontent">
			
			<ul class="hornav">
                <li id="withdraw_data_current"><a onclick="getProjectWorkerData(1)" href="#withdrawList">巡检组</a></li>
                <li id="trade_data_current"><a onclick="getProjectWorkerData(2)" href="#tradeList">维修组</a></li>
	        </ul>
		
			<div id="contentwrapper" class="contentwrapper">
				
				<div id="withdrawList" class="subcontent">
					<div class="tableoptions" style="display: block;overflow: hidden;">
						<div style="display: inline;">
							<form class="stdform stdform2">
								<div>
								 <div class="tableoptions" style="border: 0px solid #ddd;float: left;margin-top: 8px;" >
									<a class="btn btn_orange btn_flag" workerType="1" id="merchant_withdraw_btn" href="javascript:" >
										<span>新增组员</span>
									</a>
								 </div>
					      </div>
							</form>
						</div>
					 </div>
					
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="withdrawAjaxShow_Table">
						<thead>
							<tr>
							  	<th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
								<th class="head1">姓名</th>
								<th class="head1">联系方式</th>
								<th class="head1">加入时间</th>
								<th class="head1">最近登录</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		       </div>
				
				<div id="tradeList" class="subcontent" style="display: none;">
					<div class="tableoptions" style="display: block;overflow: hidden;">
						<div style="display: inline;">
							<form class="stdform stdform2">
								<div>
                                 <div class="tableoptions" style="border: 0px solid #ddd;float: left;margin-top: 8px;" >
                                    
                                    <a class="btn btn_orange btn_flag"  workerType="2" id="merchant_withdraw_btn1" href="javascript:">
                                        <span>新增组员</span>
                                    </a>
                                 </div>
                          </div>
							</form>
						</div>
					</div>
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="tradeAjaxShow_Table">
						<thead>
							<tr>
								<th class="head1 nosort" >
									<input type="checkbox" class="checkall" />
								</th>
								<th class="head1">姓名</th>
                                <th class="head1">联系方式</th>
                                <th class="head1">加入时间</th>
                                <th class="head1">最近登录</th>
                                <th class="head1">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>

</html>
<script type="text/javascript" src="js/method.js"></script>
<script type="text/javascript" src="js/pack/datatables-pack.js"></script>
<script type="text/javascript" src="js/inputValidate.js"></script>
<script type="text/javascript" src="js/EnumExChange.js"></script>
<script type="text/javascript" src="js/pack/datatables-pack.js"></script>
<script type="text/javascript">
	var reqData = { };
	var oTable = null;
	var projectId = '${projectId}';
	jQuery(document).ready(function(){
	    getProjectWorkerData(1);
	});

	/**
	 * 维修组、巡检组
	 */
	function getProjectWorkerData(flag){
	    var tableId="";
	    var tagId="";
		if(flag == 1){
		    tableId="withdrawAjaxShow_Table";
		    tagId="withdraw_data_current";
		}else{
		    tableId="tradeAjaxShow_Table";
		    tagId="trade_data_current";
		}
		if(jQuery("#"+tagId).attr("class")=="current" || 
				jQuery("#"+tagId).attr("isLoad") == "1"){
			return false;
		}
		jQuery("#"+tagId).addClass("current");
		jQuery("#"+tagId).attr("isLoad","1");
		
	
		
		var table = jQuery("#"+tableId);
		var columns = [
		    {
		    	"data":"id",
		    	"render":function(data,type,full){
		    		var content = "<div class='checker'><span>";
		    			content += "<input class='check' type='checkbox' value='"+full.id+"'>";
		    			content += "</span></div>";
		    		return content;
		    	},
		    	"orderable":false,
				"className":"center"
		    },
			{
				"data" : "worker.nickName",
				"className" : "center",
				"orderable":false
			},
			{
				"data" : "worker.phoneNumber",
				"className" : "center",
				"orderable":false
			},
			{
				"data" : "createDate",
				"render" : function(data, type, full) {
					return data != null ? data.replace(/\T/g,' ') : "-.-.-";
				},
				"className" : "center",
				"orderable" : true
			},
			{
				"data" : "worker.thisLoginDate",
				"render" : function(data, type, full) {
					return data != null ? data.replace(/\T/g,' ') : "-.-.-";
				},
				"className" : "center",
				"orderable" : true
			},
			{
                'data':null,
                'render':function(data,type,full){
                    var content="";
                    content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='projectWorkerDelete("+full.id+")'>移除</a>";
                    return content;
                },
                'orderable':false,
                'className':'center'
            }
		];
		var order = [ [ 3, "desc" ] ];
		var options = {
			"dom" : "rt<'table_bottom'lip><'clear'>"
		};
		oTable = DataTablePack.serverTable(table, "admin/projectWorkerAjaxShow?workerType="+flag+"&projectId="+projectId, reqData, columns, order, options);
	}
	
	/**
	 *	增加组员
	 */
	jQuery('#merchant_withdraw_btn,#merchant_withdraw_btn1').click(function(){
		var workerType = jQuery(this).attr("workerType"); 
	    jQuery.axse("admin/projectGetCanAddToProjectWorker?workerType="+workerType+"&projectId="+projectId, null, "请求发送中...",function(data){
            
            if(data.response=="success"){
                var html =  
                    "设备选择：<select id='projectDevice'  onchange='selectDevice(this)'>";
                var deviceList = data.result.deviceList;
                html+="<option deviceNo=''  value='0'>请选择</option>";
                for(var i = 0;i<deviceList.length;i++){
                    html+="<option deviceNo='"+deviceList[i].device.deviceNo+"'  value='"+deviceList[i].id+"'>"+deviceList[i].device.deviceInfo.deviceName+"</option>";
                }
                html+="</select><br/>"+
                "设备编号：<input class='popup_prompt none_input' id='deviceNo' onfocus=this.blur() readonly=true /><br/>"+
                    
                "人员选择：<select id='projectWorker' onchange='selectWorker(this)'>";
                    var workerList = data.result.workerList;
                    html+="<option phone='' depart='' job='' value='0'>请选择</option>";
                    for(var i = 0;i<workerList.length;i++){
                        html+="<option phone='"+workerList[i].phoneNumber+"' depart='"+workerList[i].department+"' job='"+workerList[i].job+"' value='"+workerList[i].id+"'>"+workerList[i].nickName+"</option>";
                    }
                    html+="</select><br/>"+
                    "联系方式：<input class='popup_prompt none_input' id='workerPhoneNumber' onfocus=this.blur() readonly=true /><br/>"+
                    "所在部门：<input class='popup_prompt none_input' id='workerDepartmemt' onfocus=this.blur() readonly=true /><br/>"+
                    "所属职位：<input class='popup_prompt none_input' id='workerJob' onfocus=this.blur() readonly=true /><br/>";
        
        jBmzAlert(html, "新增组员", function(r) {
            if (r) {
                var projectDeviceId = jQuery("#projectDevice").val();
                if (projectDeviceId == "" || projectDeviceId == 0 || projectDeviceId == null) {
                    jAlertErrorMsg("请选择设备信息");
                    return false;
                } 
                var workerId = jQuery("#projectWorker").val();
                if (workerId == "" || workerId == 0 || workerId == null) {
                    jAlertErrorMsg("请选择加入人员");
                    return false;
                } 
               
                    var url = "admin/projectWorkerAddSave?projectId=" + projectId + 
                            "&workerId=" + workerId+"&workerType="+workerType+"&projectDeviceId="
                            +projectDeviceId;
                    jQuery.axse(url, null, "请求发送中...",function(data){
                        
                       if(data.response=="success"){
                           jAlertSuccessMsg(data.msg);
                           jAlertHide();
                           oTable.fnDraw();
                       }else{
                           jAlertErrorMsg(data.msg);
                       }
                    },function(data){
                        jAlertErrorMsg("请求错误");
                    });
            }
        });
            }else{
                jAlertErrorMsg(data.msg);
            }
         },function(data){
             jAlertErrorMsg("请求错误");
         });
	    
	});
	function selectWorker(obj){
	    jQuery("#workerPhoneNumber").val(jQuery(obj).find("option:selected").attr("phone"));
	    jQuery("#workerDepartmemt").val(jQuery(obj).find("option:selected").attr("depart"));
	    jQuery("#workerJob").val(jQuery(obj).find("option:selected").attr("job"));
	}
	function selectDevice(obj){
	    jQuery("#deviceNo").val(jQuery(obj).find("option:selected").attr("deviceNo"));
	}
	function projectWorkerDelete(projectWorkerId){
	    var html = "<span>确认从该项目移除该人员信息,若该人员已有巡检记录操作将不能移除，移除确认?</span>";
       
        jBmzAlert(html, "移除组员", function(r) {
            if (r) {
               
               
                    var url = "admin/projectWorkerDelete?projectWorkerId=" + projectWorkerId;
                    jQuery.axse(url, null, "请求发送中...",function(data){
                        
                       if(data.response=="success"){
                           jAlertSuccessMsg(data.msg);
                           jAlertHide();
                           oTable.fnDraw();
                       }else{
                           jAlertErrorMsg(data.msg);
                       }
                    },function(data){
                        jAlertErrorMsg("请求错误");
                    });
            }
        });
          
        
	    
	}

</script>
