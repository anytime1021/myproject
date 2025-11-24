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
							<span id="numberspan" style="flex: 2; width: 30%; padding: 8px 10px; font-size: 15px; border: 1.8px solid #ccc; border-radius: 6px; transition: border-color 0.3s ease;">
							<c:choose>
								<c:when test="${searchArea eq '서산'}">
									A(SS)-
								</c:when>
								<c:when test="${searchArea eq '마산'}">
									A(MS)-
								</c:when>
								<c:when test="${searchArea eq '울산'}">
									A(US)-
								</c:when>
								<c:when test="${searchArea eq '여수'}">
									A(YS)-
								</c:when>
								<c:when test="${searchArea eq '창원'}">
									A(CW)-
								</c:when>
							</c:choose>
							<select id="usage" style="flex: 2; width: 23%; padding: 8px 10px; font-size: 15px; border: 1.8px solid #ccc; border-radius: 6px; transition: border-color 0.3s ease;">
							  <option value="UR">UR</option>
							  <option value="UQ">UQ</option>
							  <option value="OT">OT</option>
							</select>
							<span id="hyphen1">-</span>
							<select id="form" style="flex: 2; width: 25%; padding: 8px 10px; font-size: 15px; border: 1.8px solid #ccc; border-radius: 6px; transition: border-color 0.3s ease;">
								<option value="PP">PP</option>
								<option value="TU">TU</option>
								<option value="PL">PL</option>
								<option value="ETC">ETC</option>
							</select>
							<span id="hyphen2">-</span>
							<input type="text" id="number" style="flex: 2; width: 20%; padding: 8px 10px; font-size: 15px; border: 1.8px solid #ccc; border-radius: 6px; transition: border-color 0.3s ease;">
							<br>
						</div>
						<div class="form-group">
							<label></label>
							<input type="text" id="df_idNumber_display" style="text-align:right; justify-content:right;" readonly>
						</div>
						<div style="text-align:right;">
							<span id="checkMsg" style="width:180px; margin-left:0; align-items:center; text-align:right;"></span>
						</div>
						<input type="hidden" id="df_idNumber" name="df_idNumber">
						<div class="form-group">
							<label for="df_pictureName">사진 : </label>
							<input type="file" id="df_picture" name="df_picture">
						</div>
						<div class="form-group">
							<label for="df_material">재질 : </label>
							<input type="text" id="df_material" name="df_material">
						</div>
						<div class="form-group">
							<label for="df_size">크기 : </label>
							<input type="text" id="df_size" name="df_size">
						</div>
						<div class="form-group">
							<label>제작일자 : </label>
							<input type="text" id="df_manufacture" name="df_manufacture" placeholder="yyyy-mm-dd">
						</div>
						<div class="form-group">
							<label>상태 : </label>
							<select id="df_itemStatus" name="df_itemStatus">
								<option value="이상없음">이상없음</option>
								<option value="폐기">폐기</option>
								<option value="분실">분실</option>
								<option value="대여중">대여중</option>
							</select>
						</div>
						<div class="form-group">
							<label>이동현황 : </label>
							<input type="text" id="df_moveStatus" name="df_moveStatus" value="-" readonly>
						</div>
						<div class="form-group">
							<label>용접여부 : </label>
							<input type="text" id="df_weld" name="df_weld">
						</div>

						<div class="form-group">
							<label>비고 : </label>
							<input type="text" id="note" name="note">
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
	let idValid = false;
	let idAvailable = false;
	let material = false;
	let size = false;
	let manufacture = false;

	// 정규식 패턴: 접두어 + usage + form + 숫자(3자리)
	const idPattern = /^A\((SS|MS|US|YS|CW)\)-(UR|UQ|OT)-(PP|TU|PL|ETC)-\d{3}$/;

	$(document).ready(function() {
	    // 🔹 식별번호 조합 함수
	    function updateDfNumber() {
	        // prefix는 numberspan 안에 있는 텍스트 노드
	        let prefix = $("#numberspan").contents().filter(function() {
	            return this.nodeType === 3; // 텍스트 노드만 가져오기
	        }).text().trim();

	        let usage = $("#usage").val();
	        let form = $("#form").val();
	        let number = $("#number").val();

	        let fullNumber = prefix + usage + "-" + form + "-" + number;

	        // 표시용 + hidden input에 반영
	        $("#df_idNumber_display").val(fullNumber);
	        $("#df_idNumber").val(fullNumber);

	        // 정규식 체크
	        if(!idPattern.test(fullNumber)) {
	            $("#checkMsg").text("⚠ 형식 불일치").css("color","red");
	            idValid = false;
	            idAvailable = false;
	        } else {
	            idValid = true;
	        }

	        return fullNumber;
	    }

	    // 🔹 중복체크 함수
	    function checkDuplicate() {
	        let dfNumber = updateDfNumber();

	        if(!idValid) return; // 형식 안 맞으면 중복체크 안함

	        $.ajax({
	            url: "${contextPath}/blockManagement/checkDuplicateIdNumber.do",
	            type: "GET",
	            data: { df_idNumber: dfNumber },
	            success: function(result) {
	                if(result === "duplicate") {
	                    $("#checkMsg").text("❌ 이미 사용중인 번호입니다.").css("color","red");
	                    idAvailable = false;
	                } else if(result === "available") {
	                    $("#checkMsg").text("✅ 사용 가능한 번호입니다.").css("color","green");
	                    idAvailable = true;
	                }
	            },
	            error: function(err) {
	                console.error("중복체크 오류:", err);
	            }
	        });
	    }

	    // 🔹 이벤트 바인딩
	    $("#usage, #form, #number").on("input change", function() {
	        updateDfNumber();
	        checkDuplicate();
	    });

	    $("#df_material").on("input", function() {
	        material = $(this).val().trim().length > 0;
	    });

	    $("#df_size").on("input", function() {
	        size = $(this).val().trim().length > 0;
	    });

	    $("#df_manufacture").on("input", function() {
	        manufacture = $(this).val().trim().length > 0;
	    });

	    // 🔹 등록 버튼 클릭 시 검증
	    $("#submitBtn").on("click", function(e){
	        if(!idValid) {
	            alert("식별번호 형식이 올바르지 않습니다.");
	            e.preventDefault();
	            return;
	        }
	        if(!idAvailable) {
	            alert("이미 사용중인 식별번호입니다.");
	            e.preventDefault();
	            return;
	        }
	        if(!material) {
	            alert("재질을 입력해주세요.");
	            e.preventDefault();
	            return;
	        }
	        if(!size) {
	            alert("크기를 입력해주세요.");
	            e.preventDefault();
	            return;
	        }
	        if(!manufacture) {
	            alert("날짜를 입력해주세요.");
	            e.preventDefault();
	        }
	    });

	    // 페이지 로딩 시 초기 반영
	    updateDfNumber();
	});
</script>
</html>
