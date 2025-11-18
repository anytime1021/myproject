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
<body>
	<%@ include file="../include/header2.jsp" %>
	<div class="moveForm-container">
		<%@ include file="../include/sidebar.jsp" %>
		<div class="moveReport-container">
			<div class="moveReport">
				<form name="createBlockForm" autocomplete="off" method="post" action="${contextPath}/blockManagement/createBlock.do" enctype="multipart/form-data">
					<div style="width: 80%; margin: 10px 0 0 0; display: flex;">
						<label style="display:flex;">요청서 제목 : </label>
						<div class="searchWithButton" style="margin-left:10px;">
							<input type="text" name="createBlockBoard_title" style="width:250px; border:1px solid black;" placeholder="게시글 제목 입력">
						</div>
					</div>
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
					    	<td class="col-label" colspan="2">[도면]</td>
					    	<td class="col-value" colspan="4">
								<input type="file" name="cbd_drawings" multiple>
							</td>
					    </tr>
						<tr>
							<td class="col-group" colspan="2">기술팀 검토 결과</td>
							<td class="col-value" colspan="4"><input type="text" name="technical_team_comment" style="height:100px;" <c:if test="${department ne '기술'}"> readonly </c:if>></td>
						</tr>
					</table>
					<button type="submit">승인 요청</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
</html>
