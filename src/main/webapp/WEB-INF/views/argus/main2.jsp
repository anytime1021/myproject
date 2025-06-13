<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Argus</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 
	<link rel="stylesheet" href="${contextPath}/resources/css/main2.css">     
	</head>
	<body>
		<%@ include file="../include/header2.jsp"%>
		<section class="hero">
			<div class="overlay-text">
				<p class="sub-text">Technology . . . is only as good as the people who use it.</p>
				<h1>技本과 人本의 기술자 집단 아거스</h1>
			</div>
		</section>
		<section class="content">
			<h2>사업분야</h2>
			<p>첨단의 기술도 기술자의 역량만큼 빛을 발하기 마련입니다.</p>
		</section>
		<%@ include file="../include/footer2.jsp"%>
	</body>
</html>
