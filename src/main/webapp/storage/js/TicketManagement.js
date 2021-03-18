var ticketList = [];
var action = "";
var price = "";

function formatVND(money) {
    return (money).toLocaleString('vi', {
        style: 'currency',
        currency: 'VND',
    });
}

function loadTicketList() {
    $.ajax({
        method: "GET",
        url: "/FlightTicketManagement/api/tickets",
        async: false,
        success: function (response) {
            var htmlStr = ``;
            $(response).each(function (key, value) {
                ticketList.push(value);

                var id = value.ticket_Id;
                var customerName = value.customer.firstName + " " + value.customer.lastName;
                var bookingCode = value.booking.booking_Id;
                var flight = value.flight.fromAirport.name + " - " + value.flight.toAirport.name;
                var departureDate = value.flight.departureDate;
                var seat = value.seat.seat_Id;
                var price = value.ticket_PriceTotal;


                htmlStr +=
                    `<tr class=${id}>
                    <td>${id}</td>
                    <td>${customerName}</td>
                    <td>${bookingCode}</td>
                    <td>${flight}</td>
                    <td>${departureDate}</td>
                    <td>${seat}</td>
                    <td>${formatVND(price)}</td>
                    <td>
                        <button id="btnEdit" data-id=${id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateTaxModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>&nbsp
                        <button id="btnDetail" data-id=${id} type="button" class="btn btn-success" data-toggle="modal" data-target="#deltailModal"><i class="fas fa-eye"></i></button>
                    </td>
                </tr>`;
            });
            $("#tbodyData").html(htmlStr);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}



var BookingList = [];

function loadBookingClassList() {

    $.ajax({
        url: "/FlightTicketManagement/api/bookings",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (index, value) {
                BookingList.push(value);
                Customer = value.booking_Id + "-" + value.bookingDate
                $("#inpBookingClass").append(`<option value='${value.booking_Id}'>${Customer}</option>`)
            });

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

var flightList = [];

function loadFlightClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/flights",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (index, value) {
                flightList.push(value);
                flight = value.flight_Id + ". " + value.fromAirport.name + " - " + value.toAirport.name + " - " + value.departureDate
                $("#inpFlightClass").append(`<option value='${value.flight_Id}'>${flight}</option>`)
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}





var seatList = [];

function loadSeats() {
    $.ajax({
        url: "/FlightTicketManagement/api/seats",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, ticket) {
                seatList.push(ticket);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
    console.log(seatList);
}

var emptySeatList = [];
var bookedSeatList = [];

function loadBookedSeat(id) {
    emptySeatList = [];
    bookedSeatList = [];
    $.ajax({
        url: "/FlightTicketManagement/api/seats/flights/" + id,
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, ticket) {
                bookedSeatList.push(ticket);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
    seatList.forEach(seat => {
        // check seat is booked ?
        let booked = false;
        for (let i = 0; i < bookedSeatList.length; i++){
            if (seat.seat_Id == bookedSeatList[i].seat_Id) {
                booked = true;
                break;
            }
        }
        if(booked == false){
            emptySeatList.push(seat);
        }      
    })
    console.log(bookedSeatList);
    console.log(emptySeatList);

}


var luggageList = [];

function loadLuggageClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        method: "GET",
        success: function (response) {
            $("#inpLuggageClass").append(`<option value='0'>${"None"}</option>`)
            $(response).each(function (key, value) {
                luggageList.push(value);
                luggage = value.signedLuggage_Id + " - " + value.name
                $("#inpLuggageClass").append(`<option value='${value.signedLuggage_Id}'>${luggage}</option>`)
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

var taxesList = [];

function loadTaxesClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/taxes",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (index, value) {
                taxesList.push(value);
                taxes = value.tax_Id + " - " + value.taxName
                $("#form-check-tax").append(`
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value='${value.tax_Id}' id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                    ${taxes}
                    </label>                                
                </div>`)
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}


loadSeats();
loadFlightClassList();
loadBookingClassList()
loadLuggageClassList();
loadTicketList();

$('#tbodyData').on('click', '#btnDetail', function () {

    // fill input with value
    ticketList.forEach(ticket => {
        if (ticket.ticket_Id == $(this).data("id")) {
            var htmlStrHeaderDetail = ``;
            var htmlStrBodyDetail = ``;
            var htmlStrFooterDetail = ``;

            htmlStrHeaderDetail += `
            <h4 class="modal-title" id="myModalLabel"><i class="text-muted fa fa-plane">
            </i><strong>${ticket.ticket_Id}</strong> - Ticket Detail 
            </h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            <i class="text-danger fa fa-times"></i></button>`

            htmlStrBodyDetail += `
            <table class="pull-left col-md-10 ">
            <tbody>
                <tr class="spaceUnder">
                    <td class="h6"><strong>Full Name</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.customer.firstName + " " + ticket.customer.lastName}</td>
                    <td> </td>
                    <td class="h6"><strong>Departure Date</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.flight.departureDate}</td>
                </tr>

                <tr class="spaceUnder">
                    <td class="h6"><strong>From</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.flight.fromAirport.name}</td>
                    <td> </td>
                    <td class="h6"><strong>Seat</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.seat.seat_Id}</td>
                </tr>

                <tr class="spaceUnder">
                    <td class="h6"><strong>To</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.flight.toAirport.name}</td>
                    <td> </td>
                    <td class="h6"><strong>Flight</strong></td>
                    <td> </td>
                    <td class="h5">${ticket.flight.airplane.name}</td>
                </tr>

                <tr class="spaceUnder">
                <td class="h6"><strong>Lugguage</strong></td>
                <td> </td>
                <td class="h5">${ticket.signedluggage != null ? ticket.signedluggage.name : "None"}</td>
            </tr>

            </tbody>
        </table>`

            htmlStrFooterDetail += `
            <div class="text-left pull-right col-md-3">Price Total: <br />
            <span class="h3 text-muted"><strong>${formatVND(ticket.ticket_PriceTotal)} VND</strong></span>
            </div>
            <div class="text-center col-md-12">
            <a target="_blank" href='./ticket-print?ticket_Id=${ticket.ticket_Id}' class="btn btn-primary">Print Ticket</a>           
            </div>
            `

            $("#detail-modal-header").html(htmlStrHeaderDetail);
            $("#detail-modal-body").html(htmlStrBodyDetail);
            $("#detail-modal-footer").html(htmlStrFooterDetail);

        }
    })


});

$('#tbodyData').on('click', '#btnEdit', function () {   
    
    ticketList.forEach(ticket => {
        if (ticket.ticket_Id == $(this).data("id")) {

            $("#inpSeatClass").val("")
            $("#inpSeatClass").html("")
            loadBookedSeat(ticket.flight.flight_Id);
            for (let i = 0; i < emptySeatList.length; i++) {
                $("#inpSeatClass").append(`<option value='${emptySeatList[i].seat_Id}'>${emptySeatList[i].seat_Id}</option>`)
            }         
            $("#inpSeatClass").append(`<option value='${ticket.seat.seat_Id}'>${ticket.seat.seat_Id}</option>`)
            $("#inpSeatClass").val(ticket.seat.seat_Id)
            $("#inpTicket_Id").val(ticket.ticket_Id);
            $("#inpFirstName").val(ticket.customer.firstName);
            $("#inpLastName").val(ticket.customer.lastName);
            $("#inpAddress").val(ticket.customer.address);
            $("#inpBirthday").val(ticket.customer.birthDay);
            $("#inpCustomerClass").val(ticket.customer.customer_Id)
            $("#inpBookingClass").val(ticket.booking.booking_Id)
            $("#inpFlightClass").val(ticket.flight.flight_Id)
            $("#inpLuggageClass").val(ticket.signedluggage != null ? ticket.signedluggage.signedLuggage_Id : 0)
            $("#inpIdCustomer").val(ticket.customer.customer_Id)
            
        }
    })


});


$('body').on('click', '#btnUpdate', function () {

    var signedluggage;

    if($("#inpLuggageClass").val() != 0){
        signedluggage = {
            signedLuggage_Id: parseInt($("#inpLuggageClass").val())
        }
    }else{
        signedluggage = null;
    }

    let ticketForUpdate = {
        ticket_Id: parseInt($("#inpTicket_Id").val()),
        ticket_PriceTotal: 0,
        booking: {
            booking_Id: parseInt($("#inpBookingClass").val())
        },
        customer: {
            customer_Id : $("#inpIdCustomer").val(),
            firstName: $("#inpFirstName").val(),
            lastName: $("#inpLastName").val()
        },
        flight: {
            flight_Id: parseInt($("#inpFlightClass").val())
        },
        seat: {
            seat_Id: $("#inpSeatClass").val()
        },
        signedluggage: signedluggage

    };

    console.log(ticketForUpdate);

    if($("#inpFirstName").val() == '' || $("#inpLastName").val() == ''){
        alert("Do not empty blank")
    }else{
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/tickets/" + $("#inpTicket_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(ticketForUpdate),
            dataType: "json",
            success: function (response) {
                $('.close').click();
                $('.successToast').toast('show');
                ticketList = [];
                loadTicketList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }

});

$('#tbodyData').on('click', '#btnDelete', function () {
    if (confirm(`You want to delete Ticket with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/tickets/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function (response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});

$('#updateTicketModal').on("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});