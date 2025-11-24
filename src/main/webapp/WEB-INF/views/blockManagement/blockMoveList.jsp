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
								<option value="manufacture">제작일자</option>
								<option value="itemStatus">상태</option>
								<option value="moveStatus">이동현황</option>
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
								<th style="width:6%;"></th>
								<th style="width:20%;">식별번호</th>
								<th style="width:11%;">인계자</th>
								<th style="width:11%;">인수자</th>
								<th style="width:12%;">인수지역</th>
								<th style="width:14%;">대여일</th>
								<th style="width:14%;">반납일</th>
								<th style="width:13%;">상태</th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="blockMoveList" items="${blockMoveList}"> 
								<tr>
									<td>${blockMoveList.row_num}</td>
									<td><button style="font-size: 16px; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">${blockMoveList.df_idNumber}</button></td>
									<td>${blockMoveList.moveList_lender}</td>
									<td>${blockMoveList.moveList_recipient}</td>
									<td>${blockMoveList.moveList_recipient_area}</td>
									<td>${blockMoveList.moveList_rental_date}</td>
									<td>${blockMoveList.moveList_return_date}</td>
									<td>
									<c:choose>
										<c:when test="${blockMoveList.df_itemStatus eq '이상없음'}">
									    	반납완료
									    </c:when>
									    <c:otherwise>
											<c:choose>
												<c:when test="${searchArea eq blockMoveList.login_area && searchArea ne '본사'}">
									    			인계중
												</c:when>
												<c:when test="${searchArea ne blockMoveList.login_area && searchArea ne '본사'}">
													인수중
												</c:when>
												<c:otherwise>
													대여중
												</c:otherwise>
											</c:choose>
									    </c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
						<li><a href="${contextPath}/blockManagement/blockMoveList.do?page=1">&lt;&lt; First</a></li>
					  	<c:if test="${paging.startPage > 1}">
							<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
							<li><a href="${contextPath}/blockManagement/blockMoveList.do?page=${prevPage}">&lt; Previous</a></li>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<c:choose>
						    	<c:when test="${i == paging.currentPage}">
						        	<li><strong>${i}</strong></li>
						    	</c:when>
						    	<c:otherwise>
						        	<li><a href="${contextPath}/blockManagement/blockMoveList.do?page=${i}">${i}</a></li>
						    	</c:otherwise>
						    </c:choose>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage}">
						   	<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
							<li><a href="${contextPath}/blockManagement/blockMoveList.do?page=${nextPage}">Next &gt;</a></li>
						</c:if>
						<li><a href="${contextPath}/blockManagement/blockMoveList.do?page=${paging.totalPage}">Last &gt;&gt;</a></li>
					</ul>
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
</script>
</html>
