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
	<style>
		* {
			margin: 0;
			padding: 0;
		}
	</style>
</head>
<body>
	<main class="first-container">
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
						    <td style="height:70%;"></td>
						    <td style="height:70%;"></td>
						    <td style="height:70%;"></td>
						    <td style="height:70%;"></td>
							<td style="height:70%;"></td>
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
							    <td style="height:60%;"></td>
							    <td style="height:60%;"></td>
							    <td style="height:60%;"></td>
							    <td style="height:60%;"></td>
							</tr>
						</tr>
					</table>
				</div>
			</section>
			<b> 1. 작업현황</b>
			<section>
				<div class="work-rate">
					<table class="table-control-work">
						<tr>
							<td rowspan="2" style="width:4%;"><b>No</b></td>
							<td rowspan="2" style="width:14%;"><b>현 장</b></td>
							<td colspan="5"><b>작 업 량(완료 / 누계)</b></td>
							<td rowspan="2" style="width:14%;"><b>투입인원</b></td>
							<td colspan="4"><b>보 유 장 비 (수량)</b></td>
						</tr>
						<tr>
							<td style="width:7%;">${HowToWork.htw_htw1}</td>
							<td style="width:7%;">${HowToWork.htw_htw2}</td>
							<td style="width:7%;">${HowToWork.htw_htw3}</td>
							<td style="width:7%;">${HowToWork.htw_htw4}</td>
							<td style="width:7%;">${HowToWork.htw_htw5}</td>
							<td style="width:7%;">γ-ray</td>
							<td style="width:7%;">PA-UT</td>
							<td colspan="2" style="width:19%;">차 량</td>
						</tr>
						<!-- c:forEach문 적용 예정-->
						<c:forEach var="dailyReportViewMerged" items="${dailyReportViewMerged}" varStatus="status">
							<tr>
								<input type="hidden" name="fmonth_num" value="${dailyReportViewMerged.fmonth_num}">
								<td style="width:4%;">${status.count}</td>
								<td style="width:14%;">${dailyReportViewMerged.work_name}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_amount_HTW1} / ${dailyReportViewMerged.work_amount_HTW1_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_amount_HTW2} / ${dailyReportViewMerged.work_amount_HTW2_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_amount_HTW3} / ${dailyReportViewMerged.work_amount_HTW3_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_amount_HTW4} / ${dailyReportViewMerged.work_amount_HTW4_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_amount_HTW5} / ${dailyReportViewMerged.work_amount_HTW5_total}</td>
								<td style="width:14%;">${dailyReportViewMerged.work_manpower} / ${dailyReportViewMerged.work_manpower_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_xray_total}</td>
								<td style="width:7%;">${dailyReportViewMerged.work_PAUT_total}</td>
								<td colspan="2" style="width:19%;">${dailyReportViewMerged.work_charyang_total}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2" style="width:18%; text-align:center;">합 계</td>
							<td style="width:7%;">${dailySum.work_amount_HTW1_total} / ${totalSum.work_amount_HTW1_total}</td>
							<td style="width:7%;">${dailySum.work_amount_HTW2_total} / ${totalSum.work_amount_HTW2_total}</td>
							<td style="width:7%;">${dailySum.work_amount_HTW3_total} / ${totalSum.work_amount_HTW3_total}</td>
							<td style="width:7%;">${dailySum.work_amount_HTW4_total} / ${totalSum.work_amount_HTW4_total}</td>
							<td style="width:7%;">${dailySum.work_amount_HTW5_total} / ${totalSum.work_amount_HTW5_total}</td>
							<td style="width:14%;">${dailySum.work_manpower_total} / ${totalSum.work_manpower_total}</td>
							<td style="width:7%;"></td>
							<td style="width:7%;"></td>
							<td style="width:10%;">추가시간 총계</td>
							<td style="width:9%;"></td>
						</tr>
					</table>
				</div>
			</section>
<!--			<a href="${contextPath}/report/modDailyReportForm.do?work_date=${work_date}">수정하기</a>-->
<!--			<a href="${contextPath}/report/removeDailyReport.do?work_date=${work_date}">삭제하기</a>-->
<!--			<div style="display:flex; flex-direction: column; align-items: flex-end; gap: 4px;">-->
<!--			<c:if test="${getCountModLog != 0}">-->
<!--				<c:forEach var="getModDate" items="${getModDate}">-->
<!--					<div>-->
<!--						<label> 수정날짜 : ${getModDate.update_date} / 수정자 : ${getModDate.login_id}</label>-->
<!--					</div>-->
<!--				</c:forEach>-->
<!--				<div>-->
<!--					<a href="#"> 수정내역 보러가기 </a>-->
<!--				</div>-->
<!--			</c:if>-->
<!--			</div>-->
		</article>
		<article>
			<section class="section-flex">
				<div class="sow-title">
					<h4><b> 2. 근무현황 </b></h4>
				</div>
				<div class="sow-title2">
					<h4><b> 출장자(입) </b></h4>
				</div>
			</section>
			<section class="section-flex">
				<c:forEach var="i" begin="0" end="2">
					<div class="table-structure">
						<table>
							<thead>
								<tr>
									<td style="width:3%">No</td>
									<td style="width:4%">성명</td>
									<td style="width:5%">현장</td>
									<td style="width:3%">근무</td>
									<td style="width:3%">고정</td>
									<td style="width:3%">추가</td>
									<td style="width:4%">추가누계</td>
								</tr>
							</thead>
							<c:forEach var="j" begin="0" end="29" varStatus="status">
								<c:choose>
									<c:when test="${i == 0}">
										<c:set var="sowView" value="${sowViewList[status.index]}" />
										<c:set var="overtime" value="${sumOverTime[status.index]}" />
									</c:when>
									<c:when test="${i == 1}">
										<c:set var="sowView" value="${sowViewList[status.index + (i * 30)]}" />
										<c:set var="overtime" value="${sumOverTime[status.index + (i * 30)]}" />
									</c:when>
									<c:when test="${i == 2}">
										<c:set var="sowView" value="${sowViewList[status.index + (i * 30)]}" />
										<c:set var="overtime" value="${sumOverTime[status.index + (i * 30)]}" />
									</c:when>
								</c:choose>
								<tbody>
									<tr>
										<td style="width:3%">${j+1+(i*30)}</td>
										<td style="width:4%"><input type="text" value="${sowView.sowDWL_name}" readonly></td>
										<td style="width:5%"><input type="text" value="${sowView.sowDWL_work_name}" readonly></td>
										<td style="width:4%"><input type="text" value="${sowView.sowDWL_shift}" readonly></td>
										<td style="width:2%"><input type="text" value="${sowView.sowDWL_hours}" readonly></td>
										<td style="width:3%"><input type="text" value="${sowView.sowDWL_overtime}" readonly></td>
										<td style="width:4%"><input type="text" value="${overtime.dummyInt}" readonly></td>
										<input type="hidden" name="work_date" value="${work_Date}">
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</c:forEach>
			<div class="table-structure">
				<c:choose>
					<c:when test="${btInCount >= 11}">
						<c:set var="btInEnd" value="${btInCount}" />
						<c:set var="btOutEnd" value="${24 - btInCount}" />
					</c:when>
					<c:when test="${btOutCount >= 11}">
						<c:set var="btInEnd" value="${24 - btOutCount}" />
						<c:set var="btOutEnd" value="${btOutCount}" />
					</c:when>
					<c:otherwise>
						<c:set var="btInEnd" value="12" />
						<c:set var="btOutEnd" value="12" />
					</c:otherwise>
				</c:choose>
				<table>
					<thead>
						<tr>
							<td style="width:3%">No</td>
							<td style="width:4%">성명</td>
							<td style="width:5%">현장</td>
							<td style="width:4%">근무</td>
							<td style="width:2%">고정</td>
							<td style="width:3%">추가</td>
							<td style="width:4%">추가누계</td>
						</tr>
					</thead>
					<c:forEach var="j" begin="0" end="${btInEnd - 1}" varStatus="status">
						<c:set var="btIn" value="${btInList[status.index]}" />
						<c:set var="overtime_in" value="${btInSumOverTime[status.index]}" />
						<tbody>
							<tr>
								<td style="width:3%">${j+1}</td>
								<td style="width:4%"><input type="text" name="sowDWL_name_inout" value="${btIn.sowDWL_name}" readonly></td>
								<td style="width:5%"><input type="text" name="sowDWL_work_name_inout" class="sowDWL_work_name" value="${btIn.sowDWL_work_name}" readonly></td>
								<td style="width:4%"><input type="text" name="sowDWL_shift_inout" class="sowDWL_shift" value="${btIn.sowDWL_shift}" readonly></td>
								<td style="width:2%"><input type="text" name="sowDWL_hours_inout" value="${btIn.sowDWL_hours}" readonly></td>
								<td style="width:3%"><input type="text" name="sowDWL_overtime_inout" value="${btIn.sowDWL_overtime}" readonly></td>
								<td style="width:4%"><input type="text" value="${overtime_in.dummyInt}" readonly></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<table>
					<thead>
						<td style="font-size: 10px;"><b> 출장자(출) </b></td>
					</thead>
				</table>
				<table>
					<thead>
						<tr>
							<td style="width:3%">No</td>
							<td style="width:4%">성명</td>
							<td style="width:5%">현장</td>
							<td style="width:4%">근무</td>
							<td style="width:2%">고정</td>
							<td style="width:3%">추가</td>
							<td style="width:4%">추가누계</td>
						</tr>
					</thead>
					<c:forEach var="j" begin="0" end="${btOutEnd - 1}" varStatus="status">
						<c:set var="btOut" value="${btOutList[status.index]}" />
						<c:set var="overtime_out" value="${btOutSumOverTime[status.index]}" />
						<tbody>
							<tr>
								<td style="width:3%">${j+1}</td>
								<td style="width:4%"><input type="text" name="sowDWL_name_inout" value="${btOut.sowDWL_name}" readonly></td>
								<td style="width:5%"><input type="text" name="sowDWL_work_name_inout" class="sowDWL_work_name" value="${btOut.sowDWL_work_name}" readonly></td>
								<td style="width:4%"><input type="text" name="sowDWL_shift_inout" class="sowDWL_shift" value="${btOut.sowDWL_shift}" readonly></td>
								<td style="width:2%"><input type="text" name="sowDWL_hours_inout" value="${btOut.sowDWL_hours}" readonly></td>
								<td style="width:3%"><input type="text" name="sowDWL_overtime_inout" value="${btOut.sowDWL_overtime}" readonly></td>
								<td style="width:4%"><input type="text" value="${overtime_out.dummyInt}" readonly></td>
							</tr>
						</tbody>
					</c:forEach>
					<tbody>
						<tr>
							<td colspan="5"> 총   원 </td>
							<td colspan="2"><input type="text" value="${totalEmployee}"></td>
						</tr>
						<tr>
							<td colspan="5"> 출장(입) </td>
							<td colspan="2"><input type="text" value="${btInCount}"></td>
						</tr>
						<tr>
							<td colspan="5"> 출장(출) </td>
							<td colspan="2"><input type="text" value="${btOutCount}"></td>
						</tr>
						<tr>
							<td colspan="5"> 현 재 원 </td>
							<td colspan="2"><input type="text" value="${totalEmployee + btInCount - btOutCount}"></td>
						</tr>
					</tbody>
				</table>
			</div>
			</section>
<!--			<a href="${contextPath}/sow/sowModDailyForm.do?work_date=${work_date}">수정하기</a>-->
			<section class="section-flex" style="margin-top:15px;">
				<div class="table-structure">
					<table>
						<thead>
							<tr>
								<td style="width:5%; height:30px;">구분</td>
								<c:forEach var="sowWorkName" items="${sowWorkName}">
									<td>${sowWorkName.fmonth_name}</td>
								</c:forEach>
								<td>합계</td>
								<td style="width:6%; height:30px;">교육</td>
								<td style="width:6%; height:30px;">출장(입)</td>
								<td style="width:6%; height:30px;">출장(출)</td>
								<td style="width:6%; height:30px;">경조</td>
								<td style="width:6%; height:30px;">시험</td>
								<td style="width:6%; height:30px;">연차</td>
								<td style="width:6%; height:30px;">병가</td>
								<td style="width:6%; height:30px;">훈련</td>
								<td style="width:6%; height:30px;">기타</td>
								<td style="width:6%; height:30px;">비고</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="1" end="3">
								<tr>
									<c:if test="${i eq 1}">
										<td style="width:5%; height:40px; min-height:40px;">주 추가 계</td>
										<c:forEach var="dayNightOvertime" items="${dayNightOvertime}">
											<td>${dayNightOvertime.weeklyShiftAdditional}</td>
										</c:forEach>
									</c:if>
									<c:if test="${i eq 2}">
										<td style="width:5%; height:40px; min-height:40px;">야 고정 계</td>
										<c:forEach var="dayNightOvertime" items="${dayNightOvertime}">
											<td>${dayNightOvertime.nightShift}</td>
										</c:forEach>
									</c:if>
									<c:if test="${i eq 3}">
										<td style="width:5%; height:40px; min-height:40px;">야 추가 계</td>
										<c:forEach var="dayNightOvertime" items="${dayNightOvertime}">
											<td>${dayNightOvertime.nightShiftAdditional}</td>
										</c:forEach>
									</c:if>
									<c:if test="${i eq 1}">
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '교육'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="btInList" items="${btInList}">
												${btInList.sowDWL_name}
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="btOutList" items="${btOutList}">
												${btOutList.sowDWL_name}
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '경조'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '시험'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '연차'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '병가'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '훈련'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
											<c:forEach var="shiftType" items="${shiftType}">
												<c:if test="${shiftType.sowDWL_shift == '기타'}">
													${shiftType.sowDWL_name}
												</c:if>
											</c:forEach>
										</td>
										<td style="width:6%;" rowspan="3">
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</article>
		<article>
			<section class="section-flex"> 
				<div>
					<b> 3. 실 적 </b>
				</div>
			</section>
			<section class="section-flex">
				<div class="table-structure">
					<table>
						<thead>
							<tr>
								<td>No</td>
								<td>현장</td>
								<td>금월예상금액</td>
								<td>금희</td>
								<td>누계(1일~30일)</td>
								<td>달성도(%)</td>
								<td>비고</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="resultsList" items="${resultsList}" varStatus="status">
								<c:set var="sumFmonth_profits" value="${sumFmonth_profits + resultsList.fmonth_profits}" />
								<c:set var="sumResults_dailyprofits" value="${sumResults_dailyprofits + resultsList.results_dailyprofits}" />
								<c:set var="sumResults_sum" value="${sumResults_sum + resultsList.results_sum}" />
								<tr>
									<td>${status.count}</td>
									<td><input type="text" name="fmonth_name" value="${resultsList.fmonth_name}" readonly></td>
									<td><input type="text" name="fmonth_profits" value="${resultsList.fmonth_profits}" readonly></td>
									<td><input type="text" name="results_dailyprofits" value="${resultsList.results_dailyprofits}"></td>
									<td><input type="text" name="results_sum" value="${resultsList.results_sum}" readonly></td>
									<td><input type="text" name="results_achievement" value="${resultsList.results_achievement}" readonly></td>
									<td><input type="text" name="note" value="${resultsList.note}" readonly></td>
								</tr>
							</c:forEach>
						</tbody>
						<tbody>
							<tr>
								<td colspan="2">계</td>
								<td><input type="text" value="${resultsSum.fmonth_profits}" readonly></td>
								<td><input type="text" value="${resultsSum.results_dailyprofits}" readonly></td>
								<td><input type="text" value="${resultsSum.results_sum}" readonly></td>
								<td><input type="text" value="${resultsSum.results_achievement}" readonly></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>
		</article>
	</main>
</body>
<script src="${contextPath}/resources/js/script.js"></script>
<script>
	$(document).ready(function() {
		var table = document.getElementById('.table-control-work');
		var colList = table.cols;
	})
</script>
</html>