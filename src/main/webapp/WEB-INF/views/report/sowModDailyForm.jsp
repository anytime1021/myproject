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
			<form name="sowAddDailyWorkLog" method="post" action="${contextPath}/report/sowAddDailyWorkLog.do">
			<label style="text-align:center;">날 짜 : </label><input type="text" name="work_date" value=${work_date}>
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
											<td style="width:4%">성명</td>
											<td style="width:5%">현장</td>
											<td style="width:4%">근무</td>
											<td style="width:2%">고정</td>
											<td style="width:3%">추가</td>
											<td style="width:4%">추가누계</td>
										</tr>
									</thead>
										<c:forEach var="j" begin="0" end="26" varStatus="status">
											<c:choose>
												<c:when test="${i == 0}">
													<c:set var="employeeList" value="${empList[status.index]}" />
													<c:set var="sowView" value="${sowViewList[status.index]}" />
													<c:set var="overTime" value="${sumOverTime[status.index]}" />
												</c:when>
												<c:when test="${i == 1}">
													<c:set var="employeeList" value="${empList[status.index + (i * 27)]}" />
													<c:set var="sowView" value="${sowViewList[status.index + (i * 27)]}" />
													<c:set var="overTime" value="${sumOverTime[status.index + (i * 27)]}" />
												</c:when>
												<c:when test="${i == 2}">
													<c:set var="employeeList" value="${empList[status.index + (i * 27)]}" />
													<c:set var="sowView" value="${sowViewList[status.index + (i * 27)]}" />
													<c:set var="overTime" value="${sumOverTime[status.index + (i * 27)]}" />
												</c:when>
											</c:choose>
											<tbody>
												<tr>
													<td style="width:3%">${j+1+(i*27)}</td>
													<td style="width:5%"><input type="text" name="sowDWL_name" value="${employeeList.emp_name}" readonly></td>
													<td class="dropdown" style="width:4%" onclick="worknameDropdown(this)">
														<span class="selected">${sowView.sowDWL_work_name}</span>
														<input type="hidden" name="sowDWL_work_name" class="sowDWL_work_name" value="${sowView.sowDWL_work_name}">
														<ul class="dropdown-menu">
															<c:forEach var="fmonth_name" items="${fmonthName}">
																<li onclick="selectWorkname(this)">${fmonth_name.fmonth_name}</li>
															</c:forEach>
																<li onclick="selectWorkname(this)">사무실</li>
														</ul>
													</td>
													<td class="dropdown" style="width:3%" onclick="worknameDropdown(this)">
														<span class="shiftSelected">${sowView.sowDWL_shift}</span>
														<input type="hidden" name="sowDWL_shift" class="sowDWL_shift" value="${sowView.sowDWL_shift}">
														<ul class="dropdown-menu">
															<li onclick="selectWorkname(this)">주</li>
															<li onclick="selectWorkname(this)">야</li>
															<li onclick="selectWorkname(this)">교육</li>
															<li onclick="selectWorkname(this)">출장(입)</li>
															<li onclick="selectWorkname(this)">출장(출)</li>
															<li onclick="selectWorkname(this)">경조</li>
															<li onclick="selectWorkname(this)">시험</li>
															<li onclick="selectWorkname(this)">연차</li>
															<li onclick="selectWorkname(this)">병가</li>
															<li onclick="selectWorkname(this)">훈련</li>
															<li onclick="selectWorkname(this)">기타</li>
															<li onclick="selectWorkname(this)">비고</li>
														</ul>
													</td>
													<td style="width:3%"><input type="text" name="sowDWL_hours" value="${sowView.sowDWL_hours}"></td>
													<td style="width:3%"><input type="text" name="sowDWL_overtime" value="${sowView.sowDWL_overtime}"></td>
													<td style="width:4%"><input type="text" value="" readonly></td>
													<input type="hidden" name="sowDWL_num" value="${sowView.sowDWL_num}">
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
					</section>
				<div style="height:30px;">
					<button type="submit">전송</button>
				</div>
			</form>
		</article>
	</main>
</body>
<script>
	function worknameDropdown(td) {
	    // 기존 메뉴 닫기
	    document.querySelectorAll(".dropdown-menu-body").forEach(el => el.remove());

	    const rect = td.getBoundingClientRect();
	    const windowHeight = window.innerHeight;

	    // 선택 항목 복제
	    const menu = td.querySelector(".dropdown-menu").cloneNode(true);
	    menu.classList.add("dropdown-menu-body"); // body용 메뉴
	    menu.style.position = "fixed";
	    menu.style.left = rect.left + "px";
	    menu.style.width = rect.width + "px";
	    menu.style.zIndex = 9999;
	    menu.style.display = "block";
	    menu.style.maxHeight = "150px";
	    menu.style.overflowY = "auto";
	    menu.style.background = "white";
	    menu.style.border = "1px solid #ccc";
	    menu.style.boxShadow = "0 2px 6px rgba(0,0,0,0.2)";

	    // 아래 공간 부족 시 위로
	    const menuHeight = 150;
	    if (rect.bottom + menuHeight > windowHeight) {
	        menu.style.top = (rect.top - menuHeight) + "px";
	    } else {
	        menu.style.top = rect.bottom + "px";
	    }

	    document.body.appendChild(menu);

	    // 선택 시 동작 연결
		menu.querySelectorAll("li").forEach(li => {
		        li.addEventListener("click", function () {
		            const selectedSpan = td.querySelector("span");
		            const hiddenInput = td.querySelector("input[type='hidden']");

		            if (selectedSpan) {
		                selectedSpan.innerText = this.innerText;
		                selectedSpan.title = this.innerText;
		            }

		            if (hiddenInput) {
		                hiddenInput.value = this.innerText;
		            }

		            document.querySelectorAll(".dropdown-menu-body").forEach(el => el.remove());
		        });
		    });

	    // 바깥 클릭 시 닫기
	    document.addEventListener("click", function closeDropdown(e) {
	        if (!menu.contains(e.target) && !td.contains(e.target)) {
	            menu.remove();
	            document.removeEventListener("click", closeDropdown);
	        }
	    });
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
	const allInputs = document.querySelectorAll("input[name='empName']");
	allInputs.forEach(input => {
	  console.log("empName:", input.value);
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