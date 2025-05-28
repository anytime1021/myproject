<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<link rel="stylesheet" href="${contextPath}/resources/css/addFormStyle.css">
    <meta charset="UTF-8">
    <title>단가 등록</title>
</head>
<body>

<h3>직원 등록</h3>
<span id="amountText" style="margin-left:10px; color:gray;"></span>
<a href="${contextPath}/report/addDailyReportFormMixed.do">돌아가기</a>
<!-- 현장 선택 -->
<div class="addForm">
	<div class="divLeft">
		<!-- 입력 폼 -->
		<div>
		    <form id="addFollowingMonth" action="${contextPath}/sow/sowAddEmployee.do" method="post">
			    <input type="text" name="emp_name" placeholder="이름" autofocus>
			    <input type="text" name="emp_position" placeholder="직위">
				<button type="submit">전송</button>
			</form>
		</div>
	</div>
	<!-- 결과 출력 -->
	<div class="divRight">
	    <div id="resultArea">
			<div>
	            <table>
	                <thead>
	                    <tr>
							<th>이름</th>
	                        <th>직위</th>
	                    </tr>
	                </thead>
					<tbody>
						<c:forEach var="employeeList" items="${employeeList}">
						    <tr data-name="${employeeList.emp_name}">
						        <td>
						            <input type="text" name="emp_name" value="${employeeList.emp_name}" readonly />
						        </td>
								<td style="display:none;">
									<input type="text" name="dummyInt" value="${employeeList.emp_num}" />
								    <input type="text" name="login_area" value="${employeeList.login_area}" />
								</td>
						        <td>
						            <input type="text" name="emp_position" value="${employeeList.emp_position}" readonly />
						        </td>
						        <td>
						            <button type="button" class="edit-btn">수정</button>
						            <button type="button" class="save-btn" style="display:none;">저장</button>
						        </td>
						        <td>
						            <button type="button" class="delete-btn">삭제</button>
						        </td>
						    </tr>
						</c:forEach>
					</tbody>
	            </table>
			</div>
	    </div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function() {
    $('.edit-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const $positionInput = $row.find('input[name="emp_position"]');

        $row.find('input[name="emp_name"]').prop('readonly', false).focus();
        $positionInput.prop('readonly', false).focus();

        $(this).hide();
        $row.find('.save-btn').show();
    });

    // 저장하기 클릭 시
    $('.save-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const position = $row.find('input[name="emp_position"]').val();
        const name = $row.find('input[name="emp_name"]').val();
        const login_area = $row.find('input[name="login_area"]').val();
        const dummyInt = $row.find('input[name="dummyInt"]').val();

        $.ajax({
            url: '${contextPath}/sow/updateEmployee.do',
            type: 'POST',
            data: {
                emp_name: name,
                emp_position: position,
                login_area: login_area,
                dummyInt: dummyInt
            },
            success: function(res) {
                alert("수정되었습니다.");
                $row.find('input[name="emp_name"]').prop('readonly', true);
				$row.find('input[name="emp_position"]').prop('readonly', true)
                $row.find('.edit-btn').show();
                $row.find('.save-btn').hide();
            },
            error: function() {
                alert("수정 중 오류 발생");
            }
        });
    });

    // 삭제하기 클릭 시
    $('.delete-btn').on('click', function() {
        if (!confirm("정말 삭제하시겠습니까?")) return;
        const $row = $(this).closest('tr');
        const dummyInt = $row.find('input[name="dummyInt"]').val();

        $.ajax({
            url: '${contextPath}/sow/deleteEmployee.do',
            type: 'POST',
            data: {
                dummyInt: dummyInt
            },
            success: function(response) {
                alert("삭제되었습니다.");
                $row.remove();
            },
            error: function() {
                alert("삭제 중 오류 발생");
            }
        });
    });
});
</script>
</body>
</html>
