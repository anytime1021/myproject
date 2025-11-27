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
							<form autocomplete="off" class="search-box" method="get" action="${contextPath}/blockManagement/searchCreateBlock.do">
								<select name="searchType">
									<option value="createBlockBoard_title" ${searchType == 'createBlockBoard_title' ? 'selected' : ''}>게시글</option>
									<option value="createBlockBoard_date" ${searchType == 'createBlockBoard_date' ? 'selected' : ''}>요청일</option>
									<option value="createBlock_material" ${searchType == 'createBlock_material' ? 'selected' : ''}>재질</option>
									<option value="createBlock_thick" ${searchType == 'createBlock_thick' ? 'selected' : ''}>두께</option>
									<option value="createBlock_diameter" ${searchType == 'createBlock_diameter' ? 'selected' : ''}>관경</option>
									<option value="createBlock_weld" ${searchType == 'createBlock_weld' ? 'selected' : ''}>용접형상</option>
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
                                <th style="width:20%;">요청일</th>
								<th style="width:10%;">상태</th>
                            </tr>
                        </thead>
                        <tbody>
							<c:choose>
								<c:when test="${empty createBlockList}">
									<tr>
										<td colspan="7" style="text-align:center; padding:20px;">
											검색 결과가 없습니다.
										</td>
									</tr>
								</c:when>
								<c:otherwise>
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
											<input type="hidden" class="createBlockBoard_num" value="${createBlockList.createBlockBoard_num}">
		                                </tr>
		                            </c:forEach>
								</c:otherwise>
							</c:choose>
                        </tbody>
                    </table>
				</div>
				<c:if test="${not empty ApprovalList}">
					<div class="paging-list">
						<ul class="pagination">
							<li><a href="javascript:void(0);" onclick="goToPage(1)">&lt;&lt; First</a></li>
							<c:if test="${paging.startPage > 1}">
						    	<fmt:formatNumber var="prevPage" value="${paging.startPage - 1}" type="number" maxFractionDigits="0" />
						    	<li><a href="javascript:void(0);" onclick="goToPage(${prevPage})">&lt; Previous</a></li>
						  	</c:if>
							<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							    <c:choose>
							    	<c:when test="${i == paging.currentPage}">
							        	<li><strong>${i}</strong></li>
							    	</c:when>
							    	<c:otherwise>
							        	<li><a href="javascript:void(0);" onclick="goToPage(${i})">${i}</a></li>
							    	</c:otherwise>
							  	</c:choose>
							</c:forEach>
							<c:if test="${paging.endPage < paging.totalPage}">
						  		<fmt:formatNumber var="nextPage" value="${paging.endPage + 1}" type="number" maxFractionDigits="0" />
						  		<li><a href="javascript:void(0);" onclick="goToPage(${nextPage})">Next &gt;</a></li>
							</c:if>
							<li><a href="javascript:void(0);" onclick="goToPage(${paging.totalPage})">Last &gt;&gt;</a></li>
						</ul>
					</div>
				</c:if>
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
	
	function goToPage(pageNumber) {
		const form = document.createElement("form");
		form.method = "GET";
		form.action = "${contextPath}/blockManagement/searchCreateBlock.do";

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
