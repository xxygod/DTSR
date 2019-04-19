<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="login" role="dialog" aria-hidden="true"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" aria-hidden="true" type="button"
					data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">用户登录</h4>
			</div>
			<div class="modal-body">
				<form class="form-signin" method="post"
					action="${pageContext.request.contextPath}/user/login">
					<h2 class="form-signin-heading">请输入:</h2>
					<input type="text" name="userName" id="userName"
						class="form-control" placeholder="账户名" required autofocus>
					<input type="password" name="userPass" id="userPass"
						class="form-control" placeholder="密码" required autofocus>
					<div class="modal-footer">
						<input type="submit" value="登录" class="btn btn-primary">
						<button class="btn btn-default" type="button" data-dismiss="modal">关闭窗口</button>
					</div>
				</form>

			</div>

		</div>

	</div>

</div>

