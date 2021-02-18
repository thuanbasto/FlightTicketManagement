<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/admin/" var="url"/>
<h1>List Tax</h1>
	<table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
          
        </tr>
        
        <c:forEach items="${listTax}" var="tax">
        <tr>
            <td>${tax.tax_Id}</td>
            <td>${tax.taxName}</td>
           
        </tr>
        </c:forEach>
    </table>
	
