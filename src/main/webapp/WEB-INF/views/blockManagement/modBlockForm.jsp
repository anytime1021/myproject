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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>
        <div class="main-content">
			<div class="addBlock-container">
				<form autocomplete="off" name="modBlockForm" method="post" action="${contextPath}/blockManagement/modBlock.do" enctype="multipart/form-data">
					<div class="addBlock">
						<div class="form-group">
							<input type="hidden" id="df_num" name="df_num" value="${blockView.df_num}">

							<label for="df_idNumber">식별번호 : </label>
							<input type="text" id="df_idNumber" name="df_idNumber" value="${blockView.df_idNumber}" readonly>
							<br>
						</div>
						<div style="text-align:right;">
							<span id="checkMsg" style="width:180px; margin-left:0; align-items:center; text-align:right;"></span>
						</div>
						<div class="form-group">
							<label for="df_pictureName">수정할 사진 : </label>
							<input type="file" id="df_picture" name="df_picture">
						</div>
						<div class="form-group">
							<label for="picture_now"> 현재 사진 : </label>
							<img src="${contextPath}/resources/img/${blockView.df_pictureName}" style="width:20%; height:auto; object-fit:contain;">
							<input type="hidden" id="df_pictureName" name="df_pictureName" value="${blockView.df_pictureName}">
						</div>
						<div class="form-group">
							<label for="df_material">재질 : </label>
							<input type="text" id="df_material" name="df_material" value="${blockView.df_material}">
						</div>
						<div class="form-group">
							<label for="df_size">크기 : </label>
							<input type="text" id="df_size" name="df_size" value="${blockView.df_size}">
						</div>
						<div class="form-group">
							<label>용도 : </label>
							<input type="text" id="df_usage" name="df_usage" value="${blockView.df_usage}" readonly>
						</div>
						<div class="form-group">
							<label>시험편 정보 : </label>
							<input type="text" id="df_form" name="df_form" value="${blockView.df_form}" readonly>
						</div>
						<div class="form-group">
							<label>제작일자 : </label>
							<input type="text" id="df_manufacture" name="df_manufacture" value= "${blockView.df_manufacture}" placeholder="yyyy-mm-dd">
						</div>
						<div class="form-group">
							<label>사용여부 : </label>
							<select id="df_itemStatus" name="df_itemStatus">
								<option value="사용중" ${blockView.df_itemStatus == '사용중' ? 'selected' : ''}>사용중</option>
								<option value="폐기" ${blockView.df_itemStatus == '폐기' ? 'selected' : ''}>폐기</option>
								<option value="분실" ${blockView.df_itemStatus == '분실' ? 'selected' : ''}>분실</option>
								<option value="대여중" ${blockView.df_itemStatus == '대여중' ? 'selected' : ''}>대여중</option>
							</select>
						</div>
						<div class="form-group">
							<label>이동현황 : </label>
							<input type="text" name="df_moveStatus" name="df_moveStatus" value="-" readonly>
						</div>
						<div class="form-group">
							<label>비고 : </label>
							<input type="text" id="note" name="note" value="${blockView.note}">
						</div>
						<button type="submit" id="submitBtn">수정하기</button>
					</div>
				</form>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	$(document).ready(function(){
		let material = $("#df_material").val().trim().length > 0 ? true : false;
		let size = $("#df_size").val().trim().length > 0 ? true : false;
		let manufacture = $("#df_manufacture").val().trim().length > 0 ? true : false;

		$("#df_material").on("input", function() {
		    material = $(this).val().trim().length > 0;
		});
		$("#df_size").on("input", function() {
		    size = $(this).val().trim().length > 0;
		});
		$("#df_manufacture").on("input", function() {
		    manufacture = $(this).val().trim().length > 0;
		});

	    $("#submitBtn").on("click", function(e){
	        if (!material) {
	            alert("재질을 입력해주세요.");
	            e.preventDefault();
	            return;
	        }
	        if (!size) {
	            alert("크기를 입력해주세요.");
	            e.preventDefault();
	            return;
	        }
	        if (!manufacture) {
	            alert("날짜를 입력해주세요.");
	            e.preventDefault();
	        }
	    });
	});

</script>
</html>
