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
	<link rel="stylesheet" href="${contextPath}/resources/css/addPriceStyle.css">
    <meta charset="UTF-8">
    <title>단가 등록</title>
</head>
<body>

<h3>단가 등록</h3>

<!-- 현장 선택 -->
<div class="addPrice">
	<div class="divLeft">
		<div class="dropdown" id="workDropdown">
		    <div class="dropdown-toggle">현장 선택</div>
		    <div class="dropdown-menu">
		        <c:forEach var="workname" items="${workname}">
		            <div data-value="${workname.work_name_total}">${workname.work_name_total}</div>
		        </c:forEach>
		    </div>
		</div>
		
		<!-- 검사 종류 선택 -->
		<div class="dropdown" id="methodDropdown">
		    <div class="dropdown-toggle">검사 종류 선택</div>
		    <div class="dropdown-menu">
		        <div data-value="UT">UT</div>
		        <div data-value="MT">MT</div>
		        <div data-value="PT">PT</div>
		        <div data-value="RT">RT</div>
		        <div data-value="ECT">ECT</div>
		        <div data-value="기타">기타</div>
		    </div>
		</div>
		
		<!-- 입력 폼 -->
		<div id="detailsBox">
		    <form id="addPrice" action="${contextPath}/report/addPrice.do" method="post">
				<p id="selectedTypeLabel"></p>
			    <input type="text" name="unitcost_unitQuantity" id="unitcost_unitQuantity" placeholder="단위">
			    <input type="number" name="unitcost_cost" id="unitcost_cost" placeholder="단가">
				<input type="hidden" name="work_name_total" id="work_name_total">
				<input type="hidden" name="unitcost_method" id="unitcost_method">
				<button type="submit">전송</button>
			</form>
		</div>
	</div>
	<!-- 결과 출력 -->
	<div class="divRight">
	    <div id="resultArea">
	        <c:forEach var="workNameList" items="${workNameList}">
                <h4>${workNameList.work_name_total}</h4>
				<div>
                <table>
                    <thead>
                        <tr>
							<th>거래처</th>
                            <th>검사방법</th>
                            <th>규격</th>
                            <th>단가</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="unitCostList" items="${unitCostList}">
                            <c:if test="${unitCostList.work_name_total eq workNameList.work_name_total}">
                                <tr>
									<td>${unitCostList.work_name_total}</td>
                                    <td>${unitCostList.unitcost_method}</td>
                                    <td>${unitCostList.unitcost_unitQuantity}</td>
                                    <td>${unitCostList.unitcost_cost}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
				</div>
	        </c:forEach>
	    </div>
	</div>
</div>
<!-- jQuery + JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function () {
	    // 검사 종류 (methodDropdown)
	    const methodToggle = $('#methodDropdown .dropdown-toggle');
	    const methodMenu = $('#methodDropdown .dropdown-menu');

	    methodToggle.click(function (e) {
	        e.stopPropagation();
	        methodMenu.toggle();
	    });

	    methodMenu.find('div').click(function () {
	        const selected = $(this).data('value');
	        methodToggle.text(selected).data('value', selected);
	        $('#unitcost_method').val(selected);
	        methodMenu.hide();
	        $('#detailsBox').show();
	    });

	    // 현장 선택 (workDropdown)
	    const workToggle = $('#workDropdown .dropdown-toggle');
	    const workMenu = $('#workDropdown .dropdown-menu');

	    workToggle.click(function (e) {
	        e.stopPropagation();
	        workMenu.toggle();
	    });

	    workMenu.find('div').click(function () {
	        const selected = $(this).data('value');
	        workToggle.text(selected).data('value', selected);
	        $('#work_name_total').val(selected);
	        workMenu.hide();
	        $('#detailsBox').show();
	    });

	    // 외부 클릭 시 드롭다운 닫기
	    $(document).click(function () {
	        methodMenu.hide();
	        workMenu.hide();
	    });
	});
</script>
</body>
</html>
