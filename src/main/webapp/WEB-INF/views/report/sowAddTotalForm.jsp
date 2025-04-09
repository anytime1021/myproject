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
					<h4>${searchDate}월 근무자 목록</h4>
				</div>
			</section>
			<section class="section-flex">
				<div class="table-structure">
					<table id="sowTable">
						<thead>
							<tr>
								<td style="width:3%">No</td>
								<td style="width:5%">성명</td>
							</tr>
						</thead>
						<tbody id="sowTbody">
							<c:forEach var="sowWorkName" items="${sowWorkName}" varStatus="status">
							<c:set var="i" value="${i+1}" />
							<tr>
								<td>${i}</td>
								<td style="width:3%">${sowWorkName.sowDML_name}</td>
							</tr>
							</c:forEach>
						</tbody>
						<tr style="height:40px;">
						</tr>
						<form name="addTotalReport" method="post" action="${contextPath}/report/sowAddTotal.do">
							<tr>
								<td style="width:3%;">+</td>
								<td style="width:5%;"><input type="text" name="sowDML_name"></td>
								<input type="text" name="searchDate" value="${searchDate}" class="searchDate" hidden>
							</tr>
							<tr>
					            <td colspan="2" style="text-align: right;">
					                <button type="submit">추가하기</button>
					            </td>
					        </tr>
						</form>
					</table>
				</div>
			</section>
		</article>
	</main>
</body>
<script>
	let rowCount = 1;
	
	function addRow() {
		const sowDML_name = document.getElementById("sowDML_name").value;
		const work_date = document.getElementById("work_date").value;
		
		if (!sowDML_name) {
			alert("값을 입력해 주세요.");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/report/sowAddTotal.do",
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify({
				sowDML_name: sowDML_name,
				work_date: work_date
			}),
			success: function (list) {
				$("#sowTbody").empty();
				let count = 1;
				list.forEach(item => {
					const rowHtml = `
						<tr>
							<td>${count}</td>
							<td>${item.sowDML_name}</td>
						</tr>
					`;
					$("#sowTbody").append(rowHtml);
					count++;
				});
				
				$("#sowDML_name").val("");
				$("#work_date").val("");
			},
			error: function(xhr, status, error) {
				console.error("에러 발생:", error);
			}
		});
	}
</script>
<script src="${contextPath}/resources/js/script.js"></script>
</html>