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
				<form name="moveBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/moveBlock.do">
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
							<th class="title-sub">
								<img src="${contextPath}/resources/img/sign-yeosu.png" style="width:100%; height:100%;">
							</th>
							<th class="title-sub">
								<c:choose>
									<c:when test="${ApprovalView.app_rcv_status eq 'Y'}">
										<img src="${contextPath}/resources/img/sign-seosan.png" style="width:100%; height:100%;">
									</c:when>
								</c:choose>
							</th>
							<th class="title-sub">
								<c:choose>
									<c:when test="${ApprovalView.app_head_status eq 'Y'}">
										<img src="${contextPath}/resources/img/sign-qualityTeam.png" style="width:100%; height:100%;">
									</c:when>
								</c:choose>
							</th>
					    <tr>
					    	<td class="col-group" colspan="2">시험편 식별 번호</td>
					    	<td class="col-value" colspan="4"><input type="text" value="${ApprovalView.df_idNumber}" readonly></td>
					    </tr>
						<tr>
							<td class="col-group" colspan="2">이동 사유</td>
							<td class="col-value" colspan="4"><input type="text" value="${ApprovalView.app_hnd_comment}" readonly></td>
					    <tr>
					    	<td class="col-group" rowspan="4">인계</td>
					    	<td class="col-label">소 속</td>
							<td class="col-value" colspan="4"><input type="text" name="login_area" value="${ApprovalView.login_area}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_name" value="${ApprovalView.app_hnd_name}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">인계일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_create_at" value="${ApprovalView.app_hnd_create_at}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">운송방법</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_transMethod" value="${ApprovalView.app_hnd_transMethod}" readonly></td>
						</tr>
						<tr>
							<td class="col-group" rowspan="4">인수</td>
							<td class="col-label">소 속</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_area" value="${ApprovalView.app_rcv_area}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_name" value="${ApprovalView.app_rcv_name}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">인수일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_create_at" value="${ApprovalView.app_rcv_create_at}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">이상유무</td>
							<td class="col-value" colspan="4"><textarea name="app_isError" value="${ApprovalView.app_isError}"></textarea></td>
						</tr>
						<tr>
					    	<td class="col-label" colspan="2">[특이사항]</td>
					    	<td class="col-value" colspan="4">
								<input type="text" name="note" value="${ApprovalView.note}" style="height: 150px;" readonly>
							</td>
					    </tr>
					</table>
					<div style="display:flex; justify-content:right;">
						<c:choose>
							<c:when test="${searchArea eq ApprovalView.app_rcv_area && ApprovalView.app_rcv_status eq 'W'}">
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateApproval.do', '${ApprovalView.app_num}')"
								   style="display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;">
								   승인
								</a>
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateRejection.do', '${ApprovalView.app_num}')"
								   style="display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;">
								   거절
								</a>
							</c:when>
							<c:when test="${searchArea eq '본사' && ApprovalView.app_rcv_status eq 'Y' && ApprovalView.app_head_status eq 'W'}">
								<c:if test="${ApprovalView.app_type eq 'rental'}">
									<a href="#"
									   onclick="submitApproval('${contextPath}/blockManagement/updateApproval.do', '${ApprovalView.app_num}')"
									   style="display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;">
									   승인
									</a>
								</c:if>
								<c:if test="${ApprovalView.app_type eq 'return'}">
									<a href="#"
									   onclick="submitApproval('${contextPath}/blockManagement/returnApproval.do', '${ApprovalView.app_num}')"
									   style="display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;">
									   승인
									</a>
								</c:if>
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateRejection.do', '${ApprovalView.app_num}')"
								   style="display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;">
								   거절
								</a>
							</c:when>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer2.jsp"%>
</body>
<script>
const form = document.forms["moveBlockList"];

form.addEventListener("submit", function(e) {
    if (!form.dataset.allowSubmit) {
        e.preventDefault();
        return false;
    }
});

function submitApproval(url, appNum) {
    // 기존 hidden input 중복 방지
    let existing = form.querySelector('input[name="app_num"]');
    if (existing) form.removeChild(existing);

    let hiddenAppNum = document.createElement("input");
    hiddenAppNum.type = "hidden";
    hiddenAppNum.name = "app_num";
    hiddenAppNum.value = appNum;
    form.appendChild(hiddenAppNum);

    form.action = url;
    form.method = "get";
	
	form.dataset.allowSubmit = "true";
	form.submit();
	
    form.dataset.allowSubmit = "false";
}
</script>
</html>
