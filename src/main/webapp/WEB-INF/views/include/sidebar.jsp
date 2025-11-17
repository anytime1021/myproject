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
<!--	<c:if test="${sessionScope.login.login_report_access == 1}">-->
<!--	    <div class="filter-group">-->
<!--	        <div class="filter-title" onclick="toggleOptions(this)">보고서 게시판 ▾</div>-->
<!--	        <div class="filter-options">-->
<!--	            <label><a href="${contextPath}/report/reportArea.do"> 작업일보 </a></label>-->
<!--	            <label><a href="${contextPath}/report/followingMonth.do"> 익월예상보고(임시) </a></label>-->
<!--				<label><a href="${contextPath}/sow/sowAddEmployeeForm.do"> 근무인원설정 </a></label>-->
<!--	        </div>-->
<!--	    </div>-->
<!--	</c:if>-->
	
	<c:if test="${sessionScope.login.login_block_access == 1}">
		<div class="filter-group">
		    <div class="filter-title" onclick="toggleOptions(this)">시험편 관리 ▾</div>
		    <div class="filter-options">
		        <div class="sub-filter-group">
		            <div class="sub-filter-title" onclick="toggleOptions(this)">시험편 관련 ▾</div>
		            <div class="sub-filter-options">
		                <label><a href="${contextPath}/blockManagement/blockTotalList.do">• 전체 시험편</a></label>
		                <label><a href="${contextPath}/blockManagement/blockList.do">• 사업소 보유 시험편</a></label>
		                <label><a href="${contextPath}/blockManagement/blockRentalList.do">• 대여 시험편</a></label>
		            </div>
		        </div>
		        <div class="sub-filter-group">
		            <div class="sub-filter-title" onclick="toggleOptions(this)">이동 관련 ▾</div>
		            <div class="sub-filter-options">
		                <label><a href="${contextPath}/blockManagement/blockMoveList.do">• 이동 기록</a></label>
		                <label><a href="${contextPath}/blockManagement/blockApproval.do">• 이동 요청(사업소)</a></label>
						<label><a href="${contectPath}/blockManagement/expertApproval.do">• 이동 요청(외부)</a></label>
		            </div>
		        </div>
				<div class="sub-filter-group">
				    <div class="sub-filter-title" onclick="toggleOptions(this)">점검 관련 ▾</div>
				    <div class="sub-filter-options">
				        <label><a href="${contextPath}/blockManagement/blockInspectionList.do">• 시험편 점검</a></label>
				    </div>
				</div>
				<div class="sub-filter-group">
				    <div class="sub-filter-title" onclick="toggleOptions(this)">제작 관련 ▾</div>
				    <div class="sub-filter-options">
				        <label><a href="${contextPath}/blockManagement/createBlockList.do">• 제작 요청 게시판</a></label>
				    </div>
				</div>
		    </div>
		</div>
	</c:if>
</div>

