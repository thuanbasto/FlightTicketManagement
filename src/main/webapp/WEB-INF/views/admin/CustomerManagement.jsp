<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/CustomerManagement.js" var="jsUrl"/>

<div class="row mt-3 mb-4">
    <div class="col-lg-4">
        <button id="btnAdd" class="btn btn-success" data-toggle="modal" data-target="#updateCustomerModal">Add customer</button>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Customer List</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Birthday</th>
                        <th>Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="tbodyData">
                    
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- The Modal -->
<div class="modal fade" id="updateCustomerModal">
    <div class="modal-dialog">
        <div class="modal-content">
            
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Edit Customer</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
            <!-- Modal body -->
            <div class="modal-body">
                <div>
                    <div class="customer_Id form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" id="inpCustomer_Id" disabled> 
                    </div>
                    <div class="firstName form-group">
                        <label>First name</label>
                        <input class="form-control" type="text" id="inpFirstName">
                    </div>
                    <div class="lastName form-group">
                        <label>Last name</label>
                        <input class="form-control" type="text" id="inpLastName">
                    </div>
                    <div class="birthDay form-group">
                        <label>Birthday</label>
                        <input class="form-control" type="date" id="inpBirthday" max="9999-01-01" required>
                    </div>
                    <div class="address form-group">
                        <label>Address</label>
                        <input class="form-control" type="text" id="inpAddress">
                    </div>
                </div>
                <button id="btnUpdate" type="button" class="btn btn-success mt-1 float-right">Update</button>
            </div>
        </div>
    </div>
</div> 

<script src="${jsUrl}"></script>