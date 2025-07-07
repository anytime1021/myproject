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
						<form class="search-box" method="post" action="${contextPath}/blockManagement/searchBlock.do">
							<select name="searchType">
								<option value="idNumber">식별번호</option>
								<option value="itemStatus">용도</option>
							</select>
							<div class="searchWithButton">
								<input type="text" name="search" placeholder="검색어 입력">
								<button type="submit" title="검색">&#128269;</button>
							</div>
						</form>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:20%;">식별번호</th>
								<th style="width:16%;">대여자</th>
								<th style="width:16%;">수취자</th>
								<th style="width:16%;">수취지역</th>
								<th style="width:16%;">대여날짜</th>
								<th style="width:16%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="blockMoveList" items="${blockMoveList}"> 
								<tr>
									<td>${blockMoveList.df_idNumber}</td>
									<td>${blockMoveList.moveList_lender}</td>
									<td>${blockMoveList.moveList_recipient}</td>
									<td>${blockMoveList.moveList_recipient_area}</td>
									<td>${blockMoveList.moveList_rental_date}</td>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">상세보기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<div class="pagination">
						<a href="${contextPath}/blockManagement/blockMoveList.do?page=1"><strong>[≪]</strong></a>
					  	<c:if test="${paging.startPage > 1}">
							<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
							<a href="${contextPath}/blockManagement/blockMoveList.do?page=${prevPage}"><strong>[＜]</strong></a>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<c:choose>
						    	<c:when test="${i == paging.currentPage}">
						        	<strong style="font-size:20px; color:black;">[${i}]</strong>
						    	</c:when>
						    	<c:otherwise>
						        	<a href="${contextPath}/blockManagement/blockMoveList.do?page=${i}">[${i}]</a>
						    	</c:otherwise>
						    </c:choose>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage}">
						   	<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
							<a href="${contextPath}/blockManagement/blockMoveList.do?page=${nextPage}"><strong>[＞]</strong></a>
						</c:if>
						<a href="${contextPath}/blockManagement/blockMoveList.do?page=${paging.totalPage}"><strong>[≫]</strong></a>
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
</script>
</html>
