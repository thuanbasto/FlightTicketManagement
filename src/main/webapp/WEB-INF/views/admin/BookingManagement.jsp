<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="/storage/js/BookingManagement.js" var="jsUrl" />

<div class="row mt-3 mb-4"></div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Booking List</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>ID</th>
						<th>Booking Date</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Payment Method</th>
						<th>Ticket Seller</th>
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
<div class="modal fade" id="viewFlightModal">
	<div class="modal-dialog" style="max-width: 90%; margin: 1.75rem auto">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Booking</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body" id="booking-detail-body">
				<!--  booking info -->
				

				<!-- Tickets -->
				<h3>Ticket #1</h3>
				<table class="table">
					<tr>
						<td>Arrival Airport</td>
						<td>Departure Airports</td>
						<td>Flight Code + Airplane</td>
					</tr>
					<tr>
						<td>Arrival Date</td>
						<td>Departure Date</td>
						<td>Seat + Seat Class</td>
					</tr>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<h6>Ticket Price:</h6>
						<h6>Tax:</h6>
						<h6>Signed Luggage:</h6>
						<h5>Total Price:</h5>
					</div>
					<div class="col-sm-8">
						<h5>Customer Information</h5>
						<table class="table">
							<tr>
								<td>Mr/Mrs:</td>
								<td>Address:</td>
							</tr>
							<tr>
								<td>Birthday:</td>
								<td>Identity Number:</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
					<h5><b>Booking Information</b></h5>
					<table class="table">
						<tr>
							<td><b>Booking Date</b></td>
							<td>2012/12/12</td>
						</tr>
						<tr>
							<td><b>Phone:</b></td>
							<td>123456789</td>
						</tr>
						<tr>
							<td><b>Email:</b></td>
							<td>abc@gmail.com</td>
						</tr>
						<tr>
							<td><b>Payment Method:</b></td>
							<td>ONLINE</td>
						</tr>
						<tr>
							<td><b>Number of Ticket:</b></td>
							<td>2</td>
						</tr>
						<tr>
							<td><b>Total Booking Price:</b></td>
							<td>50000</td>
						</tr>
					</table>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>

<script src="${jsUrl}"></script>