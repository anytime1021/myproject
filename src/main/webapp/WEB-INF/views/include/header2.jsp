<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<link rel="stylesheet" href="${contextPath}/resources/css/main2.css">
<link rel="stylesheet" href="${contextPath}/resources/css/sidebar.css">
<script>
	function toggleOptions(el) {
	    const options = el.nextElementSibling;

	    if (options.style.display === 'block') {
	        options.style.display = 'none';
	        el.textContent = el.textContent.replace('▴', '▾');
	    } else {
	        options.style.display = 'block';
	        el.textContent = el.textContent.replace('▾', '▴');
	    }
	}
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

</style>
<header>
	<div class="login-right">
		<div></div>
		<div class="login-box">
			<nav>
				<ul class="login-menu">
					<c:choose>
						<c:when test="${logOn}">
							<li class="border-right">${login.login_area} 님
							</li>
							<li class="border-right"><a href="${contextPath}/login/logout.do">로그아웃</a></li>
							<li><a href="${contextPath}/user/umReserInfo.do?u_id=${user.u_id}">마이페이지</a></li>				
						</c:when>
						<c:otherwise>
							<li><a href="${contextPath}/login/loginForm.do">로그인</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
	</div>
	<input type="hidden" name="login_report_access" id="login_report_access" value="${sessionScope.login.login_report_access}">
	<input type="hidden" name="login_block_access" id="login_block_access" value="${sessionScope.login.login_block_access}">
	<div class="head-container">
		<div class="logo">
			<a href="${contextPath}/blockManagement/blockList.do"><img src="${contextPath}/resources/img/argusImage.jpg" alt="Argus Logo"></a>
		</div>
		<div class="board-title">
			<nav>
				<ul class="menu">
					<li></li>
<!--					<li><a href="${contextPath}/report/reportArea.do" onclick="return checkAccess('report')">보고서게시판</a></li>-->
<!--					<li><a href="${contextPath}/blockManagement/blockList.do" onclick="return checkAccess('block')">시험편 관리</a></li>-->
						<li><a href="${contextPath}/blockManagement/blockList.do">시험편 관리</a></li>
					<li>-</li>
					<li>-</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<script>
    function checkAccess(type) {
        const reportAccess = document.getElementById("login_report_access").value;
        const blockAccess = document.getElementById("login_block_access").value;

        if (type === "report") {
            if (reportAccess != 1) {
                alert("권한이 없습니다.");
                return false;
            }
        }

        if (type === "block") {
            if (blockAccess != 1) {
                alert("권한이 없습니다.");
                return false;
            }
        }
        return true;
    }
</script>