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
    <title>출장자 등록</title>
</head>
<body>

<h3>출장자 등록</h3>
<span id="amountText" style="margin-left:10px; color:gray;"></span>

<!-- 현장 선택 -->
<div class="addForm">
	<div class="divLeft">
		<!-- 입력 폼 -->
		<div>
		    <form id="addFollowingMonth" action="${contextPath}/sow/sowAddBtEmployee.do" method="post">
			    <input type="text" name="emp_name" placeholder="이름" autofocus>
			    <input type="text" name="sowDWL_work_name" placeholder="현장">
				<fieldset>
					<legend> 출장정보 선택 </legend>
					<div>
						<input type="radio" name="bt_inout" value="in" checked>
						<label for="in">출장(입)</label>
					</div>
				</fieldset>
				<button type="submit">전송</button>
			</form>
		</div>
	</div>
	<!-- 결과 출력 -->
	<div class="divRight">
	    <div id="resultArea">
			<div>
				<h3><b> 출장자(입) </b></h3>
	            <table>
	                <thead>
	                    <tr>
							<th>이름</th>
	                        <th>현장</th>
	                    </tr>
	                </thead>
					<tbody>
						<c:forEach var="btInList" items="${btInList}">
						    <tr data-name="${btInList.emp_name}">
						        <td>
						            <input type="text" name="emp_name" value="${btInList.emp_name}" readonly />
						        </td>
								<td style="display:none;">
									<input type="text" name="dummyInt" value="${btInList.emp_num}" />
								    <input type="text" name="login_area" value="${btInList.login_area}" />
								</td>
						        <td>
						            <input type="text" name="sowDWL_work_name" value="${btInList.sowDWL_work_name}" readonly />
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
<!--			<hr>-->
<!--			<div>-->
<!--				<h3><b> 출장자(출) </b></h3>-->
<!--	            <table>-->
<!--	                <thead>-->
<!--	                    <tr>-->
<!--							<th>이름</th>-->
<!--	                        <th>현장</th>-->
<!--	                    </tr>-->
<!--	                </thead>-->
<!--					<tbody>-->
<!--						<c:forEach var="btOutList" items="${btOutList}">-->
<!--						    <tr data-name="${btOutList.emp_name}">-->
<!--						        <td>-->
<!--						            <input type="text" name="emp_name" value="${btOutList.emp_name}" readonly />-->
<!--						        </td>-->
<!--								<td style="display:none;">-->
<!--									<input type="text" name="dummyInt" value="${btOutList.emp_num}" />-->
<!--								    <input type="text" name="login_area" value="${btOutList.login_area}" />-->
<!--								</td>-->
<!--						        <td>-->
<!--						            <input type="text" name="sowDWL_work_name" value="${btOutList.sowDWL_work_name}" readonly />-->
<!--						        </td>-->
<!--						        <td>-->
<!--						            <button type="button" class="edit-btn">수정</button>-->
<!--						            <button type="button" class="save-btn" style="display:none;">저장</button>-->
<!--						        </td>-->
<!--						        <td>-->
<!--						            <button type="button" class="delete-btn">삭제</button>-->
<!--						        </td>-->
<!--						    </tr>-->
<!--						</c:forEach>-->
<!--					</tbody>-->
<!--	            </table>-->
<!--			</div>-->
	    </div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function() {
    $('.edit-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const $positionInput = $row.find('input[name="sowDWL_work_name"]');

        $row.find('input[name="emp_name"]').prop('readonly', false).focus();
        $positionInput.prop('readonly', false).focus();

        $(this).hide();
        $row.find('.save-btn').show();
    });

    // 저장하기 클릭 시
    $('.save-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const position = $row.find('input[name="sowDWL_work_name"]').val();
        const name = $row.find('input[name="emp_name"]').val();
        const login_area = $row.find('input[name="login_area"]').val();
        const dummyInt = $row.find('input[name="dummyInt"]').val();

        $.ajax({
            url: '${contextPath}/sow/modBtEmployee.do',
            type: 'POST',
            data: {
                emp_name: name,
                sowDWL_work_name: position,
                login_area: login_area,
                dummyInt: dummyInt
            },
            success: function(res) {
                alert("수정되었습니다.");
                $row.find('input[name="emp_name"]').prop('readonly', true);
				$row.find('input[name="sowDWL_work_name"]').prop('readonly', true)
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
            url: '${contextPath}/sow/removeBtEmployee.do',
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
