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
	<link rel="stylesheet" href="${contextPath}/resources/css/addFormStyle.css">
    <meta charset="UTF-8">
    <title>익월 용역 예상 보고</title>
</head>
<body>

<h3>익월 용역 예상 보고(미완 - 추후 진행 예정) 11111</h3>
<a href="${contextPath}/report/reportArea.do">돌아가기</a>
<span id="amountText" style="margin-left:10px; color:gray;"></span>

<!-- 현장 선택 -->
<div class="addForm">
	<div class="divLeft">
		<!-- 입력 폼 -->
		<div>
		    <form id="addFollowingMonth" action="${contextPath}/report/addFollowingMonth.do" method="post">
			    <input type="text" name="fmonth_name" placeholder="거래처">
			    <input type="text" name="fmonth_profits" id="amountInput" placeholder="공사금액">
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
							<th>거래처</th>
	                        <th>공사금액</th>
	                    </tr>
	                </thead>
					<tbody>
						<c:forEach var="fmonth" items="${fmonth_list}">
						    <tr data-name="${fmonth.fmonth_name}">
								<c:set var="formatProfits">
									<fmt:formatNumber value="${fmonth.fmonth_profits}" pattern="#,##0" />
								</c:set>
								<input type="text" name="dummyInt" value="${fmonth.fmonth_num}" hidden>
						        <td>
						            <input type="text" name="fmonth_name" value="${fmonth.fmonth_name}" readonly />
						        </td>
								<td style="display:none;">
								    <input type="text" name="dummyInt" value="${fmonth.fmonth_num}" />
								    <input type="text" name="login_area" value="${fmonth.login_area}" />
								</td>
						        <td>
						            <input type="text" name="fmonth_profits" value="${formatProfits}" readonly />
						        </td>
						        <td>
						            <button type="button" class="edit-btn">수정하기</button>
						            <button type="button" class="save-btn" style="display:none;">저장하기</button>
						        </td>
						        <td>
						            <button type="button" class="delete-btn">삭제하기</button>
						        </td>
						    </tr>
						</c:forEach>
					</tbody>
	            </table>
			</div>
	    </div>
	</div>
</div>
<!-- jQuery + JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function() {
    // 천 단위 쉼표 포맷 함수
    function formatNumberWithCommas(x) {
        return x.replace(/[^0-9]/g, '') // 숫자만 남김
                .replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 쉼표 삽입
    }
	
    // 수정하기 클릭 시
    $('.edit-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const $profitInput = $row.find('input[name="fmonth_profits"]');

        $row.find('input[name="fmonth_name"]').prop('readonly', false).focus();
        $profitInput.prop('readonly', false).focus();

        // 금액 입력 시 실시간 쉼표 처리
        $profitInput.on('input', function() {
            this.value = formatNumberWithCommas(this.value);
        });

        $(this).hide();
        $row.find('.save-btn').show();
    });

    // 저장하기 클릭 시
    $('.save-btn').on('click', function() {
        const $row = $(this).closest('tr');
        const $profitInput = $row.find('input[name="fmonth_profits"]');
        const name = $row.find('input[name="fmonth_name"]').val();
        const rawProfit = $profitInput.val().replace(/,/g, ''); // 쉼표 제거
        const login_area = $row.find('input[name="login_area"]').val();
        const dummyInt = $row.find('input[name="dummyInt"]').val();

        $.ajax({
            url: '${contextPath}/report/updateFmonth.do',
            type: 'POST',
            data: {
                fmonth_name: name,
                fmonth_profits: rawProfit,
                login_area: login_area,
                dummyInt: dummyInt
            },
            success: function(response) {
                alert("수정되었습니다.");
                $row.find('input[name="fmonth_name"]').prop('readonly', true);
                $profitInput.prop('readonly', true).off('input'); // 이벤트 제거
                $profitInput.val(formatNumberWithCommas(rawProfit)); // 다시 쉼표 포맷
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
        const name = $row.find('input[name="fmonth_name"]').val();
        const login_area = $row.find('input[name="login_area"]').val();
        const dummyInt = $row.find('input[name="dummyInt"]').val();

        $.ajax({
            url: '${contextPath}/report/deleteFmonth.do',
            type: 'POST',
            data: {
                fmonth_name: name,
                login_area: login_area,
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
