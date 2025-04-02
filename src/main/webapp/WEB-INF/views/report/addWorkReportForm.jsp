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
					<li class="li-wrap"> 보고서 1 </li>
					<li class="li-wrap"> 보고서 2 </li>
					<li class="li-wrap"> 보고서 3 </li>
					<li class="li-wrap"> 보고서 4 </li>
				</ul>
			</div>
			<div class="report-name">
				<p>작    업    일    보(Rev.3)</p>
			</div>
			<article>
				<section class="report-date-sign"> 
					<div class="report-date">
						<table class="table-control-report">
							<tr>
								<td style="height:30%;"><b>No</b></td>
								<td style="height:30%;"><b>사업소</b></td>
								<td style="height:30%;"><b>날짜</b></td>
								<td style="height:30%;"><b>요일</b></td>
								<td style="height:30%;"><b>날씨</b></td>
							</tr>
							<tr>
							    <td style="height:70%;">1</td>
							    <td style="height:70%;">1</td>
							    <td style="height:70%;">1</td>
							    <td style="height:70%;">1</td>
								<td style="height:70%;">1</td>
							</tr>
						</table>
					</div>
					<div class="report-sign">
						<table class="table-control-report">
							<tr>
								<tr>
								    <td rowspan="3"><b>결재</b></td>
								    <td colspan="2" style="height:20%;"><b>사업소</b></td>
								    <td colspan="2" style="height:20%;"><b>본사</b></td>
								</tr>
								<tr>
								    <td style="height:20%;">담당</td>
								    <td style="height:20%;">소장</td>
								    <td style="height:20%;">담당</td>
								    <td style="height:20%;">팀장</td>
								</tr>
								<tr>
								    <td style="height:60%;">1</td>
								    <td style="height:60%;">1</td>
								    <td style="height:60%;">1</td>
								    <td style="height:60%;">1</td>
								</tr>
							</tr>
						</table>
					</div>
				</section>
				<h3><b> 1. 작업현황</b></h3>
				<section>
					<div class="work-rate">
						<table class="table-control-work">
							<tr>
								<td rowspan="2" style="width:4%;"><b>No</b></td>
								<td rowspan="2" style="width:14%;"><b>현 장</b></td>
								<td colspan="5"><b>작 업 량(완료 / 전체)</b></td>
								<td rowspan="2" style="width:14%;"><b>투입인원</b></td>
								<td colspan="3"><b>보 유 장 비 (수량)</b></td>
							</tr>
							<tr>
								<td style="width:7%;">RT</td>
								<td style="width:7%;">PA-UT</td>
								<td style="width:7%;">TOFD</td>
								<td style="width:7%;">UT</td>
								<td style="width:7%;">M/PT</td>
								<td style="width:7%;">γ-ray</td>
								<td style="width:7%;">PA-UT</td>
								<td style="width:19%;">차 량</td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<form name="addWorkReport" method="post" action="${contextPath}/report/addWorkReport.do">
								<c:forEach var="addReport_total" items="${addReport_total}">
								<c:set var="i" value="${i+1}" />
									<tr>
										<td style="width:4%;">${i}</td>
										<td style="width:14%;">${addReport_total.work_name_total}</td>
										<td style="width:7%;"><input type="text" name="work_amount_RT"> / ${addReport_total.work_amount_RT_total}</td>
										<td style="width:7%;"><input type="text" name="work_amount_PAUT"> / ${addReport_total.work_amount_PAUT_total}</td>
										<td style="width:7%;"><input type="text" name="work_amount_TOFD"> / ${addReport_total.work_amount_TOFD_total}</td>
										<td style="width:7%;"><input type="text" name="work_amount_UT"> / ${addReport_total.work_amount_UT_total}</td>
										<td style="width:7%;"><input type="text" name="work_amount_MPT"> / ${addReport_total.work_amount_MPT_total}</td>
										<td style="width:14%;"><input type="text" name="work_manpower"> / ${addReport_total.work_manpower_total}</td>
										<td style="width:7%;">${addReport_total.work_xray_total}</td>
										<td style="width:7%;">${addReport_total.work_PAUT_total}</td>
										<td style="width:19%;">${addReport_total.work_charyang_total}</td>
									</tr>
								</c:forEach>
								<tr>
								<td colspan="11" style="text-align: right;">
									<button type="submit">전송</button>
								</td>
							</form>
<!--							<form name="addWorkReport" method="post" action="${contextPath}/report/addWorkReport.do">-->
<!--								<c:forEach var="addReport_total" items="${addReport_total}">-->
<!--									<tr>-->
<!--										<td style="width:4%;">${addReport_total.work_num_total}</td>-->
<!--										<td style="width:14%;">${addReport_total.work_name_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_amount_RT"> / ${addReport_total.work_amount_RT_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_amount_PAUT"> / ${addReport_total.work_amount_PAUT_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_amount_TOFD"> / ${addReport_total.work_amount_TOFD_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_amount_UT"> / ${addReport_total.work_amount_UT_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_amount_MPT"> / ${addReport_total.work_amount_MPT_total}</td>-->
<!--										<td style="width:14%;"><input type="text" name="work_manpower"> / ${addReport_total.work_manpower_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_xray"> / ${addReport_total.work_xray_total}</td>-->
<!--										<td style="width:7%;"><input type="text" name="work_PAUT"> / ${addReport_total.work_PAUT_total}</td>-->
<!--										<td style="width:19%;"><input type="text" name="work_charyang"> / ${addReport_total.work_charyang_total}</td>-->
<!--									</tr>-->
<!--								</c:forEach>-->
<!--								<button type="submit">전송</button>-->
<!--							</form>-->
						</table>
					</div>
				</section>
			</article>
    	</main>
    <%@ include file="../include/footer.jsp"%>	
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>