taxList = [];
totalTaxPrice = 0;
travelClassList = [];
flightList = [];
signedLuggageList = [];
index = 1;
customerList = [];

function loadSignedLuggageList() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, value) {
                signedLuggageList.push(value);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}
loadSignedLuggageList()

function loadTravelClasses() {
    $.ajax({
        url: "/FlightTicketManagement/api/travelclasses",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, value) {
                travelClassList.push(value);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}
loadTravelClasses();

function loadTaxes() {
    $.ajax({
        url: "/FlightTicketManagement/api/taxes",
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {
            $.each(response, function(index, value) {
                taxList.push(value);
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
loadTaxes();


function loadSearchResults() {
    $.ajax({
        url: "/FlightTicketManagement/api/search?" + $("#url").val(),
        async: false,
        type: "get",
        dataType: "JSON",
        success: function(response) {

            var htmlStr = '';
            for (var i = 0; i < response.length; i++) {
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
loadSearchResults();

$('body').on('click', '#choose', function() {
    let flight;
    for (var i = 0; i < flightList.length; i++) {
        if (flightList[i].flight_Id == $(this).data('id')) {
            flight = flightList[i];
            break;
        }
    }
    let htmlStr =
        `<tr>
        <td>${flight.fromAirport.city.name} (${flight.fromAirport.city.city_Id})</td>
        <td>${flight.departureDate}</td>
        <td>${flight.fromAirport.name} (${flight.fromAirport.airport_Id})</td>
    </tr>
    <tr>
        <td>${flight.toAirport.city.name} (${flight.toAirport.city.city_Id})</td>
        <td>${flight.arrivalDate}</td>
        <td>${flight.toAirport.name} (${flight.toAirport.airport_Id})</td>
    </tr>
    <tr>
        <td>${flight.airplane.name}</td>
        <td>Tax Price: ${totalTaxPrice} VND</td>
        <td>Basic Price: ${flight.flight_Price} VND</td>
    </tr>`;

    let number = parseInt($("#url").val().substring($("#url").val().lastIndexOf("=") + 1));
    let htmlStrCusInfo = ``;
    let htmlStrCusDDL = `select id = "CUS" >`;

    let htmlStrDDL = `<select class="signedLuggage form-control">`;
    signedLuggageList.forEach(signedLuggage => {
        htmlStrDDL +=
            `<option value="${signedLuggage.signedLuggage_Id}">
            ${signedLuggage.name} -
            ${signedLuggage.weight} KG -
            ${signedLuggage.signedluggagePrices[0].price} VND
        </option>`
    })
    htmlStrDDL += '</select>';

    for (var i = 0; i < number; i++) {
        htmlStrCusInfo +=
            `
        <h6>Passenger ${i+1}:<h6>
        <div class="customerInfo">
            <div class="row name">
                <div class="col-sm-6">
                    <input class="firstName form-control mb-2" type="text" placeholder="First Name" name="cusFristName${i+1}">
                </div>
                <div class="col-sm-6 mb-2">
                    <input class="lastName form-control" type="text" placeholder="Last Name" name="cusLastName${i+1}">
                </div>
            </div>
            <div class="row birthdayAndSignedLuggage">
                <div class="col-sm-6">
                    <input class="birthday form-control" max="9999-01-01" type="date">
                </div>
                <div class="col-sm-6">
                    ${htmlStrDDL}  
                </div>
            </div>
        </div>`;
    }

    $('#customerData').html(htmlStrCusInfo);
    $('#tbodyModalData').html(htmlStr);
    console.log(flight)
})

$("#btnNext").on("click", function() {
    if (index < 3) {
        index++;
    }

    if (index == 3) {
        customerList = [];



        let htmlStrSeat = ``;
        $(".customerInfo").each(function(index) {
            let customer = {
            	cus_Id:index,
                firstName: $(this).find(".firstName").val(),
                lastName: $(this).find(".lastName").val(),
                birthDay: $(this).find(".birthday").val(),
                signedLuggage: $(this).find(".signedLuggage").val(),
                seat_Id: ""
            };
            htmlStrSeat +=
                `<option value="${index}">
                    ${customer.firstName} ${customer.lastName}
                </option>`
            customerList.push(customer)
        })
        console.log(JSON.stringify(customerList))
            // #customerListDDL
        $("#customerListDDL").html(htmlStrSeat);
    }


})

$("#btnPrev").on("click", function() {
    if (index > 1)
        index--;
    console.log(index)
})

$('.carousel').carousel({
    interval: false,
    wrap: false
});