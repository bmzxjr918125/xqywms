<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-客户列表</title>
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
                    <h3>客户列表</h3>
            </div>
            <div class="tableoptions" style="float: right;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                                        <span style="margin-left: 10px;">会员名称：
                                            <span style="display: inline-block;"> 
                                                <input id="nickName" type="text" style="width: 200px;" maxlength="20"/>
                                            </span>
                                        </span>
                                        <span style="margin-left: 10px;">联系电话：
                                            <span style="display: inline-block;"> 
                                                <input id="phoneNumber" type="text" style="width: 200px;" class="data-number" maxlength="11"/>
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
                        <th class="head1">名称</th>
                        <th class="head1">联系方式</th>
                        <th class="head1">所属项目</th>
                        <th class="head1">提交维修次数</th>
                        <th class="head1">最近登录时间</th>
                        <th class="head1">主负责人</th>
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
        {'data':'nickName','orderable':false,'className':'center'},
        {'data':'phoneNumber','orderable':false,'className':'center'},
        {
            "data" : null,
            "className" : "center",
            "orderable":false,
            "render" : function(data, type, full) {
              return full.projectUser.project.projectName;
            }
        },
        {'data':'commitCheckNum','orderable':true,'className':'center'},
        {
            'data':'lastLoginDate',
            'render':function(data,type,full){
                return data != null ? data.replace(/\T/g,' ') : "-.-.-";
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':null,
            'render':function(data,type,full){
                if(full.projectUser.isMain == "YES"){
                    return "<span style='color:#78ce07'>是</span>";
                }else{
                    return "<span>否</span>";
                }
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':null,
            'render':function(data,type,full){
                var content="";
                content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='sendMSM("+JSON.stringify(full)+")'>发送短信</a>";
                if(full.projectUser.isMain == "YES"){
                    content+="&nbsp;&nbsp<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='lookUser("+full.projectUser.project.id+")'>查看下属人员</a>";
                }
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
    
    oTable.fnDraw();
});
   /**
    *发送短信给客户
    */
  function sendMSM(obj){

        var html = "用户手机："+obj.phoneNumber+"<br/>";
            html+="短息内容：<br/><textarea class='popup_prompt' maxlength='40'  value='"+obj.phoneNumber+"' id='content'></textarea><br/>";
            html+="<span style='color:red;'> *短信字数不能超过100字</span><br/>";
        jBmzAlert( html, "发送短信给用户  "+obj.nickName, function(r) {
            if (r) {
                var content = jQuery("#content").val();
                
                if (content == null || content.trim() == "") {
                    jAlertErrorMsg("请输入短信内容");
                    return false;
                } 
                var reData={};
                reData["content"]=content;
                reData["phoneNumber"]=obj.phoneNumber;
                reData["userId"]=obj.id;
                
                    var url = "admin/userSendMSM";
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
   *查看关联客户
   */
  function lookUser(projectId) {

      var html = "<table cellpadding='0' cellspacing='0' border='0' class='stdtable stdtablecb' id='MeAjaxShow_Table'>"
              + "<thead>"
              + "<tr>"
              + "<th class='head1'>名称</th>"
              + "<th class='head1'>联系方式</th>"
              + "<th class='head1'>提交维修次数</th>"
              + "<th class='head1'>最近登录时间</th>"
              + "<th class='head1'>主负责人</th>"
              + "<th class='head1'>操作</th>"
              + "</tr>" + "</thead>" + "<tbody>" + "</tbody>" + "</table>";
      jBmzAlert(html, "下属人员", function(r) {
      });
      jQuery("#popup_ok").css("display", "none");
      jQuery("#popup_container").css("min-width", "800px");
      jQuery("#popup_cancel").val("关闭");
      getMeData(projectId);
  }
  var mereqData = {};
  var meTable = null;
  function getMeData(projectId) {

      var table = jQuery("#MeAjaxShow_Table");

      var columns = [
                     //{'data':'id','orderable':false},
                     {'data':'user.nickName','orderable':false,'className':'center'},
                     {'data':'user.phoneNumber','orderable':false,'className':'center'},
                     {'data':'user.commitCheckNum','orderable':true,'className':'center'},
                     {
                         'data':'user.lastLoginDate',
                         'render':function(data,type,full){
                             return data != null ? data.replace(/\T/g,' ') : "-.-.-";
                         },
                         'orderable':true,
                         'className':'center'
                     },{
                         'data':'isMain',
                         'render':function(data,type,full){
                             if(data == "YES"){
                                 return "<span style='color:#78ce07'>是</span>";
                             }else{
                                 return "<span>否</span>";
                             }
                         },
                         'orderable':false,
                         'className':'center'
                     },
                     {
                         'data':null,
                         'render':function(data,type,full){
                             var content="";
                             content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='sendMSM("+JSON.stringify(full)+")'>发送短信</a>";
                             return content;
                         },
                         'orderable':false,
                         'className':'center'
                     }
                 ];

                
      var order = [ [ 4, "desc" ] ];//,[9,"desc"]
      var options = {
          "dom" : "rt<'table_bottom'lip><'clear'>"
      };

      meTable = DataTablePack.serverTable(table,
              "admin/userUnderAjaxShow?projectId=" +projectId , mereqData,
              columns, order, options);

  }
   

</script>


