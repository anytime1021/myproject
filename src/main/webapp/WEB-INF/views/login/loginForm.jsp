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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>    
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
	<link rel="stylesheet" href="${contextPath}/resources/css/font.css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextPath}/resources/css/styles.css">            
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/styles.css">            
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
<head>
<body>
	<%@ include file="../include/header.jsp"%>
	<main class="login-container contents-container">
		<section class="login-form-wrap">
			<div class="login-box">
				<ul class="login-menu">
					<li>로그인</li>
				</ul>
				<form name="frmLogin" action="${contextPath}/login/login.do" method="post" class="login-form">								
					<ul class="form-list">
						<li><i class="bi bi-person"></i><input type="text" name="login_id" placeholder="아이디"></li>
						<li><i class="bi bi-lock"></i><input type="password" name="login_pwd" placeholder="비밀번호"></li>
					</ul>
					<input type="submit" value="로그인" class="submit-btn">	
				</form>
				<ul class="find-btn-wrap">
					<li><a href="${contextPath}/login/searchIdForm.do">아이디 찾기</a></li>
					<li><a href="${contextPath}/login/searchPasswordForm.do">비밀번호 찾기</a></li>
				</ul>
			</div>
	    </section>
	</main>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
	    $(document).ready(function() {
	        $('#findIdForm').on('submit', function(e) {
	            e.preventDefault();
	            $.ajax({
	                type: 'POST',
	                url: '/findUserId',
	                data: $(this).serialize(),
	                success: function(response) {
	                    $('#findIdResult').html('찾으신 아이디는: ' + response.u_id);
	                }
	            });
	        });

	        $('#findPasswordForm').on('submit', function(e) {
	            e.preventDefault();
	            $.ajax({
	                type: 'POST',
	                url: '/findUserPassword',
	                data: $(this).serialize(),
	                success: function(response) {
	                    $('#findPasswordResult').html('찾으신 비밀번호는: ' + response.u_password);
	                }
	            });
	        });
	    });
	</script>
	<%@ include file="../include/footer.jsp"%>
</body>
<script src="${contextPath}/resources/js/weather_location.js"></script>
<script src="${contextPath}/resources/js/script.js"></script>
</html>