<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<title>驰享家后台管理系统</title>
</head>

<body class="withvernav">
<div class="bodywrapper">
     <!--topheader引入-->
     <jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/header.jsp" />
     <jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />

    <div class="centercontent tables">
        <div id="contentwrapper" class="contentwrapper">
			<div class="tableoptions">
				<a class="btn btn_book" href="admin/adminAdd"><span>新增管理员</span></a>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="adminAjaxShow_Table">
				<thead>
					<tr>
						<th class="head1 nosort"><input type="checkbox" class="checkall"/></th>
						<th class="head1">账号</th>
						<th class="head1">角色名称</th>
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
<script type="text/javascript">
var oTable;
var reqData = {};
jQuery(document).ready(function(){

	var table = jQuery('#adminAjaxShow_Table');

	

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
	    {'data':'username','orderable':true,'className':'center'},
	    {'data':'role_name','orderable':false,'className':'center'},
	    {
	   		'data':'createDate',
	    	'render':function(data,type,full){
	    		return data != null ? data.replace(/\T/g,' ') : "-.-.-";
	    	},
	    	'orderable':true,
			'className':'center'
	   	},
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
				var content="";
				content+="<a class='stdbtn' style='font-size: 12px;' href='admin/adminEdit?admin.id="+full.id+"'>编辑</a>";
	    		return content;
	    	},
	    	'orderable':false,
			'className':'center'
	    }
	];

	var order = [[4,"desc"]];

	oTable = DataTablePack.serverTable(table,'admin/adminAjaxShow',reqData,columns,order,0);
});

</script>


