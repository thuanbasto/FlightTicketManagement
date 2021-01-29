<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/admin/signup" var="url"/>
<h1>Sign up</h1>
<div>
	<div>
	<form:form action="${url}" modelAttribute="userDTO" method="POST">
	  <div>
	    <label>Username</label>
	    <form:input path="username" placeholder="Enter username" required="true"/>
	    <p style="color:red"><form:errors path="username"></form:errors></p>
	    <p id="usernameErrorLength" style="color:red">- Length must be more than or equal to 6.</p>
	    <p id="usernameErrorRegex" style="color:red">- Only characters A-Z, a-z, 1-9 and '-'.</p>
	  </div>
	  <div>
	    <label>Password</label>
	    <form:input path="password"  type="password" placeholder="Password" required="true"/>
	  </div>
	   <div>
	    <label>Confirm password</label>
	    <form:input path="confirmPassword" type="password" placeholder="Confirm password" required="true"/>
	    <p style="color:red"><form:errors path="password"></form:errors></p>
	   	<p id="passwordErrorMatch" style="color:red">- Passwords do not match</p>
	  </div>
	  <button id="submit" type="submit" class="btn btn-primary">Sign up</button>
	</form:form>
	</div>
</div>