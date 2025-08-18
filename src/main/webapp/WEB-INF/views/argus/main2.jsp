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
	<article>
		<div id="snow-container"></div>
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
		<section class="main-container-assist">
			<div class="main-quarter">
				<div class="quarter">
					<div class="report-board">
						<div class="report-board-title">게시판 제목을 입력해주세요.</div>
						<div class="shortcut"></div>
					</div>
					<div class="list">
						<p class="sub-text">게시판 내용을 입력해주세요.</p>
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
		<section class="main-container-assist">
			<div class="main-quarter">
				<div class="quarter">
					<div class="report-board">
						<div class="report-board-title">게시판 제목을 입력해주세요.</div>
						<div class="shortcut"></div>
					</div>
					<div class="list">
						<p class="sub-text">게시판 내용을 입력해주세요.</p>
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
	</article>
	<section class="content">
		<h2>사업분야</h2>
		<p>첨단의 기술도 기술자의 역량만큼 빛을 발하기 마련입니다.</p>
	</section>
	<%@ include file="../include/footer2.jsp" %>
</body>
<script>
	const snowContainer = document.getElementById('snow-container');
	const snowCount = 50; 

	for (let i = 0; i < snowCount; i++) {
	    const snowflake = document.createElement('div');
	    snowflake.className = 'snowflake';
	    snowflake.innerHTML = '❄';
	    snowflake.style.left = Math.random() * window.innerWidth + 'px';
	    snowflake.style.opacity = Math.random();
	    snowflake.style.fontSize = (Math.random() * 10 + 10) + 'px';
	    snowContainer.appendChild(snowflake);


	    animateSnow(snowflake);
	}

	function animateSnow(snowflake) {
	    let y = -10;
	    const speed = Math.random() * 1 + 0.5;
	    const xOffset = Math.random() * 50 - 25;
	    const startX = parseFloat(snowflake.style.left);

	    function fall() {
	        y += speed;
	        snowflake.style.top = y + 'px';
	        snowflake.style.left = startX + xOffset * Math.sin(y / 50) + 'px';

	        if (y < window.innerHeight) {
	            requestAnimationFrame(fall);
	        } else {
	            y = -10;
	            snowflake.style.left = Math.random() * window.innerWidth + 'px';
	            requestAnimationFrame(fall);
	        }
	    }
	    fall();
	}
</script>
</html>
