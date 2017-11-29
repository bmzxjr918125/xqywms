<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>驰骋家后台管理系统-新闻列表</title>
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
                    <h3>新闻列表</h3>
            </div>
           <div class="tableoptions" style="float: right;">
                 <div style="display: inline-block;">
                    <a class="btn btn2 btn_book" href="admin/newAdd"><span>添加新闻</span></a>
                    <a class="btn btn2 btn_book batchOp" href="admin/newDelete?ids={values}"  title="确认删除这些公告信息,删除后将不能恢复?|删除确认"><span>批量删除</span></a>
                 </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="newAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">速览图</th>
                        <th class="head1">标题</th>
                        <th class="head1">创建日期</th>
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
<script type="text/javascript">
var oTable;
var reqData = {};
jQuery(document).ready(function(){

    var table = jQuery('#newAjaxShow_Table');

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
                "data" : "imagePath",
                "className" : "center",
                "orderable":false,
                "render" : function(data, type, full) {
                    var content="";
                    content= "<img width='80px' height='80px' src=<%=imagePath%>"+data+" />";
                  return content;
                }
            },
        {'data':'title','orderable':false,'className':'center'},
        {
            'data':'createDate',
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
                content+="<a class='stdbtn' style='font-size: 12px;' href='admin/newUpdate?id="+full.id+"' >编辑信息</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[3,"desc"]];
    var options = {
            "dom":"rt<'table_bottom'lip><'clear'>"
        };
    oTable = DataTablePack.serverTable(table,'admin/newAjaxShow',reqData,columns,order,options);
});

   /**
    *添加店员账号
    */
  function AddEmpl(){

        var html = "店员手机：<input class='popup_prompt' maxlength='11' id='phoneNum' placeholder='请输入店员手机号'/><span style='color:red;'> *</span><br/>";
            html+="店员名称：<input class='popup_prompt' maxlength='10' id='roleName' placeholder='请输入店员名称'/><span style='color:red;'> *</span><br/>";
            html+="<span style='color:red;'> * 账号初始密码为手机后六位，可在列表中进行密码修改</span>";
        jBmzAlert( html, "添加店员账号", function(r) {
            if (r) {
                var phoneNum = jQuery("#phoneNum").val();
                var roleName = jQuery("#roleName").val();
                if (phoneNum == null || phoneNum.trim() == "") {
                    jAlertErrorMsg("请输入店员手机号");
                    return false;
                } 
                if (roleName == null || roleName.trim() == "") {
                    jAlertErrorMsg("请输入店员名称");
                    return false;
                } 
                var reData={};
                reData["phoneNum"]=phoneNum;
                reData["roleName"]=roleName;
                
                    var url = "admin/adminAddSaveEmpl";
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


