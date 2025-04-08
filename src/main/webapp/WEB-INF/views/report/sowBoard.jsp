<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<style>
	th, td {
		border: 1px solid black;
		padding: 10px;
	}
	
	thead {
		background-color: white;
	}
</style>
<body>
	<%@ include file="../include/header.jsp"%>
    	<main class="first-container">
			<div class="image-container" style="background-image:url(${contextPath}/resources/img/reportImage.jpg)">
				<p id="wrap">보고서</p>
			</div>
			<div class="report-menu">
				<ul class="ul-wrap">
					<li class="li-wrap dropdown">
						<span class="report-title"> 작업일보 </span>
						<ul class="dropdown-menu">
							<li><a href="${contextPath}/report/reportArea.do">작업현황</a></li>
							<li><a href="${contextPath}/report/sowBoard.do">근무현황</a></li>
							<li><a href="#">실적</a></li>
						</ul>
					</li>
					<li class="li-wrap"> 보고서 2 </li>
					<li class="li-wrap"> 보고서 3 </li>
					<li class="li-wrap"> 보고서 4 </li>
				</ul>
			</div>
			<div class="report-menu-banner">
				<p class="text-control">근무현황</p>
			</div>
			<div class="report-container">
				<div class="report-list">
					<p>게시판</p>
					<table class="table-control">
						<thead>
							<tr>
								<th style="width:10%;">NO</th>
								<th style="width:80%;">제 목</th>
								<th style="width:10%;">작성일</th>
							</tr>
						</thead>
						<c:forEach var="sowBoardList" items="${sowBoardList}">
						<c:set var="i" value="${i+1}" />
						<tbody>
							<tr>
								<td>${sowBoardList.row_num}</td>
								<td><a href="${contextPath}/report/reportView.do?board_date=${sowBoardList.board_date}">${sowBoardList.board_title}</a></td>
								<td>${sowBoardList.board_date}</td>
							</tr>
						</tbody>
						</c:forEach>
						<c:set var="getBoard_date" value="${sowBoardList[0].board_date}" />
						<a class="btn" href="${contextPath}/report/sowAddForm.do">작성하기</a>
					</table>
				</div>
			</div>
    	</main>
    <%@ include file="../include/footer.jsp"%>	
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>