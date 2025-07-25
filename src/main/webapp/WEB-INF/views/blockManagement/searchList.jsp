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
							<form class="search-box" method="get" action="${contextPath}/blockManagement/searchList.do">
								<select name="searchType">
									<option value="idNumber" ${searchType == 'idNumber' ? 'selected' : ''}>식별번호</option>
									<option value="material" ${searchType == 'material' ? 'selected' : ''}>재질</option>
									<option value="usage" ${searchType == 'usage' ? 'selected' : ''}>용도</option>
									<option value="form" ${searchType == 'form' ? 'selected' : ''}>형태</option>
									<option value="manufacture" ${searchType == 'manufacture' ? 'selected' : ''}>제작일자</option>
									<option value="itemStatus" ${searchType == 'itemStatus' ? 'selected' : ''}>상태</option>
									<option value="moveStatus" ${searchType == 'moveStatus' ? 'selected' : ''}>이동현황</option>
									<option value="note" ${searchType == 'note' ? 'selected' : ''}>비고</option>
								</select>
								<input type="hidden" name="token" value="${token}">
								<div class="searchWithButton">
									<input type="text" name="searchQuery" value="${searchQuery}" placeholder="검색어 입력">
									<button type="submit" title="검색">&#128269;</button>
								</div>
							</form>
						</div>
						<div>
							<a style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;" href="${contextPath}/blockManagement/addBlockForm.do">시험편 등록</a>
						</div>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:20%;">식별번호</th>
								<th style="width:20%;">크기</th>
								<th style="width:15%;">재질</th>
								<th style="width:15%;">용도</th>
								<th style="width:15%;">상태</th>
								<th style="width:15%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="searchList" items="${searchList}">
								<tr>
									<td>${searchList.df_idNumber}</td>
									<td>${searchList.df_size}</td>
									<td>${searchList.df_material}</td>
									<td>${searchList.df_usage}</td>
									<td>${searchList.df_itemStatus}</td>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">상세보기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<div class="pagination">
						<a href="javascript:void(0);" onclick="goToPage(1)"><strong>[≪]</strong></a>
						<c:if test="${paging.startPage > 1}">
					    	<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
					    	<a href="javascript:void(0);" onclick="goToPage(${prevPage})"><strong>[＜]</strong></a>
					  	</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
						    <c:choose>
						    	<c:when test="${i == paging.currentPage}">
						        	<strong style="font-size:20px; color:black;">[${i}]</strong>
						    	</c:when>
						    	<c:otherwise>
						        	<a href="javascript:void(0);" onclick="goToPage(${i})">[${i}]</a>
						    	</c:otherwise>
						  	</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage}">
					  		<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
					  		<a href="javascript:void(0);" onclick="goToPage(${nextPage})"><strong>[＞]</strong></a>
						</c:if>
						<a href="javascript:void(0);" onclick="goToPage(${paging.totalPage})"><strong>[≫]</strong></a>
					</div>
				</div>
        	</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	function detailView(button) {
		const row = button.closest("tr");
		const cells = row.getElementsByTagName("td");
		
		const id = cells[0].innerText;
		
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
	
	function goToPage(pageNumber) {
			const form = document.createElement("form");
			form.method = "GET";
			form.action = "${contextPath}/blockManagement/searchList.do";

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
