var taxList = [];
var travelClassList = [];
var flightList = [];
var signedLuggageList = [];
var seatList = [];
var bookedSeatList = [];
var customerList = [];
var tickets = [];
var chosenFlight = {};
var bookingList = [];
var chosenBooking = {}; 
var user = {};

var choose = false; // check if false create customer field
var flight_Id = 0;
var totalTaxPrice = 0;
var index = 1;


loadSeats();
loadSignedLuggages();
loadTravelClasses();
loadTaxes();
loadSearchResults();
getUser();

function getUser() {
    $.ajax({
        url: "/FlightTicketManagement/api/users/" + $(".username").data("username"),
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            user = response;
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

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
                    tickets : tickets,
                    flight_Id : response[i].flight_Id
                });

                flightList.push(response[i]);
                htmlStr +=
                    `<tr>
                <td>✈${response[i].flight_Id}</td>
                <td>${response[i].departureDate}</td>
                <td>${response[i].arrivalDate}</td>
                <td>-----</td>
                <td>
                    <button id="choose" data-id="${response[i].flight_Id}" class="btn btn-info" data-toggle="modal" data-target="#myModal">Choose</button>
                </td>
            </tr>`;
            }
            $('#tbodyData').html(htmlStr)
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            $(".notFound").html("<h2>Not found flight 😢</h2>")
        }

    })
}

$('#myModal').on('hidden.bs.modal', function () {
    bookingList.forEach(booking => {
        if (booking.flight_Id == flight_Id){
            booking.tickets = tickets;
        }
    })
});


$('body').on('click', '#choose', function() {
    index = 1;
    $("#btnPay").hide();

    bookingList.forEach(booking => {
        if (booking.flight_Id == $(this).data("id")){
            tickets = booking.tickets;
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
        <td><b>Departure:</b> ${flight.departureDate}</td>
        <td><b>From airport</b> ${flight.fromAirport.name} (${flight.fromAirport.airport_Id})</td>
    </tr>
    <tr>
        <td><b>To:</b> ${flight.toAirport.city.name} (${flight.toAirport.city.city_Id})</td>
        <td><b>Arrival:</b> ${flight.arrivalDate}</td>
        <td><b>To airport:</b> ${flight.toAirport.name} (${flight.toAirport.airport_Id})</td>
    </tr>
    <tr>
        <td><b>Flight name:</b> ${flight.airplane.name}</td>
        <td><b>Tax Price:</b> ${formatVND(totalTaxPrice)}</td>
        <td><b>Flight Price:</b> ${formatVND(flight.flight_Price)}</td>
    </tr>`;

    // let htmlTravelClass = `
    //     <div class="row">
    //         <div class="col-sm-4">
    //             <b>Travel class</b>
    //         </div>
    //         <div class="col-sm-8">
    //             <table class="table">
    //                 <tbody>`;
    // travelClassList.forEach(travelClass => {
    //     htmlTravelClass += `
    //         <tr>
    //             <td>${travelClass.name}: ${formatVND(travelClass.travelClassPrices[0].price)}<td>
    //         </tr>`
    // })
    // htmlTravelClass += 
    //                 `</tbody>
    //             </table>
    //         </div>
    //     </div>`

    // htmlFlightData += htmlTravelClass;

    if (choose == false){
        choose = true;
        loadCustomerField();
    }

    loadBookedSeat(flight.flight_Id);

    let htmlSeatInfo = ``;
    bookingList.forEach(booking => {
        if (booking.flight_Id == $(this).data('id')){
            booking.tickets.forEach(ticket => {
                if (ticket.seat.seat_Id != ""){
                    $(`#${ticket.seat.seat_Id}`).prop('checked', true);
                }
                htmlSeatInfo += `<h2 style='color:teal'>${ticket.customer.firstName} ${ticket.customer.lastName} (${ticket.seat.seat_Id})</h2>`
            })
        }
    })
    $("#seatInfo").html(htmlSeatInfo);

    $('#tbodyModalData').html(htmlFlightData);
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
                    <input class="firstName form-control mb-2" type="text" placeholder="First Name">
                </div>
                <div class="col-sm-6 mb-2">
                    <input class="lastName form-control" type="text" placeholder="Last Name">
                </div>
            </div>
            <div class="row birthdayAndSignedLuggage">
                <div class="col-sm-6">
                    <input class="birthday form-control" max="9999-01-01" type="date">
                </div>
                <div class="col-sm-6">
                    ${htmlSignedLuggageDDL}  
                </div>
            </div>
        </div>`;
    }
    $('#customerData').html(htmlCustomerInfo);
}

function delayButton() {
    $("#btnCheckNext").prop("disabled", true);
    setTimeout(() => {
        $("#btnCheckNext").prop("disabled", false);
    }, 1000);
    
    $("#btnCheckPrev").prop("disabled", true);
    setTimeout(() => {
        $("#btnCheckPrev").prop("disabled", false);
    }, 1000);
}

$("#btnCheckNext").on("click", function() {
    delayButton()

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
        loadChooseSeat();
        $("#btnNext").click();
    }
    $("#btnPay").hide();
    if (index == 4) {
        if (checkSeatSelection() == false) {
            index --; // if have not selected a seat
            alert("Please select a seat!")
        }
        else {
            loadPay();
            $("#btnNext").click();
            bookingList.forEach(booking => {
                if (booking.flight_Id == flight_Id){
                    booking.tickets = tickets;
                }
            })
            $("#btnPay").show();
            // $("#btnCheckNext").prop("disabled", true);
        }
    }
})

$("#btnCheckPrev").on("click", function() {
    $("#btnPay").hide();
    $("#btnPrev").click();
    delayButton()
    if (index > 1){
        // $("#btnCheckNext").prop("disabled", false);
        index--;
    }
})

function checkSeatSelection() {
    for (i in tickets) {
        if (tickets[i].seat.seat_Id == "" || tickets[i].seat.seat_Id == undefined) {
            return false;
        }
    }
    return true;
}

function loadChooseSeat() {
    customerList = [];
    
    let seats = [];
    if (tickets.length > 0){
        for (i in tickets){
            seats.push(tickets[i].seat.seat_Id)
        }
    }

    tickets = [];
    
    let htmlCustomer = ``;

    $(".customerInfo").each(function(i) {
        let price = 0;
        signedLuggageList.forEach(signedLuggage =>{
            if (signedLuggage.signedLuggage_Id == $(this).find(".signedLuggage").val())
                price = signedLuggage.signedluggagePrices[0].price;
        })

        let ticket = {
            seat: {
                seat_Id: seats[i]
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
            id : i
        };

        ticket.flight = chosenFlight;

        htmlCustomer += `<option value="${i}">${customer.firstName} ${customer.lastName}</option>`
        
        customerList.push(customer)
        tickets.push(ticket);
        ticket.customer = customer;
    })
    

    // loadSeatInfo()
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

    tickets.forEach(ticket =>{

        travelClassPrice = 0;
        travelClassName = "";
        seatList.forEach(seat => {
            if (seat.seat_Id == ticket.seat.seat_Id) {
                travelClassPrice = seat.travelClass.travelClassPrices[0].price;
                travelClassName = seat.travelClass.name;
            }
        })

        let signedLuggagePrice = ticket.signedluggage != undefined ? ticket.signedluggage.signedluggagePrices[0].price : 0;

        totalTicketPrice = ticket.flight.flight_Price + totalTaxPrice + signedLuggagePrice + travelClassPrice;
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
                        <td><b>Mr/Mrs:</b> ${ticket.customer.firstName} ${ticket.customer.lastName}</td>
                    </tr>
                    <tr>
                        <td><b>Birthday:</b> ${ticket.customer.birthDay}</td>
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
                <td><b>Email:</b> ${email}</td>
                <td><b>Phone:</b> ${phone}</td>
                <td><b>Total booking price:</b> ${formatVND(totalBookingPrice)}</td> 
            </tr>
            </table>
    ` + htmlPay;


    $(".pay").html(htmlPay);
}

$("#btnPay").on("click", function () {
    bookingList.forEach(booking => {
        if (chosenFlight.flight_Id == booking.flight_Id){
            // if not select signed luggage , delete it
            booking.tickets.forEach(ticket =>{
                if (ticket.signedluggage.signedLuggage_Id == "0"){
                    delete ticket.signedluggage;
                }
            })
            if (user.user_Id != undefined)
                booking.user_Id = user.user_Id;

            $.ajax({
                url: "/FlightTicketManagement/api/bookings",
                contentType: "application/json",
                async: false,
                method: "POST",
                dataType: "JSON",
                data: JSON.stringify(booking),
                success: function(response) {
                    console.log(response);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            })
        }
    })
})

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