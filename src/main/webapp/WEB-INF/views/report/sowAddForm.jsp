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
							<c:forEach var="j" begin="1" end="27">
							<tbody>
								<tr>
									<td style="width:3%">${j+(i*27)}</td>
									<td style="width:5%"></td>
									<td class="dropdown" style="width:4%" onclick="worknameDropdown(this)">
										<span class="selected">현장</span>
										<ul class="dropdown-menu">
											<c:forEach var="sowWorkName" items="${sowWorkName}">
											<li onclick="selectWorkname(this)">${sowWorkName.work_name_total}</li>
											</c:forEach>
										</ul>
									</td>
									<td style="width:3%"></td>
									<td style="width:3%"></td>
									<td style="width:3%"></td>
									<td style="width:4%"></td>
							</tbody>
							</c:forEach>
							</table>
						</div>
				</c:forEach>
			</section>
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
		td.querySelector(".selected").innerText = li.innerText;
		td.querySelector(".dropdown-menu").style.display = "none";
		
		event.stopPropagation();
	}
	
	document.addEventListener("click", function (e) {
		document.querySelectorAll(".dropdown").forEach(td => {
			if (!td.contains(e.target)) {
				td.querySelector(".dropdown-menu").style.display = "none";
			}
		});
	});
</script>
<script src="${contextPath}/resources/js/script.js"></script>
</html>