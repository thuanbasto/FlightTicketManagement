<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<c:url value="/storage/img/logo.png" var="imgUrl"/>
<c:url value="/storage/css/Header.css" var="cssUrl"/>

<!-- Can fix lai cho nay -->
<link rel="stylesheet" href='<c:url value="/storage/css/Booking.css"></c:url>'>

<nav class="navbar navbar-expand-sm bg-light">
    <div class="container">
        <ul class="navbar-nav">
            <li class="nav-item">
                <img class="logoal" src="${imgUrl}" alt="logo">
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">
                    Book your trip
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    Manage my bookings
                </a>
            </li>
            <li class="nav-item">
                <button class="btn" style="background-color: #64003D;color: white;padding: 10px 20px 10px 20px;">Login</button>
            </li>
        </ul>
    </div>
</nav>


