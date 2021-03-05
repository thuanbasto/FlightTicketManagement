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
            <td>${value.user.username}</td>
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

// show edit form
$('#tbodyData').on('click', '#btnDetail', function() {

	$.ajax({
		method: "GET",
		url: "/FlightTicketManagement/api/bookings/" + $(this).data('id'),
		async: false,
		success: function(response) {
			var htmlStr = ``;
			var bookingDate = $(response).find("bookingDate").text();
			var email = $(response).find("email").first().text();
			var paymentMethod = $(response).find("paymentMethod").text();
			var phone = $(response).find("phone").first().text();
			var numberOfTicket = $(response).find("numberOfTicket").text();
			var totalPrice = $(response).find("totalPrice").text();

			$(response).find("tickets").slice(1).each( function() {
				console.log($(this))
				var ticket_Id = $(this).find("ticket_Id").text();
				var fromAirport = $(this).find("flight").find("fromAirport").find("name").text();
				var toAirport = $(this).find("flight").find("toAirport").find("name").text();
				var arrivalDate = $(this).find("flight").find("arrivalDate").text();
				var departureDate = $(this).find("flight").find("departureDate").text();
				var airplane = $(this).find("flight").find("airplane").find("name").text();
				var seat = $(this).find("seat").find("seat_Id").text();
				var seatClass = $(this).find("seat").find("travelClass").find("name").text();
				var airplane = $(this).find("flight").find("airplane").find("name").text();
				var ticket_PriceTotal = $(this).find("ticket_PriceTotal").text();
				var flightPrice = $(this).find("flight").find("flight_Price").text();
				
				var address = $(this).find("customer").find("address").text();
				var firstName = $(this).find("customer").find("firstName").text();
				var lastName = $(this).find("customer").find("lastName").text();
				var birthDay = $(this).find("customer").find("birthDay").text();
				var identityNumber = $(this).find("customer").find("identityNumber").text();

				htmlStr = htmlStr + `
			 	<h3>Ticket #${ticket_Id}</h3>
				<table class="table">
					<tr>
						<td><b>${fromAirport}</b></td>
						<td><b>${toAirport}</b></td>
						<td><b>${ticket_Id}-${airplane}</b></td> 
					</tr>
					<tr>
						<td><b>${arrivalDate}</b></td>
						<td><b>${departureDate}</b></td>
						<td><b>${seat} - ${seatClass}</b></td>
					</tr>
				</table>
				<div class="row">
					<div class="col-sm-4" style="padding-left: 40px;">
						<h6>Flight Price: ${flightPrice}</h6>
						<h6>Tax: </h6>
						<h6>Signed Luggage: </h6>
						<h6><b>Total Price:</b> ${ticket_PriceTotal}</h6>
					</div>
					<div class="col-sm-8">
						<h5>Customer Information</h5>
						<table class="table">
							<tr>
								<td>Mr/Mrs: ${firstName} ${lastName}</td>
								<td>Address: ${address}</td>
							</tr>
							<tr>
								<td>Birthday: ${birthDay}</td>
								<td>Identity Number: ${identityNumber}</td>
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
							<td>${bookingDate}</td>
						</tr>
						<tr>
							<td><b>Phone:</b></td>
							<td>${phone}</td>
						</tr>
						<tr>
							<td><b>Email:</b></td>
							<td>${email}</td>
						</tr>
						<tr>
							<td><b>Payment Method:</b></td>
							<td>${paymentMethod}</td>
						</tr>
						<tr>
							<td><b>Number of Ticket:</b></td>
							<td>${numberOfTicket}</td>
						</tr>
						<tr>
							<td><b>Total Booking Price:</b></td>
							<td>${totalPrice}</td>
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