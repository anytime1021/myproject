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
<style>
	.table-control-report {
		width: 100%;
		height: 130px;
		border-collapse: collapse;
		text-align: center;
	}	

	.table-control-work {
		width: 100%;
		height: 50px;
		border-collapse: collapse;
		text-align: center;
	}	
	
	th, td {
		border: 1px solid black;
		padding: 0px;
	}
	
	.delete-top-border {
		border-top: 0px;
	}
	
	#wrap {
		position: absolute;
		font-size:70px;
		color: black;
		text-align:center;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
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
				<h3><b> 1. 작업현황</b></h3>
				<section>
					<div class="work-rate">
						<table id="table-control-work" class="table-control-work">
							<tr>
								<td style="width:4%;"><b>No</b></td>
								<td rowspan="2" style="width:14%;"><b>현 장</b></td>
								<td colspan="5"><b>작 업 량(완료 / 전체)</b></td>
								<td rowspan="2" style="width:14%;"><b>투입인원</b></td>
								<td colspan="4"><b>보 유 장 비 (수량)</b></td>
							</tr>
							<tr>
								<td style="width:7%;">RT</td>
								<td style="width:7%;">PA-UT</td>
								<td style="width:7%;">TOFD</td>
								<td style="width:7%;">UT</td>
								<td style="width:7%;">M/PT</td>
								<td style="width:7%;">γ-ray</td>
								<td style="width:7%;">PA-UT</td>
								<td></td>
								<td colspan="2" style="width:19%;">차 량</td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<c:forEach var="addReport_total" items="${addReport_total}" varStatus="status">
							<c:set var="i" value="${i+1}" />
								<tr data-id="${status.index}">
									<td style="width:4%;" class="row-no${status.index}">${i}</td>
									<td style="width:14%;" class="work_name_total${status.index}">${addReport_total.work_name_total}</td>
									<td style="width:7%;">${addReport_total.work_amount_RT_total}</td>
									<td style="width:7%;">${addReport_total.work_amount_PAUT_total}</td>
									<td style="width:7%;">${addReport_total.work_amount_TOFD_total}</td>
									<td style="width:7%;">${addReport_total.work_amount_UT_total}</td>
									<td style="width:7%;">${addReport_total.work_amount_MPT_total}</td>
									<td style="width:14%;">${addReport_total.work_manpower_total}</td>
									<td style="width:7%;">${addReport_total.work_xray_total}</td>
									<td style="width:7%;">${addReport_total.work_PAUT_total}</td>
									<td colspan="2" style="width:19%;">${addReport_total.work_charyang_total}</td>
								</tr>
							</c:forEach>
							<!-- 여기까지 적용 예정-->
								<tr>
									<td colspan="2" style="width:18%; text-align:center;">합 계</td>
									<td style="width:7%;" id="sum_RT">${addReport_total_sum.work_amount_RT_total}</td>
									<td style="width:7%;" id="sum_PAUT">${addReport_total_sum.work_amount_PAUT_total}</td>
									<td style="width:7%;" id="sum_TOFD">${addReport_total_sum.work_amount_TOFD_total}</td>
									<td style="width:7%;" id="sum_UT">${addReport_total_sum.work_amount_UT_total}</td>
									<td style="width:7%;" id="sum_MPT">${addReport_total_sum.work_amount_MPT_total}</td>
									<td style="width:14%;" id="sum_manpower">${addReport_total_sum.work_manpower_total}</td>
									<td style="width:7%;"></td>
									<td style="width:7%;"></td>
									<td style="width:10%;">추가시간 총계</td>
									<td style="width:9%;"></td>
								</tr>
							<tr style="height: 20px;"></tr>
							<form name="addTotalReport" method="post" action="${contextPath}/report/addTotalReport.do">
								<tr>
									<td style="width:4%;">+</td>
									<td style="width:14%;"><input type="text" name="work_name_total"></td>
									<td style="width:7%;"><input type="text" name="work_amount_RT_total"></td>
									<td style="width:7%;"><input type="text" name="work_amount_PAUT_total"></td>
									<td style="width:7%;"><input type="text" name="work_amount_TOFD_total"></td>
									<td style="width:7%;"><input type="text" name="work_amount_UT_total"></td>
									<td style="width:7%;"><input type="text" name="work_amount_MPT_total"></td>
									<td style="width:14%;"><input type="text" name="work_manpower_total"></td>
									<td style="width:7%;"><input type="text" name="work_xray_total"></td>
									<td style="width:7%;"><input type="text" name="work_PAUT_total"></td>
									<td colspan="2" style="width:19%;"><input type="text" name="work_charyang_total"></td>
									<input type="text" name="work_date_total" value="${board_date}" class="work_date_total" hidden>
								</tr>
								<tr>
						            <td colspan="12" style="text-align: right;">
						                <button type="submit">추가하기</button>
						            </td>
						        </tr>
							</form>
							<tr>
								<td colspan="12" style="text-align: left;">
									<a href="${contextPath}/report/modTotalReportForm.do?work_date=${board_date}">수정하기</a>
								</td>
							</tr>
							<tr>
								<td colspan="12" style="text-align: left;">
									<button id="deleteMode"> 삭제하기 </button>
								</td>
							</tr> 
						</table>
					</div>
				</section>
			</article>
    	</main>
    <%@ include file="../include/footer.jsp"%>	
</body>

<script src="${contextPath}/resources/js/script.js"></script>
</html>