<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/UserManagement.js" var="jsUrl"/>

<div class="row mt-3 mb-4">
    <div class="col-lg-4">
        <a href="signup">
            <button id="btnAdd" class="btn btn-success">Add User</button>
        </a>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">User List</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
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
<div class="modal fade" id="updateUserModal">
    <div class="modal-dialog">
        <div class="modal-content">
            
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Edit User</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
            <!-- Modal body -->
            <div class="modal-body">
                <div>
                    <div class="user_Id form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" id="inpUser_Id" disabled> 
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
                    <div class="phone form-group">
                        <label >Phone</label>
                        <input class="form-control" type="text" id="inpPhone" maxlength="10">
                    </div>
                    <div class="username form-group">
                        <label>Username</label>
                        <input class="form-control" type="text" id="inpUsername" disabled>
                    </div>
                    <div class="password form-group">
                        <label>Password</label>
                        <input class="form-control" type="password" id="inpPassword">
                    </div>
                    <div class="email form-group">
                        <label>Email</label>
                        <input class="form-control" type="email" id="inpEmail">
                    </div>
                    <div class="role">
                        <label>Role</label>
                        <c:forEach items="${roles}" var="role">
                            <div class="form-check">
                                <input type="checkbox" id="inpRole" value="${role.role_Id}" class="form-check-input"> ${role.name}
                            </div>
                        </c:forEach>
                    </div>
                    <div class="enable">
                        <label>Active account</label>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="inpEnable" value="1">Enable
                        </div>
                    </div>
                </div>
                <button id="btnUpdate"  type="button" class="btn btn-success mt-1 float-right">Update</button>
            </div>
        </div>
    </div>
</div> 

<script src="${jsUrl}"></script>