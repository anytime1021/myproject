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
            <table class="mypage-table">
                <tr>
                    <th>아이디</th>
                    <td>${myPage.login_id}</td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>${myPage.login_name}</td>
                </tr>
                <tr>
                    <th>부서</th>
                    <td>${myPage.login_department}</td>
                </tr>
                <tr>
                    <th>직급</th>
                    <td>${myPage.login_position}</td>
                </tr>
                <tr>
                    <th>사업소</th>
                    <td>${myPage.login_area}</td>
                </tr>
            </table>

            <a href="${contextPath}/login/modMyPageForm.do" class="modMyPage">수정하기</a>
        </div>

    </main>
</main>
</html>
