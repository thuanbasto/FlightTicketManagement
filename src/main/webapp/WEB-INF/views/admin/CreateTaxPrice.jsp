<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="taxpriceapi" value="http://localhost:8084/api/taxprice"></c:url>
<c:url var="taxprive" value="http://localhost:8084/FlightTicketManagement/taxprice"></c:url>
<%-- <form <c:url action="taxprice"/> id ="formSubmit" method="get">
</form> --%>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form:form id="formsubmit" modelAttribute="listTaxPrice">
<h1>List Taxprice</h1>
<div>
	<%-- <div class="form-group">
  		<label for="usr">id:</label>
  		<input type="text" id="tax_Price_Id" name="tax_Price_Id" value="${listTaxPrice.tax_Price_Id}" />
  		<form:input path="tax_Price_Id" id="tax_Price_Id"/>
	</div> --%>
	<div class="form-group">
  		<label for="pwd">modifiedDate:</label>
  		<%-- <input type="text" id="modifiedDate" name="modifiedDate" value="${listTaxPrice.modifiedDate}" /> --%>
  		<form:input path="modifiedDate" id="modifiedDate"/>
	</div>
	<div class="form-group">
  		<label for="pwd">price:</label>
  		<%-- <input type="text" id="price" name="price"  value="${listTaxPrice.price}"/> --%>
  		<form:input path="price" id="price"/>
	</div>
	<div class="form-group">
	<label for="taxName">Select list:</label>
	<%-- <select class="form-control" id="taxName" name="taxName">
			<option value="">--Chọn thể loại--</option>
	<c:forEach items="${listTax}" var="tax">
	
    		<option value="${tax.tax_Id}">${tax.taxName}</option>
	</c:forEach>
	</select> --%>
	<form:select path="taxName" id="taxName" >
		<form:option value="">--Chọn thể loại</form:option>
		<form:options items="${listTax}"/>
	</form:select>
  		
	</div>
	<form:hidden path="tax_Price_Id"/>
	
	<button type="button" id="btncreate" name="btncreate" >Create</button>  

    
</div>
	 
</form:form>

<script>
	$('#btncreate').click(function (e) {
		e.preventDefault();
		var data = {};
	    var formData = $('#formsubmit').serializeArray();
	    $.each(formData, function (i, v) {
	        data[""+v.name+""] = v.value;
	    });
	    var id = $('#tax_Price_Id').val();
	    if (id == "") {
	    	addNew(data);
	    } else {
	    	updateNew(data);
	    }
		
	});
	function addNew(data) {
        $.ajax({
            url: '${taxpriceapi}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${taxprive}";
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

	function updateNew(data) {
		$.ajax({
	        url: '${taxpriceapi}',
	        type: 'PUT',
	        contentType: 'application/json',
	        data: JSON.stringify(data),
	        dataType: 'json',
	        success: function (result) {
	        	window.location.href = "${taxprive}";
	        },
	        error: function (error) {
	        	window.location.href = "${taxprive}";
	        }
	    });
	}
</script>
