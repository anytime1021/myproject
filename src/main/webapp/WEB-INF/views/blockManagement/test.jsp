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
    <link rel="stylesheet" href="${contextPath}/resources/css/test.css">

</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
		<div class="board-container">
		  <table class="board-table">
		    <thead>
		      <tr>
		        <th>No</th>
		        <th>제목</th>
		        <th>작성자</th>
		        <th>작성일</th>
		        <th>조회수</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>1</td>
		        <td><a href="#">디자인 예쁜 게시판 만들어봤어요!</a></td>
		        <td>홍길동</td>
		        <td>2025-07-02</td>
		        <td>123</td>
		      </tr>
		      <tr>
		        <td>2</td>
		        <td><a href="#">UI 디테일 잡는 팁 정리</a></td>
		        <td>유재석</td>
		        <td>2025-07-01</td>
		        <td>87</td>
		      </tr>
		    </tbody>
		  </table>
		</div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
