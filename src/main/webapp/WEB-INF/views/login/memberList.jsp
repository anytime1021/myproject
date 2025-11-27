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
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="../include/header2.jsp" %>
    <main class="first-container">
        <%@ include file="../include/sidebar.jsp" %>

        <div class="mypage-container">
            <h2>아이디 목록</h2>
            <table class="mypage-table">
                <tr>
                    <th>아이디</th>
                    <th>이름</th>
					<th>직급</th>
					<th>사업소</th>
					<th>부서</th>
                </tr>
				<c:forEach var="memberList" items="${memberList}">
	                <tr>
	                    <td><input type="text" name="login_id" value="${memberList.login_id}" readonly"></td>
						<td><input type="text" name="login_name" value="${memberList.login_name}" readonly></td>
						<td><input type="text" name="login_position" value="${memberList.login_position}" readonly></td>
						<td><input type="text" name="login_area" value="${memberList.login_area}" readonly></td>
						<td><input type="text" name="login_department" value="${memberList.login_department}" readonly></td>
					</tr>
				</c:forEach>
            </table>
        </div>
    </main>
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
</script>
</html>
