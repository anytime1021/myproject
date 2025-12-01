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
    <title>시험편 성적서</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/styles3.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/boardStyle.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
			<div class="slider">
				<button class="prev" onclick="prev()">←</button>
				<img id="slide" src="${pageContext.request.contextPath}/resources/img/bSpec/${blockSpecView[0].file_name}" alt="Spec Image">
				<button class="next" onclick="next()">→</button>
			</div>
			<div class="contents-container">
				<div class="contents-list">
					<div class="btn-wrapper">
						<c:if test="${blockSpecView[0].login_area eq searchArea}">
							<a class="btn" style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black; align-items:right;" href="${contextPath}/blockManagement/removeBlockSpec.do?df_num=${df_num}">삭제하기</a>
						</c:if>
					</div>
				</div>
        	</div>
		</div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	const imgs = [
		<c:forEach var="blockSpecView" items="${blockSpecView}" varStatus="status">
			"${contextPath}/resources/img/bSpec/${blockSpecView.file_name}"<c:if test="${!status.last}">,</c:if>
		</c:forEach>
	];
	
	let idx = 0;
	function show() {
		document.getElementById("slide").src = imgs[idx];
	}
	function prev() {
		idx = (idx - 1 + imgs.length) % imgs.length;
		show();
	}
	function next() {
		idx = (idx + 1) % imgs.length;
		show();
	}
	
	window.onload = show;
	
	console.log("이미지 리스트:", imgs);
</script>
</html>
