<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="${pageContext.request.contextPath}/css/carousel.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
</head>
<body>
	<%@include file="../common/loginTop.jsp"%>

	<div class="logo">
		<img alt="" height="120px" width="120px" src="${pageContext.request.contextPath}/images/logo2.png">
		<img alt="" height="90px" width="250px" src="${pageContext.request.contextPath}/images/logo.png">
	</div>
	<div class="logo3" style="position: absolute;left: 75%;top:75%;margin: 0px;padding: 0px">
		<img src="${pageContext.request.contextPath}/images/logo3.png" width="430px" style="margin-left: 30px" />
		<h3 style="color: white">山西浩然机电设备工程有限公司技术支持</h3>
	</div>

	<div class="banner">

		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout: fixed;">
				<div class="error-box"></div>
				<h3>DTSR云端管理系统</h3>
				<form class="registerform" method="post"
					action="${pageContext.request.contextPath}/user/login">
					<div class="fm-item">
						<label for="logonId" class="form-label">DTSR系统登陆：</label> <input
							type="text" placeholder="账户名" maxlength="100" id="username"
							class="i-text" ajaxurl="demo/valid.jsp" datatype="s6-18"
							errormsg="用户名至少6个字符,最多18个字符！" name="userName">
						<div class="ui-form-explain"></div>
					</div>

					<div class="fm-item">
						<label for="logonId" class="form-label">登陆密码：</label> <input
							type="password" placeholder="密码" maxlength="100" id="password"
							class="i-text" datatype="*6-16" nullmsg="请输入密码！"
							errormsg="密码范围在6~16位之间！" name="userPass">
						<div class="ui-form-explain"></div>
					</div>


					<div class="fm-item">
						<label for="logonId" class="form-label"></label> <input
							type="submit" value="" tabindex="4" id="send-btn"
							class="btn-login">
						<div class="ui-form-explain"></div>
					</div>

				</form>

			</div>
		</div>

	</div>
	<%@include file="../common/homeEnd.jsp"%>
</body>
</html>