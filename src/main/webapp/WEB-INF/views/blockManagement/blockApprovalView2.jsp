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
		<div class="moveReport">
			<form name="moveBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/moveBlock.do">
				<table class="report">
				    <tr>
				    	<th class="title" colspan="3">시험편 이동 보고서</th>
				    </tr>
				    <tr>
				    	<td class="col-group" colspan="2">작성 날짜</td>
				    	<td class="col-value"><input type="text" name="created_at" value="${ApprovalView.created_at}" readonly></td>
				    </tr>
				    <tr>
				    	<td class="col-group" rowspan="3">인계자</td>
				    	<td class="col-label">소 속</td>
				    	<td class="col-value">
							<input type="text" name="login_area" value="${ApprovalView.login_area}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-label">성 명</td>
				    	<td class="col-value">
							<input type="text" name="moveList_lender" value="${ApprovalView.moveList_lender}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-label">직 위</td>
				    	<td class="col-value">
							<input type="text" name="moveList_lender_rank" value="${ApprovalView.moveList_lender_rank}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-group" rowspan="3">인수자</td>
				    	<td class="col-label">소 속</td>
				    	<td class="col-value">
							<input type="text" name="moveList_recipient_area" value="${ApprovalView.moveList_recipient_area}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-label">성 명</td>
				    	<td class="col-value">
							<input type="text" name="moveList_recipient" value="${ApprovalView.moveList_recipient}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-label">직 위</td>
				    	<td class="col-value">
							<input type="text" name="moveList_recipient_rank" value="${ApprovalView.moveList_recipient_rank}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-group" colspan="2">시험편 식별번호</td>
				    	<td class="col-value">
							<input type="text" id="df_idNumber" name="df_idNumber" value="${ApprovalView.df_idNumber}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-group" colspan="2">이동 날짜</td>
				    	<td class="col-value">
							<input type="text" name="moveList_rental_date" value="${ApprovalView.moveList_rental_date}" readonly>
						</td>
				    </tr>
				    <tr>
				    	<td class="col-group" colspan="2">[특이사항]</td>
				    	<td class="col-value notes">
							<textarea name="note" value="${ApprovalView.note}" readonly></textarea>
						</td>
				    </tr>
				</table>
				<div style="width: 100%; height: auto; display: flex;">
					<button style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease; border-right: 1px solid white;"
					onclick="Approval(this)">승인</button>
					<button style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease;"
					onclick="Rejection(this)">거절</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
<script>
	function Approval(button) {
	
		const id = document.getElementById("df_idNumber");
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/updateApproval.do";
		
		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "df_idNumber";
		inputId.value = id;
		
		form.appendChild(inputId);
		document.body.appendChild(form);
		form.submit();
	}
	
	function Rejection(button) {

		const id = document.getElementById("df_idNumber");
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/updateRejection.do";
		
		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "df_idNumber";
		inputId.value = id;
		
		form.appendChild(inputId);
		document.body.appendChild(form);
		form.submit();
	}
</html>
