<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
	<title>雪球能源运维后台管理系统-新闻添加</title>
	<script type="text/javascript" src="<%=basePath%>ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="<%=basePath%>ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<style type="text/css">
     .uploader{
           display: none;
    }
     .imagefield img{
           margin-top: 5px;
           margin-bottom: 5px;
           border-style:dashed;
           border-color: gray;
           border-width:1px;
           width: 100px;
           height: 100px;
           cursor: pointer;
    }
     .imagefield {
           margin-right: 25px;
    }
     .p_image{
            background: #fff none repeat scroll 0 0;
            border-left: 1px solid #ddd;
            display: block;
            margin-left: 220px;
            padding: 20px;
    }
     .p_image img{
           cursor: pointer;
    }
     .image_add_btn{
           margin: 5px;
           border-style:dashed;
           border-color: gray;
           border-width:1px;
           width: 100px;
           height: 100px;
    }
     .image_delete_btn{
          color: #ff4800 ;
          font-size: 18px;
          position: absolute;
          cursor: pointer;
    }

 .guigeimagefield img{
           width: 35px;
           height: 35px;
           cursor: pointer;
           vertical-align:middle;
    }
  .guige_delete_btn{
           margin: 5px;
          position: relative;
          color: #000;
          font-size: 10px;
          background-color:#ff4800 ; 
           border-radius:3px;
           font-size: 14px;
           color:#fff;
           cursor: pointer;
            width: 100px;
           height: 100px;
            padding-top: 5px;
           padding-bottom: 5px;
           padding-left:15px;
           padding-right: 15px;
    }
    
  .guige_add_btn{
           margin: 5px;
           width: 100px;
           height: 100px;
           padding-top: 5px;
           padding-bottom: 5px;
           padding-left:15px;
           padding-right: 15px;
           background-color:#78ce07; 
           border-radius:3px;
           font-size: 14px;
           color:#fff;
           cursor: pointer;
    }   
  .piliang_add_btn{
           margin: 5px;
           width: 100px;
           height: 100px;
           padding-top: 5px;
           padding-bottom: 5px;
           padding-left:15px;
           padding-right: 15px;
           background-color:#78ce07; 
           border-radius:3px;
           font-size: 14px;
           color:#fff;
           cursor: pointer;
    }
    </style>
	<script type="text/javascript">
	  	UE.getEditor('editor');
	</script>
<body class="withvernav">
	<div class="bodywrapper">
	    <!--topheader引入-->
	    <jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
        <jsp:include page="/WEB-INF/admin/Include/header.jsp" />
        <jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />
	   
	     <!--右部类容--> 
		<div class="centercontent">
	        <div id="contentwrapper" class="contentwrapper">
		        <div id="validation" class="subcontent">
			        <ul class="breadcrumbs">
			       	 	<li><a href="admin/newShow">新闻列表</a></li>
			         	<li>添加</li>
			        </ul>
		            <div class="contenttitle2">
		            	<h3>新闻添加</h3>
		            </div>
					<form class="stdform stdform2"  enctype="multipart/form-data" id="newAddForm" method="post" action="admin/newAddSave">
		                <p>
                            <label>速览图片<span class="inputrequiredflag">*</span>
                                <small>上传最多1张预览图片，至少上传第一张图片将会用作LOGO显示.大小请不要超过1MB.<span style="color:#ff9900 ">图片尺寸750*800</span></small>
                            </label>
                                       
                            <span class="p_image">
                                <img class="image_add_btn" id="yl_add_btn" src="images/add_image_btn.png" onclick="addFileInput(this,'yl');"/>
                            </span>                 
                       </p>
                        <p>
		                	<label>标题<span class="inputrequiredflag">*</span></label>
		                    <span class="field"><input type="text" placeholder="请输入信息标题" style="width:1024px;" name="title" required/></span>
		                </p>
                        <p>
		                	<label>简述<span class="inputrequiredflag">*</span></label>
		                    <span class="field"><input type="text" placeholder="请输入信息简述" style="width:1024px;" name="description" required/></span>
		                </p>
		                <p>
		                	<label>内容<span class="inputrequiredflag">*</span></label>
		                    <span class="field">
		                    	<textarea id="editor" name="content" required></textarea>
		                    </span>
		                </p>
		                <p class="stdformbutton">
                        	<button class="submit radius2" type="submit">保存</button>
                        	<%--<input class="reset radius2" type="reset" onclick="resetForm();" value="重置">
                        --%></p>
		            </form>
		           </div>
	        </div>
		</div>
	</div>
</body>


<script type="text/javascript">
jQuery(function(){
    jQuery("#yl_add_btn").click();
    jQuery(".image_delete_btn").remove();
});
  
    var flag=1;
   //添加图片上传框
   function addFileInput(obj,qianzui){
       var currentNum=jQuery(obj).parent().find(".imagefield").size();
       
   if(("tw" == qianzui && currentNum<8) || ("yl" == qianzui && currentNum<1)){
       var span="<span  class='imagefield'>"+
                           "<input style='visibility:hidden;width:10px;'  type='file' name='"+qianzui+"file' id='"+qianzui+"file"+flag+"'  multipleaccept='image/*' onchange='handleFiles(\""+qianzui+"\",this,"+flag+")' />"+
                         "<img id='"+qianzui+"img"+flag+"' src='images/add_image_default.png' onclick='upImage(\""+qianzui+"\","+flag+")'/>"+
                        "<strong class='image_delete_btn' onclick='deleteFile(this)'  title='删除'>x</strong>"+
                      "</span>";
       jQuery(obj).before(span);
      //jQuery("#"+id).uniform();
      flag++;
      }else{
         if("tw" == qianzui ){
             jAlertErrorMsg("最多添加8张详情图片哦！");
         } else{
             jAlertErrorMsg("最多添加1张预览图片哦！");
         }
         
      }
   }
//移除
function deleteFile(obj){
   jQuery(obj).parent().remove();
}


window.URL = window.URL || window.webkitURL;
//选择图片
function upImage(qianzui,imagenum) {
    jQuery("#"+qianzui+"file" + imagenum).click();
}
//预览图片
function handleFiles(qianzui,obj, imagenum) {
    
    var files = obj.files, img = new Image();
    var fileid="#"+qianzui+"img" + imagenum;
    var type=jQuery(obj).val().substring( jQuery(obj).val().lastIndexOf(".") + 1).toLowerCase();
    if( !(type=="jpg" || type=="jpeg" || type== "png" ) ){
        jQuery(obj).val("");
         jAlertErrorMsg("请上传正确格式图片");
        return false;
    }
    
    if (window.URL) {
        if (files[0].size > 4194304) {
            alert("图片大小请不要超过4M");
            return false;
        }
        jQuery(fileid).attr("src",
                window.URL.createObjectURL(files[0]));
        jQuery(fileid).onload = function(e) {
            window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
        };

    } else if (window.FileReader) {
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = function(e) {
            if (e.total > 4194304) {
                alert("图片大小请不要超过4M");
                return false;
            }
            jQuery(fileid).attr("src", this.result);
        };
    } else {
        //ie
        obj.select();
        obj.blur();
        var nfile = document.selection.createRange().text;
        document.selection.empty();
        jQuery(fileid).attr("src", this.nfile);
        jQuery(fileid).onload = function() {
            if (jQuery(fileid).fileSize > 4194304) {
                alert("图片大小请不要超过4M");
                return false;
            }
        };

    }
}
   
</script>

</html>
