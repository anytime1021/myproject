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
    <title>시험편 추가</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/styles3.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
			<div class="addBlock-container">					
				<div class="addBlock">
					<form autocomplete="off" name="addStandardBlockForm" method="post" action="${contextPath}/blockManagement/addStandardBlock.do" enctype="multipart/form-data">
						<div class="form-group">
							<label for="df_idNumber">식별번호 : </label>
							<input type="text" id="df_idNumber" name="df_idNumber">
						</div>
						<div class="form-group">
							<label for="df_pictureName">사진 : </label>
							<input type="file" id="df_picture" name="df_picture">
						</div>
						<div class="form-group">
							<label>제작일자 : </label>
							<input type="text" id="df_manufacture" name="df_manufacture" placeholder="yyyy-mm-dd">
						</div>
						<div class="form-group">
							<label>상태 : </label>
							<select id="df_itemStatus" name="df_itemStatus">
								<option value="이상없음">이상없음</option>
								<option value="폐기">폐기</option>
								<option value="분실">분실</option>
								<option value="대여중">대여중</option>
							</select>
						</div>
						<div class="form-group">
							<label>이동현황 : </label>
							<input type="text" id="df_moveStatus" name="df_moveStatus" value="-" readonly>
						</div>
						<div class="form-group">
							<label>비고 : </label>
							<input type="text" id="note" name="note">
						</div>
						<div class="form-group">
							<label>표준 규격 : </label>
							<input type="text" id="df_stdCode" name="df_stdCode">
						</div>
						<div class="form-group">
							<label>등급 : </label>
							<input type="text" id="df_stdClass" name="df_stdClass">
						</div>
						<div class="form-group">
							<label>두께 : </label>
							<input type="text" id="df_stdThick" name="df_stdThick">
						</div>
						<button type="submit" id="submitBtn">추가하기</button>
					</div>
				</form>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
