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
								<th style="width:6%;"></th>
								<th style="width:18%;">식별번호</th>
								<th style="width:16%;">인계 사업소</th>
								<th style="width:16%;">인수업체</th>
								<th style="width:16%;">본사 승인</th>
								<th style="width:16%;">인수자 승인</th>
								<th style="width:12%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="expertApprovalList" items="${expertApprovalList}">
								<tr>
									<td>${expertApprovalList.row_num}</td>
									<td><button style="font-size: 15px; cursor: pointer; background-color: white; border: none;" onclick="detailViewIdNumber(this)">${expertApprovalList.df_idNumber}</button></td>
									<td>${expertApprovalList.login_area}</td>
									<td>${expertApprovalList.app_rcv_area}</td>
									<c:choose>
										<c:when test="${expertApprovalList.app_head_status eq 'W'}">
											<td>대기</td>
										</c:when>
										<c:when test="${expertApprovalList.app_head_status eq 'Y'}">
											<td>승인</td>
										</c:when>
										<c:otherwise>
											<td>거절</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${expertApprovalList.app_rcv_status eq 'W'}">
											<td>대기</td>
										</c:when>
										<c:when test="${expertApprovalList.app_rcv_status eq 'Y'}">
											<td>승인</td>
										</c:when>
										<c:otherwise>
											<td>거절</td>
										</c:otherwise>
									</c:choose>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" 
								        onclick="detailViewApproval(this)" 
								        data-app-num="${expertApprovalList.app_num}">이동 보고서<br> 상세보기</button>
									</td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
					    <li>
					        <a href="${contextPath}/blockManagement/expertApprovalList.do?page=1">&lt;&lt; First</a>
					    </li>

					    <c:if test="${paging.startPage > 1}">
					        <fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/expertApprovalList.do?page=${prevPage}">&lt; Previous</a>
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
					                    <a href="${contextPath}/blockManagement/expertApprovalList.do?page=${i}">${i}</a>
					                </li>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>

					    <c:if test="${paging.endPage < paging.totalPage}">
					        <fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
					        <li>
					            <a href="${contextPath}/blockManagement/expertApprovalList.do?page=${nextPage}">Next &gt;</a>
					        </li>
					    </c:if>

					    <li>
					        <a href="${contextPath}/blockManagement/expertApprovalList.do?page=${paging.totalPage}">Last &gt;&gt;</a>
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
</script>
</html>
