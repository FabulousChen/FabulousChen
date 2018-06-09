<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的iCloud</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
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
			<form action="uploadFile" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" id="inputFile" name="file">
					<button type="submit" class="btn btn-primary">Submit</button>
					<input type="hidden" name="username" value="${user_name}" /><br />
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
				<table class="table table-hover">
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

						<c:if test="${files==null}">
							<tr>
								<th>没有搜索的文件</th>
							</tr>
						</c:if>
						<c:if test="${files!=null}">
							<c:forEach items="${files}" var="file">
								<tr>
									<th>${file.id }</th>
									<th>${file.fileName }</th>
									<th>${file.fileSize }</th>
									<th>${file.user.username} </th>
									<th>${file.createTime }</th>
									<th><a href="download?fileName=${file.fileName}">下载<a>
												<a href="lookPDF?fileName=${file.fileName}">预览<a></th>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>