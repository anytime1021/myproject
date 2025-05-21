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
			<section class="section-flex">
				<div class="sow-title">
					<h3><b> 2. 근무현황 (${work_date})</b></h3>
				</div>
				<div class="sow-title2">
					<h3><b> 출장자(입) </b></h3>
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
			<a href="${contextPath}/sow/sowModDailyForm.do?work_date=${work_date}">수정하기</a>
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
	</main>
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>