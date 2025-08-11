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
						<form class="search-box" method="get" action="${contextPath}/blockManagement/searchList.do">
							<input type="hidden" name="token" value="blockMoveList">
							<select name="searchType">
								<option value="idNumber">식별번호</option>
								<option value="material">재질</option>
								<option value="usage">용도</option>
								<option value="form">형태</option>
								<option value="manufacture">제작일자</option>
								<option value="itemStatus">상태</option>
								<option value="moveStatus">이동현황</option>
								<option value="note">비고</option>
							</select>
							<div class="searchWithButton">
								<input type="text" name="searchQuery" placeholder="검색어 입력">
								<button type="submit" title="검색">&#128269;</button>
							</div>
						</form>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:20%;">식별번호</th>
								<th style="width:13%;">대여자</th>
								<th style="width:13%;">수취자</th>
								<th style="width:13%;">수취지역</th>
								<th style="width:14%;">대여일</th>
								<th style="width:14%;">반납일</th>
								<th style="width:13%;">상태</th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="searchList" items="${searchList}"> 
								<tr>
									<td><button style="font-size: 16px; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">${searchList.df_idNumber}</button></td>
									<td>${searchList.moveList_lender}</td>
									<td>${searchList.moveList_recipient}</td>
									<td>${searchList.moveList_recipient_area}</td>
									<td>${searchList.moveList_rental_date}</td>
									<td>${searchList.moveList_return_date}</td>
									<td>
									<c:choose>
										<c:when test="${searchList.df_itemStatus eq '사용중'}">
									    	반납완료
									    </c:when>
									    <c:otherwise>
									    	${searchList.df_itemStatus}
									    </c:otherwise>
									</c:choose>
									</td>
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
			const token = document.querySelector('input[name="token"]').value;

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
			
			const inputToken = document.createElement("input");
			inputToken.type = "hidden";
			inputToken.name = "token";
			inputToken.value = token;
			form.appendChild(inputToken);

			document.body.appendChild(form);
			form.submit();
		}
</script>
</html>
