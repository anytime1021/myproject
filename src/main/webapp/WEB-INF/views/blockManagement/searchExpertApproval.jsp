<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>아거스 리포트</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/boardStyle.css">
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
            <div class="contents-container">
                <div class="contents-list">
					<div class="search-write">
						<div>
							<form class="search-box" method="get" action="${contextPath}/blockManagement/searchExpertApproval.do">
								<select name="searchType">
									<option value="idNumber" ${searchType == 'idNumber' ? 'selected' : ''}>식별번호</option>
									<option value="app_hnd_area" ${searchType == 'app_hnd_area' ? 'selected' : ''}>인계 사업소</option>
									<option value="app_rcv_area" ${searchType == 'app_rcv_area' ? 'selected' : ''}>인수 사업소</option>
									<option value="approval_status" ${searchType == 'approval_status' ? 'selected' : ''}>승인상태</option>
								</select>
								<div class="searchWithButton">
									<input type="text" name="searchQuery" value="${searchQuery}" placeholder="검색어 입력">
									<button type="submit" title="검색">&#128269;</button>
								</div>
							</form>
						</div>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:6%;"></th>
								<th style="width:18%;">식별번호</th>
								<th style="width:16%;">인계 사업소</th>
								<th style="width:16%;">인수 사업소</th>
								<th style="width:16%;">본사 승인</th>
								<th style="width:16%;">인수자 승인</th>
								<th style="width:12%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:choose>
								<c:when test="${empty ApprovalList}">
									<tr>
										<td colspan="7" style="text-align:center; padding:20px;">
											검색 결과가 없습니다.
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="ApprovalList" items="${ApprovalList}">
										<tr>
											<td>${ApprovalList.row_num}</td>
											<td><button style="font-size: 15px; cursor: pointer; background-color: white; border: none;" onclick="detailViewIdNumber(this)">${ApprovalList.df_idNumber}</button></td>
											<td>${ApprovalList.app_hnd_area}</td>
											<td>${ApprovalList.app_rcv_area}</td>
											<c:choose>
												<c:when test="${ApprovalList.app_head_status eq 'W'}">
													<td>대기</td>
												</c:when>
												<c:when test="${ApprovalList.app_head_status eq 'Y'}">
													<td style="color:blue;">승인</td>
												</c:when>
												<c:otherwise>
													<td style="color:red;">거절</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${ApprovalList.app_rcv_status eq 'W'}">
													<td>대기</td>
												</c:when>
												<c:when test="${ApprovalList.app_rcv_status eq 'Y'}">
													<td style="color:blue;">승인</td>
												</c:when>
												<c:otherwise>
													<td style="color:red;">거절</td>
												</c:otherwise>
											</c:choose>
											<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" 
										        onclick="detailViewApproval(this)" 
										        data-app-num="${ApprovalList.app_num}">이동 보고서<br> 상세보기</button>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
                        </tbody>
                    </table>
				</div>
				<c:if test="${not empty ApprovalList}">
					<div class="paging-list">
						<ul class="pagination">
							<li><a href="javascript:void(0);" onclick="goToPage(1)">&lt;&lt; First</a></li>
							<c:if test="${paging.startPage > 1}">
						    	<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
						    	<li><a href="javascript:void(0);" onclick="goToPage(${prevPage})">&lt; Previous</a></li>
						  	</c:if>
							<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							    <c:choose>
							    	<c:when test="${i == paging.currentPage}">
							        	<li><strong>${i}</strong></li>
							    	</c:when>
							    	<c:otherwise>
							        	<li><a href="javascript:void(0);" onclick="goToPage(${i})">${i}</a></li>
							    	</c:otherwise>
							  	</c:choose>
							</c:forEach>
							<c:if test="${paging.endPage < paging.totalPage}">
						  		<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
						  		<li><a href="javascript:void(0);" onclick="goToPage(${nextPage})">Next &gt;</a></li>
							</c:if>
							<li><a href="javascript:void(0);" onclick="goToPage(${paging.totalPage})">Last &gt;&gt;</a></li>
						</ul>
					</div>
				</c:if>
        	</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	function detailViewIdNumber(button) {
		const row = button.closest("tr");
		const cells = row.getElementsByTagName("td");
		
		const id = cells[1].innerText;
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/blockView.do";
		
		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "df_idNumber";
		inputId.value = id;
		
		form.appendChild(inputId);
		document.body.appendChild(form);
		form.submit();
	}
	
	function detailViewApproval(button) {	
	    const appNum = button.dataset.appNum; // 클릭한 버튼의 data-app-num
	    const form = document.createElement("form");
	    form.method = "POST";
	    form.action = "/blockManagement/blockExpertApprovalView.do";
	    
	    const inputId = document.createElement("input");
	    inputId.type = "hidden";
	    inputId.name = "app_num_Str";
	    inputId.value = appNum;
	    
	    form.appendChild(inputId);
	    document.body.appendChild(form);
	    form.submit();
	}
	
	function goToPage(pageNumber) {
		const form = document.createElement("form");
		form.method = "GET";
		form.action = "${contextPath}/blockManagement/searchExpertApproval.do";

		const searchType = document.querySelector('select[name="searchType"]').value;
		const searchQuery = document.querySelector('input[name="searchQuery"]').value;

		const inputType = document.createElement("input");
		inputType.type = "hidden";
		inputType.name = "searchType";
		inputType.value = searchType;
		form.appendChild(inputType);

		const inputQuery = document.createElement("input");
		inputQuery.type = "hidden";
		inputQuery.name = "searchQuery";
		inputQuery.value = searchQuery;
		form.appendChild(inputQuery);

		const inputPage = document.createElement("input");
		inputPage.type = "hidden";
		inputPage.name = "page";
		inputPage.value = pageNumber;
		form.appendChild(inputPage);
		
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>
