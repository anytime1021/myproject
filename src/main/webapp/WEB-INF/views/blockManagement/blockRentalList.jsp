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
							<form class="search-box" method="post" action="${contextPath}/blockManagement/searchBlock.do">
								<select name="searchType">
									<option value="idNumber">식별번호</option>
									<option value="material">재질</option>
									<option value="usage">용도</option>
									<option value="form">형태</option>
									<option value="defectType">결함종류</option>
									<option value="manufacture">제작일자</option>
									<option value="itemStatus">사용여부</option>
									<option value="moveStatus">이동현황</option>
									<option value="note">비고</option>
								</select>
								<div class="searchWithButton">
									<input type="text" name="search" placeholder="검색어 입력">
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
							<c:forEach var="rentalList" items="${rentalList}">
								<tr>
									<td><button style="font-size: 16px; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">${rentalList.df_idNumber}</button></td>
									<td>${rentalList.df_size}</td>
									<td>${rentalList.df_material}</td>
									<td>${rentalList.df_usage}</td>
									<td>${rentalList.df_itemStatus}</td>
									<td><button style="font-size: 16px; cursor: pointer; background-color: white; border: none;" onclick="returnBlock(this)">반납하기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<div class="pagination">
						<a href="${contextPath}/blockManagement/blockList.do?page=1"><strong>[≪]</strong></a>
						<c:if test="${paging.startPage > 1}">
					    	<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
					    	<a href="${contextPath}/blockManagement/blockList.do?page=${prevPage}"><strong>[＜]</strong></a>
					  	</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
						    <c:choose>
						    	<c:when test="${i == paging.currentPage}">
						        	<strong style="font-size:20px; color:black;">[${i}]</strong>
						    	</c:when>
						    	<c:otherwise>
						        	<a href="${contextPath}/blockManagement/blockList.do?page=${i}">[${i}]</a>
						    	</c:otherwise>
						  	</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage}">
					  		<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
					  		<a href="${contextPath}/blockManagement/blockList.do?page=${nextPage}"><strong>[＞]</strong></a>
						</c:if>
						<a href="${contextPath}/blockManagement/blockList.do?page=${paging.totalPage}"><strong>[≫]</strong></a>
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
	
	function returnBlock(button) {
		const row = button.closest("tr");
		const cells = row.getElementsByTagName("td");
		
		const id = cells[0].innerText;
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/returnBlock.do";
		
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
