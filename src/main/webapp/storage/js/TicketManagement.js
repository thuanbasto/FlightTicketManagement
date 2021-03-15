var ticketList = [];
var action = "";
var price = "";

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
                var signedLuggage = value.signedluggage != null ? value.signedluggage.name : "";
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
                    <td>${price}</td>
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

function loadSeatClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/seats",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (index, value) {
                seatList.push(value);
                seat = value.seat_Id + " - " + value.travelClass.name
                $("#inpSeatClass").append(`<option value='${value.seat_Id}'>${seat}</option>`)
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

var luggageList = [];

function loadLuggageClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        method: "GET",
        success: function (response) {
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



loadBookingClassList();
loadFlightClassList();
loadSeatClassList();
loadLuggageClassList();
loadTaxesClassList();
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

            </tbody>
        </table>`

            htmlStrFooterDetail += `
            <div class="text-left pull-right col-md-3">Price Total: <br />
            <span class="h3 text-muted"><strong>${ticket.ticket_PriceTotal} VND</strong></span>
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

    // fill input with value
    ticketList.forEach(ticket => {
        if (ticket.ticket_Id == $(this).data("id")) {
            $("#form-check-tax input").each(function () {
                $(this).prop('checked', false);
            })
            $("#inpTicket_Id").val(response.ticket_Id);
            $("#inpCustomerClass").val(response.customer.customer_Id)
            $("#inpBookingClass").val(response.booking.booking_Id)
            $("#inpFlightClass").val(response.flight.flight_Id)
            $("#inpSeatClass").val(response.seat.seat_Id)
            $("#inpLuggageClass").val(response.signedluggage != null ? response.signedluggage.signedLuggage_Id : "")
            response.taxs.forEach(tax => {
                $("#form-check-tax input").each(function () {
                    if ($(this).val() == tax.tax_Id) {
                        $(this).prop('checked', true);
                    }
                })
            })
        }
    })



    action = "update";


    // $(".modal-title").html("Edit Ticket");

    // $("#btnUpdate").html("Update");
});


$('body').on('click', '#btnUpdate', function () {

    let taxs_Id = []

    $("#form-check-tax input").each(function () {
        if ($(this).is(":checked")) {
            var tax_Id = {
                "tax_Id": parseInt($(this).val())
            }
            taxs_Id.push(tax_Id)
        }
    })

    let ticketForUpdate = {
        ticket_Id: parseInt($("#inpTicket_Id").val()),
        ticket_PriceTotal: 0,
        booking: {
            booking_Id: parseInt($("#inpBookingClass").val())
        },
        customer: {
            address: $("#inpAddress").val(),
            birthDay: $("#inpBirthday").val(),
            firstName: $("#inpFirstName").val(),
            lastName: $("#inpLastName").val()
        },
        flight: {
            flight_Id: parseInt($("#inpFlightClass").val())
        },
        seat: {
            seat_Id: $("#inpSeatClass").val()
        },
        signedluggage: {
            signedLuggage_Id: parseInt($("#inpLuggageClass").val())
        },

        taxs: taxs_Id
    };

    console.log(ticketForUpdate);


    if (action == "update") {
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
            error: function (jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
        console.log(ticketForUpdate);
    } else if (action == "add") {
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/ticket",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(ticketForAdd),
            dataType: "json",
            success: function (response) {
                $('.close').click();
                $('.successToast').toast('show');
                ticketList = []; // 
                loadTicketList();
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
            error: function (jqXHR, textStatus, errorThrown) {
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