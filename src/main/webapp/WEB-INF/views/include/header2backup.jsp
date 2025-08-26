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
<header>
	<div class="logo">
		<a href="${contextPath}/argus/main2.do"><img src="${contextPath}/resources/img/argusImage.jpg" alt="Argus Logo"></a>
	</div>
	<div class="board-title">
		<nav>
			<ul class="menu">
				<li></li>
				<li><a href="${contextPath}/report/reportArea2.do">보고서게시판</a></li>
				<li><a href="${contextPath}/blockManagement/blockList.do">시험편 관리</a></li>
				<li>-</li>
				<li>-</li>
				<c:choose>
					<c:when test="${logOn}">
						<li>${login.login_area} 님
<!--						<span id="sessionTimer"></span>-->
						</li>
						<li><a href="${contextPath}/login/logout.do">로그아웃</a></li>
						<li><a href="${contextPath}/user/umReserInfo.do?u_id=${user.u_id}">마이페이지</a></li>				
					</c:when>
					<c:otherwise>
						<li><a href="${contextPath}/login/loginForm.do">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</header>
<script>
    let remaining = 0;

    // 서버에서 세션 유지 시간 가져오기
    $.getJSON("<c:url value='/session-info'/>", function(data) {
        remaining = data.maxInactiveInterval; // 초 단위
        startTimer();
    });

    function startTimer() {
        let timer = setInterval(function() {
            remaining--;
            if (remaining <= 0) {
                clearInterval(timer);
                alert("세션이 만료되어 자동 로그아웃됩니다.");
                window.location.href = "<c:url value='/logout'/>"; 
            } else {
                // 5분 남았을 때 경고창
                if (remaining === 300) {
                    alert("세션 만료 5분 전입니다. 작업을 저장하세요.");
                }
                // 화면에 남은 시간 표시
                document.getElementById("sessionTimer").innerText =
                    Math.floor(remaining / 60) + "분 " + (remaining % 60) + "초";
            }
        }, 1000);
    }
</script>