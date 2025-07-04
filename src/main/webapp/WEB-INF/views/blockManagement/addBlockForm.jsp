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
						<c:choose>
							<c:when test="${searchArea eq '서산'}">
								<c:set var="idNumValue" value="A(SS)-" />
							</c:when>
							<c:when test="${searchArea eq '마산'}">
								<c:set var="idNumValue" value="A(MS)-" />
							</c:when>
							<c:when test="${searchArea eq '울산'}">
								<c:set var="idNumValue" value="A(US)-" />
							</c:when>
							<c:when test="${searchArea eq '여수'}">
								<c:set var="idNumValue" value="A(YS)-" />
							</c:when>
							<c:when test="${searchArea eq '창원'}">
								<c:set var="idNumValue" value="A(CW)-" />
							</c:when>
						</c:choose>
						<div style="display:inline-flex; align-items:center; gap:10px;">
							<input type="text" id="df_idNumber" name="df_idNumber"
							value="${idNumValue}"><span id="checkMsg" style="width:200px; margin-left: 10px;"></span>
						</div>
						<input type="file" name="df_picture">
						<input type="text" name="df_material">
						<input type="text" name="df_size">
						<input type="text" name="df_usage" readonly>
						<input type="text" name="df_form" readonly>
						<input type="text" name="df_defectType">
						<input type="date" name="df_manufacture">
						<input type="text" name="df_itemStatus">
						<select name="df_moveStatus">
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
						<input type="text" name="note"><br>
						<button type="submit" onclick="return submitCheck()">추가하기</button>
					</div>
				</div>
			</form>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	$(document).ready(function(){
		$('#df_idNumber').on('keyup', function() {
			const idNumber = $(this).val().trim();
			
			if (idNumber.length === 0) {
				$('#checkMsg').text('');
				return;
			}
			
			$.ajax({
				url: '${contextPath}/blockManagement/checkDuplicateIdNumber.do',
				type: 'GET',
				data: { 
					idNumber: idNumber 
				},
				success: function(result) {
					if (result === 'available') {
						$('#checkMsg').text('사용 가능한 번호입니다.').css('color', 'green');
					} else if(result === 'duplicate') {
						$('#checkMsg').text('이미 사용중인 번호입니다.').css('color', 'red');
					} else {
						$('#checkMsg').text('사용 불가능한 번호입니다.').css('color', 'red');
					}
				},
				error: function() {
					$('#checkMsg').text('서버 오류').css('color', 'gray');
				}
			});
		});
	});
</script>
</html>
