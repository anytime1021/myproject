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
								<option value="UR">PP</option>
								<option value="UQ">TU</option>
								<option value="OT">PL</option>
								<option value="ETC">ETC</option>
							</select>
							<span id="hyphen2">-</span>
							<input type="text" id="number" style="flex: 2; width: 20%; padding: 8px 10px; font-size: 15px; border: 1.8px solid #ccc; border-radius: 6px; transition: border-color 0.3s ease;">
							<br>
						</div>
						<div style="text-align:right;">
							<span id="checkMsg" style="width:180px; margin-left:0; align-items:center; text-align:right;"></span>
						</div>
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
							<label>용접현상 : </label>
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
	// 요소 가져오기
	const usageSelect = document.getElementById("usage");
	const formSelect = document.getElementById("form");
	const numberInput = document.getElementById("number");
	const numberSpan = document.getElementById("numberspan");
	const checkMsg = document.getElementById("checkMsg");

	// df_idNumber hidden input 추가
	let hiddenInput = document.createElement("input");
	hiddenInput.type = "hidden";
	hiddenInput.name = "df_idNumber";
	hiddenInput.id = "df_idNumber";
	document.getElementById("numberspan").parentElement.appendChild(hiddenInput);

	// df_idNumber 생성
	function updateDfNumber() {
	    let prefix = document.getElementById("prefix").textContent; // prefix 따로 가져오기
	    let usage = usageSelect.value;
	    let form = formSelect.value;
	    let number = numberInput.value;

	    let dfNumber = `${prefix}${usage}-${form}-${number}`;
	    hiddenInput.value = dfNumber;
	    return dfNumber;
	}

	function checkDuplicate() {
	    let dfNumber = updateDfNumber();

	    if (dfNumber.endsWith('-')) {
	        $("#checkMsg").text(""); 
	        return;
	    }

	    $.ajax({
	        url: "${contextPath}/blockManagement/checkDuplicateIdNumber.do",
	        type: "GET",
	        data: { df_idNumber: dfNumber },
	        success: function(result) {
	            if (result === 'duplicate') {
	                $("#checkMsg").text("⚠ 이미 사용중인 번호입니다.").css("color", "red");
	            } else if (result === 'available') {
	                $("#checkMsg").text("✅ 사용 가능한 번호입니다.").css("color", "green");
	            }
	        },
	        error: function() {
	            $("#checkMsg").text("서버 오류").css("color","red");
	        }
	    });
	}

	// 이벤트 등록
	usageSelect.addEventListener("change", checkDuplicate);
	formSelect.addEventListener("change", checkDuplicate);
	numberInput.addEventListener("input", checkDuplicate);

	// 초기 상태 업데이트
	checkDuplicate();
</script>
</html>
