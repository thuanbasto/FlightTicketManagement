<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/LuggageManagement.js" var="jsUrl" />

<div class="row mt-3 mb-4">
    <div class="col-lg-4">
        <button id="btnAdd" class="btn btn-success" data-toggle="modal" data-target="#updateLuggageModal">Add Luggage</button>
    </div>
</div>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Luggage Management</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Luggage Code</th>
                        <th>Luggage Name</th>
                        <th>Luggage weight (KG)</th>
                        <th>Luggage Price (VND)</th>
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
<div class="modal fade" id="updateLuggageModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">

                    <div class="LuggageID form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" id="inpLuggageID" disabled>
                    </div>

                    <div class="LuggageName form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" id="inpLuggageName" >
                    </div>

                    
                    <div class="LuggageWeight form-group">
                        <label>Weight</label>
                        <input class="form-control" type="number" id="inpLuggageWeight" >
                    </div>

                    <div class="price form-group">
                        <label>Price (VND)</label>
                        <input class="form-control" type="number" id="inpPrice">
                    </div>

                    
                <button id="btnUpdate" type="button" class="btn btn-success mt-1 float-right">Update</button>
            </div>
        </div>
    </div>
</div>


</div>
<!-- /.container-fluid -->

</div>
</div>
<!-- End of Main Content -->

<script src="${jsUrl}"></script>