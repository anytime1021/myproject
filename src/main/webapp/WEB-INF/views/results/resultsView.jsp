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
			<label style="text-align:center;">날 짜 : <input type="text" value="${work_date}">
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
</html>