<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- sidebar.jsp --%>
<div class="sidebar">
    <div class="filter-group">
        <div class="filter-title" onclick="toggleOptions(this)">보고서 게시판 ▾</div>
        <div class="filter-options">
            <label><a href="${contextPath}/report/reportArea2.do"> 작업일보 </a></label>
            <label><a href="${contextPath}/report/followingMonth.do"> 익월예상보고(미완) </a></label>
        </div>
    </div>

    <div class="filter-group">
        <div class="filter-title" onclick="toggleOptions(this)">시험편 관리 ▾</div>
        <div class="filter-options">
            <label><a href="${contextPath}/blockManagement/blockList.do"> 시험편 관리</a></label>
        </div>
    </div>
</div>

