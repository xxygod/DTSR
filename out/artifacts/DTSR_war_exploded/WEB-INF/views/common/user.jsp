<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="user" role="dialog" aria-hidden="true"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" aria-hidden="true" type="button"
					data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">请选择：</h4>
			</div>
			<div class="modal-footer">
				<form class="form-signin" method="post"
					action="${pageContext.request.contextPath}/user/getUser">
					<input type="submit" value="我的中心" class="btn btn-primary">
					<a href="${pageContext.request.contextPath}/user/logout"
						data-toggle="modal">退出登录</a>

				</form>
			</div>


		</div>

	</div>

</div>

