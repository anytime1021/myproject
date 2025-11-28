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
    <title>점검 내용 상세보기</title>
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
                <div class="contents-list">
					<div style="width: 80%; margin: 10px auto; display: flex;">
						<label style="display:flex;">게시글 제목 : </label>
						<div class="searchWithButton" style="margin-left:10px;">
							<input type="text" name="bib_title" style="width:250px;" value="${inspectionTitle}">
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
							<c:forEach var="blockInspectionView" items="${blockInspectionView}">
								<tr>
									<td style="border-bottom:1px solid:gray;">${blockInspectionView.row_num}</td>
									<td style="border-bottom:1px solid:gray;">${blockInspectionView.df_idNumber}</td>
									<input type="hidden" name="df_idNumber" value="${blockInspectionView.df_idNumber}">
									<td style="border-bottom:1px solid:gray;">${blockInspectionView.bil_status}</td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
					<div class="search-write">
						<div>
						</div>
						<div>
							<a href="${contextPath}/blockManagement/removeInspectionView.do?bib_num=${bib_num}" style="display:inline-block; width:100px; text-align:center; height:30px; border:1px solid black;"
							onclick="return confirm('정말로 삭제하시겠습니까?');">삭제하기</a>
						</div>
					</div>
				</div>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
