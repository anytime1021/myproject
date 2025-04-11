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
					<c:forEach var="i" begin="0" end="3">
						<div class="table-structure">
							<table>
								<thead>
									<tr>
										<td style="width:3%">No</td>
										<td style="width:5%">성명</td>
										<td style="width:4%">현장</td>
										<td style="width:3%">근무</td>
										<td style="width:3%">고정</td>
										<td style="width:3%">추가</td>
										<td style="width:4%">추가누계</td>
									</tr>
								</thead>
								<c:forEach var="j" begin="0" end="26" varStatus="status">
									<c:choose>
										<c:when test="${i == 0}">
											<c:set var="sowView" value="${sowViewList[status.index]}" />
											<c:set var="overTime" value="${sumOverTime[status.index]}" />
										</c:when>
										<c:when test="${i == 1}">
											<c:set var="sowView" value="${sowViewList[status.index + (i * 27)]}" />
										</c:when>
										<c:when test="${i == 2}">
											<c:set var="sowView" value="${sowViewList[status.index + (i * 27)]}" />
										</c:when>
									</c:choose>
									<tbody>
										<tr>
											<td style="width:3%">${j+1+(i*27)}</td>
											<td style="width:5%"><input type="text" value="${sowView.sowDWL_name}" readonly></td>
											<td style="width:4%"><input type="text" value="${sowView.sowDWL_work_name}" readonly></td>
											<td style="width:3%"><input type="text" value="${sowView.sowDWL_shift}" readonly></td>
											<td style="width:3%"><input type="text" value="${sowView.sowDWL_hours}" readonly></td>
											<td style="width:3%"><input type="text" value="${sowView.sowDWL_overtime}" readonly></td>
											<td style="width:4%"><input type="text" value="${overTime.dummyInt}" readonly></td>
											<input type="hidden" name="work_date" value="${work_Date}">
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>
					</c:forEach>
					<tr>
						<td style="text-align:right;">
<!--							<button type="button" onclick="submitAllForms()">전송</button>-->
						</td>
					</tr>
				</form>
			</section>
			<button type="submit">전송</button>
		</article>
	</main>
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>