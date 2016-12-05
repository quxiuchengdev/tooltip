<%@ page contentType="text/html;charset=UTF-8"%>
<%--引入 taglib页面--%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>字典管理</title>

		<meta name="description" content="字典管理" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta name="menu" content="${ctx}/dict/list" />
	</head>
	
	<body>
		<script type="text/javascript">

            var table;
            $(document).ready(function() {

                $(document).on('click', 'th input:checkbox' , function(){
                    var that = this;
                    $(this).closest('table').find('tr > td:first-child input:checkbox')
                        .each(function(){
                            this.checked = that.checked;
                            $(this).closest('tr').toggleClass('selected');
                        });
                });

                var contentTable = $('#contentTabel')
                    .dataTable( {
                        "info" : true,
                        "autoWidth": false,
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                        "language": {
                            "processing": "处理中...",
                            "lengthMenu": "显示 _MENU_ 项结果",
                            "zeroRecords": "没有匹配结果",
                            "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                            "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                            "infoFiltered": "",
                            "infoPostFix": "",
                            "search": "搜索:",
                            "url": "",
                            "emptyTable": "表中数据为空",
                            "loadingRecords": "载入中...",
                            "infoThousands": ",",
                            "paginate": {
                                "first": "首页",
                                "previous": "上页",
                                "next": "下页",
                                "last": "末页"
                            },
                            "aria": {
                                "sortAscending": ": 以升序排列此列",
                                "sortDescending": ": 以降序排列此列"
                            },
                        },
                        "pagingType":"full_numbers",//分页样式
                        "processing": false,//加载数据时显示正在加载信息
                        "serverSide" : true,//服务器分页.
                        "ajax":{
                            "url" : "${ctx}/dict/datatables",//给服务器发请求的url
                            "type" : "post",
                            "data" :{
                                "label":$("#label").val(),
                                "type":$("#type").val(),
                                "description":$("#description").val(),
                            }

                        },
                        "columns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
                            {"data": 'id',"className":'center',"orderable":false}, //mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                            {"data": 'value',"bSortable":false}, // sDefaultContent 如果这一列不需要填充数据用这个属性，值可以不写，起占位作用
                            {"data": 'label'},
                            {"data": 'type'},
                            {"data": 'sort'},
                            {"data": 'description'},
                            {"data": 'id',"orderable":false},//sClass 表示给本列加class

                        ],
                        "columnDefs":[
                            {
                                "name":"id",
                                "targets":[0],
                                "sortable":false,
                                "data": "id", // 数据列名
                                "render": function(data, type, full) { // 返回自定义内容
                                    var html = "";
                                    html = "<label class='position-relative'>";
                                    html += "<input type='checkbox' name='ids' class='ace' value='" + data + "' >";
                                    html += "<span class='lbl'></span>";
                                    html += "</label>";
                                    return html;
                                }
                            },
                            {
                                "targets":[6],
                                "sortable":false,
                                "data": "id", // 数据列名
                                "render": function(data, type, full) { // 返回自定义内容
                                    var html = "<div class='action-buttons'>";
                                    html += "<a title='添加' data-rel='tooltip' class='blue' href='${ctx}/dict/add?id=" + data + "' ><i class='ace-icon fa fa-plus-square-o bigger-130'></i></a>";
                                    html += "<a title='修改' data-rel='tooltip' class='green' href='${ctx}/dict/form?id=" + data + "' ><i class='ace-icon fa fa-pencil-square-o bigger-130'></i></a>";
                                    html += "<a title='删除' data-rel='tooltip' class='red' href='${ctx}/dict/delete?id=" + data + "' onclick='return confirmx(\"删除吗?\",this.href)'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>";
                                    html += "</div>";
                                    return html;
                                }

                            }

                        ],
                        "paging": true,  //是否分页。
                        "searching": true,  //是否使用内置的过滤功能
                        /* "fnCreatedRow":function ( row, data, index ) {
                         $('td', row).eq(0).addClass("center");
                         } */
                        //绘图回调函数
                        "drawCallback": function(oSettings){
                            //增加提示框
                            $("[data-rel=tooltip]" ).tooltip({
                                show: null,
                                position: {
                                    my: "left top",
                                    at: "left bottom"
                                },
                                open: function( event, ui ) {
                                    ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
                                }
                            });
                            var oTable = $("#contentTabel").dataTable();
                            $('#redirect').keypress(function(e){
                                var c=e.keyCode||e.which;
                                if(c==13){
                                    if($(this).val() && $(this).val()>0){
                                        var redirectpage = $(this).val()-1;
                                        oTable.fnPageChange( redirectpage );
                                    }else{
                                        var redirectpage = 0;
                                        oTable.fnPageChange( redirectpage );
                                    }
                                }
                            });
                            //设置选择跳转指定页
                            var pageNum =  Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength );
                            if(pageNum==null){
                                pageNum = 0;
                            }
                            pageNum = pageNum + 1;
                            $("#redirect").val(pageNum);

                            //全选
                            $('#redirect').click(function(){
                                $('#redirect').select();
                            });
                        },

                    } );

                $("#searchBtn").click(function(){
                    contentTable.fnDraw();
                });

                //延时搜索
                // contentTable.fnSetFilteringDelay();

                table = contentTable;
            });
        </script>

		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active">
					<a>
						字典列表
					</a>
				</li>
		
				<li>
					<a href="${ctx}/dict/form">
						字典添加
					</a>
				</li>
		
			</ul>
		</div>
		<div class="widget-box" style="margin:0px 0px 6px 0px;">
			<div class="widget-body">
				<div class="widget-main">
					<form class="form-search" id="searchForm" action="${ctx}/dict/list" method="post">
						<div class="row">
							<div class="col-xs-12 col-sm-8">
								<div class="input-group">
                                    <table class="">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <%--<label style="font-size: 13px;">&nbsp;标签:&nbsp;</label>--%>
                                                    &nbsp;&nbsp;<span class="label label-info">标签:</span>&nbsp;&nbsp;
                                                </td>
                                                <td>
                                                    <input class="input-sm" type="text" id="label">
                                                </td>
                                                <td>
                                                    &nbsp;&nbsp; <span class="label label-info">类型:</span>&nbsp;&nbsp;
                                                </td>
                                                <td>
                                                    <input class="input-sm" type="text" id="type">
                                                </td>
                                                <td>
                                                    &nbsp;&nbsp;<span class="label label-info">描述:</span>&nbsp;&nbsp;
                                                </td>
                                                <td>
                                                    <input class="input-sm" type="text" id="description">
                                                </td>
                                                <td>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-purple btn-sm" id="searchBtn">
                                                        搜索
                                                        <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                                    </button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-blue3 btn-sm">
                                                        删除一堆
                                                        <i class="ace-icon fa fa-trash-o icon-on-right bigger-110"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div>
			<div class="dataTables_wrapper form-inline">
				<!-- <div class="clearfix">
					<div class="pull-right tableTools-container"></div>
				</div>
				<div class="table-header">
					Results for "Latest Registered Domains"
				</div> -->
				<%@include file="retain.jsp" %>
				<table id="contentTabel" class="table table-striped table-bordered table-hover dataTable" >
					<thead>
						<tr>
							<th class="center sorting_disabled">
								<label class="position-relative"> 
									<input type="checkbox" class="ace"><span class="lbl"></span>
								</label>
							</th>
							<th class="sorting">
								值
							</th>
							<th class="sorting" >
								标签
							</th>
							<th class="sorting" >
								类型
							</th>
							<th class="sorting" >
								<!-- <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> -->
								 排序
							</th>
							<th class="sorting" >
								描述
							</th>
							
							<th class="sorting_disabled" >
								操作
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
