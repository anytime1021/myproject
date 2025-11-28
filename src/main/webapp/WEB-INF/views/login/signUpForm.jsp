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
    <title>아이디 생성</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>

        <div class="mypage-container">
            <h2>아이디 생성</h2>
			<form name="signUp" method="get" action="${contextPath}/login/signUp.do">
	            <table class="mypage-table">
	                <tr>
	                    <th>아이디</th>
	                    <td>
							<input type="text" id="login_id" name="login_id" onkeyup="checkDuplicate()">
							<span id="checkMsg"></span>
						</td>
	                </tr>
					<tr>
	                    <th>비밀번호</th>
	                    <td><input type="password" name="login_pwd"></td>
	                </tr>
	                <tr>
	                    <th>이름</th>
	                    <td><input type="text" name="login_name"></td>
	                </tr>
	                <tr>
	                    <th>부서</th>
						<td>
							<select name="login_department">
								<c:forEach var="dept" items="${singUpList}">
									<option value="${dept.login_department}">${dept.login_department}</option>
								</c:forEach>
								<option value="write">직접입력</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>부서명</th>
	                    <td><input type="text" name="write_department" ></td>
	                </tr>
	                <tr>
	                    <th>직급</th>
	                    <td><input type="text" name="login_position"></td>
	                </tr>
	                <tr>
	                    <th>사업소</th>
	                    <td>
							<select name="login_area">
								<c:forEach var="area" items="${singUpList}">
									<option value="${area.login_area}">${area.login_area}</option>
								</c:forEach>
							</select>
						</td>
	                </tr>
	            </table>
				<div style="text-align:right;">
					<button type="submit">아이디 생성</button>
				</div>
			</form>
        </div>
    </main>
	<%@ include file="../include/footer2.jsp"%>
</body>
<script>
	const departmentSelect = document.querySelector('select[name="login_department"]');
    const writeDeptRow = document.querySelector('input[name="write_department"]').closest('tr');

    // 처음에는 숨기기
    writeDeptRow.style.display = 'none';

    departmentSelect.addEventListener('change', function() {
        if (this.value === 'write') {
            writeDeptRow.style.display = '';
        } else {
            writeDeptRow.style.display = 'none';
        }
    });
	function checkDuplicate() {
		let id = document.getElementById("login_id").value;
		
		$.ajax({
			url: "${contextPath}/login/checkDuplicate.do",
			type: "GET",
			data : {login_id : id},
			success: function(result) {
				if(result === "duplicate") {
                    $("#checkMsg").text("❌ 이미 사용중인 아이디입니다.").css("color","red");
                    idAvailable = false;
                } else if(result === "available") {
                    $("#checkMsg").text("✅ 사용 가능한 아이디입니다.").css("color","green");
                    idAvailable = true;
                }
            },
			error: function(err) {
				console.error("중복체크 오류:", err);
			}
		});
	}
</script>
</html>
