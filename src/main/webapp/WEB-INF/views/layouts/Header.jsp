<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url value="/storage/img/logo.png" var="imgUrl" />
<c:url value="/storage/css/Header.css" var="cssUrl" />
<c:url value="/admin/dashboard" var="dashboardUrl" />
<c:url value="/logout" var="logoutUrl" />

<link rel="stylesheet"
	href='<c:url value="/storage/css/Header.css"></c:url>'>

<nav class="navbar navbar-expand-sm bg-light">
	<div class="container">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="home"> 
					<img class="logoal" src="${imgUrl}" alt="logo">
				</a>
			</li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="book">Book your trip </a></li>
			<security:authorize access="!isAuthenticated()">
				<li class="nav-item">
					<button class="btn"
						style="background-color: #64003D; color: white; padding: 10px 20px 10px 20px;" onclick="window.location.href = 'signin';">
						Login
					</button>
				</li>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<security:authentication property="principal" var="user" />
				<li class="nav-item">
					<a class="nav-link" href="${dashboardUrl}">
						Dashboard
					</a>
				</li>
				<li class="nav-item dropdown">
					<a class="username nav-link dropdown-toggle" style="color: teal" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" data-username="${user.username}" aria-expanded="false"> 
						${user.username} 
					</a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="${logoutUrl}">Logout</a>
					</div>
				</li>
			</security:authorize>
		</ul>
	</div>
</nav>


