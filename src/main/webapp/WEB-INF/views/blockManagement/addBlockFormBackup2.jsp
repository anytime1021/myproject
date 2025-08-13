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
				<form autocomplete="off" name="addBlockForm" method="post" action="${contextPath}/blockManagement/addBlock.do" enctype="multipart/form-data">
					<div class="addBlock">
						<div class="form-group">
							<label for="df_idNumber">식별번호 : </label>
							<c:choose>
								<c:when test="${searchArea eq '서산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(SS)-" />
								</c:when>
								<c:when test="${searchArea eq '마산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(MS)-" />
								</c:when>
								<c:when test="${searchArea eq '울산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(US)-" />
								</c:when>
								<c:when test="${searchArea eq '여수'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(YS)-" />
								</c:when>
								<c:when test="${searchArea eq '창원'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(CW)-" />
								</c:when>
							</c:choose>
							<br>
						</div>
						<div style="text-align:right;">
							<span id="checkMsg" style="width:180px; margin-left:0; align-items:center; text-align:right;"></span>
						</div>
						<div class="form-group">
							<label for="df_pictureName">사진 : </label>
							<input type="file" id="df_pictureName" name="df_pictureName" />
						</div>
						<div class="form-group">
							<label for="df_material">재질 : </label>
							<input type="text" id="df_material" name="df_material" />
						</div>
						<div class="form-group">
							<label for="df_size">크기 : </label>
							<input type="text" id="df_size" name="df_size" />
						</div>
						<div class="form-group">
							<label>용도 : </label>
							<input type="text" id="df_usage" name="df_usage" />
						</div>
						<div class="form-group">
							<label>시험편 정보 : </label>
							<input type="text" id="df_form" name="df_form" />
						</div>
						<div class="form-group">
							<label>제작일자 : </label><br>
							<input type="text" id="df_manufacture" name="df_manufacture" />
						</div>
						<div class="form-group">
							<label>사용여부 : </label><br>
							<input type="text" id="df_itemStatus" name="df_itemStatus" />
						</div>
						<div class="form-group">
							<label>이동현황 : </label><br>
							<select id="df_moveStatus" name="df_moveStatus">
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
							<label>비고 : </label><br>
							<input type="text" id="note" name="note" />
						</div>
						<button type="submit">추가하기</button>
					</div>
				</form>
			</div>
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
