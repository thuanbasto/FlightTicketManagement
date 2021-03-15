var action = "";


// load flight func
function loadBookings() {
	$.ajax({
		url: "/FlightTicketManagement/api/bookings",
		async: false,
		success: function(response) {
			var htmlStr = ``;
			$(response).find("item").each(function() {
				var bookingId = $(this).find("booking_Id").text();
				var bookingDate = $(this).find("bookingDate").text();
				var email = $(this).find("email").first().text();
				var paymentMethod = $(this).find("paymentMethod").text();
				var numberOfTicket = $(this).find("numberOfTicket").text();
				var ticketSeller = $(this).find("user").find("username").text();
				var phone = $(this).find("phone").first().text();
				
				console.log(email + paymentMethod + numberOfTicket + ticketSeller + phone)

				htmlStr = htmlStr + `
			 	<tr>
				 	<td>${bookingId}</td>
	                <td>${bookingDate}</td>
	                <td>${email}</td>
	                <td>${phone}</td>
	                <td>${paymentMethod}</td>
	                <td>${ticketSeller}</td>
                	<td><button id="btnEdit" data-id=${bookingId} type="button" class="btn btn-info" data-toggle="modal" data-target="#viewFlightModal"><i class="fas fa-eye"></i></i></button>&nbsp
                	<button id="btnDelete" data-id=${bookingId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                	</td>
            	</tr>`
			});
			$("#tbodyData").html(htmlStr);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}

	})
}

loadBookings();

// delete booking

$('#tbodyData').on('click', '#btnDelete', function() {
	// console.log($(this).data('id'));
	if (confirm(`You want to delete booking with id = ${$(this).data('id')}?`)) {
		let tr = $(this).closest('tr');
		$.ajax({
			method: "DELETE",
			url: "/FlightTicketManagement/api/bookings/" + $(this).data('id'),
			contentType: "application/json",
			async: false,
			success: function(resp) {
				tr.remove();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				// $('.failedToast').children('.toast-body').html('Unsuccessful')
				console.log(textStatus, errorThrown);
			}
		});
	} else { }
});

// show edit form
$('#tbodyData').on('click', '#btnEdit', function() {

	$.ajax({
		url: "/FlightTicketManagement/api/bookings/" + $(this).data('id'),
		ethod: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
		success: function(response) {
			var htmlStr = ``;
			
			response.tickets.forEach(function(value) {
				let totalTaxPrice = 0;
				value.taxs.forEach(tax=>{
					if(tax.taxPrices != null)
						totalTaxPrice += tax.taxPrices[0].price
				})
				htmlStr = htmlStr + `
			 	<h3>Ticket #${value.ticket_Id}</h3>
				<table class="table">
					<tr>
						<td><b>From: </b>${value.flight.fromAirport.name}</td>
						<td><b>To: </b>${value.flight.toAirport.name}</td>
						<td><b>Flight Code: </b>${value.flight.flight_Id}-${value.flight.airplane.name}</td> 
					</tr>
					<tr>
						<td><b>Arrival Date: </b>${value.flight.arrivalDate}</td>
						<td><b>Departure Date: </b>${value.flight.departureDate}</td>
						<td><b>Seat: </b>${value.seat.seat_Id} - ${value.seat.travelClass.name}</td>
					</tr>
				</table>
				<div class="row">
					<div class="col-sm-4" style="padding-left: 40px;">
						<h6><b>Flight Price:</b> ${value.flight.flight_Price}</h6>
						<h6><b>Tax:</b> ${totalTaxPrice}</h6>
						<h6><b>Signed Luggage:</b> ${!value.signedluggage
														? '' 
														: value.signedluggage.signedluggagePrices
														? value.signedluggage.signedluggagePrices[0].price : ''}</h6>

														
						<h6><b>Travel Class Price:</b> ${value.seat.travelClass.travelClassPrices  
														? value.seat.travelClass.travelClassPrices[0].price : ''}</h6>
						<h6><b>Total Price:</b> ${value.ticket_PriceTotal}</h6>
					</div>
					<div class="col-sm-8">
						<h5>Customer Information</h5>
						<table class="table">
							<tr>
								<td><b>Mr/Mrs: </b> ${value.customer.firstName} ${value.customer.lastName}</td>
								<td><b>Address: </b>${value.customer.address}</td>
							</tr>
							<tr>
								<td><b>Birthday: </b>${value.customer.birthDay}</td>
								<td><b>Identity Number: </b>${value.customer.identityNumber !=null ? value.customer.identityNumber : ""}</td>
							</tr>
						</table>
					</div>
				</div>
				`
			});
			htmlStr += `
			<div class="row">
				<div class="col-sm-8"></div>
				<div class="col-sm-4">
					<h5><b>Booking Information</b></h5>
					<table class="table">
						<tr>
							<td><b>Booking Date</b></td>
							<td>${response.bookingDate}</td>
						</tr>
						<tr>
							<td><b>Phone:</b></td>
							<td>${response.phone}</td>
						</tr>
						<tr>
							<td><b>Email:</b></td>
							<td>${response.email}</td>
						</tr>
						<tr>
							<td><b>Payment Method:</b></td>
							<td>${response.paymentMethod}</td>
						</tr>
						<tr>
							<td><b>Number of Ticket:</b></td>
							<td>${response.numberOfTicket}</td>
						</tr>
						<tr>
							<td><b>Total Booking Price:</b></td>
							<td>${response.totalPrice}</td>
						</tr>
					</table>
				</div>
			</div>
			`
			$("#booking-detail-body").html(htmlStr);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
});

$('#viewFlightModal').on("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		$('#btnUpdate').click();
	}
});