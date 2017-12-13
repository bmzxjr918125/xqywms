<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>雪球能源运维后台管理系统-项目列表</title>
<style type="text/css">
 .none_input{
   border:none;
 }
 #ui-datepicker-div{
     z-index: 99997 !important;
    }
.address{
   width: 90px !important;
}   
.width150{
    width: 150px;
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
                    <h3>项目列表</h3>
            </div>
            <div class="tableoptions" style="float: right;padding: 1px;border: 0px solid #fff !important;">
                 <div style="display: inline-block;">
                            <form class="stdform stdform2">
                               <div>
                                <div class="tableoptions"
                                    style="border: 0px solid #fff !important;float: left;padding: 1px;">
                                    <a class="btn btn_book"   onclick="addSave();" href="javascript:void(0);"><span>添加项目</span></a> 
                                    <a class="btn btn_trash batchOp" href="admin/projectDelete?ids={values}" title="确认删除这些项目,删除项目后将不能恢复,且会删除项目相关所有数据,</br>若已发生添加<span style='color:red;'>下属负责人 添加设备 添加团队</span>操作则不能进行删除操作?|项目删除确认"><span>批量删除</span></a>  
                           </div>
                             </div>      
                            </form>
                        </div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="projectAjaxShow_Table">
                <thead>
                    <tr>
                        <th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
                        <th class="head1" style="width: 8%;">项目名称</th>
                        <th class="head1" style="width: 8%;">编号</th>
                        <th class="head1" style="width: 4%;">类型</th>
                        <th class="head1" style="width: 10%;">地址</th>
                        <th class="head1" style="width: 8%;">负责人</th>
                        <th class="head1" style="width: 6%;">收费维保</th>
                        <th class="head1" style="width: 8%;">维保开始</th>
                        <th class="head1" style="width: 8%;">维保截止</th>
                        <th class="head1" style="width: 8%;">巡检周期</th>
                        <th class="head1" style="width: 10%;">项目概况</th>
                        <th class="head1" style="width: 8%;">创建时间</th>
                        <th class="head1" style="width: 10%;">操作</th>
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

    var table = jQuery('#projectAjaxShow_Table');

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
        {'data':'projectName','orderable':false,'className':'center'},
        {'data':'projectNo','orderable':false,'className':'center'},
        {
            'data':'projectType',
            'render':function(data,type,full){
                return projectType(data);
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':null,
            'render':function(data,type,full){
                return full.address.province + " "+
                       full.address.city + " "+
                       full.address.area + " "+
                       full.address.address + " ";
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':null,
            'render':function(data,type,full){
                return full.user.nickName + "</br>("+full.user.phoneNumber+")";
            },
            'orderable':false,
            'className':'center'
        },
        {
            'data':'isChargeRepair',
            'render':function(data,type,full){
                return boolean(data);
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'repairDateStart',
            'render':function(data,type,full){
                return data != null ? data.replace(/\T/g,' ') : "-.-.-";
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'repairDateEnd',
            'render':function(data,type,full){
                return data != null ? data.replace(/\T/g,' ') : "-.-.-";
            },
            'orderable':true,
            'className':'center'
        },
        {
            'data':'checkType',
            'render':function(data,type,full){
                return checkType(data) ;
            },
            'orderable':true,
            'className':'center'
        },
        {'data':'description','orderable':false,'className':'center'},
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
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='admin/projectDeviceShow?projectId="+full.id+"' >设备</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+",2)'>能源</a></br></br>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='admin/projectWorkerShow?projectId="+full.id+"'>团队</a>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+",2)'>报表</a></br></br>";
                content+="&nbsp;&nbsp;<a class='stdbtn' style='font-size: 12px;' href='javascript:void(0);' onclick='updateSave("+full.id+","+JSON.stringify(full)+")'>编辑</a>";
                return content;
            },
            'orderable':false,
            'className':'center'
        }
    ];

    var order = [[11,"desc"]];
    var options = {
        };
    oTable = DataTablePack.serverTable(table,'admin/projectAjaxShow',reqData,columns,order,options);
});

   
   
   
   /**
    *新建项目信息
    */
  function addSave(){
       
      jQuery.axse(
              "common/commonGetProvinceList",
              null,
              "请求发送中...",
              function(data) {
                  if (data.response == "success") {
                      
                      var html = "";
                      html += "项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：<select class='popup_prompt' id='input_projectType'>";
                      html+="<option value='1'>个人项目</option>";
                      html+="<option value='2'>工程项目</option>";
                      html+="</select> <span style='color:red;'> *</span></br>";
                      
                      html+="项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称：<input class='popup_prompt' id='input_projectName'   placeholder='请输入项目名称' /><span style='color:red;'> *</span><br/>";
                      
                      html += "收费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维保：<select class='popup_prompt' id='input_isChargeRepair'>";
                      html+="<option value='1'>是</option>";
                      html+="<option value='0'>否</option>";
                      html+="</select> <span style='color:red;'> *</span></br>";
                      html+="维保开始时间：<input class='popup_prompt width150' id='input_repairDateStart'   placeholder='请选择项目维保开始日期' /><span style='color:red;'> *</span><br/>";
                      html+="维保结束时间：<input class='popup_prompt width150' id='input_repairDateEnd'   placeholder='请选择项目维保结束日期' /><span style='color:red;'> *</span><br/>";
                      html+="甲方联系电话：<input class='popup_prompt' id='input_userPhoneNumber'   placeholder='请输入项目甲方负责人联系电话' /><span style='color:red;'> *</span><br/>";
                      
                      html += "巡检&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周期：<select class='popup_prompt' id='input_checkType'>";
                      html+="<option value='1'>无巡检</option>";
                      html+="<option value='2'>日巡检</option>";
                      html+="<option value='3'>周巡检</option>";
                      html+="<option value='4'>月巡检</option>";
                      html+="<option value='5'>季度巡检</option>";
                      html+="</select> <span style='color:red;'> *</span></br>";
                      var provinceList = data.result;
                      
                      html += "项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址："+province(provinceList);
                      html += "<span style='color:red;'> *</span></br>";
                      html+="详细&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：<input class='popup_prompt' id='input_address'   placeholder='请输入项目详细地址' /><span style='color:red;'> *</span><br/>";
                      
                      html+="项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;概况：<input class='popup_prompt' id='input_description'   placeholder='请输入项目概况描述' /><span style='color:red;'> *</span><br/>";
                   
                   html+="<span style='color:red;'> * 请保甲方联系电话为正确甲方人员所有手机号，新建项目后</br>该手机号将自动注册用户账号，默认密码为手机号后六位。</span><br/>";
                   
           jBmzAlert( html, "添加项目基本信息", function(r) {
               if (r) {
                   var projectType = jQuery("#input_projectType").val();
                   var projectName = jQuery("#input_projectName").val();
                   var isChargeRepair = jQuery("#input_isChargeRepair").val();
                   var repairDateStart = jQuery("#input_repairDateStart").val();
                   var repairDateEnd = jQuery("#input_repairDateEnd").val();
                   var userPhoneNumber = jQuery("#input_userPhoneNumber").val();
                   var checkType = jQuery("#input_checkType").val();
                   var address = jQuery("#input_address").val();
                   var description = jQuery("#input_description").val();
                   var provinceId = jQuery("#input_province").val();
                   var cityId = jQuery("#input_city").val();
                   var areaId = jQuery("#input_area").val();
                   
                   
                   if (projectType == null || projectType.trim() == "") {
                       jAlertErrorMsg("请选择项目类型");
                       return false;
                   } 
                   if (projectName == null || projectName.trim() == "") {
                       jAlertErrorMsg("请输入项目名称");
                       return false;
                   } 
                   if (isChargeRepair == null || isChargeRepair.trim() == "") {
                       jAlertErrorMsg("请选择项目是否是收费维保");
                       return false;
                   } 
                   if (repairDateStart == null || repairDateStart.trim() == "") {
                       jAlertErrorMsg("请选择项目维保开始日期");
                       return false;
                   } 
                   if (repairDateEnd == null || repairDateEnd.trim() == "") {
                       jAlertErrorMsg("请选择项目维保结束日期");
                       return false;
                   } 
                   if (userPhoneNumber == null || userPhoneNumber.trim() == "") {
                       jAlertErrorMsg("请输入项目甲方负责人联系电话");
                       return false;
                   } 
                   if (provinceId == null || provinceId.trim() == "" || provinceId == 0) {
                       jAlertErrorMsg("请选择项目所在省");
                       return false;
                   } 
                   if (cityId == null || provinceId.trim() == "" || provinceId == 0) {
                       jAlertErrorMsg("请选择项目所在市");
                       return false;
                   } 
                   if (areaId == null || areaId.trim() == "" || areaId == 0) {
                       jAlertErrorMsg("请选择项目所在区域");
                       return false;
                   } 
                   if (address == null || address.trim() == "") {
                       jAlertErrorMsg("请输入项目详细地址");
                       return false;
                   } 
                   if (description == null || description.trim() == "") {
                       jAlertErrorMsg("请输入项目概况描述");
                       return false;
                   } 
                  
                   var reData={};
                   reData["projectType"]=projectType;
                   reData["projectName"]=projectName;
                   reData["isChargeRepair"]=isChargeRepair;
                   reData["repairDateStart"]=repairDateStart;
                   reData["repairDateEnd"]=repairDateEnd;
                   reData["userPhoneNumber"]=userPhoneNumber;
                   reData["checkType"]=checkType;
                   reData["address"]=address;
                   reData["description"]=description;
                   reData["provinceId"]=provinceId;
                   reData["cityId"]=cityId;
                   reData["areaId"]=areaId;
                  
                   
                       var url = "admin/projectAddSave";
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
               jQuery("#popup_container").css({"min-width":"400px"});
               jQuery("#popup_content").css({"max-height":"700px"});
               jQuery(".chzn-select").chosen();
               ///// DATE PICKER /////
               jQuery("#input_repairDateStart").datepicker({dateFormat:"yy-mm-dd"});
               jQuery("#input_repairDateEnd").datepicker({dateFormat:"yy-mm-dd"});
               jQuery("#popup_container").css({
                   "margin-top" : "-20px",
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
   *编辑项目信息
   */
 function updateSave(projectId,obj){
      
     jQuery.axse(
             "common/commonGetProvinceList",
             null,
             "请求发送中...",
             function(data) {
                 if (data.response == "success") {
                     
                     var html = "";
                     html += "项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：<select class='popup_prompt' id='input_projectType'>";
                     html+="<option value='1'>个人项目</option>";
                     html+="<option value='2'>工程项目</option>";
                     html+="</select> <span style='color:red;'> *</span></br>";
                     
                     html+="项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称：<input class='popup_prompt' id='input_projectName'   placeholder='请输入项目名称' /><span style='color:red;'> *</span><br/>";
                     
                     html += "收费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维保：<select class='popup_prompt' id='input_isChargeRepair'>";
                     html+="<option value='1'>是</option>";
                     html+="<option value='0'>否</option>";
                     html+="</select> <span style='color:red;'> *</span></br>";
                     html+="维保开始时间：<input class='popup_prompt width150' id='input_repairDateStart'   placeholder='请选择项目维保开始日期' /><span style='color:red;'> *</span><br/>";
                     html+="维保结束时间：<input class='popup_prompt width150' id='input_repairDateEnd'   placeholder='请选择项目维保结束日期' /><span style='color:red;'> *</span><br/>";
                     html+="甲方联系电话：<input class='popup_prompt none_input' onfocus=this.blur() readonly=true id='input_userPhoneNumber'   placeholder='请输入项目甲方负责人联系电话' /><br/>";
                     
                     html += "巡检&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周期：<select class='popup_prompt' id='input_checkType'>";
                     html+="<option value='1'>无巡检</option>";
                     html+="<option value='2'>日巡检</option>";
                     html+="<option value='3'>周巡检</option>";
                     html+="<option value='4'>月巡检</option>";
                     html+="<option value='5'>季度巡检</option>";
                     html+="</select> <span style='color:red;'> *</span></br>";
                     var provinceList = data.result;
                     
                     html += "项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址："+province(provinceList);
                     html += "<span style='color:red;'> *</span></br>";
                     html+="详细&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：<input class='popup_prompt' id='input_address'   placeholder='请输入项目详细地址' /><span style='color:red;'> *</span><br/>";
                     
                     html+="项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;概况：<input class='popup_prompt' id='input_description'   placeholder='请输入项目概况描述' /><span style='color:red;'> *</span><br/>";
                  
                  html+="<span style='color:red;'> * 甲方联系电话新建后不能再进行修改操作。</span><br/>";
                  
          jBmzAlert( html, "修改项目基本信息", function(r) {
              if (r) {
                  var projectType = jQuery("#input_projectType").val();
                  var projectName = jQuery("#input_projectName").val();
                  var isChargeRepair = jQuery("#input_isChargeRepair").val();
                  var repairDateStart = jQuery("#input_repairDateStart").val();
                  var repairDateEnd = jQuery("#input_repairDateEnd").val();
                  var checkType = jQuery("#input_checkType").val();
                  var address = jQuery("#input_address").val();
                  var description = jQuery("#input_description").val();
                  var provinceId = jQuery("#input_province").val();
                  var cityId = jQuery("#input_city").val();
                  var areaId = jQuery("#input_area").val();
                  
                  
                  if (projectType == null || projectType.trim() == "") {
                      jAlertErrorMsg("请选择项目类型");
                      return false;
                  } 
                  if (projectName == null || projectName.trim() == "") {
                      jAlertErrorMsg("请输入项目名称");
                      return false;
                  } 
                  if (isChargeRepair == null || isChargeRepair.trim() == "") {
                      jAlertErrorMsg("请选择项目是否是收费维保");
                      return false;
                  } 
                  if (repairDateStart == null || repairDateStart.trim() == "") {
                      jAlertErrorMsg("请选择项目维保开始日期");
                      return false;
                  } 
                  if (repairDateEnd == null || repairDateEnd.trim() == "") {
                      jAlertErrorMsg("请选择项目维保结束日期");
                      return false;
                  } 
                  
                 
                  if (address == null || address.trim() == "") {
                      jAlertErrorMsg("请输入项目详细地址");
                      return false;
                  } 
                  if (description == null || description.trim() == "") {
                      jAlertErrorMsg("请输入项目概况描述");
                      return false;
                  } 
                 
                  var reData={};
                  reData["projectId"]=obj.id;
                  reData["projectType"]=projectType;
                  reData["projectName"]=projectName;
                  reData["isChargeRepair"]=isChargeRepair;
                  reData["repairDateStart"]=repairDateStart;
                  reData["repairDateEnd"]=repairDateEnd;
                  reData["checkType"]=checkType;
                  reData["address"]=address;
                  reData["description"]=description;
                  reData["provinceId"]=provinceId;
                  reData["cityId"]=cityId;
                  reData["areaId"]=areaId;
                 
                  
                      var url = "admin/projectUpdateSave";
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
              
          jQuery("#input_projectType").val(projectTypeIndex(obj.projectType));
          jQuery("#input_projectName").val(obj.projectName);
          jQuery("#input_isChargeRepair").val(booleanIndex(obj.isChargeRepair));
          jQuery("#input_repairDateStart").val(obj.repairDateStart);
          jQuery("#input_repairDateEnd").val(obj.repairDateEnd);
          jQuery("#input_userPhoneNumber").val(obj.user.phoneNumber);
          jQuery("#input_checkType").val(checkTypeIndex(obj.checkType));
          jQuery("#input_address").val(obj.address.address);
          jQuery("#input_description").val(obj.description);
          
          jQuery("#input_province option[value='0']").remove();
          jQuery("#input_city option[value='0']").remove();
          jQuery("#input_area option[value='0']").remove();
          jQuery("#input_province").prepend("<option value='0' >"+obj.address.province+"</option>");
          jQuery("#input_city").prepend("<option value='0'>"+obj.address.city+"</option>");
          jQuery("#input_area").prepend("<option value='0'>"+obj.address.area+"</option>");
          jQuery("#input_province option[value='0']").attr("selected",true);
          jQuery("#input_city option[value='0']").attr("selected",true);
          jQuery("#input_area option[value='0']").attr("selected",true);
          
              jQuery("#popup_container").css({"min-width":"400px"});
              jQuery("#popup_content").css({"max-height":"700px"});
              jQuery(".chzn-select").chosen();
              ///// DATE PICKER /////
              jQuery("#input_repairDateStart").datepicker({dateFormat:"yy-mm-dd"});
              jQuery("#input_repairDateEnd").datepicker({dateFormat:"yy-mm-dd"});
              jQuery("#popup_container").css({
                  "margin-top" : "-20px",
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

   
   
</script>


