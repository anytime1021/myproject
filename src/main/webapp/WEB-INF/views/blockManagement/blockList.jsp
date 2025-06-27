<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<link rel="stylesheet" href="${contextPath}/resources/css/blockList.css">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>필터 사이드바</title>
</head>
<body>
	<%@ include file="../include/header2.jsp" %>
	<%@ include file="../include/sidebar.jsp" %>
	<%@ include file="../include/footer2.jsp" %>
</body>
</html>
