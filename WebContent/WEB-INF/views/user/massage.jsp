<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body background="${pageContext.request.contextPath}/images/2.jpg">
	<%@include file="../common/topNav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-6">
									<img alt="Bootstrap Image Preview"
										src="${pageContext.request.contextPath}${userMassage.userPic}"
										width="100px" height="100px" />
								</div>
								<div class="col-md-6">
									<dl>
										<dt>账户名：</dt>
										<dd>${userMassage.userName}</dd>
									</dl>
								</div>
							</div>
							<form role="form" method="post"
								action="${pageContext.request.contextPath}/user/setUserCommonName">
								<div class="form-group">
									<input type="hidden" value="${userMassage.userName}" name="id">
									<label for="exampleInputEmail1"> 修改昵称： </label> <input
										class="form-control" id="exampleInputEmail1"
										value="${userMassage.userCommonName}" name="userCommonName"
										minlength="2" maxlength="10" size="10" required="required" />
								</div>
								<button class="btn btn-default" type="submit">确认修改昵称</button>
							</form>
							<form role="form" method="post"
								action="${pageContext.request.contextPath}/user/setUserPass">
								<div class="form-group">

									<label for="exampleInputPassword1"> 修改密码： </label> <input
										class="form-control" id="exampleInputPassword1"
										type="password" name="userPass" minlength="8" maxlength="20"
										size="20" required="required" />
								</div>
								<div class="form-group">

									<label for="exampleInputPassword1"> 请输入原密码： </label> <input
										class="form-control" id="exampleInputPassword1"
										type="password" name="userPass2" minlength="8" maxlength="20"
										size="20" required="required" />
								</div>
								<button class="btn btn-default" type="submit">确认修改密码</button>
							</form>
						</div>
						<div class="col-md-4"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/homeEnd.jsp"%>
</body>
</html>