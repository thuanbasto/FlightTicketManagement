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
                        <button id="btnDelete" data-id=${id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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


var CustomerList = [];

function loadCustomerClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/customers",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $.each(response, function (index, value) {
                CustomerList.push(value);
                Customer = value.customer_Id + "-" + value.firstName + " " + value.lastName
                $("#inpCustomerClass").append(`<option value='${value.customer_Id}'>${Customer}</option>`)
            });
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



loadCustomerClassList();
loadBookingClassList()
loadFlightClassList();
loadSeatClassList();
loadLuggageClassList();
loadTaxesClassList()
loadTicketList();


$('#tbodyData').on('click', '#btnEdit', function () {

    $.ajax({
        method: "GET",
        url: "/FlightTicketManagement/api/ticket/" + $(this).data('id'),
        async: false,
        success: function (response) {
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
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });

    action = "update";


    $(".modal-title").html("Edit Ticket");

    $("#btnUpdate").html("Update");
});


$('#btnAdd').on('click', function() {
    $("#inpTicket_Id").val("");
    $("#inpCustomerClass").val("");
    $("#inpBookingClass").val("")
    $("#inpFlightClass").val("");
    $("#inpSeatClass").val("");
    $("#inpLuggageClass").val("");
    $("#form-check-tax input").each(function () {
        $(this).prop('checked', false);
    })
    action = "add";


    $(".modal-title").html("Add seat");

    $("#btnUpdate").html("Add");
});

$('body').on('click', '#btnUpdate', function() {

    let tax_Id = []

    $("#form-check-tax input").each(function () {
        if($(this).is(":checked")){
        tax_Id.push($(this).val()) 
        }
    })

    console.log(tax_Id);

    let ticketForAdd = {
        ticket_PriceTotal: 0,
        customer_Id:  $("#inpCustomerClass").val(),
        booking_Id: $("#inpBookingClass").val(),
        flight_Id:  $("#inpFlightClass").val(),
        seat_Id: $("#inpSeatClass").val(),
        signedLuggage_Id:  $("#inpLuggageClass").val(),
        tax_Id : tax_Id
    };

    let ticketForUpdate = {
        ticket_Id: $("#inpTicket_Id").val(),
        ticket_PriceTotal: 0,
        customer_Id:  $("#inpCustomerClass").val(),
        booking_Id: $("#inpBookingClass").val(),
        flight_Id:  $("#inpFlightClass").val(),
        seat_Id: $("#inpSeatClass").val(),
        signedLuggage_Id:  $("#inpLuggageClass").val(),
        tax_Id : tax_Id
    };


    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/ticket/" + $("#inpTicket_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(ticketForUpdate),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                ticketList = []; 
                loadTicketList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/ticket",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(ticketForAdd),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                ticketList = []; // 
                loadTicketList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }
});

$('#tbodyData').on('click', '#btnDelete', function() {
    console.log("xoa");
    if (confirm(`You want to delete Ticket with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/ticket/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function(response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});

$('#updateTicketModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});