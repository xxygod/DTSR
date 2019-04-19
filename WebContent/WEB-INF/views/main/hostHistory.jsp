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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史记录</title>
</head>

<!-- <script type="text/javascript">
var zhi 
function addClick(){ 
$(document).ready(function(){
var idsstr = "";
var zhi = "";
$(".mian_b_bg_xz input[name=channelId]").each(function(){ //遍历table里的全部checkbox	
idsstr += $(this).val() + ","; //获取所有checkbox的值
if($(this).attr("checked")) //如果被选中
zhi += $(this).val() + ","; //获取被选中的值
});
if(idsstr.length > 0) //如果获取到
idsstr = idsstr.substring(0, idsstr.length - 1); //把最后一个逗号去掉
if(zhi.length > 0) //如果获取到
zhi = zhi.substring(0, zhi.length - 1); //把最后一个逗号去掉
alert("所有checkbox的值：" + idsstr);
alert("被选中checkbox的值：" + zhi);
document.form.action="${pageContext.request.contextPath}/host/reg?channelIdList="+zhi
document.form.submit()
});
}

</script>
 -->

<body>
	<%@include file="../common/homeTop.jsp"%>
	<%@include file="../common/topNav.jsp"%>
	<a id="modal-677212" href="#modal-container-677212" role="button"
		class="btn" data-toggle="modal">请输入筛选条件</a>

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
						<strong>注意!</strong>任意一行要是全部不选或者全部不输入则将从全部范围查找！
					</div>

				</div>
				<form role="form" method="post"
					action="${pageContext.request.contextPath}/host/getAlarmRecord"
					name="form">
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail1">请选择通道：</label><br>
						</div>
						<div class="checkbox">
							<c:if test="${!empty channelList }">
								<c:forEach items="${channelList}" var="cl">
									<label><input type="checkbox" name="channelId"
										value="${cl.channelId}" />${cl.channelName}</label>
								</c:forEach>
							</c:if>
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">请输入起始时间：</label><br> <input
								type="number" min="2000" max="2100" id="exampleInputEmail1"
								name="beYear" />年 <input type="number" min="1" max="12"
								id="exampleInputEmail1" name="beMonth" />月 <input type="number"
								min="1" max="31" id="exampleInputEmail1" name="beDay" />日

						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">请输入终止时间：</label><br> <input
								type="number" min="2000" max="2100" name="reYear" />年 <input
								type="number" min="1" max="12" name="reMonth" />月 <input
								type="number" min="1" max="31" name="reDay" />日
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">请选择类型：</label><br>
						</div>
						<div class="checkbox">
							<label><input type="checkbox" name="arType2" value=2 />故障记录</label>
							<label><input type="checkbox" name="arType3" value=3 />升温报警</label>
							<label><input type="checkbox" name="arType4" value=4 />定温报警</label>
						</div>
						<label><input type="hidden" name="hostGuid"
							value="${channelList[0].hostGuid}" /></label>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">确认</button>
					</div>
				</form>
			</div>
		</div>
	</div>

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

			<c:if test="${empty arList }">
				<tr>
					<td><strong>暂无数据!</strong>请选择查询条件.</td>

				</tr>
			</c:if>

			<c:if test="${!empty arList }">
				<c:forEach items="${arList}" var="al">
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