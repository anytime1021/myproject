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
</head>
<style>


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
			<div class="report-name">
				<p>작    업    일    보(Rev.3)</p>
			</div>
			<article>
				<h3><b> 1. 작업 현황 </b></h3>
				<br>
				<h3><b> 합계 </b></h3>
				<section>
					<div class="work-rate">
						<table class="table-control-work">
							<tr>
								<td style="width:10%;"><b>현장</b></td>
								<td style="width:12%;"><b>RT</b></td>
								<td style="width:17%;"><b>PAUT</b></td>
								<td style="width:17%;"><b>TOFD</b></td>
								<td style="width:17%;"><b>UT</b></td>
								<td style="width:12%;"><b>달성도(%)</b></td>
								<td style="width:15%;"><b>비고</b></td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<c:forEach var="item" items="${testList2}" varStatus="status">
								<tr>
									<td style="width:10%;">${item.work_name_total}</td>
									<td style="width:12%;">${item.work_amount_RT_total}</td>
									<td style="width:17%;">${item.work_amount_PAUT_total}</td>
									<td style="width:17%;">${item.work_amount_TOFD_total}</td>
									<td style="width:17%;">${item.work_amount_UT_total}</td>
									<td style="width:12%;"></td>
									<td style="width:15%;"></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</section>
			</article>
			<article>
				<h3><b> 울산 </b></h3>
				<section>
					<div class="work-rate">
						<table class="table-control-work">
							<tr>
								<td style="width:10%;"><b>현장</b></td>
								<td style="width:12%;"><b>RT</b></td>
								<td style="width:17%;"><b>PAUT</b></td>
								<td style="width:17%;"><b>TOFD</b></td>
								<td style="width:17%;"><b>UT</b></td>
								<td style="width:12%;"><b>달성도(%)</b></td>
								<td style="width:15%;"><b>비고</b></td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<c:forEach var="item" items="${ulsan}" varStatus="status">
								<tr>
									<td style="width:10%;">${item.work_name_total}</td>
									<td style="width:12%;">${item.work_amount_RT_total}</td>
									<td style="width:17%;">${item.work_amount_PAUT_total}</td>
									<td style="width:17%;">${item.work_amount_TOFD_total}</td>
									<td style="width:17%;">${item.work_amount_UT_total}</td>
									<td style="width:12%;"></td>
									<td style="width:15%;"></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</section>
			</article>
			<article>
				<h3><b> 여수 </b></h3>
				<section>
					<div class="work-rate">
						<table class="table-control-work">
							<tr>
								<td style="width:10%;"><b>현장</b></td>
								<td style="width:12%;"><b>RT</b></td>
								<td style="width:17%;"><b>PAUT</b></td>
								<td style="width:17%;"><b>TOFD</b></td>
								<td style="width:17%;"><b>UT</b></td>
								<td style="width:12%;"><b>달성도(%)</b></td>
								<td style="width:15%;"><b>비고</b></td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<c:forEach var="item" items="${yeosu}" varStatus="status">
								<tr>
									<td style="width:10%;">${item.work_name_total}</td>
									<td style="width:12%;">${item.work_amount_RT_total}</td>
									<td style="width:17%;">${item.work_amount_PAUT_total}</td>
									<td style="width:17%;">${item.work_amount_TOFD_total}</td>
									<td style="width:17%;">${item.work_amount_UT_total}</td>
									<td style="width:12%;"></td>
									<td style="width:15%;"></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</section>
			</article>
    	</main>
    <%@ include file="../include/footer.jsp"%>	
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>