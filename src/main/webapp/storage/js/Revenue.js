userList = [];
bookingList = [];

// load user data for option/select
function loadUserList() {
    $.ajax({
        url: "/FlightTicketManagement/api/users",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            $.each(response,function(index,value){
                userList.push(value);
                $("#inpUser").append(`<option value='${value.user_Id}'>${value.user_Id}: ${value.username} | ${value.firstName} ${value.lastName}</option>`)
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function loadBookingList() {
    $.ajax({
        url: "/FlightTicketManagement/api/bookings",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            ajaxSuccess(response)
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadBookingList()
loadUserList()

function loadBookingListWithUser() {
    $.ajax({
        url: "/FlightTicketManagement/api/bookings?user_Id=" + $('#inpUser').val(),
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            ajaxSuccess(response)
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function loadBookingListWithTime() {
    $.ajax({
        url: "/FlightTicketManagement/api/bookings?fromDate=" + $('#inpFromDate').val() + "&toDate=" + $('#inpToDate').val(),
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            ajaxSuccess(response)
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function ajaxSuccess(response) {
    let htmlStr = ``;
    totalPrice = 0;
    $.each(response,function(index,value){
        bookingList.push(value);
        priceBooking = 0;

        value.tickets.forEach(ticket => {
            priceBooking += ticket.ticket_PriceTotal;
        });
        totalPrice += priceBooking;

        htmlStr +=
        `<tr>
            <td>${value.booking_Id}</td>
            <td>${value.bookingDate}</td>
            <td>${value.email}</td>
            <td>${value.user == null ? "" : value.user.username}</td>
            <td>${priceBooking}</td>
            <td>
                <button id="btnDetail" data-id=${value.booking_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#viewBookingModal"><i class="fas fa-eye"></i></i></button>&nbsp
            </td>
        </tr>`;

        if (index == response.length-1){
            $(".totalPrice").html("Total price: " + totalPrice + " VND");
        }
    });
    $("#tbodyData").html(htmlStr);
}

$('#tbodyData').on('click', '#btnDetail', function() {

	$.ajax({
		url: "/FlightTicketManagement/api/bookings/" + $(this).data('id'),
		ethod: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
		success: function(response) {
			var htmlStr = ``;
			let totalTaxPrice = 0;
			response.tickets.forEach(function(value) {
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
						<h6><b>Signed Luggage:</b> ${value.signedluggage.signedluggagePrices[0].price}</h6>
						<h6><b>Travel Class Price:</b> ${value.seat.travelClass.travelClassPrices[0].price}</h6>
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


$('#btnUserStatistics').on('click', function (event) {
    bookingList = [];
    loadBookingListWithUser();
    $('.close').click();
})

$('#btnTimeStatistics').on('click', function (event) {
    bookingList = [];
    loadBookingListWithTime();
    $('.close').click();
})