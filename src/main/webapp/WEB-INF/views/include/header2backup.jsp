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

	    // 토글
	    if (options.style.display === 'block') {
	        options.style.display = 'none';
	    } else {
	        options.style.display = 'block';
	    }
	}
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	.header {
		display: flex;
		justify-content: space-between;
		align-items: flex-start; /* 오른쪽 위 기준 */
		padding: 10px 20px;
	}

	.logo img {
		height: 40px; /* 필요에 맞게 조절 */
	}

	.right-area {
		display: flex;
		flex-direction: column; /* 위/아래 배치 */
		align-items: flex-end;  /* 오른쪽 정렬 */
	}

	.login-menu {
		font-size: 0.85rem;
		margin-bottom: 5px;
		display: flex;
		gap: 10px;
	}

	.login-menu a {
		text-decoration: none;
		color: #007acc;
		font-weight: bold;
	}

	.login-user {
		font-weight: bold;
		color: #333;
	}

	.menu {
		display: flex;
		list-style: none;
		gap: 20px;
		margin: 0;
		padding: 0;
	}

	.menu li a {
		text-decoration: none;
		color: #000;
	}
</style>
<header>
	<input type="hidden" name="login_report_access" id="login_report_access" value="${sessionScope.login.login_report_access}">
	<input type="hidden" name="login_block_access" id="login_block_access" value="${sessionScope.login.login_block_access}">
	<div class="logo">
		<a href="${contextPath}/argus/main2.do"><img src="${contextPath}/resources/img/argusImage.jpg" alt="Argus Logo"></a>
	</div>
	<div class="right-area">
		<div class="login-menu">
			<c:choose>
				<c:when test="${logOn}">
					<li>${login.login_area} 님
	<!--				<span id="sessionTimer"></span>-->
					</li>
					<li><a href="${contextPath}/login/logout.do">로그아웃</a></li>
					<li><a href="${contextPath}/user/umReserInfo.do?u_id=${user.u_id}">마이페이지</a></li>				
				</c:when>
				<c:otherwise>
					<li><a href="${contextPath}/login/loginForm.do">로그인</a></li>
				</c:otherwise>
			</c:choose>
		</div>
		<nav>
			<ul class="menu">
				<li></li>
				<li><a href="${contextPath}/report/reportArea.do" onclick="return checkAccess('report')">보고서게시판</a></li>
				<li><a href="${contextPath}/blockManagement/blockList.do" onclick="return checkAccess('block')">시험편 관리</a></li>
				<li>-</li>
				<li>-</li>
			</ul>
		</nav>
	</div>
</header>
<script>
    function checkAccess(type) {
        const reportAccess = document.getElementById("login_report_access").value;
        const blockAccess = document.getElementById("login_block_access").value;

        if (type === "report") {
            if (reportAccess != "1") {
                alert("권한이 없습니다.");
                return false;
            }
        }

        if (type === "block") {
            if (blockAccess != "1") {
                alert("권한이 없습니다.");
                return false;
            }
        }
        return true;
    }
</script>