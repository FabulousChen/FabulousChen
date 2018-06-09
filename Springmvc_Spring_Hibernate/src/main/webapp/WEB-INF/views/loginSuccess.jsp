<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网盘</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
<script src="${APP_PATH }/static/js/echarts.js"></script>



<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<div class="col-md-4 col-md-offset-8">

			<iframe width="580" scrolling="no" height="55" frameborder="0"
				allowtransparency="true"
				src="http://i.tianqi.com/index.php?c=code&id=34&icon=1&num=3"></iframe>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<h1>欢迎${username}</h1>
		</div>
		<div class="col-md-1 col-md-offset-1">
			<form action="uploadFile" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" id="inputFile" name="file">
					<button type="submit" class="btn btn-primary">上传</button>
					<input type="hidden" name="username" value="${username}" /><br />
				</div>

			</form>
		</div>
		<div class="col-md-1 col-md-offset-2">
			<form action="myFile" method="post">
				<div class="form-group">
					<button type="submit" class="btn btn-info">我上传的文件</button>
					<input type="hidden" name="username" value="${username}" /><br />
				</div>
			</form>

		</div>
		<div class="col-md-4 col-md-offset-8">

			<form class="form-inline" action="searchFile" method="post">
				<div class="form-group">
					<label>搜索文件名</label> <input type="text" class="form-control"
						id="InputFileName" placeholder="文件名" name="fileName">
				</div>

				<button type="submit" class="btn btn-primary">搜索</button>
			</form>
		</div>

		<div class="row">
			<br>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="lists_table">
					<thead>
						<tr>
							<th>#</th>
							<th>fileName</th>
							<th>fileSize</th>
							<th>author</th>
							<th>creatTime</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${files}" var="file">
							<tr>
								<th>${file.id }</th>
								<th>${file.fileName }</th>
								<th>${file.fileSize }</th>
								<th>${file.user.username }</th>
								<th>${file.createTime }</th>
								<th><a href="download?fileName=${file.fileName}">下载<a>
											<a href="lookPDF?fileName=${file.fileName}">预览<a></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div>
			<div class="col-md-4 col-md-offset-6" id="pageInfo"></div>
			<div>
				<a><button class="btn btn-primary" id="lastPage" onclick="lastPage">上一页</button></a>
				<a><button class="btn btn-primary" id="nextPage" onclick="nextPage">下一页</button></a>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div>
			<div id="main"
				style="width: 600px; height: 400px; position: absolute;"></div>
			<div id="main2"
				style="width: 600px; height: 400px; position: absolute; margin-left: 600px;"></div>
		</div>
	</div>
	<script type="text/javascript">
	alert("操作成功");
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '上传文件排行榜条形图'
			},
			tooltip : {},
			legend : {
				data : [ '数量' ]
			},
			xAxis : {
				data : []
			},
			yAxis : {},
			series : [ {
				name : '数量',
				type : 'bar',
				data : []
			} ]
		};
		function fetchData(cb) {
			// 通过 setTimeout 模拟异步加载
			setTimeout(function() {
				cb({
					categories : categoriess,
					data : dataa
				});
			}, 1000);
		}
		fetchData(function(data) {
			myChart.setOption({
				xAxis : {
					data : data.categories
				},
				series : [ {
					// 根据名字对应到相应的系列
					name : '上传文件数目',
					data : data.data
				} ]
			});
		});
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		//页面加载完成后直接发送一个ajax请求
		var categoriess = [];
		var dataa = [];
		$(function() {
			$.ajax({
				url : "${APP_PATH}/echarts",
				type : "GET",
				success : function(result) {
					var datac = result.entend.echarts;
					$.each(datac, function(index, item) {
						categoriess.push(index);
						dataa.push(item);
					})
				}
			})
		});
	</script>

	<script type="text/javascript">
		var dom = document.getElementById("main2");
		var myChart2 = echarts.init(dom);
		var app = {};
		option = null;
		option = {
			title : {
				text : '文件上传排行饼状图',
				subtext : '',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : []
			},
			series : [ {
				name : '用户',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
		if (option && typeof option === "object") {
			myChart2.setOption(option, true);
		}
		var echartdata2 = [];
		var legenddata2 = [];
		$.ajax({
			url : "${APP_PATH}/echarts2",
			async : false,
			dataType : "json",
			type : "GET",
			dataType : "json",
			success : function(result) {
				$.each(result.entend.echarts3, function(index, item) {
					echartdata2.push({
						value : item.value,
						name : item.name
					});
					legenddata2.push(item.name);

				});
				myChart.hideLoading();
				myChart2.setOption({ //加载数据图表  
					series : [ {
						data : echartdata2
					} ],
					legend : [ {
						data : legenddata2
					} ]
				});
			}
		});
	</script>
	<script type="text/javascript">
		
		$("#lastPage").click(function(){
				window.open("getPageFileJsp?pageNo=1")
		});
		$("#nextPage").click(function(){
				window.open("getPageFileJsp?pageNo=2")
		});
		
	</script>

</body>
</html>