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
    <link rel="stylesheet" href="${contextPath}/resources/css/boardStyle.css">

</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
			<div class="contents-container">
			    <div class="contents-list">
			        <table class="table-blockView">
						<tbody>
							<tr>
								<td class="background" style="width:20%;">식별번호</td>
								<td class="background" style="width:20%;">크기</td>
								<td class="background" style="width:15%;">재질</td>
								<td class="background" style="width:15%;">용도</td>
								<td class="background" style="width:15%;">상태</td>
								<td class="background" style="width:15%;">시험편정보</td>
							</tr>
							<tr>
								<td style="width:20%;">${blockView.df_idNumber}</td>
								<td style="width:20%;">${blockView.df_size}</td>
								<td style="width:15%;">${blockView.df_material}</td>
								<td style="width:15%;">${blockView.df_usage}</td>
								<td style="width:15%;">${blockView.df_itemStatus}</td>
								<td style="width:15%;">${blockView.df_form}</td>
							</tr>
							<tr>
								<td class="background" colspan="5" style="width:85%;">사진</td>
								<td class="background" style="width:15%;">제작일자</td>
							</tr>
							<tr>
								<td colspan="5" rowspan="7" style="width:85%; height:300px;"><img src="${contextPath}/resources/img/${blockView.df_pictureName}" style="object-fit:contain; display:block; margin: 0 auto;"></td>
								<td width="15%">${blockView.df_manufacture}</td>
							</tr>
							<tr>
								<td class="background" >이동현황</td>
							</tr>
							<tr>
								<td>${blockView.df_moveStatus}</td>
							</tr>
							<tr>
								<td class="background" >비고</td>
							</tr>
							<c:choose>
								<c:when test="${empty blockView.note}">
									<tr>
										<td>
											-
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td>${blockView.note}</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<c:if test="${blockView.login_area eq searchArea}">
						<c:if test="${blockView.df_itemStatus eq '사용중'}">
							<div class="btn-wrapper">
								<a class="btn" href="${contextPath}/blockManagement/moveBlockForm.do?df_idNumber=${blockView.df_idNumber}">시험편 대여 등록</a>
								<a class="btn" href="${contextPath}/blockManagement/modBlockForm.do?df_idNumber=${blockView.df_idNumber}">수정하기</a>
								<a class="btn" onclick="return confirm('정말 삭제하시겠습니까?');" href="${contextPath}/blockManagement/removeBlock.do?df_idNumber=${blockView.df_idNumber}">삭제하기</a>
							</div>
						</c:if>
					</c:if>
				</div>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
