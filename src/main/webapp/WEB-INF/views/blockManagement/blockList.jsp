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
    <link rel="stylesheet" href="${contextPath}/resources/css/styles3.css">

</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
            <div class="contents-container">
                <div class="contents-list">
                    <table class="table-control">
                        <thead>
                            <tr>
								<th style="width:20%;">식별번호</th>
								<th style="width:20%;">크기</th>
								<th style="width:15%;">재질</th>
								<th style="width:15%;">용도</th>
								<th style="width:15%;">사용여부</th>
								<th style="width:15%;"></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach var="blockList" items="${blockList}">
								<tr>
									<td>${blockList.df_idNumber}</td>
									<td>${blockList.df_size}</td>
									<td>${blockList.df_material}</td>
									<td>${blockList.df_usage}</td>
									<td>${blockList.df_itemStatus}</td>
									<td><button style="font-weight: bold; cursor: pointer; background-color: white; border: none;" onclick="detailView(this)">상세보기</button></td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
					<a class="btn" href="${contextPath}/blockManagement/addBlockForm.do">작성하기</a>
					<div class="pagination">
						<c:forEach var="i" begin="1" end="${totalPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<strong>[${i}]</strong>
								</c:when>
								<c:otherwise>
									<a href="${contextPath}/blockManagement/blockList.do?page=${i}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
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
		const material = cells[1].innerText;
		
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
