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
				<div class="addBlock-label">
					<br>
					<label>식별번호 : </label><br>
					<label>재질 : </label><br>
					<label>크기 : </label><br>
					<label>용도 : </label><br>
					<label>시험편 정보 : </label><br>
					<label>결함종류 : </label><br>
					<label>제작일자 : </label><br>
					<label>상태 : </label><br>
					<label>이동현황 : </label><br>
					<label>비고 : </label><br>
				</div>
				<div class="addBlock-text">
					<label style="text-align:center;">대여할 시험편 정보</label><br>
					<input type="text" id="df_idNumber" name="df_idNumber" value="${blockInformation.df_idNumber}" readonly>
					<input type="text" name="df_material" value="${blockInformation.df_material}" readonly>
					<input type="text" name="df_size" value="${blockInformation.df_size}" readonly>
					<input type="text" name="df_usage" value="${blockInformation.df_usage}" readonly>
					<input type="text" name="df_form" value="${blockInformation.df_form}" readonly>
					<input type="text" name="df_defectType" value="${blockInformation.df_defectType}" readonly>
					<input type="date" name="df_manufacture" value="${blockInformation.df_manufacture}" readonly>
					<input type="text" name="df_itemStatus" value="${blockInformation.df_itemStatus}" readonly>
					<input type="text" name="df_moveStatus" value="${blockInformation.df_moveStatus}" readonly>
					<input type="text" name="note" value="${blockInformation.note}" readonly>
				</div>
				<div class="addBlock-label-border">
					<br>
					<label>식별번호 : </label><br>
					<label>대여자 : </label><br>
					<label>수취자 : </label><br>
					<label>수취지역 : </label><br>
					<label>비고 : </label><br>
				</div>
				<div class="addBlock-text">
					<form name="moveBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/moveBlock.do">
						<br>
						<input type="text" name="df_idNumber" value="${blockInformation.df_idNumber}" readonly>
						<input type="text" name="moveList_lender">
						<input type="text" name="moveList_recipient">
						<select name="moveList_recipient_area">
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
						<input type="text" name="note">
						<button type="submit" onclick="moveBlock()">시험편 대여 등록</button>
					</form>
				</div>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
