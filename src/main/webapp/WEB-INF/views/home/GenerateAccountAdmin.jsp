<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${generated == 1}">
	<h1>Tao account thanh cong</h1>
</c:if>
<c:if test="${generated == 0}">
	<h1>Account da duoc tao</h1>
</c:if>