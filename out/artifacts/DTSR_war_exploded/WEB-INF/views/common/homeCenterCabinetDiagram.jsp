<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/default.css">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/carousel.css"
	rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
.centerHome {
	position: absolute;
	top: 15%;
	left: 22.5%;
	width: 55%;
	height: 65%;
}

.center {
	text-align: center;
}

.cd {
	position: absolute;
	left: 0%;
	top: 20%;
	text-align: lift;
}

.red {
	position: absolute;
	left: 18.5%;
	top: 25%;
	text-align: lift;
}
</style>
<div class="centerHome">
	<div class="center">
		<%@include file="../common/logo.jsp"%>
		<div class="cd">
			<c:choose>
				<c:when test="${cabinetDiagram.hostGuid!=0}">
					<!--如果 -->
					<%@include file="../common/CabinetDiagram.jsp"%>
				</c:when>
				<c:otherwise>
					<!--否则 -->
					<div class="alert alert-dismissable alert-warning">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						<h4>注意!</h4>
						<strong>警告!</strong>这台主机暂时无机柜试图.
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
