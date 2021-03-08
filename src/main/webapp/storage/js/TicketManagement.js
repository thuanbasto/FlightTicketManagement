var ticketList = [];
var action = ""; // check update or add
var price = ""; // check update price by comparing prices

//func load tax
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
                var signedLuggage = value.signedluggage.name;
                var taxs = "";
                value.taxs.forEach(e => {
                    taxs += " - " + e.taxName
                });
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
                    <td>${signedLuggage}</td>
                    <td>${taxs}</td>
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
// load Customer class data
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

var flightList = [];
// load Customer class data
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
// load Customer class data
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
// load Customer class data
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
// load Customer class data
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
loadFlightClassList();
loadSeatClassList();
loadLuggageClassList();
loadTaxesClassList()
loadTicketList();

// edit seat
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
            $("#inpFlightClass").val(response.flight.flight_Id)
            $("#inpSeatClass").val(response.seat.seat_Id)
            $("#inpLuggageClass").val(response.signedluggage.signedLuggage_Id)
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

    // set title for modal popup
    $(".modal-title").html("Edit Ticket");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding seat
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpTicket_Id").val("");
    $("#inpCustomerClass").val("");
    $("#inpFlightClass").val("");
    $("#inpSeatClass").val("");
    $("#inpLuggageClass").val("");
    $("#form-check-tax input").each(function () {
        $(this).prop('checked', false);
    })
    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add seat");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});

$('body').on('click', '#btnUpdate', function() {
    let ticket = {
        ticket_PriceTotal: 0,
        "customer_Id": 3,
        "flight_Id": 2,
        "seat_Id": "1",
        "signedLuggage_Id": 16,
        "tax_Id" : [1,2,3,4] 
    };

    console.log(seat);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/seats/" + $("#inpSeat_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(seat),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                seatList = []; // empty the old list to add a new one
                loadSeatList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/seats",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(seat),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                seatList = []; // empty the old list to add a new one
                loadSeatList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }
});

$('#updateTicketModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});