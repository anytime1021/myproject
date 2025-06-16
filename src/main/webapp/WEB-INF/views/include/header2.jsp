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
				<li>-</li>
				<li>-</li>
				<li>-</li>
				<li class="brochure">-</li>
			</ul>
		</nav>
	</div>
	<div class="menu-icon"></div>
</header>