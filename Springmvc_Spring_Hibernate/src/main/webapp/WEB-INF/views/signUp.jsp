<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
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
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>用户注册</h1>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6 col-sm-offset-2">
				<font color="red">${msg}</font>
			</div>
		</div>


		<div>
			<form class="form-horizontal" action="sign" method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label">Username</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="username"
							placeholder="chen" name="username">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" id="password"
							placeholder="Password" name="password">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-4">
						<button type="submit" class="btn btn-default">注册</button>

					</div>
				</div>
			</form>

		</div>
</body>
</html>