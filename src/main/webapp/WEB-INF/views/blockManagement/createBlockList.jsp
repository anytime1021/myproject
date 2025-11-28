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
    <title>시험편 제작 요청 목록</title>
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
							<form autocomplete="off" class="search-box" method="get" action="${contextPath}/blockManagement/searchCreateBlock.do">
								<select name="searchType">
									<option value="createBlockBoard_title">게시글 제목</option>
									<option value="createBlockBoard_date">요청일</option>
									<option value="createBlock_material">재질</option>
									<option value="createBlock_thick">두께</option>
									<option value="createBlock_diameter">관경</option>
									<option value="createBlock_weld">용접형상</option>
								</select>
								<div class="searchWithButton">
									<input type="text" name="searchQuery" placeholder="검색어 입력">
									<button type="submit" title="검색">&#128269;</button>
								</div>
							</form>
						</div>
						<div>
							<a style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;" href="${contextPath}/blockManagement/createBlockForm.do">시험편 요청</a>
						</div>
					</div>
                    <table class="table-control">
                        <thead>
                            <tr>
                                <th style="width:10%;">NO</th>
                                <th style="width:60%;">제 목</th>
                                <th style="width:15%;">요청일</th>
								<th style="width:8%;">상태</th>
								<c:if test="${department eq '품질' || department eq '기술'}">
									<th></th>								
								</c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="createBlockList" items="${createBlockList}">
                                <tr>
                                    <td>${createBlockList.row_num}</td>
 									<td><button style="font-size: 15px; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">${createBlockList.createBlockBoard_title}</button></td>
                                    <td>${createBlockList.createBlockBoard_date}</td>
									<td>
										<c:choose>
											<c:when test="${createBlockList.createBlock_status eq 'W'}">
												<span style="color:green">승인대기</span>
											</c:when>
											<c:when test="${createBlockList.createBlock_status eq 'Y'}">
												<span style="color:blue">승인</span>
											</c:when>
											<c:when test="${createBlockList.createBlock_status eq 'N'}">
												<span style="color:red">거절</span>
											</c:when>
										</c:choose>
									</td>
									<c:if test="${department eq '품질' || department eq '기술'}">
										<td>
											<button type="button" onclick="deleteSelect('${createBlockList.createBlockBoard_num}')"
											style="background-color: white; color: black; border: none; border-radius: 4px; cursor: pointer;">
											삭제
											</button>
										</td>							
									</c:if>
									<input type="hidden" class="createBlockBoard_num" value="${createBlockList.createBlockBoard_num}">
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
				</div>
				<div class="paging-list">
					<ul class="pagination">
						<li>
					  		<a href="${contextPath}/blockManagement/createBlockBoard.do?page=1">&lt;&lt; First</a>
						</li>
						
						<c:if test="${paging.startPage > 1}">
							<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
						    <a href="${contextPath}/blockManagement/createBlockBoard.do?page=${prevPage}">&lt; Previous</a>
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
							        	<a href="${contextPath}/blockManagement/createBlockBoard.do?page=${i}">${i}</a>
							    	</li>
								</c:otherwise>
						  	</c:choose>
						</c:forEach>

						<c:if test="${paging.endPage < paging.totalPage}">
							<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
						    <li>
								<a href="${contextPath}/blockManagement/createBlockBoard.do?page=${nextPage}">Next &gt;</a>
							</li>
						</c:if>
					
						<li>
							<a href="${contextPath}/blockManagement/createBlockBoard.do?page=${paging.totalPage}">Last &gt;&gt;</a>
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
	    const id = row.querySelector(".createBlockBoard_num").value;

	    const form = document.createElement("form");
	    form.method = "POST";
	    form.action = "/blockManagement/createBlockView.do";

	    const inputId = document.createElement("input");
	    inputId.type = "hidden";
	    inputId.name = "createBlockBoard_numStr";
	    inputId.value = id;

	    form.appendChild(inputId);
	    document.body.appendChild(form);
	    form.submit();
	}
	
	function deleteSelect(createNum) {
		const tableName="create"
		if (confirm("정말 삭제하십니까?")) {
			window.location.href = "${contextPath}/blockManagement/deleteSelect.do?tableName="+tableName+"&primaryKey="+createNum;
		}
	}
</script>
</html>
