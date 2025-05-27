<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">            
<header class="hd">        
        <section class="top-menu-wrap">            
            <div class="inner-con">
				<div class="logo-wrap">
                	<a class="logo" href="${contextPath}/argus/main.do"><h1>사이트 제작<h1></a>
				</div>
                <nav class="user-menu-wrap">
                    <ul class="user-menu">
						<c:choose>
							<c:when test="${logOn}">
								<li>${login.login_area} 님.</li>
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
        </section>
        <section class="bottom-menu-wrap">  
			<div class="bottom-menu-box">
	            <div class="inner-con">
	                <ul class="main-menu">
						<li>
							<a href="${contextPath}/report/reportArea.do">게시판</a>
							<ul class="sub-menu">
								<li><a href="${contextPath}/report/reportArea.do">작업일보</a></li>
								<li><a href="${contextPath}/report/followingMonth.do">익월 용역 예상 보고서</a></li>							
							</ul>
						</li>
						<li class="area-menu">						
						</li>
	                    <li>
						</li>
	                    <li>
						</li>                
						<li>
						</li>
	                </ul>				
	            </div>
			</div>
		
			<figure class="sub-menu-wrap">
				<div class="inner-con">
					<ul class="sub-menu-list">
					</ul>
				</div>
			</figure>	
		
        </section>        
    </header>