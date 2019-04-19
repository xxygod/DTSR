<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./bootstrap3.3.5/css/bootstrap.min.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="./jquery1.11.3/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./bootstrap3.3.5/js/bootstrap.min.js"></script>
</head>
<body background="${pageContext.request.contextPath}/images/2.jpg">
	<%@include file="../common/topNav.jsp"%>
	<!--操作结果对话框-->
	<div class="modal fade" id="modal-result" role="dialog"
		aria-hidden="true" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" aria-hidden="true" type="button"
						data-dismiss="modal">×</button>
					<span class="modal-title" id="myModalLabel"> 提示信息 </span>
				</div>
				<div class="modal-body">
					<h3 class="text-warning"><%=session.getAttribute("dropOff")  %></h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="javascript:location.replace('allUserGoods')">确定</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	$('#modal-result').modal();
</script>
</body>
</html>