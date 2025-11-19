<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아거스 리포트</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
	    <div class="mypage-container">
	        <h2>마이페이지</h2>
			<form name="modPassword" id="modPassword" method="get" action="${contextPath}/login/modPassword.do">
		        <table class="mypage-table">
		            <tr>
		                <th>현재 비밀번호</th>
		                <td><input type="password" name="passwordNow" id="passwordNow"></td>
		            </tr>
		            <tr>
		                <th>변경 비밀번호</th>
		                <td><input type="password" name="changePassword" id="changePassword"></td>
		            </tr>
		            <tr>
		                <th>변경 비밀번호 확인</th>
		                <td><input type="password" name="changePasswordCheck" id="changePasswordCheck"></td>
		            </tr>
		        </table>
		    <button type="button" id="saveBtn" class="modMyPage">저장하기</button>
			</form>
	    </div>
    </main>
</body>
<script>
    const currentPassword = "${passwordForm.login_pwd}";

    const form = document.getElementById("modPassword");
    const passwordNow = document.getElementById("passwordNow");
    const changePassword = document.getElementById("changePassword");
    const changePasswordCheck = document.getElementById("changePasswordCheck");
    const saveBtn = document.getElementById("saveBtn");

    saveBtn.addEventListener("click", function() {
        // 현재 비밀번호 확인
        if (passwordNow.value !== currentPassword) {
            alert("현재 비밀번호가 맞지 않습니다.");
            passwordNow.focus();
            return;
        }

        // 변경 비밀번호와 확인 일치 여부 확인
        if (changePassword.value !== changePasswordCheck.value) {
            alert("변경 비밀번호와 확인이 일치하지 않습니다.");
            changePassword.focus();
            return;
        }

        // 모두 통과하면 form action 지정 후 전송
		alert("비밀번호가 변경되었습니다.");
        form.action = "${contextPath}/login/modPassword.do";
        form.method = "post";
        form.submit();
    });
</script>
</html>
