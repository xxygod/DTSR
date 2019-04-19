<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>温度曲线</title>
<link href="${pageContext.request.contextPath}/css/carousel.css"
	rel="stylesheet">
</head>
<body>
	<%@include file="../common/homeTop.jsp"%>
	<%@include file="../common/topNav.jsp"%>
	<h3>
		${Host.hostName} <small>实时温度数据</small>
	</h3>
	<c:choose>
		<c:when test="${Host.hostCode!=0}">
			<!--如果 -->
			<%@include file="../common/chart.jsp"%>
		</c:when>
		<c:otherwise>
			<!--否则 -->
			<div class="alert alert-dismissable alert-warning">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">×</button>
				<h4>注意!</h4>
				<strong>警告!</strong>这台主机已经下线.
			</div>
		</c:otherwise>
	</c:choose>
	<h3>今天的报警信息</h3>
	<table class="table">
		<thead>
			<tr>
				<th>编号</th>
				<th>通道名</th>
				<th>时间</th>
				<th>类型</th>
				<th>位置</th>
				<th>温度</th>
			</tr>
		</thead>
		<tbody>

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
	<%@include file="../common/homeEnd.jsp"%>
</body>
</html>