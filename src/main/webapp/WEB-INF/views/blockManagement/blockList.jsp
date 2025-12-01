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
    <title>시험편 제작 요청 상세보기</title>
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
								<input type="hidden" name="token" value="blockList">
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
						<div>
							<a style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;" href="${contextPath}/blockManagement/addStandardBlockForm.do">표준 시험편 등록</a>
							<a style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;" href="${contextPath}/blockManagement/addBlockForm.do">일반 시험편 등록</a>
						</div>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:6%;"></th>
								<th style="width:18%;">식별번호</th>
								<th style="width:18%;">크기</th>
								<th style="width:15%;">재질</th>
								<th style="width:15%;">용도</th>
								<th style="width:14%;">상태</th>
								<th style="width:14%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="blockList" items="${blockList}">
								<tr>
									<input type="hidden" name="df_num" value="${blockList.df_num}">
									<td>${blockList.row_num}</td>
									<td><button style="font-size: 15px; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">${blockList.df_idNumber}</button></td>
									<td>${blockList.df_size}</td>
									<td>${blockList.df_material}</td>
									<td>${blockList.df_usage}</td>
									<td>${blockList.df_itemStatus}</td>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">상세보기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
					    <li>
					        <a href="${contextPath}/blockManagement/blockList.do?page=1">&lt;&lt; First</a>
					    </li>

					    <c:if test="${paging.startPage > 1}">
					        <fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/blockList.do?page=${prevPage}">&lt; Previous</a>
					        </li>
					    </c:if>

					    <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					        <c:choose>
					            <c:when test="${i == paging.currentPage}">
					                <li>
					                    <strong>${i}</strong>
					                </li>
					            </c:when>
					            <c:otherwise>
					                <li>
					                    <a href="${contextPath}/blockManagement/blockList.do?page=${i}">${i}</a>
					                </li>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>

					    <c:if test="${paging.endPage < paging.totalPage}">
					        <fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/blockList.do?page=${nextPage}">Next &gt;</a>
					        </li>
					    </c:if>

					    <li>
					        <a href="${contextPath}/blockManagement/blockList.do?page=${paging.totalPage}">Last &gt;&gt;</a>
					    </li>
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

		const dfNum = row.querySelector("input[name='df_num']").value;
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/blockView.do";

		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "df_num";
		inputId.value = dfNum;

		form.appendChild(inputId);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>
