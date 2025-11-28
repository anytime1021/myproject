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
    <title>검색결과</title>
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
							<form autocomplete="off" class="search-box" method="get" action="${contextPath}/blockManagement/searchInspectionList.do">
								<select name="searchType">
									<option value="bib_title" ${searchType == 'bib_title' ? 'selected' : ''}>게시글 제목</option>
									<option value="bil_status" ${searchType == 'bil_status' ? 'selected' : ''}>점검내용</option>
								</select>
								<div class="searchWithButton">
									<input type="text" name="searchQuery" value="${searchQuery}" placeholder="검색어 입력">
									<button type="submit" title="검색">&#128269;</button>
								</div>
								<input type="date" id="startDate" name="startDate" value="${startDate}">~<input type="date" id="endDate" name="endDate" value="${endDate}">
							</form>
						</div>
						<div>
							<a style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;" href="${contextPath}/blockManagement/addInspectionForm.do">점검일지 작성</a>
						</div>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
                                <th style="width:10%;">NO</th>
                                <th style="width:50%;">제 목</th>
								<th style="width:20%;">사업소</th>
                                <th style="width:20%;">점검일</th>
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
		                            <c:forEach var="blockInspectionList" items="${blockInspectionList}">
		                                <tr>
		                                    <td>${blockInspectionList.row_num}</td>
		                                    <td><a href="${contextPath}/blockManagement/blockInspectionView.do?bib_num=${blockInspectionList.bib_num}">${blockInspectionList.bib_title}</a></td>
		                                    <td>${blockInspectionList.login_area}</td>
											<td>${blockInspectionList.bib_date}</td>
		                                </tr>
		                            </c:forEach>
								</c:otherwise>
							</c:choose>
                        </tbody>
                    </table>
				</div>
				<c:if test="${not empty blockInspectionList}">
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
	
	function goToPage(pageNumber) {
		const form = document.createElement("form");
		form.method = "GET";
		form.action = "${contextPath}/blockManagement/searchInspectionList.do";

		const searchType = document.querySelector('select[name="searchType"]').value;
		const searchQuery = document.querySelector('input[name="searchQuery"]').value;
		const startDate = document.querySelector('input[name="startDate"]').value;
		const endDate = document.querySelector('input[name="endDate"]').value;

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
		
		const inputStart = document.createElement("input");
		inputStart.type = "hidden";
		inputStart.name = "startDate";
		inputStart.value = startDate;
		form.appendChild(inputStart);
		
		const inputEnd = document.createElement("input");
		inputEnd.type = "hidden";
		inputEnd.name = "endDate";
		inputEnd.value = endDate;
		form.appendChild(inputEnd);
		
		document.body.appendChild(form);
		form.submit();
	}
	
	function formatDate(date) {
		return date.toISOString().substring(0,10);
	}
	
	const today = new Date();
	
	const standard_Date = new Date();
	standard_Date.setMonth(standard_Date.getMonth()-3);
	
	document.getElementById('startDate').value = formatDate(standard_Date);
	document.getElementById('endDate').value = formatDate(today);
</script>
</html>
