<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<style>
a:hover {
	color: #ffffff;
	/* text-decoration: underline; */
}

.nav-tabs {
	border-bottom: none;
}

.tab_select_co {
	background-color: #f7c4ff !important;
}

.tab_no_select_co {
	background-color: #a85285 !important;
}

.logoal {
	max-width: 100%;
	width: auto;
	max-height: 6.167rem;
}

.nav-link {
	font-family: Jotia, Verdana, Geneva, sans-serif;
	color: #64003D;
}

body {
	background-color: #c4669e;
}

.chieu li {
	border: 1px solid white;
	background-color: white;
	padding: 5px;
	border-radius: 12px !important;
	display: inline;
	cursor: default;
}

.chieu li:hover {
	background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff,
		#00c1ff);
}

.selected {
	background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff,
		#00c1ff);
}

ul {
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	color: #495057;
}

a#ftab {
	/* border-top-color: white;
    border-right-color: white;
    border-bottom-color: transparent;
    border-left-color: white; */
	
}
</style>

<!-- <body> -->



<nav class="navbar navbar-expand-sm bg-light" >
	<div class="container">
		<ul class="navbar-nav">
			<li class="nav-item"><img class="logoal" src="./logoal.png"
				alt="logo"></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="#"> Book your
					trip </a></li>
			<li class="nav-item"><a class="nav-link" href="#"> Manage my
					bookings </a></li>
			<li class="nav-item">
				<button class="btn"
					style="background-color: #64003D; color: white; padding: 10px 20px 10px 20px;">Login</button>
			</li>
		</ul>
	</div>
</nav>
