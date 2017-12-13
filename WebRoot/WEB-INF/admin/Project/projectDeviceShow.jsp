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
                    <h3>项目设备列表</h3>
            </div>
            <div class="tableoptions" style="float: right;padding: 1px;border: 0px solid #fff !important;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                               <div>
                                <div class="tableoptions"
                                    style="border: 0px solid #fff !important;float: left;padding: 1px;">
                                    <a class="btn btn_book"   onclick="addSave();" href="javascript:void(0);"><span>加入设备</span></a> 
                                    <a class="btn btn_trash batchOp" href="admin/projectDeviceDelete?ids={values}" title="确认移除这些设备,若设备已产生巡检或维修记录将不能移除?|移除确认"><span>批量移除</span></a>  
                           </div>
                             </div>      
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="projectDeviceAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1">名称</th>
                        <th class="head1">编号</th>
                        <th class="head1">类型</th>
                        <th class="head1">型号</th>
                        <th class="head1">状态</th>
                        <th class="head1">所在位置</th>
                        <th class="head1">维修次数</th>
                        <th class="head1">巡检次数</th>
                        <th class="head1">巡检项</th>
                        <th class="head1">最近巡检</th>
                        <th class="head1">加入日期</th>
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
var projectId ='${projectId}' ;
var entryJa =eval('(' + '${entryTypeJa}' + ')');
var projectCheckType ='${project.checkType}' ;
jQuery(document).ready(function(){

    var table = jQuery('#projectDeviceAjaxShow_Table');

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
        {'data':'device.deviceInfo.deviceName','orderable':false,'className':'center'},
        {'data':'device.deviceNo','orderable':false,'className':'center'},
        {'data':'device.deviceInfo.deviceType','orderable':false,'className':'center'},
        {'data':'device.deviceInfo.modelNum','orderable':false,'className':'center'},
        {
            'data':'device.status',
            'render':function(data,type,full){
                return status(data);
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'position',
            'render':function(data,type,full){
                return  data;
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':'repairNum',
            'render':function(data,type,full){
                return full.count.repairNum;
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'checkNum',
            'render':function(data,type,full){
                return full.count.checkNum;
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'checkEntryJa',
            'render':function(data,type,full){
                return "<a class='click-a' href='javascript:void(0);' onclick='lookCheckEntry("+JSON.stringify(full.checkEntryJa)+",1)'>"+checkType(projectCheckType)+"</a>";
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'lastCheckDate',
            'render':function(data,type,full){
                return full.count.lastCheckDate != null ? full.count.lastCheckDate.replace(/\T/g,' ') : "-.-.-";
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
               
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+")'>编辑</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='admin/projectDeviceCheckShow?projectDeviceId="+full.id+"'>巡检记录</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='lookRepairDetails("+full.id+","+JSON.stringify(full)+")'>维修记录</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[10,"desc"]];
    var options = {
        };
    oTable = DataTablePack.serverTable(table,'admin/projectDeviceAjaxShow?projectId='+projectId,reqData,columns,order,options);
});
   /**
    *加入设备信息
    */
  function addSave(){
            jQuery.axse(
                "admin/deviceGetDeviceNameAndIdList",
                null,
                "请求发送中...",
                function(data) {
                    if (data.response == "success") {
                        
                        var html = "<p>设备选择：<span class='formwrapper'>"+
                                    "<select id='input_device' onchange='selecrDevice(this);' data-placeholder='请选择设备信息' class='chzn-select' style='width:275px;' tabindex='2'>";
                                    html +=  "<option id='op1' value=''></option> ";
                                 var cardList = data.result;
                                    for(var i=0;i<cardList.length;i++){
                                        html +=  "<option value='"+cardList[i].id+"'>"+cardList[i].name+"</option> ";
                                     }     
                                   html += "</select>"+
                                    "</span><span style='color:red;'> *</span></p>";
                           
                                   html += "<div>"+
                                    "<ul id='sortable2' class='sortlist' >"+
                                        "<li>"+
                                            "<div class='label' style='border:none;padding:10px 0px;'>"+
                                                "<span class='moveicon'></span>"+
                                                "<span class='arrowdrop'></span> 设备名片详细"+
                                            "</div>"+
                                            "<div class='details' style='border:none;height:200px;overflow: auto;'>";
                                                  
                                   html += "设备&nbsp;名称：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_deviceName'   placeholder='请输入设备名称'/><br/>";
                                   html += "设备&nbsp;编号：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceNo'   placeholder='请输入设备唯一指定编号' /></span><br/>";
                                   html += "设备&nbsp;类型：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceType'   placeholder='请输入设备类型' /></span><br/>";
                                   html+="设备&nbsp;厂家：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_supplier'   placeholder='请输入设备生成厂家' /><br/>";
                                   html+="设备&nbsp;型号：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_modeNum'   placeholder='请输入设备型号' /><br/>";
                                   html+="制冷/输入：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
                                   html+="制热/输出：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
                                   html+="制冷&nbsp;类型：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
                                   html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
                                   html+="设备&nbsp;描述：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_description'   placeholder='请输入设备描述' /><br/>";
                                  
                                          html +=  "</div>"+
                                        "</li>"+
                                    "</ul>"+
                                "</div>";     
                                
                                html += "<p><label>巡检词条：</label>"+
                                            "<span id='dualselect' style='margin-left:2px;' class='dualselect'>"+
                                                "<select class='uniformselect' style='width:43%' name='select3' multiple='multiple' size='10'>";
                                                    
                                                for(var k=0;k<entryJa.length;k++){
                                                    html += "<option value='"+entryJa[k].id+"' style='padding:2px;' title='"+entryJa[k].value+"'>"+">."+entryJa[k].value+"</option>";
                                                }
                                                
                                               
                               html += "</select>"+
                                                "<span class='ds_arrow'>"+
                                                    "<span class='arrow ds_prev'>&laquo;</span>"+
                                                    "<span class='arrow ds_next'>&raquo;</span>"+
                                                "</span>"+
                                                "<select name='select4' id='input_entry' style='width:43%' multiple='multiple' size='10'>"+
                                                    "<option value=''></option>"+
                                                "</select>"+
                                            "</span></p>";
                                
                                            html+="所在位置：<input class='popup_prompt' id='input_position'    placeholder='设备所在位置' /><span style='color:red;'> *</span><br/>";
                                            html+="安装人员：<input class='popup_prompt' id='input_installWorker'   placeholder='设备安装人员' /><br/>";
                                html+="<span style='color:red;'> * 请选择巡检词条，保证巡检人员勾选使用，无巡检项目可忽略此项。</span><br/>";
                                
                        jBmzAlert( html, "加入设备信息", function(r) {
                            if (r) {
                                
                                
                                var deviceId = jQuery("#input_device").val();
                                var position = jQuery("#input_position").val();
                                var installWorker = jQuery("#input_installWorker").val();
                                var entryIds = jQuery("#input_entry option").map(function(){return jQuery(this).val();}).get().join(",");
                               
                                if (deviceId == null || deviceId.trim() == "") {
                                    jAlertErrorMsg("请选择加入设备信息");
                                    return false;
                                } 
                                if (projectCheckType != "NONE" && (entryIds == null || entryIds.trim() == "")) {
                                    jAlertErrorMsg("当前项目为'"+checkType(projectCheckType)+"',请选择巡检词条。");
                                    return false;
                                } 
                                if (position == null || position.trim() == "") {
                                    jAlertErrorMsg("请输入设备所在位置");
                                    return false;
                                } 
                               
                                
                               
                                var reData={};
                                reData["projectId"]=projectId;
                                reData["deviceId"]=deviceId;
                                reData["entryIds"]=entryIds;
                                reData["position"]=position;
                                reData["installWorker"]=installWorker;
                                    var url = "admin/projectDeviceAddSave";
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
                        jQuery("#popup_container").css({"min-width":"600px"});
                        jQuery("#popup_content").css({"max-height":"900px"});
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
                        jQuery("#popup_container").css({
                            "margin-top" : "-40px",
                            "z-index" : "99996"
                        });
                        jQuery("#popup_overlay").css({
                            "z-index" : "99995"
                        });
                        ///// DUAL BOX /////
                        var db = jQuery('#dualselect').find('.ds_arrow .arrow');    //get arrows of dual select
                        var sel1 = jQuery('#dualselect select:first-child');        //get first select element
                        var sel2 = jQuery('#dualselect select:last-child');         //get second select element
                        
                        sel2.empty(); //empty it first from dom.
                        
                        db.click(function(){
                            var t = (jQuery(this).hasClass('ds_prev'))? 0 : 1;  // 0 if arrow prev otherwise arrow next
                            if(t) {
                                sel1.find('option').each(function(){
                                    if(jQuery(this).is(':selected')) {
                                        jQuery(this).attr('selected',false);
                                        var op = sel2.find('option:first-child');
                                        sel2.append(jQuery(this));
                                    }
                                }); 
                            } else {
                                sel2.find('option').each(function(){
                                    if(jQuery(this).is(':selected')) {
                                        jQuery(this).attr('selected',false);
                                        sel1.append(jQuery(this));
                                    }
                                });     
                            }
                        });
                    } else {
                        jAlertErrorMsg(data.msg);
                    }
                }, function(data) {
                    jAlertErrorMsg("请求错误");
                });
  }
   
  /**
   *编辑已加入设备信息
   */
 function updateSave(projectDeviceId,obj){
      
      var entryCheckJa = eval('(' + obj.checkEntryJa+ ')');
           jQuery.axse(
               "admin/deviceGetDeviceNameAndIdList",
               null,
               "请求发送中...",
               function(data) {
                   if (data.response == "success") {
                       
                       var html = "<p>设备选择：<span class='formwrapper'>"+
                                   "<select id='input_device' onchange='selecrDevice(this);' data-placeholder='请选择设备信息' class='chzn-select' style='width:275px;' tabindex='2'>";
                                   html +=  "<option id='op1' value=''></option> ";
                                var cardList = data.result;
                                
                                   html +=  "<option value='"+obj.device.id+"'>"+obj.device.deviceInfo.deviceName+"</option> ";
                                   for(var i=0;i<cardList.length;i++){
                                       html +=  "<option value='"+cardList[i].id+"'>"+cardList[i].name+"</option> ";
                                    }     
                                  html += "</select>"+
                                   "</span><span style='color:red;'> *</span></p>";
                          
                                  html += "<div>"+
                                   "<ul id='sortable2' class='sortlist' >"+
                                       "<li>"+
                                           "<div class='label' style='border:none;padding:10px 0px;'>"+
                                               "<span class='moveicon'></span>"+
                                               "<span class='arrowdrop'></span> 设备名片详细"+
                                           "</div>"+
                                           "<div class='details' style='border:none;height:200px;overflow: auto;'>";
                                                 
                                  html += "设备&nbsp;名称：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_deviceName'   placeholder='请输入设备名称'/><br/>";
                                  html += "设备&nbsp;编号：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceNo'   placeholder='请输入设备唯一指定编号' /></span><br/>";
                                  html += "设备&nbsp;类型：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_deviceType'   placeholder='请输入设备类型' /></span><br/>";
                                  html+="设备&nbsp;厂家：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_supplier'   placeholder='请输入设备生成厂家' /><br/>";
                                  html+="设备&nbsp;型号：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_modeNum'   placeholder='请输入设备型号' /><br/>";
                                  html+="制冷/输入：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_input'   placeholder='请输入设备制冷量/输入功率' /><br/>";
                                  html+="制热/输出：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_output'   placeholder='请输入设备制热量/输出功率' /><br/>";
                                  html+="制冷&nbsp;类型：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_cryogenType'   placeholder='请输入设备制冷剂类型' /><br/>";
                                  html+="充&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;量：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true  id='input_charge'   placeholder='请输入设备制冷剂充注量' /><br/>";
                                  html+="设备&nbsp;描述：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_description'   placeholder='请输入设备描述' /><br/>";
                                 
                                         html +=  "</div>"+
                                       "</li>"+
                                   "</ul>"+
                               "</div>";     
                               
                               html += "<p><label>巡检词条：</label>"+
                                           "<span id='dualselect' style='margin-left:2px;' class='dualselect'>"+
                                               "<select class='uniformselect' style='width:43%' name='select3' multiple='multiple' size='10'>";
                                                   
                                               for(var k=0;k<entryJa.length;k++){
                                                   var flag = true;
                                                   for(var j=0;j<entryCheckJa.length;j++){
                                                      if(entryJa[k].id == entryCheckJa[j].id){
                                                          flag = false;
                                                          break;
                                                       }
                                                       
                                                   }
                                                   if(flag){
                                                       html += "<option value='"+entryJa[k].id+"' style='padding:2px;' title='"+entryJa[k].value+"'>"+">."+entryJa[k].value+"</option>";
                                                   }
                                               }
                                               
                                              
                              html += "</select>"+
                                               "<span class='ds_arrow'>"+
                                                   "<span class='arrow ds_prev'>&laquo;</span>"+
                                                   "<span class='arrow ds_next'>&raquo;</span>"+
                                               "</span>"+
                                               "<select name='select4' id='input_entry' style='width:43%' multiple='multiple' size='10'>";
                                               for(var k=0;k<entryCheckJa.length;k++){
                                                   html += "<option value='"+entryCheckJa[k].id+"' style='padding:2px;' title='"+entryCheckJa[k].name+"'>"+entryCheckJa[k].name+"</option>";
                                               }
                                               
                                               html += "</select>"+
                                           "</span></p>";
                               
                                           html+="所在位置：<input class='popup_prompt' id='input_position'  value='"+obj.position+"'  placeholder='设备所在位置' /><span style='color:red;'> *</span><br/>";
                                           html+="安装人员：<input class='popup_prompt' id='input_installWorker' value='"+obj.installWorker+"'   placeholder='设备安装人员' /><br/>";
                               html+="<span style='color:red;'> * 请选择巡检词条，保证巡检人员勾选使用，无巡检项目可忽略此项。</span><br/>";
                               
                       jBmzAlert( html, "编辑设备信息", function(r) {
                           if (r) {
                               
                               
                               var deviceId = jQuery("#input_device").val();
                               var position = jQuery("#input_position").val();
                               var installWorker = jQuery("#input_installWorker").val();
                               var entryIds = jQuery("#input_entry option").map(function(){return jQuery(this).val();}).get().join(",");
                              
                               if (deviceId == null || deviceId.trim() == "") {
                                   jAlertErrorMsg("请选择加入设备信息");
                                   return false;
                               } 
                               if (projectCheckType != "NONE" && (entryIds == null || entryIds.trim() == "")) {
                                   jAlertErrorMsg("当前项目为'"+checkType(projectCheckType)+"',请选择巡检词条。");
                                   return false;
                               } 
                               if (position == null || position.trim() == "") {
                                   jAlertErrorMsg("请输入设备所在位置");
                                   return false;
                               } 
                              
                               var reData={};
                               reData["projectDeviceId"]=projectDeviceId;
                               reData["deviceId"]=deviceId;
                               reData["entryIds"]=entryIds;
                               reData["position"]=position;
                               reData["installWorker"]=installWorker;
                                   var url = "admin/projectDeviceUpdateSave";
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
                       
                       jQuery("#input_device").val(obj.device.id);
                       selecrDevice(jQuery("#input_device"));
                       jQuery("#popup_container").css({"min-width":"600px"});
                       jQuery("#popup_content").css({"max-height":"900px"});
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
                       jQuery("#popup_container").css({
                           "margin-top" : "-60px",
                           "z-index" : "99996"
                       });
                       jQuery("#popup_overlay").css({
                           "z-index" : "99995"
                       });
                       ///// DUAL BOX /////
                       var db = jQuery('#dualselect').find('.ds_arrow .arrow');    //get arrows of dual select
                       var sel1 = jQuery('#dualselect select:first-child');        //get first select element
                       var sel2 = jQuery('#dualselect select:last-child');         //get second select element
                       
                       //sel2.empty(); //empty it first from dom.
                       
                       db.click(function(){
                           var t = (jQuery(this).hasClass('ds_prev'))? 0 : 1;  // 0 if arrow prev otherwise arrow next
                           if(t) {
                               sel1.find('option').each(function(){
                                   if(jQuery(this).is(':selected')) {
                                       jQuery(this).attr('selected',false);
                                       var op = sel2.find('option:first-child');
                                       sel2.append(jQuery(this));
                                   }
                               }); 
                           } else {
                               sel2.find('option').each(function(){
                                   if(jQuery(this).is(':selected')) {
                                       jQuery(this).attr('selected',false);
                                       sel1.append(jQuery(this));
                                   }
                               });     
                           }
                       });
                       
                   } else {
                       jAlertErrorMsg(data.msg);
                   }
               }, function(data) {
                   jAlertErrorMsg("请求错误");
               });
 }
  
   /**
    *选择设备信息
    */
   function selecrDevice(obj){
       //alert("选中改变触发");
       var deviceId = jQuery(obj).val();
       var url = "admin/deviceGetDeviceById?id="+deviceId;
       jQuery.axse(
           url,
           null,
           "请求发送中...",
           function(data) {
               if (data.response == "success") {
                   jQuery("#input_deviceName").val(data.result.deviceInfo.deviceName);
                   jQuery("#input_deviceType").val(data.result.deviceInfo.deviceType);
                   jQuery("#input_supplier").val(data.result.deviceInfo.supplier);
                   jQuery("#input_modeNum").val(data.result.deviceInfo.modelNum);
                   jQuery("#input_input").val(data.result.deviceInfo.input);
                   jQuery("#input_output").val(data.result.deviceInfo.output);
                   jQuery("#input_cryogenType").val(data.result.deviceInfo.cryogenType);
                   jQuery("#input_charge").val(data.result.deviceInfo.charge);
                   jQuery("#input_description").val(data.result.deviceInfo.description);
                   jQuery("#input_deviceNo").val(data.result.deviceNo);
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


   
   
   
</script>


