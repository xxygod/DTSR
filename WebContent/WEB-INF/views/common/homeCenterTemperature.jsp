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

table {
	color: #00ffff;
	height: 666px;
	overflow: auto;
}

.center {
	text-align: center;
}

.right {
	left: 78.5%;
	top: -20%;
	text-align: right;
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
<div class="centerHome">
	<div class="center">
		<%@include file="../common/logo.jsp"%>
		<div class="right">
			<a id="modal-677212" href="#modal-container-677212" role="button"
				class="btn" data-toggle="modal"><img alt="" height="35px"
				width="175px"
				src="${pageContext.request.contextPath}/images/soso.png"></a>
			<c:if test="${not empty temperatureList }">
				<a id=""
					href="${pageContext.request.contextPath}/host/getHostTimeTemperatureListExcel"
					role="button" class="btn" data-toggle="modal">下载筛选数据</a>
			</c:if>
		</div>

		<div class="modal fade" id="modal-container-677212" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">请输入条件：</h4>
						<br>
						<div class="alert alert-dismissable alert-warning">
							<strong>注意!</strong>将查找输入时间前一天的数据,如果不输入则默认为当前时间
						</div>
					</div>
					<form role="form" method="post"
						action="${pageContext.request.contextPath}/host/getHostTimeTemperatureList"
						name="form">
						<div class="modal-body">
							<div class="form-group">
								<label for="exampleInputEmail1">请输入查询时间：</label><br> <input
									type="number" min="2000" max="2100" id="exampleInputEmail1"
									name="beYear" />年 <input type="number" min="1" max="12"
									id="exampleInputEmail1" name="beMonth" />月 <input
									type="number" min="1" max="31" id="exampleInputEmail1"
									name="beDay" />日 <input type="number" min="1" max="24"
									id="exampleInputEmail1" name="beHH" />时 <input type="number"
									min="0" max="59" id="exampleInputEmail1" name="beMM" />分 <input
									type="number" min="0" max="59" id="exampleInputEmail1"
									name="beSS" />秒

							</div>
							<label><input type="hidden" name="hostGuid"
								value="${host.hostGuid}" /></label>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">确认</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="scrollTbody">
		<table class="table" class="scrollTbody">
			<thead>
				<tr>
					<th>编号</th>
					<th>主机号</th>
					<th>通道号</th>
					<th>通道名</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody style="color: #ffffff">
				<c:if test="${empty temperatureList }">
					<tr>
						<td><strong>暂无数据!</strong>请选择其他主机.</td>
					</tr>
				</c:if>
				<c:if test="${!empty temperatureList }">
					<c:forEach items="${temperatureList}" var="t">
						<tr>
							<td>${t.temId}</td>
							<td>${t.hostGuid}</td>
							<td>${t.channelId}</td>
							<td>${t.channelName}</td>
							<td>${t.temTime}</td>
							<td><a
								href="${pageContext.request.contextPath}/host/getHostTemperature?temId=${t.temId}"><button
										type="button" class="btn btn-info btn-sm">温度曲线</button></a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
