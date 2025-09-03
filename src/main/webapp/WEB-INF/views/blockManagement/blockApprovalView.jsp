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
				<form name="moveBlockList" autocomplete="off">
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
								<textarea name="note" readonly> ${ApprovalView.note} </textarea>
							</td>
					    </tr>
					</table>
					<input type="hidden" id="app_num" name="app_num" value="${ApprovalView.app_num}">
					<c:choose>
					    <c:when test="${searchArea eq ApprovalDivision.app_branch_area}">
					        <c:if test="${ApprovalDivision.app_branch_status ne '승인'}">
					            <!-- 승인/거절 버튼 -->
					            <div style="width: 100%; height: auto; display: flex;">
					                <button type="button" style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease; border-right: 1px solid white;"
					                onclick="Approval(this)">승인</button>
					                <button type="button" style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease;"
					                onclick="Rejection(this)">거절</button><br>
					            </div>
								<div style="width: 100%; margin-top: 12px;">
								    <label for="app_comment" style="font-weight: bold; margin-bottom: 5px; display: block;">[Comment]</label>
								    <textarea id="app_comment" name="app_comment" style="width: 100%; height: 50px; border: 1px solid black; resize: vertical; text-align: left;"></textarea>
								</div>
					        </c:if>
					    </c:when>
					    <c:when test="${searchArea eq '본사'}">
					        <c:if test="${ApprovalDivision.app_head_status ne '승인'}">
					            <!-- 승인/거절 버튼 -->
					            <div style="width: 100%; height: auto; display: flex;">
					                <button type="button" style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease; border-right: 1px solid white;"
					                onclick="Approval(this)">승인</button>
					                <button type="button" style="display: flex; justify-content: center; align-items: center; width: 50%; padding: 12px 0; background: #4a90e2; border: none; color: #FFFFFF; font-size: 17px; font-weight: 700; cursor: pointer; transition: background 0.25s ease;"
					                onclick="Rejection(this)">거절</button>
					        	</div>
								<div style="width: 100%; margin-top: 12px;">
								    <label for="app_comment" style="font-weight: bold; margin-bottom: 5px; display: block;">[Comment]</label>
								    <textarea id="app_comment" name="app_comment" style="width: 100%; height: 50px; border:1px solid black; resize: vertical; text-align: left;"></textarea>
								</div>
					    	</c:if>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${ApprovalDivision.app_branch_comment ne '' && ApprovalDivision.app_branch_comment ne null}">
							${ApprovalDivision.app_branch_area} Comment : ${ApprovalDivision.app_branch_comment}
						</c:when>
						<c:otherwise>
							${ApprovalDivision.app_branch_area} Comment : 없음
						</c:otherwise>
					</c:choose>
					<br>
					<c:choose>
						<c:when test="${ApprovalDivision.app_head_comment ne '' && ApprovalDivision.app_head_comment ne null}">
							본사 Comment : ${ApprovalDivision.app_head_comment}
						</c:when>
						<c:otherwise>
							본사 Comment : 없음
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
<script>
	function Approval(button) {
		const app_num = document.getElementById("app_num");
		const app_numVal = app_num.value;

		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/updateApproval.do";

		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "app_num_Str";
		inputId.value = app_numVal;

		form.appendChild(inputId);
		
        const app_comment = document.getElementById("app_comment").value;
        const inputComment = document.createElement("input");
        inputComment.type = "hidden";
        inputComment.name = "app_comment";
        inputComment.value = app_comment;
        form.appendChild(inputComment);
		
		document.body.appendChild(form);
		form.submit();
	}

	function Rejection(button) {
		const app_num = document.getElementById("app_num");
		const app_numVal = app_num.value;

		const form = document.createElement("form");
		form.method = "POST";
		form.action = "/blockManagement/updateRejection.do";

		const inputId = document.createElement("input");
		inputId.type = "hidden";
		inputId.name = "app_num_Str";
		inputId.value = app_numVal;

		form.appendChild(inputId);

		const app_comment = document.getElementById("app_comment").value;
		const inputComment = document.createElement("input");
		inputComment.type = "hidden";
		inputComment.name = "app_comment";
		inputComment.value = app_comment;
		form.appendChild(inputComment);

		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>
