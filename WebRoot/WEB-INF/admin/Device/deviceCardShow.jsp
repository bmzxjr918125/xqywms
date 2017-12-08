<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-设备名片列表</title>
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
                    <h3>设备名片列表</h3>
            </div>
            <div class="tableoptions" style="float: right;padding: 1px;border: 0px solid #fff !important;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                               <div>
                                <div class="tableoptions"
                                    style="border: 0px solid #fff !important;float: left;padding: 1px;">
                                    <a class="btn btn_book"   onclick="cardAddSave();" href="javascript:void(0);"><span>添加名片</span></a> 
                                    <a class="btn btn_trash batchOp" href="admin/deviceCardDelete?ids={values}" title="确认删除这些名片,删除名片后将不能恢复?|删除确认"><span>批量删除</span></a>  
                           </div>
                             </div>      
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="deviceCardAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">名称</th>
                        <th class="head1">类型</th>
                        <th class="head1">厂家</th>
                        <th class="head1">型号</th>
                        <th class="head1">制冷/输入</th>
                        <th class="head1">制热/输出</th>
                        <th class="head1">制冷剂</th>
                        <th class="head1">充注量</th>
                        <th class="head1">描述</th>
                        <th class="head1">创建时间</th>
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
var entryList =eval('(' + '${entryTypeJa}' + ')');
jQuery(document).ready(function(){

    var table = jQuery('#deviceCardAjaxShow_Table');

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
        {'data':'deviceInfo.deviceName','orderable':false,'className':'center'},
        {'data':'deviceInfo.deviceType','orderable':false,'className':'center'},
        {'data':'deviceInfo.supplier','orderable':false,'className':'center'},
        {'data':'deviceInfo.modelNum','orderable':false,'className':'center'},
        {'data':'deviceInfo.input','orderable':false,'className':'center'},
        {'data':'deviceInfo.output','orderable':false,'className':'center'},
        {'data':'deviceInfo.cryogenType','orderable':false,'className':'center'},
        {'data':'deviceInfo.charge','orderable':false,'className':'center'},
        {'data':'deviceInfo.description','orderable':false,'className':'center'},
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
               
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateDeviceCard("+full.id+","+JSON.stringify(full.deviceInfo)+",1)'>编辑</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateDeviceCard("+full.id+","+JSON.stringify(full.deviceInfo)+",2)'>复制添加</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[10,"desc"]];
    var options = {
           
        };
    oTable = DataTablePack.serverTable(table,'admin/deviceCardAjaxShow',reqData,columns,order,options);
});

   
   /**
    *新建设备名片信息
    */
  function cardAddSave(){
            if(entryList == null || entryList.length == 0 || typeof(entryList) == "undefined" ){
                jAlertErrorMsg("请先在系统管理中添加对应设备类型词条");
                return false;
            }
           
        var html = "设备&nbsp;名称：<input class='popup_prompt' id='input_deviceName'   placeholder='请输入设备名称'/><span style='color:red;'> *</span><br/>";
            html+="设备&nbsp;类型：<select class='popup_prompt' id='input_deviceType'>";
            for(var i=0;i<entryList.length;i++){
                html+="<option value='"+entryList[i].id+"'>"+entryList[i].value+"</option>";
            }
            html+="</select> <span style='color:red;'> *</span></br>";
            html+="设备&nbsp;厂家：<input class='popup_prompt'  id='input_supplier'   placeholder='请输入设备生成厂家' /><span style='color:red;'> *</span><br/>";
            html+="设备&nbsp;型号：<input class='popup_prompt'  id='input_modeNum'   placeholder='请输入设备型号' /><span style='color:red;'> *</span><br/>";
            html+="制冷/输入：<input class='popup_prompt'  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
            html+="制热/输出：<input class='popup_prompt'  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
            html+="制冷&nbsp;类型：<input class='popup_prompt'  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
            html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input class='popup_prompt'  id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
            html+="设备&nbsp;描述：<input class='popup_prompt' id='input_description'   placeholder='请输入设备描述' /><br/>";
            
            html+="<span style='color:red;'> * 设备名片信息可在添加设备时重复使用哟。</span><br/>";
        jBmzAlert( html, "添加设备名片信息", function(r) {
            if (r) {
                var deviceName = jQuery("#input_deviceName").val();
                var deviceType = jQuery("#input_deviceType").find("option:selected").text();
                var supplier = jQuery("#input_supplier").val();
                var modeNum = jQuery("#input_modeNum").val();
                var input = jQuery("#input_input").val();
                var output = jQuery("#input_output").val();
                var cryogenType = jQuery("#input_cryogenType").val();
                var charge = jQuery("#input_charge").val();
                var description = jQuery("#input_description").val();
                
                if (deviceName == null || deviceName.trim() == "") {
                    jAlertErrorMsg("请输入设备名称");
                    return false;
                } 
                if (deviceType == null || deviceType.trim() == "") {
                    jAlertErrorMsg("请输入设备类型");
                    return false;
                } 
                if (supplier == null || supplier.trim() == "") {
                    jAlertErrorMsg("请输入设备生产厂家");
                    return false;
                } 
                if (modeNum == null || modeNum.trim() == "") {
                    jAlertErrorMsg("请输入设备型号");
                    return false;
                } 
                
               
                var reData={};
                reData["deviceName"]=deviceName;
                reData["deviceType"]=deviceType;
                reData["supplier"]=supplier;
                reData["modeNum"]=modeNum;
                reData["input"]=input;
                reData["output"]=output;
                reData["cryogenType"]=cryogenType;
                reData["charge"]=charge;
                reData["description"]=description;
                
                    var url = "admin/deviceCardAddSave";
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
   *编辑设备名片信息
   */
 function updateDeviceCard(deviceCardId,obj,flag){
     if(entryList == null || entryList.length == 0 || typeof(entryList) == "undefined" ){
         jAlertErrorMsg("请先在系统管理中添加对应设备类型词条");
         return false;
     }
    
 var html = "设备&nbsp;名称：<input class='popup_prompt' id='input_deviceName' value='"+obj.deviceName+"'  placeholder='请输入设备名称'/><span style='color:red;'> *</span><br/>";
     html+="设备&nbsp;类型：<select class='popup_prompt' id='input_deviceType'>";
     html+="<option value='0' select='select'>"+obj.deviceType+"</option>";
     for(var i=0;i<entryList.length;i++){
         html+="<option value='"+entryList[i].id+"'>"+entryList[i].value+"</option>";
     }
     html+="</select> <span style='color:red;'> *</span></br>";
     html+="设备&nbsp;厂家：<input class='popup_prompt' value='"+obj.supplier+"'  id='input_supplier'   placeholder='请输入设备生成厂家' /><span style='color:red;'> *</span><br/>";
     html+="设备&nbsp;型号：<input class='popup_prompt' value='"+obj.modelNum+"'  id='input_modeNum'   placeholder='请输入设备型号' /><span style='color:red;'> *</span><br/>";
     html+="制冷/输入：<input class='popup_prompt' value='"+obj.input+"'  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
     html+="制热/输出：<input class='popup_prompt' value='"+obj.output+"'  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
     html+="制冷&nbsp;类型：<input class='popup_prompt' value='"+obj.cryogenType+"'  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
     html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input class='popup_prompt' value='"+obj.charge+"' id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
     html+="设备&nbsp;描述：<input class='popup_prompt' id='input_description' value='"+obj.description+"'  placeholder='请输入设备描述' /><br/>";
     
     html+="<span style='color:red;'> * 编辑设备名片信息不会改变已添加设备的信息。</span><br/>";
 jBmzAlert( html, "编辑设备名片信息", function(r) {
     if (r) {
         var deviceName = jQuery("#input_deviceName").val();
         var deviceType = jQuery("#input_deviceType").find("option:selected").text();
         var supplier = jQuery("#input_supplier").val();
         var modeNum = jQuery("#input_modeNum").val();
         var input = jQuery("#input_input").val();
         var output = jQuery("#input_output").val();
         var cryogenType = jQuery("#input_cryogenType").val();
         var charge = jQuery("#input_charge").val();
         var description = jQuery("#input_description").val();
         
         if (deviceName == null || deviceName.trim() == "") {
             jAlertErrorMsg("请输入设备名称");
             return false;
         } 
         if (deviceType == null || deviceType.trim() == "") {
             jAlertErrorMsg("请输入设备类型");
             return false;
         } 
         if (supplier == null || supplier.trim() == "") {
             jAlertErrorMsg("请输入设备生产厂家");
             return false;
         } 
         if (modeNum == null || modeNum.trim() == "") {
             jAlertErrorMsg("请输入设备型号");
             return false;
         } 
         
        
         var reData={};
         reData["deviceCardId"]=deviceCardId;
         reData["deviceName"]=deviceName;
         reData["deviceType"]=deviceType;
         reData["supplier"]=supplier;
         reData["modeNum"]=modeNum;
         reData["input"]=input;
         reData["output"]=output;
         reData["cryogenType"]=cryogenType;
         reData["charge"]=charge;
         reData["description"]=description;
         
             var url = "";
             
             if(flag == 1){
                 url = "admin/deviceCardUpdateSave";
             }else{
                url = "admin/deviceCardAddSave";
             }
             
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


