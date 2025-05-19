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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아거스 리포트</title>    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/font.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextPath}/resources/css/styles.css">            
</head>
<body>
	<%@ include file="../include/header.jsp"%>
    <main class="main-container contents-container">
        <section class="con1 main-slide-container">
            <div class="top-slide-wrap">
                <figure class="slide-wrapper swiper-wrapper">
                    <div class="slide swiper-slide" style="background-image:url(${contextPath}/resources/img/mainimage.jpg)"></div>                    
					<div class="slide swiper-slide" style="background-image:url(${contextPath}/resources/img/mainimage2.jpg)"></div> 
                </figure>
            </div>
            <div class="swiper-pagination"></div>
			<!--
			<video controls src="${contextPath}/resources/video/video/food1.mp4">
		        not use video
		    </video>
			-->
			<a href="${contextPath}/report/testList.do">단가등록</a>
			<a href="${contextPath}/report/testList2.do">업체등록</a>
			<a href="${contextPath}/sow/sowAddEmployeeForm.do">직원등록</a>
			<!--유튜브 영상 삽입
			<div id="player"></div>
			-->
			<script>
			     // 2. This code loads the IFrame Player API code asynchronously.
			     var tag = document.createElement('script');

			     tag.src = "https://www.youtube.com/iframe_api";
			     var firstScriptTag = document.getElementsByTagName('script')[0];
			     firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

			     // 3. This function creates an <iframe> (and YouTube player)
			     //    after the API code downloads.
			     var player;
			     function onYouTubeIframeAPIReady() {
			       player = new YT.Player('player', {
			         height: '360',
			         width: '640',
			         videoId: 'eqQPBSVN49Q',
			         events: {
			           'onReady': onPlayerReady,
			           'onStateChange': onPlayerStateChange
			         }
			       });
			     }

			     // 4. The API will call this function when the video player is ready.
			     function onPlayerReady(event) {
			       event.target.playVideo();
			     }

			     // 5. The API calls this function when the player's state changes.
			     //    The function indicates that when playing a video (state=1),
			     //    the player should play for six seconds and then stop.
			     var done = false;
			     function onPlayerStateChange(event) {
			       if (event.data == YT.PlayerState.PLAYING && !done) {
			         setTimeout(stopVideo, 6000);
			         done = true;
			       }
			     }
			     function stopVideo() {
			       player.stopVideo();
			     }
			   </script>
			<!--유튜브 영상 삽입-->
    </main>
    <%@ include file="../include/footer.jsp"%>	
</body>
<script src="${contextPath}/resources/js/script.js"></script>
</html>