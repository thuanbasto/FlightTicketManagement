<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>List Taxprice</h1>
<div>
<table border="1">
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Price</th>
            <th>Chúc năng</th>
          
        </tr>
        
        <c:forEach items="${listTaxPrice}" var="taxprice">
        <tr>
            <td>${taxprice.tax_Price_Id}</td>
            <td>${taxprice.modifiedDate}</td>
            <td>${taxprice.price}</td>
            <c:url var="updatetaxprive" value="http://localhost:8084/FlightTicketManagement/createtaxprice">
            	<c:param name="id" value="${taxprice.tax_Price_Id}"></c:param>
            </c:url>
            <td><a href='${updatetaxprive}'>Sửa</a></td>
            
            
            
        </tr>
        </c:forEach>
    </table>
    <c:url var="createtaxprive" value="http://localhost:8084/FlightTicketManagement/createtaxprice"></c:url>
    <a href='${createtaxprive}'>Create</a>
    
</div>
	 

