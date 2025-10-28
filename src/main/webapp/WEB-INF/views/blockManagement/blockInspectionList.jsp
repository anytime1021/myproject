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
							<form class="search-box" method="get" action="#">
								<select name="searchType">
									<option value="-">게시글 제목</option>
									<option value="-">-</option>
								</select>
								<div class="searchWithButton">
									<input type="text" name="searchQuery" placeholder="검색어 입력">
									<button type="submit" title="검색">&#128269;</button>
								</div>
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
                                <th style="width:70%;">제 목</th>
                                <th style="width:20%;">점검일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="blockInspectionList" items="${blockInspectionList}">
                                <tr>
                                    <td>${blockInspectionList.row_num}</td>
                                    <td><a href="${contextPath}/blockManagement/blockInspectionView.do?bib_num=${blockInspectionList.bib_num}">${blockInspectionList.bib_title}</a></td>
                                    <td>${blockInspectionList.bib_date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
						<li>
					  		<a href="${contextPath}/blockManagement/blockInspectionList.do?page=1">&lt;&lt; First</a>
						</li>
						
						<c:if test="${paging.startPage > 1}">
							<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
						    <a href="${contextPath}/blockManagement/blockInspectionList.do?page=${prevPage}">&lt; Previous</a>
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
							        	<a href="${contextPath}/blockManagement/blockInspectionList.do?page=${i}">${i}</a>
							    	</li>
								</c:otherwise>
						  	</c:choose>
						</c:forEach>

						<c:if test="${paging.endPage < paging.totalPage}">
							<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
						    <li>
								<a href="${contextPath}/blockManagement/blockInspectionList.do?page=${nextPage}">Next &gt;</a>
							</li>
						</c:if>
					
						<li>
							<a href="${contextPath}/blockManagement/blockInspectionList.do?page=${paging.totalPage}">Last &gt;&gt;</a>
						</li>
					</ul>
	            </div>
            </div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
