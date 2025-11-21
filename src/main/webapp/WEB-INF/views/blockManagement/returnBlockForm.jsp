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
				<form name="returnBlockApproval" autocomplete="off" method="post" action="${contextPath}/blockManagement/returnBlockApproval.do">
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
					    	<th class="title" colspan="3" rowspan="2">시험편 이동 보고서</th>
							<th class="title-sub">인계</th>
							<th class="title-sub">인수</th>
							<th class="title-sub">품질팀</th>
					    </tr>
						<tr>
							<th class="title-sub"></th>
							<th class="title-sub"></th>
							<th class="title-sub"></th>
					    <tr>
					    	<td class="col-group" colspan="2">시험편 식별 번호</td>
					    	<td class="col-value" colspan="4"><input type="text" name="df_idNumber" value="${returnBlockForm.df_idNumber}" reaonly></td>
					    </tr>
						<tr>
							<td class="col-group" colspan="2">이동 사유</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_comment" placeholder="이동 사유 입력"></td>
					    <tr>
					    	<td class="col-group" rowspan="4">인계</td>
					    	<td class="col-label">소 속</td>
							<td class="col-value" colspan="4"><input type="text" name="login_area" value="${returnBlockForm.app_rcv_area}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_name" value="${login_name}"></td>
						</tr>
						<tr>
							<td class="col-label">인계일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_create_at" value="${timeNow}"></td>
						</tr>
						<tr>
							<td class="col-label">운송방법</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_transMethod"></td>
						</tr>
						<tr>
							<td class="col-group" rowspan="4">인수</td>
							<td class="col-label">소 속</td>
							<td class="col-value" colspan="4">
								<input type="text" name="app_rcv_area" value="${returnBlockForm.login_area}" readonly>
							</td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_name" placeholder="성명 입력"></td>
						</tr>
						<tr>
							<td class="col-label">인수일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_create_at" readonly></td>
						</tr>
						<tr>
							<td class="col-label">이상유무</td>
							<td class="col-value" colspan="4"><input type="text" name="app_isError" readonly></td>
						</tr>
						<tr>
					    	<td class="col-label" colspan="2">[특이사항]</td>
					    	<td class="col-value" colspan="4">
								<input type="text" name="note" style="height: 150px;" placeholder="특이사항 입력">
							</td>
					    </tr>
						<input type="hidden" name="app_num" value="${returnBlockForm.app_num}">
					</table>
					<button type="submit">시험편 이동 등록</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
</html>
