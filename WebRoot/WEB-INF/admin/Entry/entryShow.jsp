<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-词条列表</title>
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
                    <h3>词条列表</h3>
            </div>
           <div class="tableoptions" style="float: right;">
                 <div style="display: inline-block;">
                    <a class="btn btn_book" href="javascript:void(0);" onclick="addEntry(${flag})"><span>添加词条</span></a>
                    <a class="btn btn_trash batchOp" href="admin/entryDelete?ids={values}"  title="确认删除这些词条信息,删除后将不能恢复?|删除确认"><span>批量删除</span></a>
                 </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="entryAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">ID</th>
                        <th class="head1">词条名称</th>
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
var entryType = '${flag}';
reqData["flag"]=entryType;
jQuery(document).ready(function(){

    var table = jQuery('#entryAjaxShow_Table');

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
        {'data':'id','orderable':false,'className':'center'},
        {'data':'value','orderable':false,'className':'center'},
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
                content+="<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateEntry("+entryType+","+full.id+",\""+full.value+"\")' >编辑信息</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[3,"asc"]];
    var options = {
            "dom":"rt<'table_bottom'lip><'clear'>"
        };
    oTable = DataTablePack.serverTable(table,'admin/entryAjaxShow',reqData,columns,order,options);
});

   /**
    *添加词条
    */
  function addEntry(flag){
        var html = "词条内容：<br/><textarea class='popup_prompt' id='entryValue' placeholder='请输入词条内容'></textarea><span style='color:red;'> *</span><br/>";
        var str = "";
        if(flag == 1){
            str =  "巡检";
        }else if(flag == 2){
            str =  "维修";
        }else if(flag == 2){
            str =  "部门";
        }else{
            str =  "职位";
        }
        jBmzAlert( html, "添加"+str+"词条", function(r) {
            if (r) {
                var entryValue = jQuery("#entryValue").val();
                if (entryValue == null || entryValue.trim() == "") {
                    jAlertErrorMsg("请输入词条内容");
                    return false;
                } 
                
                var reData={};
                reData["name"]=entryValue;
                
                    var url = "admin/entryAddSave?flag="+flag;
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
            };
        });
   }
  /**
   *修改词条
   */
 function updateEntry(flag,id,entryValue){
       var html = "词条内容：<br/><textarea class='popup_prompt' id='entryValue' placeholder='请输入词条内容'>"+entryValue+"</textarea><span style='color:red;'> *</span><br/>";
       var str = "";
       if(flag == 1){
           str =  "巡检";
       }else if(flag == 2){
           str =  "维修";
       }else if(flag == 2){
           str =  "部门";
       }else{
           str =  "职位";
       }
       jBmzAlert( html, "修改"+str+"词条", function(r) {
           if (r) {
                entryValue = jQuery("#entryValue").val();
               if (entryValue == null || entryValue.trim() == "") {
                   jAlertErrorMsg("请输入词条内容");
                   return false;
               } 
               
               var reData={};
               reData["name"]=entryValue;
               
                   var url = "admin/entryUpdateSave?id="+id;
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
           };
       });
  }
</script>


