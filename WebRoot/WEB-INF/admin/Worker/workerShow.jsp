<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-人员列表</title>
</head>

<body class="withvernav">
<div class="bodywrapper">
     <!--topheader引入-->
     <jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/header.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />

    <div class="centercontent tables">
        <div id="contentwrapper" class="contentwrapper">
            <div class="contenttitle2">
                    <h3>人员列表</h3>
            </div>
            <div class="tableoptions" style="float: right;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                               <div>
                                <div class="tableoptions"
                                    style="border: 0px solid #ddd;float: left;">
                                    <a class="btn btn_book"  id="addEmpl" href="javascript:void(0);"><span>添加人员</span></a> 
                                    <%--<a class="btn btn2 btn_book batchOp" href="merchant/employeeDeleteEmployees?ids={values}" title="确认删除这些店员,删除店员后将不能恢复?|删除确认"><span>批量删除</span>
                                    </a>  
                           --%></div>
                               <div class="tableoptions" style="border: 0px solid #ddd;float: right;">
                                        <span style="margin-left: 10px;">名称：
                                            <span style="display: inline-block;"> 
                                                <input id="nickName" type="text" style="width: 200px;" maxlength="20"/>
                                            </span>
                                        </span>
                                        <span style="margin-left: 10px;">联系电话：
                                            <span style="display: inline-block;"> 
                                                <input id="phoneNumber" type="text" style="width: 200px;" class="data-number" maxlength="11"/>
                                            </span>
                                        </span>
                                        <span style="font-size: 14px;margin-left: 10px;">状态：
                                           <span style="display: inline-block;">
                                            <select name="status" id="status" class="radius3" style="font-size: 12px;min-width: 15%;">
                                                <option value="0"  selected="selected">--全部--</option>
                                                <option value="31">--正常--</option>
                                                <option value="32">--冻结--</option>
                                                
                                            </select>
                                           </span>
                                        </span> 
                                        <a id="query_button" class="query_button btn btn_orange btn_search radius120" style="margin-left: 10px;cursor:pointer;">
                                            <span>查询</span>
                                        </a>
                                    </div>
                            </div>      
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="workerAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">名称</th>
                        <th class="head1">联系方式</th>
                        <th class="head1">部门</th>
                        <th class="head1">职位</th>
                        <th class="head1">项目数</th>
                        <th class="head1">维修次数</th>
                        <th class="head1">巡检次数</th>
                        <th class="head1">状态</th>
                        <th class="head1">最近登录时间</th>
                        <th class="head1">操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div><!--contentwrapper-->
    </div><!-- centercontent -->
</div><!--bodywrapper-->
</body>
</html>
<script type="text/javascript" src="js/pack/datatables-pack.js"></script>
<script type="text/javascript" src="js/inputValidate.js"></script>
<script type="text/javascript">
var oTable;
var reqData = {};
jQuery(document).ready(function(){

    var table = jQuery('#workerAjaxShow_Table');

    var columns = [
        {
            'data':null,
            'render':function(data,type,full){
                var content = '<div class="checker"><span>';
                    content += '<input class="check" type="checkbox" value="'+full.id+'">';
                    content += '</span></div>';
                return content;
            },
            'orderable':false,
            'className':'center'
        },
        //{'data':'id','orderable':false},
        {'data':'nickName','orderable':false,'className':'center'},
        {'data':'phoneNumber','orderable':false,'className':'center'},
        {'data':'department','orderable':false,'className':'center'},
        {'data':'job','orderable':false,'className':'center'},
        {'data':'projectNum',
            'render':function(data,type,full){
                return full.count.projectNum;
            },
            'orderable':true,'className':'center'},
        {'data':'repairNum',
            'render':function(data,type,full){
                return full.count.repairNum;
            },
            'orderable':true,'className':'center'},
        {'data':'checkNum',
            'render':function(data,type,full){
                return full.count.checkNum;
            },
            'orderable':true,'className':'center'},
        {
            'data':'status',
            'render':function(data,type,full){
                return status(full.status);
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'thisLoginDate',
            'render':function(data,type,full){
                return data != null ? data.replace(/\T/g,' ') : "-.-.-";
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':null,
            'render':function(data,type,full){
                var content="";
                if(full.status == "WORKER_ABLE"){
                    
                    content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='changeStatus("+JSON.stringify(full)+",1)'>冻结</a>";
                }else if(full.status == "WORKER_DISABLE"){
                    
                    content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='changeStatus("+JSON.stringify(full)+",2)'>启用</a>";
                }
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='UpdateDefaultPwd("+JSON.stringify(full)+")'>密码重置</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='UpdateIntegral("+JSON.stringify(full)+")'>编辑</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[5,"desc"]];
    var options = {
            "dom":"rt<'table_bottom'lip><'clear'>"
        };
    oTable = DataTablePack.serverTable(table,'admin/workerAjaxShow',reqData,columns,order,options);
});

jQuery("#query_button").click(function() {
    reqData["nickName"] = jQuery("#nickName").val();
    reqData["phoneNumber"] = jQuery("#phoneNumber").val();
    reqData["status"] = jQuery("#status").val();
    oTable.fnDraw();
});
   /**
    *人员账号启用或冻结
    */
  function changeStatus(obj,flag){

        var html ="人员手机：<input class='popup_prompt' maxlength='11'  value='"+obj.phoneNumber+"' disabled=true/><br/>";
            html+="人员名称：<input class='popup_prompt' maxlength='10' value='"+obj.nickName+"' disabled=true /><br/>";
                html+="<span style='color:red;'> *冻结人员账号后，该账号将不能登录;启用账号后账号将恢复可用状态。</span><br/>";
        var str = "";
        if(flag == 1){
            str="冻结";
        }else{
            str="启用";
        }
                jBmzAlert( html, "人员账号"+str, function(r) {
            if (r) {
               
                var reData={};
                reData["workerId"]=obj.id;
                reData["flag"]=flag;
                
                    var url = "admin/workerChangeStatus";
                    jQuery.axse(
                        url,
                        reData,
                        "请求发送中...",
                        function(data) {
                            if (data.response == "success") {
                                jAlertSuccessMsg(data.msg);
                                jAlertHide();
                                oTable.fnDraw();
                            } else {
                                jAlertErrorMsg(data.msg);
                            }
                        }, function(data) {
                            jAlertErrorMsg("请求错误");
                        });
            }
        });
  }
   
  /**
   *人员密码重置
   */
 function UpdateDefaultPwd(obj){

     jConfirm(" <span style='color:red;'> * 重置账号密码后，密码为对应手机号后六位。</span>", "确认重置该账号密码?", function(r) {
           if (r) {
              
               var reData={};
               reData["workerId"]=obj.id;
               
                   var url = "admin/workerUpdateDefaultPwd";
                   jQuery.axse(
                       url,
                       reData,
                       "请求发送中...",
                       function(data) {
                           if (data.response == "success") {
                               jAlertSuccessMsg(data.msg);
                               jAlertHide();
                               oTable.fnDraw();
                           } else {
                               jAlertErrorMsg(data.msg);
                           }
                       }, function(data) {
                           jAlertErrorMsg("请求错误");
                       });
           }
       });
 }
   
   /**
    *新建人员信息
    */
  function UpdateIntegral(obj){

        var html = "人员手机：<input class='popup_prompt' maxlength='11'  value='"+obj.phoneNumber+"' disabled=true/><br/>";
            html+="人员昵称：<input class='popup_prompt' maxlength='10' value='"+obj.nickName+"' disabled=true /><br/>";
            html+="增加积分：<input class='popup_prompt data-number' maxlength='10' id='integralNum' placeholder='请输入增加积分数量'/><span style='color:red;'> *</span><br/>";
        jBmzAlert( html, "增加会员积分", function(r) {
            if (r) {
                var integralNum = jQuery("#integralNum").val();
                
                if (integralNum == null || integralNum.trim() == "") {
                    jAlertErrorMsg("请输入增加积分数量");
                    return false;
                } 
                var reData={};
                reData["integralNum"]=integralNum;
                
                    var url = "admin/userAddSaveIntegral?userId="+obj.id;
                    jQuery.axse(
                        url,
                        reData,
                        "请求发送中...",
                        function(data) {
                            if (data.response == "success") {
                                jAlertSuccessMsg(data.msg);
                                jAlertHide();
                                oTable.fnDraw();
                            } else {
                                jAlertErrorMsg(data.msg);
                            }
                        }, function(data) {
                            jAlertErrorMsg("请求错误");
                        });
            }
        });
  }

</script>


