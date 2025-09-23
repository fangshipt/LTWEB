<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
</head>
<body>
	<h2>Quên mật khẩu</h2>

	<c:if test="${not empty alert}">
		<p style="color: red">${alert}</p>
	</c:if>

	<form method="post" action="${pageContext.request.contextPath}/forgot">
		<label>Username</label><br /> <input type="text" name="username"
			required /><br />
		<br /> <label>Email</label><br /> <input type="email" name="email"
			required /><br />
		<br /> <label>Mật khẩu mới</label><br /> <input type="password"
			name="newPassword" required minlength="4" /><br />
		<br /> <label>Xác nhận mật khẩu</label><br /> <input type="password"
			name="confirmPassword" required minlength="4" /><br />
		<br />

		<button type="submit">Đổi mật khẩu</button>
	</form>

	<p>
		<a href="${pageContext.request.contextPath}/login">Quay lại Đăng
			nhập</a>
	</p>
</body>
</html>
