<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
function formCheck(){
var password = $("#password").val();
var confirmPassword = $("#confirmPassword").val();
if (password != confirmPassword) {
    alert("两次密码输入不一致");
    return false;
}
return true;
}
</script>

<div class="modal fade" id="reg" role="dialog" aria-hidden="true"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" aria-hidden="true" type="button"
					data-dismiss="modal">×</button>
				<h4 class="modal-title">用户注册</h4>
			</div>
			<div class="modal-body">
				<form class="form-signin" method="post"
					action="${pageContext.request.contextPath}/user/reg"
					onsubmit="return formCheck() ">
					<h2 class="form-signin-heading">请输入:</h2>
					<input type="text" name="userName" id="userName"
						class="form-control" placeholder="账户名" minlength="2"
						maxlength="10" size="10" required autofocus> <input
						type="text" name="userCommonName" id="userCommonName"
						class="form-control" placeholder="昵称" minlength="2" maxlength="10"
						size="10" required autofocus> <input type="password"
						name="userPass" id="userPass" class="form-control"
						placeholder="密码" minlength="8" maxlength="20" size="20" required
						autofocus> <input type="password" name="confirmPassword"
						id="confirmPassword" class="form-control" placeholder="确认密码"
						minlength="8" maxlength="20" size="20" required autofocus>
					<div class="modal-footer">
						<input type="submit" value="注册" class="btn btn-primary">
						<button class="btn btn-default" type="button" data-dismiss="modal">关闭窗口</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

