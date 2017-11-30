/**
*处理DataTable的数据
*公共部分
*/
var DataTablePack = function(){

	var oTable;

	//汉化分页条
	var language = {
		paginate:{
			first:'首页',
			last:'末页',
			previous:'上一页',
			next:'下一页'
		},
		lengthMenu:'显示 _MENU_ 条',
		zeroRecords:'<div width="100%" align="center"><span style="text-align:center;">没有检索到有效数据！</span></div>',
		info:'显示 _START_ 到 _END_ 条记录/共计 _TOTAL_ 条记录',
		infoEmpty:'没有检索到有效数据！',
		search:'查找：',
		processing:'正在加载数据，请稍候...',
		infoFiltered:'共计 _TOTAL_ 条记录',
	};

	/* 全选以及选中效果功能  */
	function checkAll(table){
		//全选功能
		table.find('.checkall').change(function () {
			var allCheck = table.find('tbody input[type=checkbox]');
			if(jQuery(this).is(":checked")){
				allCheck.each(function(){
					jQuery(this).attr('checked',true);
					jQuery(this).parent().addClass('checked');	//used for the custom checkbox style
				});
			}else{
				allCheck.each(function(){
					jQuery(this).attr('checked',false);
					jQuery(this).parent().removeClass('checked');	//used for the custom checkbox style
				});
			}
        });

		//选中效果
        table.on('change', 'tbody tr .check', function () {
            $(this).parents('tr').toggleClass("active");
            if(jQuery(this).is(":checked")){
            	jQuery(this).attr('checked',true);
            	jQuery(this).parent().addClass('checked');
            }else{
            	jQuery(this).attr('checked',false);
            	jQuery(this).parent().removeClass('checked');
            }
        });

       // table.parent().parent().find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	}

	/* 通用批量操作 */
	function batchOperate(table){
		//获取所有选中的checkbox的value
		jQuery('.batchOp').click(function(){
			var href = jQuery(this).attr('href');

			var title = jQuery(this).attr('title');

			if(title == undefined){
				jQuery.jGrowl("请设置title属性！",{sticky: true,position:"center",theme:"jGrowlErorr"});
				return false;
			}

			//解析弹窗属性
			var param = title.split('|');

			param[0] = param[0] == "" ? "您确定要操作这些信息么？" : param[0];
			param[1] = param[1] == "" ? "确定操作" : param[1];

			jConfirm(param[0], param[1], function(r) {
				if(r){
					var ids = "";
					var check = table.find('tbody input[type=checkbox]');
					check.each(function(){
						if(jQuery(this).is(':checked')){
							ids += jQuery(this).val()+",";
						}
					});
					ids = ids != "" ? ids.substr(0,ids.length-1) : ids;

					if(ids.length < 1){
						jQuery.jGrowl("请选择信息！",{sticky: true,position:"center",theme:"jGrowlWarning"});
						return false;
					}else{
						href = href.replace('{values}',ids);
						//alert(href);
						jQuery.ajax({
							type:'post',
							url:href,
							success:function(notify,textStatus){
							    if (notify.response == "success") {
		                               jAlertSuccessMsg(notify.msg);
		                               jAlertHide();
		                               oTable.fnDraw();
		                                //刷新表格数据
		                                oTable.fnDraw(false);
		                                //将全选标志设置false
		                                table.find('.checkall').attr('checked',false);
		                                table.find('.checkall').parent().removeClass('checked');
		                           } else {
		                               jAlertErrorMsg(notify.msg);
		                           }
							},
							error:function(XMLHttpRequest,textStatus,errorThrown){
								alert('请求异常');
							}
						});
					}
				}
			});
			return false;
		});
	}

	/** 公开调用方法 **/
	var DataTablePublic = {
		/* 处理处理静态数据  */
		baseTable:function(table){
			oTable = table.dataTable({
				'language':language,//汉化工具条
				'lengthMenu':[10,20,30,50,100],//显示每页大小的下拉框中的选项
				//'dom': "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'f><'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
				'pagingType':'full_numbers',//分页样式
				'columnDefs':[{
					'targets':[0],
					'orderable':false
				}],//默认列参数
				"order":[
			        [1, "asc"]
			    ] //默认排序的列
			});

			checkAll(table);//全选功能

			return oTable;
		},
		/* 即时后台获取数据*/
		serverTable:function(table,url,reqData,columns,order,options){
			var displayStart = 0,pageLength = 10,dom = 'lfrtip',oTableTools={},lengthMenu=[10,20,30,50,100];
			var callback = function(data){},createdRow = function(row,data,dataIndex){};
			if(options != null && options != undefined&&options != 0){
				displayStart = options.displayStart == null || options.displayStart == '' ? 0 : options.displayStart;
				pageLength = options.pageLength == null || options.pageLength == '' ? 10 : options.pageLength;
				dom = options.dom == null || options.dom == '' || options.dom == undefined ? dom : options.dom;
				lengthMenu = options.lengthMenu == null || options.lengthMenu == '' || options.lengthMenu == undefined ? lengthMenu : options.lengthMenu;
				oTableTools = options.oTableTools == null || options.oTableTools == '' || options.oTableTools == undefined ? oTableTools : options.oTableTools;
				createdRow = options.createdRow == null || options.createdRow == '' || options.createdRow == undefined ? createdRow : options.createdRow;
				callback = options.callback = null || options.callback == '' || options.callback == undefined ? callback : options.callback;
			}
			loadWait();
			//alert(startNum);
			oTable = table.dataTable({
				ajax:{
					dataType:'json',
					/////contentType:'application/json',
					type:'post',//更改Ajax的请求方式
					url:url,//从一个ajax数据源读取数据给表格内容
					data:function(aoData){
						removeLoadWait();
						reqData['dtJson'] = JSON.stringify(aoData);
						return reqData;
					},//添加额外的参数发送到服务器
					//data:'{"username":"admin","userpwd":"123456"}',
					error:function (resp){
						removeLoadWait();
						alert(JSON.stringify(resp));
					},
					dataSrc:function(dtJson){if(dtJson.code == "400"){
						jQuery.jGrowl(dtJson.msg,{sticky: true,position:"center",theme:"jGrowlErorr"});
						return false;
					}else if(dtJson.code == "300"){
						jQuery.jGrowl(dtJson.msg,{sticky: true,position:"center",theme:"jGrowlErorr"});
						return false;
					}else{
						//alert(JSON.stringify(dtJson.params));
						if(dtJson.params != null){
							callback(dtJson.params);
						}
						return dtJson.data;
					}}

				},
				'serverSide':true,//是否开启服务器模式
				'stateSave':true,//保存状态 - 在页面重新加载的时候恢复状态（页码等内容）
				'processing':true,//当表格处在处理过程（例如排序）中时，启用或者禁止 'processing'指示器的显示。
				'displayStart':displayStart,//初始化显示的时候从第几条数据开始显示(一开始显示第几页)
				'columns':columns,
				'language':language,//汉化工具条
				'lengthMenu':lengthMenu,//显示每页大小的下拉框中的选项
				'dom': dom,
				'oTableTools': oTableTools,
				'pagingType':'full_numbers',//分页显示所有按钮
				//'columnDefs':[{
				//	'targets':[0],
				//	'orderable':false
				//}]
				//,//默认列参数
				"order":order,//默认排序的列
				"pageLength":pageLength,
				"createdRow":createdRow //创建行
			});

			checkAll(table);//全选功能

			batchOperate(table);//批量操作

			return oTable;
		}
	};

	return DataTablePublic;

}();