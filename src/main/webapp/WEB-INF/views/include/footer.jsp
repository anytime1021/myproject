<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<div class="roulette-fixed-wrap">
	<div class="roulette-wrap">
		<button class="cancel-btn" onclick="rollette_toggle()">
			<span class="line line1"></span>
			<span class="line line2"></span>
		</button>
		<div class="roulette-frame">
			<div class="arrow"><img src="${contextPath}/resources/img/ico/roulette_arrow.png"></img></div>
			<canvas width="380" height='380' id="roulette-canvas"></canvas>
			<button onclick="rotate()">추천<br>받기</button>
		</div>  
		<div class="roulette-txt">
			<div class="alert-txt">
				<p>
					<span class="menu-txt"></span>
					<span class="txt">룰렛을 돌려주세요.</span>
				</p>
			</div>
			<a href="javascript:;" class="btn" onclick="alertFunction()">이동하기</a>
		</div>
		
	</div>	
</div>
<footer class="ft">
	<aside class="aside-menu-wrap">
		<ul class="aside-menu">
		</ul>
	</aside>
	<article class="ft-txt-wrap">
		<div class="inner-con">
			<div class="ft-logo">
				<h1 style="color:white !important;">테스트용 사이트</h1>
			</div>
			<div class="txt-wrap">
				<div class="txt-flex-box">
					<ul class="txt-list">
						<li>주소 : 경기 이매동</li>
						<li>대표자 : 아거스 </li>					
					</ul>	
					<div class="tel-txt">
						<h5>고객센터</h5>
						<p>대표번호 : 1222-2222</p>
					</div>
				</div>
				<p class="copyright">copyright &copy; Argus Company</p>
			</div>
		</div>
	</article>	
</footer>    
</script>