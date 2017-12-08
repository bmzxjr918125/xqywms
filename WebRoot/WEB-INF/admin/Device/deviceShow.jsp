<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-设备列表</title>
<style type="text/css">
 .none_input{
   border:none;
 }
 #ui-datepicker-div{
     z-index: 99997 !important;
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
                    <h3>设备列表</h3>
            </div>
            <div class="tableoptions" style="float: right;padding: 1px;border: 0px solid #fff !important;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                               <div>
                                <div class="tableoptions"
                                    style="border: 0px solid #fff !important;float: left;padding: 1px;">
                                    <a class="btn btn_book"   onclick="addSave();" href="javascript:void(0);"><span>添加设备</span></a> 
                                    <a class="btn btn_trash batchOp" href="admin/deviceDelete?ids={values}" title="确认删除这些设备,删除设备后将不能恢复?|删除确认"><span>批量删除</span></a>  
                           </div>
                             </div>      
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="deviceAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">名称</th>
                        <th class="head1">编号</th>
                        <th class="head1">类型</th>
                        <th class="head1">型号</th>
                        <th class="head1">状态</th>
                        <th class="head1">所属项目</th>
                        <th class="head1">所在位置</th>
                        <th class="head1">维修次数</th>
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

    var table = jQuery('#deviceAjaxShow_Table');

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
        {'data':'deviceNo','orderable':false,'className':'center'},
        {'data':'deviceInfo.deviceType','orderable':false,'className':'center'},
        {'data':'deviceInfo.modelNum','orderable':false,'className':'center'},
        {
            'data':'status',
            'render':function(data,type,full){
                return status(data);
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'projectDevice.project.projectName',
            'render':function(data,type,full){
                
                return full.projectDevice == null ? "--" : data;
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':'projectDevice.position',
            'render':function(data,type,full){
                return full.projectDevice == null ? "--" : data;;
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':'projectDevice.count.repairNum',
            'render':function(data,type,full){
                return full.projectDevice == null ? "--" : data;;
            },
            'orderable':true,
            'className':'center'
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
            'data':null,
            'render':function(data,type,full){
                var content="";
               
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+",1)'>编辑</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+",2)'>复制添加</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[10,"desc"]];
    var options = {
           
        };
    oTable = DataTablePack.serverTable(table,'admin/deviceAjaxShow',reqData,columns,order,options);
});

   
   /**
    *新建设备信息
    */
  function addSave(){
            if(entryList == null || entryList.length == 0 || typeof(entryList) == "undefined" ){
                jAlertErrorMsg("请先在系统管理中添加对应设备类型词条");
                return false;
            }
           
            jQuery.axse(
                "admin/deviceGetCardNameAndIdList",
                null,
                "请求发送中...",
                function(data) {
                    if (data.response == "success") {
                        
                        var html = "<p>设备名片：<span class='formwrapper'>"+
                                    "<select id='input_deviceCard' onchange='selecrCard(this);' data-placeholder='请选择设备名片信息' class='chzn-select' style='width:275px;' tabindex='2'>";
                                    html +=  "<option id='op1' value=''></option> ";
                                 var cardList = data.result;
                                    for(var i=0;i<cardList.length;i++){
                                        html +=  "<option value='"+cardList[i].id+"'>"+cardList[i].name+"</option> ";
                                     }     
                                   html += "</select>"+
                                    "</span><span style='color:red;'> *</span></p>";
                           
                                   html += "<div >"+
                                    "<ul id='sortable2' class='sortlist' >"+
                                        "<li>"+
                                            "<div class='label' style='border:none;padding:10px 0px;'>"+
                                                "<span class='moveicon'></span>"+
                                                "<span class='arrowdrop'></span> 设备名片详细"+
                                            "</div>"+
                                            "<div class='details' style='border:none;height:200px;overflow: auto;'>";
                                                  
                                   html += "设备&nbsp;名称：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_deviceName'   placeholder='请输入设备名称'/><span style='color:red;'> *</span><br/>";
                                   html += "设备&nbsp;类型：<select class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceType'>";
                                   for(var i=0;i<entryList.length;i++){
                                       html+="<option value='"+entryList[i].id+"'>"+entryList[i].value+"</option>";
                                   }
                                   html+="</select> <span style='color:red;'> *</span></br>";
                                   html+="设备&nbsp;厂家：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_supplier'   placeholder='请输入设备生成厂家' /><span style='color:red;'> *</span><br/>";
                                   html+="设备&nbsp;型号：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_modeNum'   placeholder='请输入设备型号' /><span style='color:red;'> *</span><br/>";
                                   html+="制冷/输入：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
                                   html+="制热/输出：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
                                   html+="制冷&nbsp;类型：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
                                   html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
                                   html+="设备&nbsp;描述：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_description'   placeholder='请输入设备描述' /><br/>";
                                   
                                          html +=  "</div>"+
                                        "</li>"+
                                    "</ul>"+
                                "</div>";     
                          
                                html+="设备编号：<input class='popup_prompt' id='input_deviceNo'   placeholder='请输入设备唯一指定编号' /><span style='color:red;'> *</span><br/>";
                                html+="出厂日期：<input class='popup_prompt width100 datepicker' id='input_productionDate'   placeholder='请输入设备出厂日期' /><span style='color:red;'> *</span><br/>";
                                html+="购买日期：<input class='popup_prompt width100 ' id='input_buyDate'   placeholder='请输入设备购买日期' /><span style='color:red;'> *</span><br/>";
                                html+="其它描述：<input class='popup_prompt' id='input_otherDesc'   placeholder='设备其它的描述' /><br/>";
                                
                                html+="<span style='color:red;'> * 请保证设备编号的唯一性，添加后将不能修改。</span><br/>";
                                
                        jBmzAlert( html, "添加设备信息", function(r) {
                            if (r) {
                                
                                
                                var deviceCardId = jQuery("#input_deviceCard").val();
                                var deviceNo = jQuery("#input_deviceNo").val();
                                var productionDate = jQuery("#input_productionDate").val();
                                var buyDate = jQuery("#input_buyDate").val();
                                var otherDesc = jQuery("#input_otherDesc").val();
                                
                                
                                if (deviceCardId == null || deviceCardId.trim() == "") {
                                    jAlertErrorMsg("请选择设备名片信息");
                                    return false;
                                } 
                                if (deviceNo == null || deviceNo.trim() == "") {
                                    jAlertErrorMsg("请输入设备唯一编号");
                                    return false;
                                } 
                                if (productionDate == null || productionDate.trim() == "") {
                                    jAlertErrorMsg("请选择设备出厂日期");
                                    return false;
                                } 
                                if (buyDate == null || buyDate.trim() == "") {
                                    jAlertErrorMsg("请选择设备购买日期");
                                    return false;
                                } 
                               
                                
                               
                                var reData={};
                                reData["deviceCardId"]=deviceCardId;
                                reData["deviceNo"]=deviceNo;
                                reData["productionDate"]=productionDate;
                                reData["buyDate"]=buyDate;
                                reData["otherDesc"]=otherDesc;
                               
                                
                                    var url = "admin/deviceAddSave";
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
                        jQuery("#popup_container").css({"min-width":"420px"});
                        jQuery("#popup_content").css({"max-height":"700px"});
                        jQuery(".chzn-select").chosen();
                        ///// SORTABLE ITEM WITH DETAILS /////
                        jQuery('.arrowdrop').click(function(){
                            var t = jQuery(this);
                            var det = t.parents('li').find('.details');
                            if(!det.is(':visible')) {
                                det.slideDown();
                                t.addClass('arrowup');
                            } else {
                                det.slideUp();
                                t.removeClass('arrowup');
                            }
                        });
                        ///// DATE PICKER /////
                        jQuery("#input_productionDate").datepicker({dateFormat:"yy-mm-dd"});
                        jQuery("#input_buyDate").datepicker({dateFormat:"yy-mm-dd"});
                        jQuery("#popup_container").css({
                            "margin-top" : "-80px",
                            "z-index" : "99996"
                        });
                        jQuery("#popup_overlay").css({
                            "z-index" : "99995"
                        });
                       
                    } else {
                        jAlertErrorMsg(data.msg);
                    }
                }, function(data) {
                    jAlertErrorMsg("请求错误");
                });
  }
   
   /**
    *选择设备名片信息
    */
   function selecrCard(obj){
       //alert("选中改变触发");
       var cardId = jQuery(obj).val();
       var url = "admin/deviceGetCardById?id="+cardId;
       jQuery.axse(
           url,
           null,
           "请求发送中...",
           function(data) {
               if (data.response == "success") {
                   jQuery("#input_deviceName").val(data.result.deviceInfo.deviceName);
                   jQuery("#op1").text(data.result.deviceInfo.deviceType);
                   jQuery("#op1").click();
                   jQuery("#input_supplier").val(data.result.deviceInfo.supplier);
                   jQuery("#input_modeNum").val(data.result.deviceInfo.modelNum);
                   jQuery("#input_input").val(data.result.deviceInfo.input);
                   jQuery("#input_output").val(data.result.deviceInfo.output);
                   jQuery("#input_cryogenType").val(data.result.deviceInfo.cryogenType);
                   jQuery("#input_charge").val(data.result.deviceInfo.charge);
                   jQuery("#input_description").val(data.result.deviceInfo.description);
                   if(!jQuery(".arrowdrop").hasClass("arrowup")){
                       jQuery(".arrowdrop").click();
                   }
               } else {
                   jAlertErrorMsg(data.msg);
               }
           }, function(data) {
               jAlertErrorMsg("请求错误");
           });
       
   }

   /**
    *编辑设备信息
    */
  function updateSave(deviceId,obj,flag){
           
            jQuery.axse(
                "admin/deviceGetCardNameAndIdList",
                null,
                "请求发送中...",
                function(data) {
                    if (data.response == "success") {
                        
                        var html = "<p>设备名片：<span class='formwrapper'>"+
                                    "<select id='input_deviceCard' onchange='selecrCard(this);' data-placeholder='请选择设备名片信息' class='chzn-select' style='width:275px;' tabindex='2'>";
                                    html +=  "<option id='op1' value='0'>"+obj.deviceInfo.deviceName+"</option> ";
                                 var cardList = data.result;
                                    for(var i=0;i<cardList.length;i++){
                                        html +=  "<option value='"+cardList[i].id+"'>"+cardList[i].name+"</option> ";
                                     }     
                                   html += "</select>"+
                                    "</span><span style='color:red;'> *</span></p>";
                           
                                   html += "<div >"+
                                    "<ul id='sortable2' class='sortlist' >"+
                                        "<li>"+
                                            "<div class='label' style='border:none;padding:10px 0px;'>"+
                                                "<span class='moveicon'></span>"+
                                                "<span class='arrowdrop'></span> 设备名片详细"+
                                            "</div>"+
                                            "<div class='details' style='border:none;height:200px;overflow: auto;'>";
                                                  
                                   html += "设备&nbsp;名称：<input value='"+obj.deviceInfo.deviceName+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_deviceName'   placeholder='请输入设备名称'/><span style='color:red;'> *</span><br/>";
                                   html += "设备&nbsp;类型：<select class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceType'>";
                                   html+="<option value='0'>"+obj.deviceInfo.deviceType+"</option>";
                                   for(var i=0;i<entryList.length;i++){
                                       html+="<option value='"+entryList[i].id+"'>"+entryList[i].value+"</option>";
                                   }
                                   html+="</select> <span style='color:red;'> *</span></br>";
                                   html+="设备&nbsp;厂家：<input value='"+obj.deviceInfo.supplier+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_supplier'   placeholder='请输入设备生成厂家' /><span style='color:red;'> *</span><br/>";
                                   html+="设备&nbsp;型号：<input value='"+obj.deviceInfo.modelNum+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_modeNum'   placeholder='请输入设备型号' /><span style='color:red;'> *</span><br/>";
                                   html+="制冷/输入：<input value='"+obj.deviceInfo.input+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
                                   html+="制热/输出：<input value='"+obj.deviceInfo.output+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
                                   html+="制冷&nbsp;类型：<input value='"+obj.deviceInfo.cryogenType+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
                                   html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input value='"+obj.deviceInfo.charge+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
                                   html+="设备&nbsp;描述：<input value='"+obj.deviceInfo.description+"' class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_description'   placeholder='请输入设备描述' /><br/>";
                                   
                                          html +=  "</div>"+
                                        "</li>"+
                                    "</ul>"+
                                "</div>";     
                          if(flag == 1){
                              html+="设备编号：<input value='"+obj.deviceNo+"' class='popup_prompt none_input' id='input_deviceNo' onfocus=this.blur() readonly=true  placeholder='请输入设备唯一指定编号' /><span style='color:red;'> *</span><br/>";
                          }else{
                              html+="设备编号：<input value='"+obj.deviceNo+"' class='popup_prompt' id='input_deviceNo'  placeholder='请输入设备唯一指定编号' /><span style='color:red;'> *</span><br/>";
                          }
                               
                                html+="出厂日期：<input value='"+(obj.productionDate != null ? obj.productionDate.replace(/\T/g,' ') : "")+"' class='popup_prompt width100 datepicker' id='input_productionDate'   placeholder='请输入设备出厂日期' /><span style='color:red;'> *</span><br/>";
                                html+="购买日期：<input value='"+(obj.buyDate != null ? obj.buyDate.replace(/\T/g,' ') : "")+"' class='popup_prompt width100 ' id='input_buyDate'   placeholder='请输入设备购买日期' /><span style='color:red;'> *</span><br/>";
                                html+="其它描述：<input value='"+obj.otherDesc+"' class='popup_prompt' id='input_otherDesc'   placeholder='设备其它的描述' /><br/>";
                                if(flag == 1){
                                    html+="<span style='color:red;'> * 设备编码唯一且不能进行修改。</span><br/>";
                                }else{
                                    html+="<span style='color:red;'> * 请保证设备编号的唯一性，添加后将不能修改。</span><br/>";
                                }
                               
                                
                        jBmzAlert( html, "修改设备信息", function(r) {
                            if (r) {
                                
                                
                                var deviceCardId = jQuery("#input_deviceCard").val();
                                var deviceNo = jQuery("#input_deviceNo").val();
                                var productionDate = jQuery("#input_productionDate").val();
                                var buyDate = jQuery("#input_buyDate").val();
                                var otherDesc = jQuery("#input_otherDesc").val();
                                
                                
                                if (deviceCardId == null || deviceCardId.trim() == "") {
                                    jAlertErrorMsg("请选择设备名片信息");
                                    return false;
                                } 
                                if (deviceNo == null || deviceNo.trim() == "") {
                                    jAlertErrorMsg("请输入设备唯一编号");
                                    return false;
                                } 
                                if (productionDate == null || productionDate.trim() == "") {
                                    jAlertErrorMsg("请选择设备出厂日期");
                                    return false;
                                } 
                                if (buyDate == null || buyDate.trim() == "") {
                                    jAlertErrorMsg("请选择设备购买日期");
                                    return false;
                                } 
                               
                                
                               
                                var reData={};
                                reData["flag"]=flag;
                                reData["deviceId"]=deviceId;
                                reData["deviceCardId"]=deviceCardId;
                                reData["deviceNo"]=deviceNo;
                                reData["productionDate"]=productionDate;
                                reData["buyDate"]=buyDate;
                                reData["otherDesc"]=otherDesc;
                                var url;
                                   url = "admin/deviceUpdateSave";
                                    
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
                        jQuery("#popup_container").css({"min-width":"420px"});
                        jQuery("#popup_content").css({"max-height":"700px"});
                        jQuery(".chzn-select").chosen();
                        ///// SORTABLE ITEM WITH DETAILS /////
                        jQuery('.arrowdrop').click(function(){
                            var t = jQuery(this);
                            var det = t.parents('li').find('.details');
                            if(!det.is(':visible')) {
                                det.slideDown();
                                t.addClass('arrowup');
                            } else {
                                det.slideUp();
                                t.removeClass('arrowup');
                            }
                        });
                        ///// DATE PICKER /////
                        jQuery("#input_productionDate").datepicker({dateFormat:"yy-mm-dd"});
                        jQuery("#input_buyDate").datepicker({dateFormat:"yy-mm-dd"});
                        jQuery("#popup_container").css({
                            "margin-top" : "-80px",
                            "z-index" : "99996"
                        });
                        jQuery("#popup_overlay").css({
                            "z-index" : "99995"
                        });
                       
                    } else {
                        jAlertErrorMsg(data.msg);
                    }
                }, function(data) {
                    jAlertErrorMsg("请求错误");
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


