<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<c:url value="/signin" var="url"/>

<div>
	<h1>Sign In</h1>
	<div>
	<form action="${url}" method="POST">
	  <div>
	    <label>Username</label>
	    <input name="username" placeholder="Enter username" autofocus required/>
	  </div>
	  <div>
	    <label>Password</label>
	    <input name="password" type="password" placeholder="Password" required/>
	  </div>
	  <p style="color:red">${msg}</p>
	  <button id="submit" type="submit" class="btn btn-primary">Sign in</button>
	</form>
	</div>
</div>
    
