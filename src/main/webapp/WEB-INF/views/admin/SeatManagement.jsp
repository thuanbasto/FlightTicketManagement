<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/SeatManagement.js" var="jsUrl"/>

<div class="row mt-3 mb-4">
    <div class="col-lg-4">
        <button id="btnAdd" class="btn btn-success" data-toggle="modal" data-target="#updateSeatModal">Add seat</button>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Seat List</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Travel class</th>
                        <th>Price</th>
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
<div class="modal fade" id="updateTicketModal">
    <div class="modal-dialog">
        <div class="modal-content">
            
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Edit seat</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
            <!-- Modal body -->
            <div class="modal-body">
                <div>
                    <div class="seat_Id form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" id="inpSeat_Id"> 
                    </div>
                    <select class="browser-default custom-select" id="inpTravelClass" required>

                    </select>
                    <div class="price form-group">
                        <label>Price</label>
                        <input class="form-control" type="text" id="inpPrice" disabled>
                    </div>
                </div>
                <button id="btnUpdate" type="button" class="btn btn-success mt-1 float-right">Update</button>
            </div>
        </div>
    </div>
</div> 

<script src="${jsUrl}"></script>