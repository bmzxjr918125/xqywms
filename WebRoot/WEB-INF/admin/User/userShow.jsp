<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>驰骋家后台管理系统-会员列表</title>
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
                    <h3>会员列表</h3>
            </div>
            <div class="tableoptions" style="float: right;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                                        <span style="margin-left: 10px;">会员昵称：
                                            <span style="display: inline-block;"> 
                                                <input id="nickName" type="text" style="width: 200px;" maxlength="20"/>
                                            </span>
                                        </span>
                                        <span style="margin-left: 10px;">联系电话：
                                            <span style="display: inline-block;"> 
                                                <input id="phoneNumber" type="text" style="width: 200px;" class="data-number" maxlength="11"/>
                                            </span>
                                        </span>
                                        <span style="font-size: 14px;margin-left: 10px;">是否认证：
                                           <span style="display: inline-block;">
                                            <select name="authen" id="authen" class="radius3" style="font-size: 12px;min-width: 15%;">
                                                <option value="0"  selected="selected">--全部--</option>
                                                <option value="1">--未认证--</option>
                                                <option value="2">--认证中--</option>
                                                <option value="3">--已认证--</option>
                                                <option value="4">--认证未通过--</option>
                                            </select>
                                           </span>
                                        </span> 
                                        <span style="font-size: 14px;margin-left: 10px;">会员等级：
                                           <span style="display: inline-block;">
                                            <select name="grade" id="grade" class="radius3" style="font-size: 12px;min-width: 15%;">
                                                <option value="0"  selected="selected">--全部会员--</option>
                                                <option value="1">--普通会员--</option>
                                                <option value="2">--金卡会员--</option>
                                                <option value="3">--钻石会员--</option>
                                            </select>
                                           </span>
                                        </span> 
                                        <a id="query_button" class="query_button btn btn_orange btn_search radius120" style="margin-left: 10px;cursor:pointer;">
                                            <span>查询</span>
                                        </a>
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="userAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">头像</th>
                        <th class="head1">账号</th>
                        <th class="head1">昵称</th>
                        <th class="head1">推荐码</th>
                        <th class="head1">被推荐码</th>
                        <th class="head1">积分</th>
                        <th class="head1">等级</th>
                        <th class="head1">认证</th>
                        <th class="head1">创建时间</th>
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

    var table = jQuery('#userAjaxShow_Table');

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
        {
                "data" : "headerImg.imageUrl",
                "className" : "center",
                "orderable":false,
                "render" : function(data, type, full) {
                    var content="";
                    content= "<img width='80px' height='80px' src=<%=imagePath%>"+data+" />";
                  return content;
                }
            },
        {'data':'phoneNumber','orderable':false,'className':'center'},
        {'data':'nickName','orderable':false,'className':'center'},
        {'data':'myCode','orderable':false,'className':'center'},
        {'data':'higherCode','orderable':false,'className':'center'},
        {'data':'account.integral','orderable':true,'className':'center'},
        {
            "data" : "grade",
            "className" : "center",
            "orderable":false,
            "render" : function(data, type, full) {
              return grade(data);
            }
        },
        {
            "data" : "userCheck",
            "className" : "center",
            "orderable":false,
            "render" : function(data, type, full) {
                var content="----";
                if(data == null){
                    content="<span style='color:red;'>未认证</span>";
                }else{
                    content=authen(data,full.phoneNumber);
                }
              return content;
            }
        },
        {
            'data':'createDate',
            'render':function(data,type,full){
                return data != null ? data.replace(/\T/g,' ') : "-.-.-";
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
                content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='UpdateIntegral("+JSON.stringify(full)+")'>增加积分</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[4,"desc"]];
    var options = {
            "dom":"rt<'table_bottom'lip><'clear'>"
        };
    oTable = DataTablePack.serverTable(table,'admin/userAjaxShow',reqData,columns,order,options);
});

jQuery("#query_button").click(function() {
    reqData["nickName"] = jQuery("#nickName").val();
    reqData["phoneNumber"] = jQuery("#phoneNumber").val();
    reqData["grade"] = jQuery("#grade").val();
    reqData["authen"] = jQuery("#authen").val();
    oTable.fnDraw();
});
   /**
    *添加店员账号
    */
  function UpdateIntegral(obj){

        var html = "用户手机：<input class='popup_prompt' maxlength='11'  value='"+obj.phoneNumber+"' disabled=true/><br/>";
            html+="用户昵称：<input class='popup_prompt' maxlength='10' value='"+obj.nickName+"' disabled=true /><br/>";
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


