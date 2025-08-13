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
				<form autocomplete="off" name="addBlockForm" method="post" action="${contextPath}/blockManagement/addBlock.do" enctype="multipart/form-data">
					<div class="addBlock">
						<div class="form-group">
							<label for="df_idNumber">식별번호 : </label>
							<c:choose>
								<c:when test="${searchArea eq '서산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(SS)-" />
								</c:when>
								<c:when test="${searchArea eq '마산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(MS)-" />
								</c:when>
								<c:when test="${searchArea eq '울산'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(US)-" />
								</c:when>
								<c:when test="${searchArea eq '여수'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(YS)-" />
								</c:when>
								<c:when test="${searchArea eq '창원'}">
									<input type="text" id="df_idNumber" name="df_idNumber" value="A(CW)-" />
								</c:when>
							</c:choose>
							<br>
						</div>
						<div style="text-align:right;">
							<span id="checkMsg" style="width:180px; margin-left:0; align-items:center; text-align:right;"></span>
						</div>
						<div class="form-group">
							<label for="df_pictureName">사진 : </label>
							<input type="file" id="df_picture" name="df_picture" />
						</div>
						<div class="form-group">
							<label for="df_material">재질 : </label>
							<input type="text" id="df_material" name="df_material" />
						</div>
						<div class="form-group">
							<label for="df_size">크기 : </label>
							<input type="text" id="df_size" name="df_size" />
						</div>
						<div class="form-group">
							<label>용도 : </label>
							<input type="text" id="df_usage" name="df_usage" readonly />
						</div>
						<div class="form-group">
							<label>시험편 정보 : </label>
							<input type="text" id="df_form" name="df_form" readonly />
						</div>
						<div class="form-group">
							<label>제작일자 : </label>
							<input type="text" id="df_manufacture" name="df_manufacture" placeholder="yyyy-mm-dd" />
						</div>
						<div class="form-group">
							<label>사용여부 : </label>
							<select id="df_itemStatus" name="df_itemStatus">
								<option value="사용중">사용중</option>
								<option value="폐기">폐기</option>
								<option value="분실">분실</option>
								<option value="대여중">대여중</option>
							</select>
						</div>
						<div class="form-group">
							<label>이동현황 : </label>
							<select id="df_moveStatus" name="df_moveStatus">
								<option value="${searchArea}">-</option>
								<c:if test="${searchArea ne '서산'}">
									<option value="서산">서산</option>
								</c:if>
								<c:if test="${searchArea ne '마산'}">
									<option value="마산">마산</option>
								</c:if>
								<c:if test="${searchArea ne '울산'}">
									<option value="울산">울산</option>
								</c:if>
								<c:if test="${searchArea ne '여수'}">
									<option value="여수">여수</option>
								</c:if>
								<c:if test="${searchArea ne '창원'}">
									<option value="창원">창원</option>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label>비고 : </label>
							<input type="text" id="note" name="note" />
						</div>
						<button type="submit" id="submitBtn">추가하기</button>
					</div>
				</form>
			</div>
        </div>
    </main>
    <%@ include file="../include/footer2.jsp"%>
</body>
<script>
	$(document).ready(function(){
	    let idValid = false; // 형식 유효성 여부
	    let idAvailable = false; // DB 중복 여부
		let material = false; // 재질 입력 여부
		let size = false; // 사이즈 입력 여부
		let manufacture = false; // 날짜 입력 여부
		
	    // 식별번호 패턴
	    const idPattern = /^A\((SS|MS|US|YS|CW)\)-(UR|UQ|UB|OT)-(PI|UT|PL|NZ|ND|OT)-\d{3}$/;

	    // 입력 시 형식 + 중복 체크
	    $("#df_idNumber").on("input", function(){
	        let df_idNumber = $(this).val().trim();

	        // 1. 형식 검사
	        if(!idPattern.test(df_idNumber)) {
	            $("#checkMsg").text("형식 불일치").css("color","red");
	            idValid = false;
	            idAvailable = false;
	            return;
	        } else {
	            idValid = true;
	        }

	        // 2. AJAX 중복 검사
	        $.ajax({
	            url: "${contextPath}/blockManagement/checkDuplicateIdNumber.do", // 형님 원래 쓰던 URL 그대로
	            type: "GET",
	            data: { df_idNumber: df_idNumber },
	            success: function(result){
	                if(result === 'duplicate') {
	                    $("#checkMsg").text("이미 사용중인 번호입니다.").css("color","red");
	                    idAvailable = false;
	                } else if(result === 'available') {
	                    $("#checkMsg").text("사용 가능한 번호입니다.").css("color","green");
	                    idAvailable = true;
	                } else {
						$("#checkMsg").text("사용 불가능한 번호입니다.").css("color","red");
					}
	            },
	            error: function(){
	                $("#checkMsg").text("서버 오류").css("color","red");
	                idAvailable = false;
	            }
	        });
	    });

		let tf_material = $("#df_material").val().trim();
		
		if(!tf_material.length > 0) {
			material = true;
		} else {
			material = false;
		}
		
		let tf_size = $("#df_size").val().trim();
		
		if(!tf_size.length > 0) {
			size = true;
		} else {
			size = false;
		}
		
		let tf_manufacture = $("#df_manufacture").val().trim();
		if(!tf_manufacture.length > 0) {
			manufacture = true;
		} else {
			manufacture = false;
		}
		
		
		
	    // 등록 버튼 클릭 시
	    $("#submitBtn").on("click", function(e){
	        if(!idValid) {
	            alert("식별번호 형식이 올바르지 않습니다.");
	            e.preventDefault();
	            return;
	        }
	        if(!idAvailable) {
	            alert("이미 사용중인 식별번호입니다.");
	            e.preventDefault();
	        }
			let tf_material = $("#df_material").val().trim();
		    let tf_size = $("#df_size").val().trim();
		    let tf_manufacture = $("#df_manufacture").val().trim();
		    
		    let material = tf_material.length > 0;
		    let size = tf_size.length > 0;
		    let manufacture = tf_manufacture.length > 0;

		    if(!idValid){
		        alert("식별번호 형식이 올바르지 않습니다.");
		        e.preventDefault();
		        return;
		    }
		    if(!idAvailable){
		        alert("이미 사용중인 식별번호입니다.");
		        e.preventDefault();
		        return;
		    }
		    if(!material){
		        alert("재질을 입력해주세요.");
		        e.preventDefault();
		        return;
		    }
		    if(!size){
		        alert("크기를 입력해주세요.");
		        e.preventDefault();
		        return;
		    }
		    if(!manufacture){
		        alert("날짜를 입력해주세요.");
		        e.preventDefault();
		        return;
		    }
		}

		document.getElementById('df_idNumber').addEventListener('input', function() {
		    let str = document.getElementById('df_idNumber').value;
		    let df_usage = '';
		    let df_form = '';
		    
		    if (str.length >= 8 ) {
		        df_usage = str.substring(6,8);
		    } else if (str.length >= 7) {
		        df_usage = str.substring(6);
		    } else {
		        df_usage = '';
		    }
		    
		    if (str.length >= 11) {
		        df_form = str.substring(9,11)
		    } else if (str.length >= 10) {
		        df_form = str.substring(9);
		    } else {
		        df_form = '';
		    }
		    
		    document.getElementById('df_usage').value = df_usage;
		    document.getElementById('df_form').value = df_form;
		});
	});
</script>
</html>
