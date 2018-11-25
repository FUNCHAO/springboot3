var table;
$(document).ready(function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	console.info(localhostPaht);
	console.info(projectName);
	var option_winebrand ={
			"aLengthMenu":[3,5,10,20], //动态指定分页后每页显示的记录数。
			"searching":false,//禁用搜索
			"lengthChange":false, //是否启用改变每页显示多少条数据的控件
			"sort" : "position",  //是否开启列排序，对单独列的设置在每一列的bSortable选项中指定
			"deferRender":true,//延迟渲染
			"bStateSave" : false, //在第三页刷新页面，会自动到第一页
			"iDisplayLength" : 5,  //默认每页显示多少条记录
			"iDisplayStart" : 0,
			//----------------
			 "sPaginationType": "full_numbers",
			 "serverSide":true,
			 "bInfo": true,//Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息---fc
			 "bAutoWidth":false,//是否自动计算表格各列宽度，默认true-----fc
			 "bSort":false,//禁用排序-----默认true---false指排序--fc
			 "bFilter":false,//是否启动过滤，搜索功能
			 
			 //"aaSorting":[[2,'desc']],//---------fc
			//---------------
			//"sAjaxSource": 'DataProvider',//这将是控制器的操作方法使用JSON返回类型从而填补你的数据表---fc
			// "sAjaxDataProp": "data",
			"ordering": true,//全局禁用排序
			"dom": '<l<\'#topPlugin\'>f>rt<ip><"clear">',
	        //"sAjaxSource":url,
	        "ajax": {
                "type": "POST",
                "url":localhostPaht+projectName+'/teacher/findPageTea',
                "data":function(d){
                	d.searchByName=$("#searchByName").val()
                	d.searchByType=$("#searchByType").val()
                }  
                 
	        },       
			"aoColumns" : [{  //aoColumns设置列时，不可以任意指定列，必须列出所有列。
				"mData" : "id",
				"orderable": false , // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%"
	        },{  //aoColumns设置列时，不可以任意指定列，必须列出所有列。
				"mData" : "id",
				"orderable": false , // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%"
	        },{
				"mData" : "age",
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%",
				
			}, 
			{
				"mData" : "name",
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%",
				
			},
			
//			{
//				"mData" : "roledetail",
//				"orderable" : true, // 禁用排序
//				"sDefaultContent" : "",
//				"sWidth" : "10%",
//				"render":function(data, type, full, meta){  //render改变该列样式,4个参数，其中参数数量是可变的。
//					var  html=' <span style="color: red"> '+data+'</span>  ';
//			    return	html;
//			    }
//			}, 
			{
				"mData" : "id",//自己定义的字段，"render":function(data, type, full, meta)，data是rid的值
				"orderable" : false, // 禁用排序
				"sDefaultContent" : '',
				"sWidth" : "10%",
			    "render":function(data, type, full, meta){  //render改变该列样式,4个参数，其中参数数量是可变的。
			    	var aniu='';
			    	var aa="&nbsp&nbsp&nbsp&nbsp&nbsp";
			    	var html='<button   class="btn btn-primary" onclick="winebrandDelete('+data+')" >删除</button><button   class="btn btn-primary" onclick="winebrand_update('+data+')" >修改</button>';
			    return html;
			    }}
			],
			"columnDefs" : 
			[{
				"orderable" : false, // 禁用排序
				"targets" : [0], // 指定的列
				"orderData": [ 0, 1 ],  //如果第一列进行排序，有相同数据则按照第二列顺序排列
				"data" : "id",
				"render" : function(data, type, full, meta) {
					
					return '<input type="checkbox" value="'+ data + '" name="id"/>';
				}
			}],
			"oLanguage" : { // 国际化配置
				"sProcessing" : "正在获取数据，请稍后...",
				"sLengthMenu" : "显示 _MENU_ 条",
				"sZeroRecords" : "没有找到数据",
				"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"sInfoEmpty" : "记录数为0",
				"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索",
				"sUrl" : "",
				"oPaginate" : {
					"sFirst" : "第一页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "最后一页"
				}
			},
			drawCallback: function( settings ) {
		        $('input[name=allChecked]')[0].checked=false;//取消全选状态
	    } 
//		        ,
//			initComplete:initComplete,
//			drawCallback: function( settings ) {
//		        $('input[name=allChecked]')[0].checked=false;//表格初始化完成取消全选状态
//		    }
//		    "ajax": {  
//	            "url": url,   
//	            "dataSrc": "aaData",   
//	            "data": function ( d ) {  
//	                var level1 = $('#rname_search').val();  
//	                //添加额外的参数传给服务器  
//	                d.rname = level1;  
//	            }  
//	        },  
		}
		
	
	 table_winebrand= $("#teacherTable").DataTable(option_winebrand);
	
	  });
/**
 * 全选与取消全选
 */
$(function(){//start $
	$('input[name=allChecked]').click(function(){ 
		$("input[name='id']").each(function(){
			  if ($('input[name=allChecked]')[0].checked) {
				  this.checked = true;
			  }
			  else {
				  this.checked = false;
			  }
			});
	})//end $
})