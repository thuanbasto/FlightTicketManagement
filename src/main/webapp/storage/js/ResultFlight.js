var taxList = [];
var travelClassList = [];
var flightList = [];
var signedLuggageList = [];
var seatList = [];
var bookedSeatList = [];
var customerList = [];
var ticketList = [];
var chosenFlight = {};
var bookingList = [];
var chosenBooking = {}; 

var choose = false; // check if false create customer field
var flight_Id = 0;
var totalTaxPrice = 0;
var index = 1;


loadSeats();
loadSignedLuggages();
loadTravelClasses();
loadTaxes();
loadSearchResults();


function loadSignedLuggages() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, ticket) {
                signedLuggageList.push(ticket);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}
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
}

function loadTravelClasses() {
    $.ajax({
        url: "/FlightTicketManagement/api/travelclasses",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, ticket) {
                travelClassList.push(ticket);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

function loadTaxes() {
    $.ajax({
        url: "/FlightTicketManagement/api/taxes",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, ticket) {
                taxList.push(ticket);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }

    })
    taxList.forEach(tax => {
        totalTaxPrice += parseInt(tax.taxPrices[0].price);
    });
}

function loadBookedSeat(id) {
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
    let htmlCabin = ``;
    let count = 0; // check if count = 6 break row
    seatList.forEach(seat => {
        // check seat is booked ?
        let booked = false;
        for (let i = 0; i < bookedSeatList.length; i++){
            if (seat.seat_Id == bookedSeatList[i].seat_Id) {
                booked = true;
                break;
            }
        }

        if (count == 0) htmlCabin += `<li class="row row_ct"> <ol class="seats" type="A">`

        htmlCabin += `<li class="seat">
                        <input type="checkbox" ${booked == true ? "disabled" : ""} id="${seat.seat_Id}" />
                        <label for="${seat.seat_Id}">${seat.seat_Id}</label>
                    </li>`
                    
        if (count == 5) {
            htmlCabin += `</ol></li>`
            count = -1;
        }
        count ++;
    })

    $(".cabin").html(htmlCabin)
}

function loadSearchResults() {
    $.ajax({
        url: "/FlightTicketManagement/api/search?" + $("#url").val(),
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {

            var htmlStr = '';
            for (var i = 0; i < response.length; i++) {
                bookingList.push({
                    ticketList : ticketList,
                    flight_Id : response[i].flight_Id
                });

                flightList.push(response[i]);
                htmlStr +=
                    `<tr>
                <td>${response[i].flight_Id}</td>
                <td>${response[i].departureDate}</td>
                <td>${response[i].arrivalDate}</td>
                <td>-----</td>
                <td>
                	<button id="choose" data-id="${response[i].flight_Id}" class="btn btn-outline-warning" data-toggle="modal" data-target="#myModal">Choose</button>
                </td>
            </tr>`;

            }
            $('#tbodyData').html(htmlStr)


        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }

    })
}

$('#myModal').on('hidden.bs.modal', function () {
    bookingList.forEach(booking => {
        if (booking.flight_Id == flight_Id){
            booking.ticketList = ticketList;
        }
    })
});


$('body').on('click', '#choose', function() {
    index = 1;
    
    bookingList.forEach(booking => {
        if (booking.flight_Id == $(this).data("id")){
            ticketList = booking.ticketList;
        }
    })

    $(".carousel-inner>.carousel-item").removeClass("active");
    $(".firstCarousel").addClass("active");

    let flight;
    for (var i = 0; i < flightList.length; i++) {
        if (flightList[i].flight_Id == $(this).data('id')) {
            flight_Id = $(this).data('id');
            flight = flightList[i];
            chosenFlight = flightList[i];
            break;
        }
    }
    // load informate of flight
    let htmlFlightData =
    `<tr>
        <td><b>From:</b> ${flight.fromAirport.city.name} (${flight.fromAirport.city.city_Id})</td>
        <td><b>To</b> ${flight.departureDate}</td>
        <td><b>Flight name</b> ${flight.fromAirport.name} (${flight.fromAirport.airport_Id})</td>
    </tr>
    <tr>
        <td>${flight.toAirport.city.name} (${flight.toAirport.city.city_Id})</td>
        <td>${flight.arrivalDate}</td>
        <td>${flight.toAirport.name} (${flight.toAirport.airport_Id})</td>
    </tr>
    <tr>
        <td>${flight.airplane.name}</td>
        <td>Tax Price: ${formatVND(totalTaxPrice)}</td>
        <td>Basic Price: ${formatVND(flight.flight_Price)}</td>
    </tr>`;

    if (choose == false){
        choose = true;
        loadCustomerField();
    }

    loadBookedSeat(flight.flight_Id);

    let htmlSeatInfo = ``;
    bookingList.forEach(booking => {
        if (booking.flight_Id == $(this).data('id')){
            booking.ticketList.forEach(ticket => {
                if (ticket.seat.seat_Id != ""){
                    $(`#${ticket.seat.seat_Id}`).prop('checked', true);
                }
                htmlSeatInfo += `<h2 style='color:teal'>${ticket.customer.firstName} ${ticket.customer.lastName} (${ticket.seat.seat_Id})</h2>`
            })
        }
    })
    $("#seatInfo").html(htmlSeatInfo);

    $('#tbodyModalData').html(htmlFlightData);
    console.log(flight)
})

function loadCustomerField() {
    // load customer field
    let number = parseInt($("#url").val().substring($("#url").val().lastIndexOf("=") + 1));
    
    // add data for SignedLuggage drop down list
    let htmlSignedLuggageDDL = `<select class="signedLuggage form-control"><option value="0">Not Select</option>`;
    signedLuggageList.forEach(signedLuggage => {
        htmlSignedLuggageDDL +=
        `<option value="${signedLuggage.signedLuggage_Id}">
            ${signedLuggage.name} -
            ${signedLuggage.weight} KG -
            ${formatVND(signedLuggage.signedluggagePrices[0].price)}
        </option>`
    })
    htmlSignedLuggageDDL += '</select>';
    

    let htmlCustomerInfo = ``;
    for (var i = 0; i < number; i++) {
        htmlCustomerInfo += `
        <h6>Passenger ${i+1}:<h6>
        <div class="customerInfo">
            <div class="row name">
                <div class="col-sm-6">
                    <input class="firstName form-control mb-2" required type="text" placeholder="First Name">
                </div>
                <div class="col-sm-6 mb-2">
                    <input class="lastName form-control" required type="text" placeholder="Last Name">
                </div>
            </div>
            <div class="row birthdayAndSignedLuggage">
                <div class="col-sm-6">
                    <input class="birthday form-control" required max="9999-01-01" type="date">
                </div>
                <div class="col-sm-6">
                    ${htmlSignedLuggageDDL}  
                </div>
            </div>
        </div>`;
    }
    $('#customerData').html(htmlCustomerInfo);
}

$("#btnCheckNext").on("click", function() {
    if (index == 2){
        if ($("#email").val() != "" && $("#phone").val() != ""){
            let check = true;
            $(".firstName").each(function() {
                if ($(this).val() == "" || $(this).val() == null) {
                    check = false;
                }
            });
            $(".lastName").each(function() {
                if ($(this).val() == "" || $(this).val() == null) {
                    check = false;
                }
            });
            $(".birthday").each(function() {
                if ($(this).val() == "" || $(this).val() == null || $(this).val().length != 10) {
                    check = false;
                }
            });
            if (check){
                index++;
                $("#btnNext").click();
            }
            else alert("Please fill full field")
        } else {
            alert("Please fill full field")
        }
    } else if (index < 4) {
        if (index == 1) $("#btnNext").click();
        index++;
    }

    if (index == 3) {
        if (ticketList.length == 0)
            loadChooseSeat();
        $("#btnNext").click();
    }

    if (index == 4) {
        loadPay();
        $("#btnNext").click();
    }
})

$("#btnPrev").on("click", function() {
    if (index > 1){
        index--;
    }
})  

function loadChooseSeat() {
    customerList = [];
    ticketList = [];
    
    let htmlCustomer = ``;
    $(".customerInfo").each(function(i) {
        let price = 0;
        signedLuggageList.forEach(signedLuggage =>{
            if (signedLuggage.signedLuggage_Id == $(this).find(".signedLuggage").val())
                price = signedLuggage.signedluggagePrices[0].price;
            else {
                price = 0;
            }
        })

        let ticket = {
            seat: {
                seat_Id: ""
            },
            signedluggage: {
                signedLuggage_Id : $(this).find(".signedLuggage").val(),
                signedluggagePrices : [{
                    price : price
                }]
            }
        }
        let customer = {
            firstName: $(this).find(".firstName").val(),
            lastName: $(this).find(".lastName").val(),
            birthDay: $(this).find(".birthday").val(),
            customer_Id : i
        };

        ticket.flight = chosenFlight;

        htmlCustomer += `<option value="${i}">${customer.firstName} ${customer.lastName}</option>`
        
        customerList.push(customer)
        ticket.customer = customer;
        ticketList.push(ticket);
    })
    // #customerListDDL
    $("#customerListDDL").html(htmlCustomer);
}

function loadPay() {
    htmlPay = ``;
    let count = 1;
    let totalBookingPrice = 0;

    bookingList.forEach(booking => {
        if (chosenFlight.flight_Id == booking.flight_Id){
            booking.phone = $("#phone").val();
            booking.email = $("#email").val();
        }
    })

    ticketList.forEach(ticket =>{

        travelClassPrice = 0;
        travelClassName = "";
        seatList.forEach(seat => {
            if (seat.seat_Id == ticket.seat.seat_Id) {
                travelClassPrice = seat.travelClass.travelClassPrices[0].price;
                travelClassName = seat.travelClass.name;
            }
        })

        totalTicketPrice = ticket.flight.flight_Price + totalTaxPrice +
            ticket.signedluggage.signedluggagePrices[0].price + travelClassPrice;
        totalBookingPrice += totalTicketPrice;

        ticket.ticket_PriceTotal = totalTicketPrice;

        htmlPay += `
        <h3>Ticket #${count++}</h3>
        <table class="table">
            <tr>
                <td><b>From:</b> ${ticket.flight.fromAirport.name}</td>
                <td><b>To:</b> ${ticket.flight.toAirport.name}</td>
                <td><b>Flight name:</b> ${ticket.flight.airplane.name}</td> 
            </tr>
            <tr>
                <td><b>Departure date:</b> ${ticket.flight.arrivalDate}</td>
                <td><b>Arrival date:</b> ${ticket.flight.departureDate}</td>
                <td><b>Seat:</b> ${ticket.seat.seat_Id} - ${travelClassName}</td>
            </tr>
        </table>
        <div class="row">
            <div class="col-sm-4" style="padding-left: 40px;">
                <h6>Flight Price: ${formatVND(ticket.flight.flight_Price)}</h6>
                <h6>Tax: ${formatVND(totalTaxPrice)}</h6>
                <h6>Travelclass Price: ${formatVND(travelClassPrice)}</h6>
                <h6>Signed Luggage: ${formatVND(ticket.signedluggage.signedluggagePrices[0].price)}</h6>
                <h6><b>Total Price:</b> ${formatVND(totalTicketPrice)}</h6>
            </div>
            <div class="col-sm-8">
                <h5>Customer Information</h5>
                <table class="table">
                    <tr>
                        <td>Mr/Mrs: ${ticket.customer.firstName} ${ticket.customer.lastName}</td>
                    </tr>
                    <tr>
                        <td>Birthday: ${ticket.customer.birthDay}</td>
                    </tr>
                </table>
            </div>
        </div>
        `
    })


    let email = "";
    let phone = "";
    bookingList.forEach(booking => {
        if (chosenFlight.flight_Id == booking.flight_Id){
            email = booking.email;
            phone = booking.phone;
        }
    })

    htmlPay = `
        <table class="table">
            <tr>
                <td><b>Email: ${email}</b></td>
                <td><b>Phone: ${phone}</b></td>
                <td><b>Total booking price: ${formatVND(totalBookingPrice)}</b></td> 
            </tr>
            </table>
    ` + htmlPay;


    $(".pay").html(htmlPay);
}


$('.carousel').carousel({
    interval: false,  // set auto next slide = false
    wrap: false // set carousel can not loop
});

function formatVND(money) {
    return (money).toLocaleString('vi', {
        style: 'currency',
        currency: 'VND',
    });
}