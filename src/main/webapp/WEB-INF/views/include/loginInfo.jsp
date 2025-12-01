<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="login" value="${sessionScope.login}" />

<c:if test="${not empty login}">
    <c:set var="login_num" value="${login.login_num}" />
    <c:set var="login_id" value="${login.login_id}" />
	<c:set var="login_area" value="${login.login_area}" />
    <c:set var="login_name" value="${login.login_name}" />
	<c:set var="login_position" value="${login.login_position}" />
	<c:set var="login_department" value="${login.login_department}" />
    <c:set var="login_signName" value="${login.login_signName}" />
</c:if>