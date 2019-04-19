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
.rightHome {
	position: absolute;
	top: 15%;
	left: 78%;
	width: 22%;
	height: 65%;
	color: #00ffff;
}

.center {
	text-align: center;
}

::-webkit-scrollbar {
	display: none;
}

.fixedThead {
	display: block;
	width: 100%;
}

.scrollTbody {
	display: block;
	height: 1000px;
	overflow: auto;
	width: 100%;
}
</style>
<div class="rightHome">
	<div class="center">
		<h3>今天的报警信息</h3>
		<table class="table"
			style="border-collapse: separate; border-spacing: 0px 0px;">
			<thead class="fixedThead">
				<tr>
					<th>编号</th>
					<th>通道名</th>
					<th width="40%">时间</th>
					<th width="13%">类型</th>
					<th width="12%">位置</th>
					<th>温度</th>
				</tr>
			</thead>
			<tbody style="color: #ffffff" class="scrollTbody">
				<c:if test="${empty arListToDay }">
					<tr>
						<td><strong>暂无数据!</strong>请查看其他主机</td>

					</tr>
				</c:if>
				<c:if test="${!empty arListToDay }">
					<c:forEach items="${arListToDay}" var="al">
						<tr>
							<td>${al.arId}</td>
							<td>${al.channelName}</td>
							<td>${al.arTime}</td>
							<c:if test="${al.arType==2}">
								<td>故障</td>
							</c:if>
							<c:if test="${al.arType==3}">
								<td>升温报警</td>
							</c:if>
							<c:if test="${al.arType==4}">
								<td>定温报警</td>
							</c:if>
							<td>${al.arPosition}</td>
							<td>${al.arDate}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
