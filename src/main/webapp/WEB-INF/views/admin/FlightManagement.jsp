<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="/storage/js/FlightManagement.js" var="jsUrl" />

<div class="row mt-3 mb-4">
	<div class="col-lg-4">
		<button id="btnAdd" class="btn btn-success" data-toggle="modal"
			data-target="#updateFlightModal">Add Flight</button>
	</div>
</div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Flight List</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>ID</th>
						<th>From Airport</th>
						<th>To Airport</th>
						<th>Arrival Date</th>
						<th>Departure Date</th>
						<th>Price</th>
						<th>Airplane</th>
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
<div class="modal fade" id="updateFlightModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Edit Flight</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div>
					<input class="form-control" type="hidden" id="inpFlight_Id" />
					<div class="form-group">
						<label>From Airport</label> <select
							class="form-control loadAirport" id="selFromAirport">
						</select>
					</div>
					<div class="form-group">
						<label>To Airport</label> <select class="form-control loadAirport"
							id="selToAirport">
						</select>
						<div class="alert alert-danger" id="alertToAirport">
							The departure airport must be different from the arrival airport!
						</div>
					</div>
					<div class="form-group">
						<label>Arrival Date</label> <input class="form-control"
							type="datetime-local" id="inpArrivalDate" required>
					</div>
					<div class="form-group">
						<label>Departure Date</label> <input class="form-control"
							type="datetime-local" id="inpDepartureDate" required>
							<div class="alert alert-danger" id="alertDepartureDate">
								The departure Date must be greater than the arrival Date!
							</div>
					</div>
					<div class="form-group">
						<label>Flight Price</label> <input class="form-control"
							type="text" id="inpPrice" required>
					</div>
					<div class="form-group">
						<label>Airplane</label> <select class="form-control"
							id="selAirplane">
						</select>
					</div>
				</div>
				<button id="btnUpdate" type="button"
					class="btn btn-success mt-1 float-right">Update</button>
			</div>
		</div>
	</div>
</div>

<script src="${jsUrl}"></script>