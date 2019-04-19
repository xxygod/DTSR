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
.leftHome {
	position: absolute;
	top: 10%;
	left: 2%;
	width: 18%;
	height: 65%;
	color: #00ffff;
	background-image: url(%E6%9C%80%E5%90%8E%E4%B8%80%E4%B8%AA.jpg);
}

.leftTable {
	position: absolute;
	left: 0%;
	top: 35%;
	width: 90%;
	height: 30%;
	z-index: 1;
	overflow: auto;
}

.center {
	text-align: center;
}
</style>
<div class="leftHome">
	<div class="center">
		<img alt="140x140" height="100%" width="100%"
			src="${pageContext.request.contextPath}/images/hostLogo.png" />
	</div>
	<h3>设备列表</h3>
	<hr style="border: none; border-top: 4px groove skyblue;" />
	<table class="leftTable">
		<thead>
			<tr>
				<th>主机号
					<hr style="border: none; border-top: 1px groove skyblue;" />
				</th>
				<th>主机名
					<hr style="border: none; border-top: 1px groove skyblue;" />
				</th>
				<th>状态
					<hr style="border: none; border-top: 1px groove skyblue;" />
				</th>
				<th>&nbsp&nbsp操作
					<hr style="border: none; border-top: 1px groove skyblue;" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty hostList }">
				<c:forEach items="${hostList}" var="h" varStatus="hl">
					<c:if test="${hl.count<=20}">
						<tr>
							<td>${h.hostGuid}
								<hr style="border: none; border-top: 1px groove skyblue;" />
							</td>
							<td>${h.hostName}
								<hr style="border: none; border-top: 1px groove skyblue;" />
							</td>
							<c:if test="${h.hostCode==0}">
								<td><span class="label label-default">离线</span>！
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
								<td><a
									href="${pageContext.request.contextPath}/host/getTemperature?hostGuid=${h.hostGuid}&hostName=${h.hostName}">
										选择主机 </a>
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
							</c:if>
							<c:if test="${h.hostCode==1}">
								<td><span class="label label-success">在线</span>！
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
								<td><a
									href="${pageContext.request.contextPath}/host/getTemperature?hostGuid=${h.hostGuid}&hostName=${h.hostName}">
										选择主机 </a>
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
							</c:if>
							<c:if test="${h.hostCode==2}">
								<td><span class="label label-danger">异常</span>！
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
								<td><a
									href="${pageContext.request.contextPath}/host/getTemperature?hostGuid=${h.hostGuid}&hostName=${h.hostName}">
										选择主机 </a>
									<hr style="border: none; border-top: 1px groove skyblue;" /></td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
