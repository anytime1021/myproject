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
    <title>시험편 점검</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/boardStyle.css">
	<style>
		input[type="text"] {display: inline-block; width: 100%; height: 30px; background-color: white; border: none; font-weight: bold; text-align: center; margin:0 auto;}
	</style>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
            <div class="contents-container">
				<form autocomplete="off" name="addInspectionForm" method="post" action="${contextPath}/blockManagement/addInspection.do">
	                <div class="contents-list">
						<div style="width: 80%; margin: 10px auto; display: flex;">
							<label style="display:flex;">게시글 제목 : </label>
							<div class="searchWithButton" style="margin-left:10px;">
								<input type="text" name="bib_title" style="width:250px;" placeholder="게시글 제목 입력">
							</div>
						</div>
	                    <table class="table-control-special">
	                        <thead>
	                            <tr>
									<th style="width:6%;"></th>
									<th style="width:20%;">식별번호</th>
									<th style="width:40%;">점검내용</th>
	                            </tr>
	                        </thead>
	                        <tbody>
								<c:forEach var="inspectionList" items="${inspectionList}">
									<tr>
										<td style="border-bottom:1px solid:gray;">${inspectionList.row_num}</td>
										<td style="border-bottom:1px solid:gray;">${inspectionList.df_idNumber}</td>
										<input type="hidden" name="df_idNumber" value="${inspectionList.df_idNumber}">
										<td><input type="text" name="bil_status" value="특이사항없음"></td>
									</tr>
								</c:forEach>
	                        </tbody>
	                    </table>
						<div class="search-write">
							<div>
							</div>
							<div>
								<button type="submit" style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;">점검기록 저장</button>
							</div>
						</div>
					</div>
				</form>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
