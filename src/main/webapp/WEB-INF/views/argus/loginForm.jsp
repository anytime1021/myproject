<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>로그인 화면</title>
		<link rel="stylesheet" href="${contextPath}/resources/css/loginForm.css">
		<c:choose>
			<c:when test="${result=='loginFailed'}">
				<script>
					window.onload=function() {
						alert("아이디나 비밀번호가 틀립니다.");
					}
				</script>
			</c:when>
		</c:choose>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	</head>
	<body>
		<section>
			<div class="image">
				<img src="${contextPath}/resources/img/argusImage.jpg" alt="사내 로고">
				<form name="Login" action="${contextPath}/login/login.do" method="post">
					<fieldset>
						<ul>
							<li><label for="id">id </label><input type="text" name="login_id"></li>
							<li><label for="pw">pw </label><input type="password" name="login_pwd"></li>
						</ul>
						<input type="submit" value="로그인" class="submit-btn">
					</fieldset>
				</form>	
			</div>
		</section>
	</body>
</html>
		