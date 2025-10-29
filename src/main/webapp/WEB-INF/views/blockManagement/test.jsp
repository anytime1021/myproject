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
<meta charset="UTF-8" />
<title>폼 예시</title>
<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: #f7f9fc;
    padding: 20px;
  }
  form {
    background: #fff;
    max-width: 450px;
    padding: 30px 40px;
    margin: 0 auto;
    border-radius: 12px;
    box-shadow: 0 10px 20px rgb(0 0 0 / 0.1);
  }
  .form-group {
    display: flex;
    margin-bottom: 18px;
    align-items: center;
  }
  label {
    flex: 1 0 110px;
    font-weight: 600;
    color: #333;
  }
  input[type="text"],
  input[type="date"],
  select,
  input[type="file"] {
    flex: 2;
    padding: 8px 10px;
    font-size: 15px;
    border: 1.8px solid #ccc;
    border-radius: 6px;
    transition: border-color 0.3s ease;
  }
  input[type="text"]:focus,
  input[type="date"]:focus,
  select:focus {
    outline: none;
    border-color: #4a90e2;
    box-shadow: 0 0 6px #4a90e2;
  }
  input[type="file"] {
    padding: 5px 0;
  }
  button {
    display: block;
    width: 100%;
    padding: 12px 0;
    background: #4a90e2;
    border: none;
    border-radius: 8px;
    color: #fff;
    font-size: 17px;
    font-weight: 700;
    cursor: pointer;
    margin-top: 12px;
    transition: background 0.25s ease;
  }
  button:hover {
    background: #357ABD;
  }
</style>
</head>
<body>
<form>
  <div class="form-group">
    <label for="id">식별번호 :</label>
    <input type="text" id="id" name="id" value="A(SS)-" />
  </div>
  <div class="form-group">
    <label for="photo">사진 :</label>
    <input type="file" id="photo" name="photo" />
  </div>
  <div class="form-group">
    <label for="material">재질 :</label>
    <input type="text" id="material" name="material" value="${count}" />
  </div>
  <div class="form-group">
    <label for="size">크기 :</label>
    <input type="text" id="size" name="size" />
  </div>
  <div class="form-group">
    <label for="purpose">용도 :</label>
    <input type="text" id="purpose" name="purpose" />
  </div>
  <div class="form-group">
    <label for="testInfo">시험편 정보 :</label>
    <input type="text" id="testInfo" name="testInfo" />
  </div>
  <div class="form-group">
    <label for="date">제작일자 :</label>
    <input type="date" id="date" name="date" />
  </div>
  <div class="form-group">
    <label for="usage">사용여부 :</label>
    <input type="text" id="usage" name="usage" />
  </div>
  <div class="form-group">
    <label for="status">이동현황 :</label>
    <select id="status" name="status">
      <option value="">-</option>
      <option value="이동중">이동중</option>
      <option value="보관중">보관중</option>
      <option value="사용중">사용중</option>
    </select>
  </div>
  <div class="form-group">
    <label for="note">비고 :</label>
    <input type="text" id="note" name="note" />
  </div>
  <button type="submit">추가하기</button>
</form>
</body>
</html>