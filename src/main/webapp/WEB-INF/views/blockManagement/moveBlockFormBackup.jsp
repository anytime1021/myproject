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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
			<div class="addBlock-container">
				<form name="moveBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/moveBlock.do">
					<div class="addBlock">
						<div class="form-group">
							<label for="df_idNumber">식별번호 : </label>
							<input type="text" id="df_idNumber" name="df_idNumber" value="${blockInformation.df_idNumber}" readonly>
						</div>
						<div class="form-group">
							<label for="moveList_lender">대여자 : </label>
							<input type="text" id="moveList_lender" name="moveList_lender">
						</div>
						<div class="form-group">
							<label for="moveList_recipient">수취자 : </label>
							<input type="text" id="moveList_recipient" name="moveList_recipient">
						</div>
						<div class="form-group">
							<label for="moveList_recipient_area">수취지역 : </label>
							<select id="moveList_recipient_area" name="moveList_recipient_area">
								<option value="${searchArea}">-</option>
								<c:if test="${searchArea ne '서산'}">
									<option value="서산">서산</option>
								</c:if>
								<c:if test="${searchArea ne '마산'}">
									<option value="마산">마산</option>
								</c:if>
								<c:if test="${searchArea ne '울산'}">
									<option value="울산">울산</option>
								</c:if>
								<c:if test="${searchArea ne '여수'}">
									<option value="여수">여수</option>
								</c:if>
								<c:if test="${searchArea ne '창원'}">
									<option value="창원">창원</option>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label for="note">비고 : </label>
							<input type="text" id="note" name="note">
						</div>
						<button type="submit">시험편 대여 등록</button>
					</div>
				</form>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
