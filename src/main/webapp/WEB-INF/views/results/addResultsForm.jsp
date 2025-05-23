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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아거스 리포트</title>    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextPath}/resources/css/styles.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/styles2.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
	<main class="first-container">
		<article>
			<form name="sowAddDailyWorkLog" method="post" action="${contextPath}/sow/sowAddDailyWorkLog.do">
			<label style="text-align:center;">날 짜 : </label><input type="date" name="work_date" placeholder="날짜를 입력해 주세요" style="text-align: center; border:1px solid black;">
			<a href="${contextPath}/sow/sowAddBtEmployeeForm.do" style="text-align:center;">출장인원설정</a>
				<section class="section-flex"> 
					<div class="sow-title">
						<b> 3. 실 적 </b>
					</div>
				</section>
				<section class="section-flex">
					<div>
					</div>
				</section>
				<div style="height:30px;">
					<button type="submit">전송</button>
				</div>
			</form>
		</article>
	</main>
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>