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
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:20%;">식별번호</th>
								<th style="width:17%;">인계 사업소</th>
								<th style="width:17%;">인수 사업소</th>
								<th style="width:15%;">인수자 승인</th>
								<th style="width:15%;">본사 승인</th>
								<th style="width:16%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="ApprovalList" items="${ApprovalList}">
								<tr>
									<td><button style="font-size: 15px; cursor: pointer; background-color: white; border: none;" onclick="detailViewIdNumber(this)">${ApprovalList.df_idNumber}</button></td>
									<td>${ApprovalList.login_area}</td>
									<td>${ApprovalList.app_branch_area}</td>
									<td>${ApprovalList.app_branch_status}</td>
									<td>${ApprovalList.app_head_status}</td>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" onclick="detailViewApproval(this)">이동 보고서<br> 상세보기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
					    <li>
					        <a href="${contextPath}/blockManagement/blockApproval.do?page=1">&lt;&lt; First</a>
					    </li>

					    <c:if test="${paging.startPage > 1}">
					        <fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/blockApproval.do?page=${prevPage}">&lt; Previous</a>
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
					                    <a href="${contextPath}/blockManagement/blockApproval.do?page=${i}">${i}</a>
					                </li>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>

					    <c:if test="${paging.endPage < paging.totalPage}">
					        <fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/blockApproval.do?page=${nextPage}">Next &gt;</a>
					        </li>
					    </c:if>

					    <li>
					        <a href="${contextPath}/blockManagement/blockApproval.do?page=${paging.totalPage}">Last &gt;&gt;</a>
					    </li>
					</ul>
            	</div>
        	</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	function detailViewIdNumber(button) {
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
	
	function detailViewApproval(button) {
		const row = button.closest("tr");
		const cells = row.getElementsByTagName("td");
		
		const id = cells[0].innerText;
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/blockApprovalView.do";
		
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
