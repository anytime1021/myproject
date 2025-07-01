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
			<form autocomplete="off" name="addBlockForm" method="post" action="${contextPath}/blockManagement/addBlock.do" enctype="multipart/form-data">
 				<div class="addBlock-container">
					<div class="addBlock-label">
						<label>식별번호 : </label><br>
						<label>사진 : </label><br>
						<label>재질 : </label><br>
						<label>크기 : </label><br>
						<label>용도 : </label><br>
						<label>시험편 정보 : </label><br>
						<label>결함종류 : </label><br>
						<label>제작일자 : </label><br>
						<label>사용여부 : </label><br>
						<label>이동현황 : </label><br>
						<label>비고 : </label><br>
					</div>
					<div class="addBlock-text">
						<input type="text" name="df_idNumber">
						<input type="file" name="df_picture">
						<input type="text" name="df_material">
						<input type="text" name="df_size">
						<input type="text" name="df_usage">
						<input type="text" name="df_form">
						<input type="text" name="df_defectType">
						<input type="date" name="df_manufacture">
						<input type="text" name="df_itemStatus">
						<input type="text" name="df_moveStatus">
						<input type="text" name="note"><br>
						<button type="submit">추가하기</button>
					</div>
				</div>
			</form>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
