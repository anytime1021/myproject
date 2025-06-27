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
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
            <div class="report-container">
                <div class="report-list">
                    <table class="table-control">
                        <thead>
                            <tr>
                                <th style="width:10%; background-image: url('${contextPath}/resources/img/th-background.jpg">NO</th>
                                <th style="width:80%; background-image: url('${contextPath}/resources/img/th-background.jpg"">제 목</th>
                                <th style="width:10%; background-image: url('${contextPath}/resources/img/th-background.jpg"">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="reportListJsp" items="${reportListJsp}">
                                <tr>
                                    <td>${reportListJsp.row_num}</td>
                                    <td><a href="${contextPath}/report/reportView.do?board_num=${reportListJsp.board_num}&work_date=${reportListJsp.work_date}">${reportListJsp.board_title}</a></td>
                                    <td>${reportListJsp.work_date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a class="btn" href="${contextPath}/report/addDailyReportForm.do">작성하기</a>
            </div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
</html>
