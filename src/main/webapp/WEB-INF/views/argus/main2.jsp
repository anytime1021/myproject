<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Argus</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 
	<link rel="stylesheet" href="${contextPath}/resources/css/main2.css">     
	</head>
	<body>
		<%@ include file="../include/header2.jsp" %>
		<section class="main-container">
			<div class="main-quarter">
				<div class="quarter">
					<div class="report-board">
						<div class="report-board-title">작업일보</div>
						<div class="shortcut">바로가기 + </div>
					</div>
					<div class="list">
						<ul>
							<c:forEach var="workrateListValue" items="${workrateList}" varStatus="status">
								<li><a href="${contextPath}/report/reportView.do?board_num=${workrateListValue.board_num}&work_date=${workrateListValue.work_date}">${status.index+1}. ${workrateListValue.board_title} ${workrateListValue.work_date}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="quarter">
					<div class="report-board">
						<div class="report-board-title">게시판 제목을 입력해주세요.</div>
						<div class="shortcut"></div>
					</div>
					<div class="list">
						<p class="sub-text">게시판 내용을 입력해주세요.</p>
					</div>
				</div>
			</div>
		</section>
		<section class="content">
			<h2>사업분야</h2>
			<p>첨단의 기술도 기술자의 역량만큼 빛을 발하기 마련입니다.</p>
		</section>
		<%@ include file="../include/footer2.jsp" %>
	</body>
</html>
