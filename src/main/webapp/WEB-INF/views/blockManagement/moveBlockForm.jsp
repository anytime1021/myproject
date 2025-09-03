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
	<title>시험편 이동 보고서</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/moveBlockForm.css">
</head>
<body>
	<%@ include file="../include/header2.jsp" %>
	<div class="moveForm-container">
		<%@ include file="../include/sidebar.jsp" %>
		<div class="moveReport-container">
			<div class="moveReport">
				<form name="moveBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/moveBlock.do">
					<table class="report">
					    <tr>
					    	<th class="title" colspan="3">시험편 이동 보고서</th>
					    </tr>
					    <tr>
					    	<td class="col-group" colspan="2">작성 날짜</td>
					    	<td class="col-value"><input type="text" name="created_at" value="${timeNow}"></td>
					    </tr>
					    <tr>
					    	<td class="col-group" rowspan="3">인계자</td>
					    	<td class="col-label">소 속</td>
					    	<td class="col-value">
								<input type="text" name="login_area" value="${blockInformation.login_area}">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-label">성 명</td>
					    	<td class="col-value">
								<input type="text" name="moveList_lender">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-label">직 위</td>
					    	<td class="col-value">
								<input type="text" name="moveList_lender_rank">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-group" rowspan="3">인수자</td>
					    	<td class="col-label">소 속</td>
					    	<td class="col-value">
								<input type="text" name="moveList_recipient_area">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-label">성 명</td>
					    	<td class="col-value">
								<input type="text" name="moveList_recipient">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-label">직 위</td>
					    	<td class="col-value">
								<input type="text" name="moveList_recipient_rank">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-group" colspan="2">시험편 식별번호</td>
					    	<td class="col-value">
								<input type="text" name="df_idNumber" value="${blockInformation.df_idNumber}" readonly>
							</td>
					    </tr>
					    <tr>
					    	<td class="col-group" colspan="2">이동 날짜</td>
					    	<td class="col-value">
								<input type="date" name="moveList_rental_date" placeholder="(yyyy-mm-dd)">
							</td>
					    </tr>
					    <tr>
					    	<td class="col-group" colspan="2">[특이사항]</td>
					    	<td class="col-value notes">
								<textarea name="note"></textarea>
							</td>
					    </tr>
					</table>
					<button type="submit">시험편 이동 등록</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
</html>
