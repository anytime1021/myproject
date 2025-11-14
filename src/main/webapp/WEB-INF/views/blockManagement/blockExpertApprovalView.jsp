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
<style>
	.submitButton {display:flex; width:120px; padding:12px 0; color:black; font-size:17px; font-weight:700; justify-content:center; border:1px solid black;};
</style>
<body>
	<%@ include file="../include/header2.jsp" %>
	<div class="moveForm-container">
		<%@ include file="../include/sidebar.jsp" %>
		<div class="moveReport-container">
			<div class="moveReport">
				<form name="expertBlockList" autocomplete="off" method="post" action="${contextPath}/blockManagement/expertBlockList.do" enctype="multipart/form-data">
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
							<th class="title" colspan="3" rowspan="2">시험편 반출증</th>
							<th class="title-sub">인계</th>
							<th class="title-sub">품질팀</th>
							<th class="title-sub">인수</th>
						</tr>
						<tr>
							<th class="title-sub">
								<c:if test="${expertApprovalView.app_type eq 'rental'}">
									<img src="${contextPath}/resources/img/sign-${hndArea}.png" style="width:100%; height:100%;">
								</c:if>
								<c:if test="${expertApprovalView.app_type eq 'return'}">
									<img src="${contextPath}/resources/img/${expertApprovalView.expSign_name}" style="width:100%; height:100%;">
								</c:if>
							</th>
							<th class="title-sub">
								<c:choose>
									<c:when test="${expertApprovalView.app_head_status eq 'Y'}">
										<img src="${contextPath}/resources/img/sign-qualityTeam.png" style="width:100%; height:100%;">
									</c:when>
								</c:choose>
							</th>
							<th class="title-sub">
								<c:choose>
									<c:when test="${expertApprovalView.app_rcv_status eq 'Y'}">
										<img src="${contextPath}/resources/img/${expertApprovalView.expSign_name}" style="width:100%; height:100%;">
									</c:when>
								</c:choose>
							</th>
						<tr>
							<td class="col-group" colspan="2">시험편 식별 번호</td>
							<td class="col-value" colspan="4"><input type="text" name="df_idNumber" value="${expertApprovalView.df_idNumber}"></td>
						</tr>
						<tr>
							<td class="col-group" colspan="2">반출 사유</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_comment" value="${expertApprovalView.app_hnd_comment}"></td>
						<tr>
						<tr>
							<td class="col-group" colspan="2">반출 기간</td>
							<td class="col-value" colspan="4"><input type="text" name="app_period" value="${expertApprovalView.app_period}"></td>
						<tr>
					    	<td class="col-group" rowspan="4">인계</td>
					    	<td class="col-label">소 속</td>
							<td class="col-value" colspan="4"><input type="text" name="login_area" value="${expertApprovalView.login_area}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_name" value="${expertApprovalView.app_hnd_name}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">인계일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_hnd_create_at" value="${expertApprovalView.app_hnd_create_at}" readonly></td>
						</tr>
						<c:choose>
							<c:when test="${expertApprovalView.app_type eq 'rental'}">
								<tr>
									<td class="col-label">운송방법</td>
									<td class="col-value" colspan="4"><input type="text" name="app_hnd_transMethod" value="${expertApprovalView.app_hnd_transMethod}" readonly></td>
								</tr>
							</c:when>
							<c:when test="${expertApprovalView.app_type eq 'return'}">
								<tr>
									<td class="col-label">운송방법</td>
									<td class="col-value" colspan="4"><input type="text" name="app_hnd_transMethod"></td>
								</tr>
							</c:when>
						</c:choose>
						<tr>
							<td class="col-group" rowspan="4">인수</td>
							<td class="col-label">소 속</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_area" value="${expertApprovalView.app_rcv_area}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">성 명</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_name" value="${expertApprovalView.app_rcv_name}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">인수일</td>
							<td class="col-value" colspan="4"><input type="text" name="app_rcv_create_at" value="${expertApprovalView.app_rcv_create_at}" readonly></td>
						</tr>
						<tr>
							<td class="col-label">이상유무</td>
							<td class="col-value" colspan="4"><textarea name="app_isError"><c:out value="${expertApprovalView.app_isError}"/></textarea></td>
						</tr>
						<tr>
					    	<td class="col-label" colspan="2">[특이사항]</td>
					    	<td class="col-value" colspan="4">
								<input type="text" name="note" value="${expertApprovalView.note}" style="height: 130px;">
							</td>
					    </tr>
						<input type="hidden" name="app_type" value="${expertApprovalView.app_type}">
					</table>
					<div style="display:flex; justify-content:right;">
						<c:choose>
							<c:when test="${searchArea eq '본사' && expertApprovalView.app_head_status eq 'W'}">
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateExpertApproval.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
								   승인
								</a>
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateExpertRejection.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
								   거절
								</a>
							</c:when>
							<c:when test="${searchArea eq '본사' && expertApprovalView.app_head_status eq 'Y' && empty expertApprovalView.expSign_name}">
								<input type="file" name="expertSign">
								<a href="#"
									onclick="submitExpertSign('${contextPath}/blockManagement/updateExpertSign.do', '${expertApprovalView.app_num}')"
									class="submitButton">
									사인등록
								</a>
							</c:when>
							<c:when test="${searchArea eq '본사' && expertApprovalView.app_head_status eq 'Y' && not empty expertApprovalView.expSign_name && expertApprovalView.app_rcv_status eq 'W' && expertApprovalView.app_type eq 'rental'}">
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateExpertApproval.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
								   승인
								</a>
								<a href="#"
								   onclick="submitApproval('${contextPath}/blockManagement/updateExpertRejection.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
								   거절
								</a>
							</c:when>
							<c:when test="${searchArea eq expertApprovalView.login_area && expertApprovalView.app_head_status eq 'Y' && not empty expertApprovalView.expSign_name && expertApprovalView.app_rcv_status eq 'Y' && expertApprovalView.returnRequest eq 'N'}">
								<a href="${contextPath}/blockManagement/returnExpertApprovalForm.do?app_num=${expertApprovalView.app_num}"
								class="submitButton">
									반납요청
								</a>
							</c:when>
							<c:when test="${searchArea ne '본사' && expertApprovalView.app_type eq 'return' && expertApprovalView.app_head_status eq 'Y' && expertApprovalView.app_rcv_status eq 'W'}">
								<a href="#"
								   onclick="returnSubmit('${contextPath}/blockManagement/updateFinalExpertApproval.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
								   승인
								</a>
								<a href="#"
    							   onclick="submitReject('${contextPath}/blockManagement/updateExpertRejection.do', '${expertApprovalView.app_num}')"
								   class="submitButton">
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
const form = document.forms["expertBlockList"];

const searchArea = "${searchArea}";
const appHeadStatus = "${expertApprovalView.app_head_status}";
const app_rcv_area = "${expertApprovalView.app_rcv_area}";
const app_type = "${expertApprovalView.app_type}";

form.addEventListener("submit", function(e) {
    if (!form.dataset.allowSubmit) {
        e.preventDefault();
        return false;
    }
});

function submitApproval(url, appNum) {
    // 기존 hidden input 중복 방지
	form.querySelectorAll('input[name="app_num"], input[name="token"]').forEach(el => el.remove());

    let hiddenAppNum = document.createElement("input");
    hiddenAppNum.type = "hidden";
    hiddenAppNum.name = "app_num";
    hiddenAppNum.value = appNum;
    form.appendChild(hiddenAppNum);

	let tokenValue = null;
	if (searchArea === "본사" && appHeadStatus !== "Y") {
		tokenValue = 1;
	} else if (searchArea === "본사" && appHeadStatus === "Y") {
		tokenValue = 2;
	}
	
	if (tokenValue !== null) {
		let hiddenToken = document.createElement("input");
		hiddenToken.type = "hidden";
		hiddenToken.name = "token";
		hiddenToken.value = tokenValue;
		form.appendChild(hiddenToken);
	}
	
    form.action = url;
    form.method = "get";
	
	form.dataset.allowSubmit = "true";
	form.submit();
	
    form.dataset.allowSubmit = "false";
}

function submitExpertSign(url, appNum) {
	const fileInput = form.querySelector('input[name="expertSign"]');
	if (!fileInput || !fileInput.files.length) {
		alert("사인 파일을 선택해주세요.");
		return;
	}

	form.querySelectorAll('input[name="app_num"], input[name="app_rcv_area"]').forEach(el => el.remove());
	
	const hiddenAppNum = document.createElement("input");
	hiddenAppNum.type = "hidden";
	hiddenAppNum.name = "app_num";
	hiddenAppNum.value = appNum;
	form.appendChild(hiddenAppNum);
	
	const hiddenArea = document.createElement("input");
	hiddenArea.type = "hidden";
	hiddenArea.name = "app_rcv_area";
	hiddenArea.value = app_rcv_area;
	form.appendChild(hiddenArea);

	form.action = url;
	form.method = "post";
	form.enctype = "multipart/form-data";

	form.submit();
}

function returnSubmit(url, appNum) {
	const form = document.querySelector('form');
	
	form.querySelectorAll('input[name="app_num"]').forEach(el => el.remove());
	
	const hiddenAppNum = document.createElement("input");
	hiddenAppNum.type = "hidden";
	hiddenAppNum.name = "app_num";
	hiddenAppNum.value = appNum;
	form.appendChild(hiddenAppNum);
	
	form.action = url;
	form.method = "post";
	form.submit();
}

function submitReject(url, appNum) {
	form.querySelectorAll('input[name="app_num"], input[name="token"]').forEach(el => el.remove());

	let hiddenAppNum = document.createElement("input");
	hiddenAppNum.type = "hidden";
	hiddenAppNum.name = "app_num";
	hiddenAppNum.value = appNum;
	form.appendChild(hiddenAppNum);

	let tokenValue = null;
	if (searchArea !== "본사" && appHeadStatus !== "Y") {
		tokenValue = 1;
	} else if (searchArea !== "본사" && appHeadStatus === "Y") {
		tokenValue = 2;
	}

	if (tokenValue !== null) {
		let hiddenToken = document.createElement("input");
		hiddenToken.type = "hidden";
		hiddenToken.name = "token";
		hiddenToken.value = tokenValue;
		form.appendChild(hiddenToken);
	}

	form.action = url;
	form.method = "get";

	form.dataset.allowSubmit = "true";
	form.submit();

	form.dataset.allowSubmit = "false";
}
</script>
</html>
