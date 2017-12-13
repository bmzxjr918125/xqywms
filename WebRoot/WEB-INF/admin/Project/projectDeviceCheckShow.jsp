<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-项目设备列表</title>
<style type="text/css">
.none_input {
    border: none;
}

#ui-datepicker-div {
    z-index: 99997 !important;
}

.entry-span {
    word-break: normal;
    width: 400px;
    display: block;
    white-space: pre-wrap;
    word-wrap: break-word;
    overflow: hidden;
}
.click-a{
   color:#4093c6;
}
.click-a:hover{
    text-decoration: none;
    color: #ca0c16;
}
</style>
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
                    <h3><a href="javascript:void(0);" onclick="history.go(-1);">返回上一页</a></h3>
            </div>
            >
            <div class="contenttitle2">
                    <h3>项目设备巡检记录列表</h3>
            </div>
            
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="projectCheckDetailsAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">项目</th>
                        <th class="head1">设备</th>
                        <th class="head1">巡检人员</th>
                        <th class="head1">巡检时间</th>
                        <th class="head1">巡检备注</th>
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
var projectDeviceId ='${projectDeviceId}' ;
jQuery(document).ready(function(){

    var table = jQuery('#projectCheckDetailsAjaxShow_Table');

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
                   {'data':'projectDevice.project.projectName','orderable':false,'className':'center'},
                   {'data':'projectDevice.device.deviceInfo.deviceName','orderable':false,'className':'center'},
                   {
                       'data':'projectWorker.worker.nickName',
                       'render':function(data,type,full){
                           return data+"</br>"+full.projectWorker.worker.phoneNumber;
                       },
                       'orderable':false,
                       'className':'center'
                   },
                   {
                       'data':'createDate',
                       'render':function(data,type,full){
                           return data != null ? data.replace(/\T/g,' ') : "-.-.-";
                       },
                       'orderable':true,
                       'className':'center'
                   },{
                       'data':'description',
                       'render':function(data,type,full){
                           return data;
                       },
                       'orderable':false,
                       'className':'center'
                   },
                   {
                       'data':null,
                       'render':function(data,type,full){
                           var content="";
                           content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='lookCheckEntry("+JSON.stringify(full.checkJa)+",0)'>查看报告</a>";
                           return content;
                       },
                       'orderable':false,
                       'className':'center'
                   }
               ];

              
    var order = [ [ 4, "desc" ] ];//,[9,"desc"]
    var options = {
        //"dom" : "rt<'table_bottom'lip><'clear'>"
    };

    oTable = DataTablePack.serverTable(table,
            "admin/projectCheckDetailsAjaxShow?projectDeviceId=" +projectDeviceId , reqData,
            columns, order, options);
});
</script>


