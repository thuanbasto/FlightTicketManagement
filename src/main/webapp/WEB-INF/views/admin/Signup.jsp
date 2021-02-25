<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/admin/signup" var="actionUrl"/>
<c:url value="/storage/js/Signup.js" var="jsUrl"/>
<c:url value="/storage/css/Signup.css" var="cssUrl"/>

<link rel="stylesheet" href='${cssUrl}'>

<div class="boxSignup">
<h1>Sign up</h1>
<form:form action="${actionUrl}" modelAttribute="userDTO" method="POST">
	<div class="username form-group">
		<label>Username<span class="required"> *</span></label>
		<form:input path="username" class="form-control" placeholder="Enter username" required="true"/>
		<p class="errorSignup"><form:errors path="username"></form:errors></p>
		<p id="usernameErrorExist" class="errorSignup">- This username has already been taken. Try another.</p>
		<p id="usernameErrorLength" class="errorSignup">- Length must be more than or equal to 6.</p>
		<p id="usernameErrorRegex" class="errorSignup">- Only characters A-Z, a-z and 0-9.</p>
	</div>
	<div class="password form-group">
		<label>Password<span class="required"> *</span></label>
		<form:input path="password" class="form-control"  type="password" placeholder="Password" required="true"/>
	</div>
	<div class="confirmPassword form-group">
		<label>Confirm password<span class="required"> *</span></label>
		<form:input path="confirmPassword" class="form-control" type="password" placeholder="Confirm password" required="true"/>
		<p class="errorSignup"><form:errors path="password"></form:errors></p>
		<p id="passwordErrorMatch" class="errorSignup">- Passwords do not match</p>
	</div>
	<div class="name form-group">
		<label>First name<span class="required"> *</span></label>
		<label>Last name<span class="required"> *</span></label>
		<form:input path="firstName" class="form-control" placeholder="First name" required="true"/>
		<form:input path="lastName" class="form-control" placeholder="Last name" required="true"/>
	</div>
	<div class="birthDay form-group">
		<label>Birthday<span class="required"> *</span></label>
		<form:input path="birthDay" type="date" class="form-control" required="true"/>
	</div>
	<div class="phone form-group">
		<label>Phone<span class="required"> *</span></label>
		<form:input path="phone" class="form-control" maxlength="10" placeholder="0123456789" required="true"/>
	</div>
	<div class="address form-group">
		<label>Address<span class="required"> *</span></label>
		<form:input path="address" class="form-control" placeholder="20 ABC XYZ" required="true"/>
	</div>
	<div class="email form-group">
		<label>Email<span class="required"> *</span></label>
		<form:input path="email" type="email" class="form-control" placeholder="Email@gmail.com" required="true"/>
	</div>
	<div class="role">
	   	<label>Role<span class="required"> *</span></label>
		<c:forEach items="${userDTO.roles}" var="role">
			<c:choose>
				<c:when test="${role.name eq 'ROLE_STAFF'}">
				    <div class="form-check">
					    <form:checkbox path="roleIdList" value="${role.role_Id}" checked="checked" class="form-check-input" /> ${role.name}
				  	</div>
				</c:when>
				<c:otherwise>
				    <div class="form-check">
					    <form:checkbox path="roleIdList" value="${role.role_Id}" class="form-check-input" /> ${role.name}
				  	</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${role.name == 'ROLE_STAFF'}">
			</c:if>
		</c:forEach>
	</div>
	<div class="enable">
	   	<label>Active account</label>
	    <div class="form-check">
		    <form:checkbox path="enable" value="1" class="form-check-input"/> Enable
	  	</div>
	</div>
	<button id="back" class="btn btn-primary">Back</button>
	<button id="submit" type="submit" class="btn btn-success">Sign up</button>
	<button id="next" class="btn btn-primary">Next</button>
</form:form>
</div>


<script src="${jsUrl}"></script>