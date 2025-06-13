<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<link rel="stylesheet" href="${contextPath}/resources/css/main2.css">
<header>
	<div class="logo">
		<img src="${contextPath}/resources/img/argusImage.jpg" alt="Argus Logo">
	</div>
	<div class="board-title">
		<nav>
			<ul class="menu">
				<li></li>
				<li><a href="${contextPath}/report/reportArea.do">보고서게시판</a></li>
				<li>사업분야</li>
				<li>기술현황</li>
				<li>고객센터</li>
				<li class="brochure">BROCHURE</li>
			</ul>
		</nav>
	</div>
	<div class="menu-icon"></div>
</header>