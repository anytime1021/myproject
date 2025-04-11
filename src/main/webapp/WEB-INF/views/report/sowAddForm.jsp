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
					<h3><b> 2. 근무현황 </b></h3>
				</div>
				<div class="sow-title2">
					<h3><b> 출장자(입) </b></h3>
				</div>
			</section>
			<form name="sowAddDailyWorkLog" method="post" action="${contextPath}/report/sowAddDailyWorkLog.do">
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
												<c:set var="sowName" value="${sowMonthList[status.index]}" />
											</c:when>
											<c:when test="${i == 1}">
												<c:set var="sowName" value="${sowMonthList[status.index + (i * 27)]}" />
											</c:when>
											<c:when test="${i == 2}">
												<c:set var="sowName" value="${sowMonthList[status.index + (i * 27)]}" />
											</c:when>
										</c:choose>
										<tbody>
											<tr>
												<td style="width:3%">${j+1+(i*27)}</td>
												<td style="width:5%"><input type="text" name="sowDWL_name" value="${sowName.sowMWL_name}" readonly></td>
												<td class="dropdown" style="width:4%" onclick="worknameDropdown(this)">
													<span class="selected"></span>
													<input type="hidden" name="sowDWL_work_name" class="sowDWL_work_name">
													<ul class="dropdown-menu">
														<c:forEach var="sowWorkName" items="${sowWorkName}">
															<li onclick="selectWorkname(this)">${sowWorkName.work_name_total}</li>
														</c:forEach>
													</ul>
												</td>
												<td style="width:3%"><input type="text" name="sowDWL_shift"></td>
												<td style="width:3%"><input type="text" name="sowDWL_hours"></td>
												<td style="width:3%"><input type="text" name="sowDWL_overtime"></td>
												<td style="width:4%"><input type="text" value="" readonly></td>
												<input type="hidden" name="work_date" value="${work_Date}">
											</tr>
										</tbody>
									</c:forEach>
								</form>
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
<script>
	function worknameDropdown(td) {
		const menu = td.querySelector(".dropdown-menu");
		menu.style.display = (menu.style.display === "block") ? "none" : "block";
	}
	
	function selectWorkname(li) {
		const td = li.closest(".dropdown");
		const selectedText = li.innerText;
		td.querySelector(".selected").innerText = li.innerText;
		td.querySelector(".dropdown-menu").style.display = "none";
		
		const sowDWL_work_name = td.querySelector('input.sowDWL_work_name');
		if (sowDWL_work_name) sowDWL_work_name.value = selectedText;
		
		event.stopPropagation();
	}
	
	document.addEventListener("click", function (e) {
		document.querySelectorAll(".dropdown").forEach(td => {
			if (!td.contains(e.target)) {
				td.querySelector(".dropdown-menu").style.display = "none";
			}
		});
	});
	const allInputs = document.querySelectorAll("input[name='sowDWL_name']");
	allInputs.forEach(input => {
	  console.log("sowDWL_name:", input.value);
	});
	
	function submitAllForms() {
		const formParams = new URLSearchParams();
		const allForms = document.querySelectorAll('form[name="sowAddDailyWorkLog"]');
		
		allForms.forEach(form => {
			const inputs = form.querySelectorAll("input, select, textarea");
			inputs.forEach(input => {
				if (input.name) {
					formData.append(input.name, input.value);
				}
			});
		});
		
		const workDate = document.querySelector("input[name='work_date']");
		if (workDate) formData.append("work_date", workDate.value);
		
		fetch("/report/sowAddDailyWorkLog.do", {
			method: "POST",
			headers: {
				"Content-type": "application/x-www-form-urlencoded"
			},
			body: formData
		})
		.then(res => res.text())
		.then(result => {
			alert("전송 완료!");
			console.log(result);
		});
	}		
</script>
<script src="${contextPath}/resources/js/script.js"></script>
</html>