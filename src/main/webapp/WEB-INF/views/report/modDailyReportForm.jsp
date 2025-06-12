<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<body>
	<main class="first-container">
		<article>
			<section>
				<form autocomplete="off" name="modDailyReport" onsubmit="removeCommaBeforeSubmit()" method="post" action="${contextPath}/report/modDailyReport.do">
					<section class="work-rate-flex"> 
						<div class="work-basic">
							<b> 1. 작업현황 </b>
						</div>
					</section>
					<label style="text-align:center;">날 짜 : </label><input type="date" name="work_date" value="${work_date}" placeholder="날짜를 입력해 주세요" style="text-align: center; border:1px solid black;"> 
					<label style="text-align:center;">날 씨 : </label>
					<select name="weather">
						<option value="맑음" ${weatherDayOfWeek.weather == '맑음' ? 'selected' : ''}>맑음</option>
						<option value="흐림" ${weatherDayOfWeek.weather == '흐림' ? 'selected' : ''}>흐림</option>
						<option value="비" ${weatherDayOfWeek.weather == '비' ? 'selected' : ''}>비</option>
						<option value="비/소나기" ${weatherDayOfWeek.weather == '비/소나기' ? 'selected' : ''}>비/소나기</option>
						<option value="눈" ${weatherDayOfWeek.weather == '눈' ? 'selected' : ''}>눈</option>
						<option value="안개" ${weatherDayOfWeek.weather == '안개' ? 'selected' : ''}>안개</option>
					</select>
					<br>
					<label style="text-align:center;">제 목 : </label><input type="text" name="board_title" value="${board_title}" placeholder="제목을 입력해 주세요" style="text-align: center; border:1px solid black; width:500px;">
					<div class="work-rate">
						<table class="table-control-work">
							<tr>
								<td rowspan="2" style="width:4%;"><b>No</b></td>
								<td rowspan="2" style="width:14%;"><b>현 장</b></td>
								<td colspan="5"><b>작 업 량</b></td>
								<td rowspan="2" style="width:14%;"><b>투입인원</b></td>
								<td colspan="3"><b>보 유 장 비 (수량)</b></td>
							</tr>
							<tr>
								<td style="width:7%;">${HowToWork.htw_htw1}</td>
								<td style="width:7%;">${HowToWork.htw_htw2}</td>
								<td style="width:7%;">${HowToWork.htw_htw3}</td>
								<td style="width:7%;">${HowToWork.htw_htw4}</td>
								<td style="width:7%;">${HowToWork.htw_htw5}</td>
								<td style="width:7%;">γ-ray</td>
								<td style="width:7%;">PA-UT</td>
								<td style="width:19%;">차 량</td>
							</tr>
							<!-- c:forEach문 적용 예정-->
							<c:forEach var="selectFmonth" items="${selectFmonth}" varStatus="status">
								<c:set var="workrateBefore" value="${workrateFormBefore[status.index]}" />
								<tr>
									<td style="width:4%;">${status.index + 1}</td>
									<td style="width:14%;"><input type="text" name="work_name" value="${selectFmonth.fmonth_name}" hidden> ${selectFmonth.fmonth_name}</td>
									<td style="width:7%;"><input type="text" name="work_amount_HTW1" value="${workrateBefore.work_amount_HTW1}"></td>
									<td style="width:7%;"><input type="text" name="work_amount_HTW2" value="${workrateBefore.work_amount_HTW2}"></td>
									<td style="width:7%;"><input type="text" name="work_amount_HTW3" value="${workrateBefore.work_amount_HTW3}"></td>
									<td style="width:7%;"><input type="text" name="work_amount_HTW4" value="${workrateBefore.work_amount_HTW4}"></td>
									<td style="width:7%;"><input type="text" name="work_amount_HTW5" value="${workrateBefore.work_amount_HTW5}"></td>
									<td style="width:14%;"><input type="text" name="work_manpower" value="${workrateBefore.work_manpower}"></td>
									<td style="width:7%;"><input type="text" name="work_xray" value="${workrateBefore.work_xray}"></td>
									<td style="width:7%;"><input type="text" name="work_PAUT" value="${workrateBefore.work_PAUT}"></td>
									<td style="width:19%;"><input type="text" name="work_charyang" value="${workrateBefore.work_charyang}"></td>
									<input type="hidden" name="fmonth_num" value="${selectFmonth.fmonth_num}">
								</tr>
							</c:forEach>
						</table>
					</div>
				</section>
				<section class="section-flex"> 
					<div class="sow-title">
						<b> 2. 근무현황 </b>
					</div>
					<div class="sow-title2">
						<b> 출장자(입) </b>
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
										<td style="width:4%">근무</td>
										<td style="width:2%">고정</td>
										<td style="width:3%">추가</td>
										<td style="width:4%">추가누계</td>
									</tr>
								</thead>
								<c:forEach var="j" begin="0" end="29" varStatus="status">
									<c:choose>
										<c:when test="${i == 0}">
											<c:set var="employeeList" value="${empList[status.index]}" />
											<c:set var="countIndex" value="${status.index}" />
											<c:set var="sowView" value="${sowViewList[status.index]}" />
										</c:when>
										<c:when test="${i == 1}">
											<c:set var="employeeList" value="${empList[status.index + (i * 30)]}" />
											<c:set var="countIndex" value="${status.index + (i * 30)}" />
											<c:set var="sowView" value="${sowViewList[status.index + (i * 30)]}" />
										</c:when>
										<c:when test="${i == 2}">
											<c:set var="employeeList" value="${empList[status.index + (i * 60)]}" />
											<c:set var="countIndex" value="${status.index + (i * 30)}" />
											<c:set var="sowView" value="${sowViewList[status.index + (i * 60)]}" />
										</c:when>
									</c:choose>
									<tbody>
										<tr>
											<td style="width:3%">${j+1+(i*30)}</td>
											<td style="width:4%"><input type="text" name="sowDWL_name" value="${employeeList.emp_name}" readonly></td>
											<td class="dropdown" style="width:5%" onclick="worknameDropdown(this)">
												<span class="selected">${sowView.sowDWL_work_name}</span>
												<input type="hidden" name="sowDWL_work_name" class="sowDWL_work_name" value="${sowView.sowDWL_work_name}">
												<ul class="dropdown-menu">
													<c:forEach var="fmonth_name" items="${fmonthName}">
														<li onclick="selectWorkname(this)">${fmonth_name.fmonth_name}</li>
													</c:forEach>
														<li onclick="selectWorkname(this)">울산</li>
														<li onclick="selectWorkname(this)">마산</li>
														<li onclick="selectWorkname(this)">창원</li>
														<li onclick="selectWorkname(this)">여수</li>
														<li onclick="selectWorkname(this)">서산</li>
												</ul>
											</td>
											<td class="dropdown" style="width:4%" onclick="worknameDropdown(this)">
												<span class="shiftSelected">${sowView.sowDWL_shift}</span>
												<input type="hidden" name="sowDWL_shift" class="sowDWL_shift" value="${sowView.sowDWL_shift}">
												<ul class="dropdown-menu">
													<li onclick="selectWorkname(this)">주</li>
													<li onclick="selectWorkname(this)">야</li>
													<li onclick="selectWorkname(this)">교육</li>
													<li onclick="selectWorkname(this)">출장(출)</li>
													<li onclick="selectWorkname(this)">경조</li>
													<li onclick="selectWorkname(this)">시험</li>
													<li onclick="selectWorkname(this)">연차</li>
													<li onclick="selectWorkname(this)">병가</li>
													<li onclick="selectWorkname(this)">훈련</li>
													<li onclick="selectWorkname(this)">비고</li>
												</ul>
											</td>
											<td style="width:2%"><input type="text" name="sowDWL_hours" value="${hoursList[countIndex]}"></td>
											<td style="width:3%"><input type="text" name="sowDWL_overtime" value="${sowView.sowDWL_overtime}"></td>
											<td style="width:4%"><input type="text" value="" readonly></td>
											<input type="hidden" name="emp_num" value="${employeeList.emp_num}">
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
								<c:set var="btInEmpList" value="${btInList[status.index]}" />
								<c:set var="btInData" value="${btInListData[status.index]}" />
								<tbody>
									<tr>
										<td style="width:3%">${j+1}</td>
										<td style="width:4%"><input type="text" name="sowDWL_name_in" value="${btInEmpList.emp_name}" readonly></td>
										<td style="width:5%"><input type="text" name="sowDWL_work_name_in" class="sowDWL_work_name" value="${btInData.sowDWL_work_name}"></td>
										<td style="width:4%"><input type="text" name="sowDWL_shift_in" class="sowDWL_shift" value="${btInData.sowDWL_shift}"></td>
										<td style="width:2%"><input type="text" name="sowDWL_hours_in" value="${btInHoursList[status.index]}"></td>
										<td style="width:3%"><input type="text" name="sowDWL_overtime_in" value="${btInData.sowDWL_overtime}"></td>
										<td style="width:4%"><input type="text" value="" readonly></td>
										<input type="hidden" name="emp_num_in" value="${btInEmpList.emp_num}">
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
								<c:set var="btOutEmpList" value="${btOutList[status.index]}" />
								<c:set var="btOutData" value="${btOutListData[status.index]}" />
								<tbody>
									<tr>
										<td style="width:3%">${j+1}</td>
										<td style="width:4%"><input type="text" name="sowDWL_name_out" value="${btOutEmpList.emp_name}" readonly></td>
										<td style="width:5%"><input type="text" name="sowDWL_work_name_out" class="sowDWL_work_name" value="${btOutData.sowDWL_work_name}"></td>
										<td style="width:4%"><input type="text" name="sowDWL_shift_out" class="sowDWL_shift" value="${btOutData.sowDWL_shift}"></td>
										<td style="width:2%"><input type="text" name="sowDWL_hours_out" value="${btOutHoursList[status.index]}"></td>
										<td style="width:3%"><input type="text" name="sowDWL_overtime_out" value="${btOutData.sowDWL_overtime}"></td>
										<td style="width:4%"><input type="text" value="" readonly></td>
										<input type="hidden" name="emp_num_out" value="${btOutEmpList.emp_num}">
									</tr>
								</tbody>
							</c:forEach>
							<tbody>
								<tr>
									<td colspan="5"> 총   원 </td>
									<td colspan="2"><input type="text" value="-"></td>
								</tr>
								<tr>
									<td colspan="5"> 출장(입) </td>
									<td colspan="2"><input type="text" value="-"></td>
								</tr>
								<tr>
									<td colspan="5"> 출장(출) </td>
									<td colspan="2"><input type="text" value="-"></td>
								</tr>
								<tr>
									<td colspan="5"> 현 재 원 </td>
									<td colspan="2"><input type="text" value="-"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</section>
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
								<c:forEach var="fmonth_list" items="${fmonth_list}" varStatus="status">
									<c:set var="resultsList" value="${results_list[status.index]}" />
									<tr>
										<td>${status.count}</td>
										<td><input type="text" name="fmonth_name" value="${fmonth_list.fmonth_name}" readonly></td>
										<td>
											<fmt:formatNumber value="${fmonth_list.fmonth_profits}" pattern="#,###" />
											<input type="hidden" name="fmonth_profits" value="${fmonth_list.fmonth_profits}" readonly>
										</td>
										<td><input type="text" class="results_dailyprofits" name="results_dailyprofits" id="results_dailyprofits" oninput="formatComma(this)" value="${resultsList.results_dailyprofits_comma}"></td>
										<td>
											<fmt:formatNumber value="${resultsList.results_sum}" type="number" pattern="#,###" />
											<input type="hidden" name="results_sum" value="${resultsList.results_sum}" placeholder="-" readonly>
										</td>
										<td><input type="text" name="results_achievement" value="${resultsList.results_achievement}" placeholder="-" readonly></td>
										<td><input type="text" name="note" value="${resultsList.note}"></td>
										<input type="hidden" name="results_fmonth_num" value="${resultsList.fmonth_num}">
									</tr>
								</c:forEach>
						</table>
					</div>
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
	
	function formatComma(input) {
		const cursorPosition = input.selectionStart;
		
		let value = input.value.replace(/[^0-9]/g, '');
		
		input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		
		input.setSelectionRange(input.value.length, input.value.length);
	}
		
	function removeCommaBeforeSubmit() {
		const input = document.querySelectorAll(".results_dailyprofits");
		input.forEach(input => {
			input.value = input.value.replace(/,/g,'');
		});
	}
</script>
<script src="${contextPath}/resources/js/script.js"></script>
</html>