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
    <title>내 정보 수정</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="mypage-container">
            <h2>마이페이지</h2>
			<form name="modMyPage" method="post" action="${contextPath}/login/modMyPage.do" enctype="multipart/form-data">
	            <table class="mypage-table">
					<input type="hidden" name="login_num" value="${myPageForm.login_num}">
	                <tr>
	                    <th>아이디</th>
	                    <td>${myPageForm.login_id}</td>
						<input type="hidden" name="login_id" value="${myPageForm.login_id}">
	                </tr>
	                <tr>
	                    <th>이름</th>
	                    <td><input type="text" name="login_name" value="${myPageForm.login_name}"></td>
	                </tr>
					<tr>
						<th>비밀번호</th>
						<td><a href="${contextPath}/login/modPasswordForm.do">비밀번호 변경하기</a></td>
					</tr>
	                <tr>
	                    <th>부서</th>
	                    <td><input type="text" name="login_department" value="${myPageForm.login_department}"></td>
	                </tr>
	                <tr>
	                    <th>직급</th>
	                    <td><input type="text" name="login_position" value="${myPageForm.login_position}"></td>
	                </tr>
	                <tr>
	                    <th>사업소</th>
	                    <td><input type="text" name="login_area" value="${myPageForm.login_area}"></td>
	                </tr>
					<tr>
						<th>사인추가</th>
						<td><input type="file" name="login_sign"></td>
					</tr>
					<tr>
						<th>현재사인</th>
						<c:choose>
							<c:when test="${empty myPageForm.login_signName}">
								<td>등록된 사인이 없습니다</td>
							</c:when>
							<c:otherwise>
								<td><img src="${contextPath}/resources/img/loginSign/${myPageForm.login_signName}"></td>
								<input type="hidden" name="login_signName" value="${myPageForm.login_signName}">
							</c:otherwise>
						</c:choose>
					</tr>
	            </table>
            <button type="submit" class="modMyPage">저장하기</button>
			</form>
        </div>
    </main>
		<%@ include file="../include/footer2.jsp"%>
</body>
</html>
