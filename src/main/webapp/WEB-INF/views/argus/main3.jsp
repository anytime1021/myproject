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
	<%@ include file="../include/header2.jsp" %>
	<article style="text-align:center;">
		<img src="${contextPath}/resources/img/main3.jpg" style="width:90%; height:auto; display:block; margin:0 auto;"">
	</article>
	<%@ include file="../include/footer2.jsp" %>
</body>
</html>
