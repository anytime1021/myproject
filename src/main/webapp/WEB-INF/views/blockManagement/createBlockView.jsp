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
	<title>시험편 제작 승인 요청서</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/moveBlockForm.css">
</head>
<style>
	.submitButton {display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;};
	a:active {} 
</style>
<body>
	<%@ include file="../include/header2.jsp" %>
	<div class="moveForm-container">
		<%@ include file="../include/sidebar.jsp" %>
		<div class="moveReport-container">
			<div class="moveReport">
				<form name="createBlockForm" autocomplete="off" method="post">
					<table class="report">
						<colgroup>
							<col style="width: 16%;">
							<col style="width: 16%;">
							<col style="width: 16%;">
							<col style="width: 16%;">
							<col style="width: 16%;">
							<col style="width: 16%;">
						</colgroup>
					    <tr>
					    	<th class="title" colspan="4" rowspan="2">시험편 제작 승인 요청서</th>
							<th class="title-sub">사업소</th>
							<th class="title-sub">기술팀</th>
					    </tr>
						<tr>
							<th class="title-sub">
								<img src="${contextPath}/resources/img/sign-${hndArea}.png" style="width:100%; height:100%;">
							</th>
							<th class="title-sub">
								<c:choose>
									<c:when test="${createBlockView.createBlock_status eq 'Y'}">
										<img src="${contextPath}/resources/img/sign-technical_team.png" style="width:100%; height:100%;">
									</c:when>
								</c:choose>
							</th>
					    <tr>
					    	<td class="col-group" colspan="2">사업소 명</td>
					    	<td class="col-value" colspan="4"><input type="text" name="login_area" value="${createBlockView.login_area}" readonly></td>
					    </tr>
						<tr>
							<td class="col-group" colspan="2">작성 날짜</td>
							<td class="col-value" colspan="4"><input type="text" name="createBlock_date" value="${createBlockView.createBlock_date}" readonly></td>
						<tr>
						<tr>
							<td class="col-group" colspan="2">적용 규격</td>
							<td class="col-value" colspan="4"><input type="text" name="blockSpec" value="${createBlockView.blockSpec}" readonly></td>
						<tr>
						<tr>
							<td class="col-group" colspan="2">사용 용도</td>
							<td class="col-value" colspan="4"><input type="text" name="df_usage" value="${createBlockView.df_usage}" readonly></td>
						<tr>
						<tr>
							<td class="col-group" colspan="2" rowspan="2">Block Spec.</td>
							<td class="col-value">재질</td>
							<td class="col-value"><input type="text" name="blockSpec_material" value="${createBlockView.blockSpec_material}" readonly></td>
							<td class="col-value">두께</td>
							<td class="col-value"><input type="text" name="blockSpec_thick" value="${createBlockView.blockSpec_thick}" readonly></td>
						</tr>
						<tr>
							<td class="col-value">관경</td>
							<td class="col-value"><input type="text" name="blockSpec_diameter" value="${createBlockView.blockSpec_diameter}" readonly></td>
							<td class="col-value">용접 형상</td>
							<td class="col-value"><input type="text" name="blockSpec_weld" value="${createBlockView.blockSpec_weld}" readonly></td>
						</tr>
						<tr>
							<td class="col-group" colspan="2">[도면]</td>
							<td class="col-value" colspan="4"><a href="#" onclick="submitDrawingView('${contextPath}/blockManagement/drawingView.do', '${createBlockView.createBlock_num}')">도면보기</a></td>
						<tr>
							<td class="col-group" colspan="2">기술팀 검토 결과</td>
							<td class="col-value" colspan="4"><input type="text" name="technical_team_comment" style="height:100px;" value="${createBlockView.technical_team_comment}" <c:if test="${department ne '기술' && createBlockView.createBlock_status eq 'W'}"> readonly </c:if>></td>
						</tr>
						<input type="hidden" name="createBlock_num" value="${createBlockView.createBlock_num}">
					</table>
					<div style="display:flex; justify-content:right;">
						<c:if test="${department eq '기술' && createBlockView.createBlock_status eq 'W'}">
							<a href="#" onclick="submitApproval('${contextPath}/blockManagement/createBlockApproval.do', '${createBlockView.createBlock_num}')"
							class="submitButton">
							승인
							</a>
							<a href="#" onclick="submitApproval('${contextPath}/blockManagement/createBlockRejection.do', '${createBlockView.createBlock_num}')"
							class="submitButton">
							거절
							</a>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
<script>
	const form = document.forms["createBlockForm"];
	
	const createBlock_numStr = "${createBlockView.createBlock_num}";
	
	form.addEventListener("submit", function(e) {
		if (!form.dataset.allowSubmit) {
			e.preventDefault();
			return false;
		}
	});

	function submitApproval(url, createBlock_num) {


        form.action = url;
        form.method = "get";

        form.dataset.allowSubmit = "true";
        form.submit();
        form.dataset.allowSubmit = "false";
	}
	
	function submitDrawingView(url, createBlock_num) {

        form.action = url;
        form.method = "get";

        form.dataset.allowSubmit = "true";
        form.submit();
        form.dataset.allowSubmit = "false";
	}
</script>
</html>
