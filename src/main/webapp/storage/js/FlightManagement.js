var action = "";
var airplaneList = [];
var airportList = [];


// load flight func
function loadFlights() {
	$.ajax({
		url: "/FlightTicketManagement/api/flights",
		async: false,
		success: function(response) {
			var htmlStr = ``;
			$(response).find("item").each(function() {
				var flightId = $(this).find("flight_Id").text();
				var arrivalDate = $(this).find("arrivalDate").text();
				var departureDate = $(this).find("departureDate").text();
				var price = $(this).find("flight_Price").text();
				var airplane = $(this).find("airplane").find("name").text();
				var fromAirport = $(this).find("fromAirport").find("name").first().text();
				var toAirport = $(this).find("toAirport").find("name").first().text();

				htmlStr = htmlStr + `<tr><td>${flightId}</td>
				 <td>${fromAirport}</td>
                <td>${toAirport}</td>
                <td>${arrivalDate}</td>
                <td>${departureDate}</td>
                <td>${price}</td>
                <td>${airplane}</td>
                <td><button id="btnEdit" data-id=${flightId} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateFlightModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${flightId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                </td></tr>`
			});
			$("#tbodyData").html(htmlStr);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}

	})
}

loadFlights();

// delete flight 

$('#tbodyData').on('click', '#btnDelete', function() {
	// console.log($(this).data('id'));
	if (confirm(`You want to delete flight with id = ${$(this).data('id')}?`)) {
		let tr = $(this).closest('tr');
		$.ajax({
			method: "DELETE",
			url: "/FlightTicketManagement/api/flights/" + $(this).data('id'),
			contentType: "application/json",
			async: false,
			success: function(resp) {
				tr.remove();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				// $('.failedToast').toast('show');
				console.log(textStatus, errorThrown);
			}
		});
	} else { }
});


// load airport list
function loadAirportList() {
	$.ajax({
		url: "/FlightTicketManagement/api/airports",
		async: false,
		success: function(response) {
			var htmlStr = ``;
			// lap qua ket qua tra ve & tao html theo mong muon
			$(response).find("item").each(function(value) {
				airportList.push(value)
				var airportId = $(this).find("airport_Id").text();
				var airportName = $(this).find("name").first().text();
				htmlStr = htmlStr + `<option value=${airportId}>${airportName}</option>`;
			});
			//hien thi len
			$(".loadAirport").html(htmlStr);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
}


// load airplane list
function loadAirplaneList() {
	$.ajax({
		url: "/FlightTicketManagement/api/airplanes",
		async: false,
		success: function(response) {
			var htmlStr = ``;
			// lap qua ket qua tra ve & tao html theo mong muon
			$(response).find("item").each(function() {
				var airplaneId = $(this).find("airplane_Id").text();
				var airportName = $(this).find("name").text();
				htmlStr = htmlStr + `<option value=${airplaneId}>${airportName}</option>`;
			});
			//hien thi len
			$("#selAirplane").html(htmlStr);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
}
loadAirplaneList()
loadAirportList()

// show  add form
$('#btnAdd').on('click', function() {

	// hide error alert
	$("#alertToAirport").hide()
	$("#alertDepartureDate").hide()


	// make empty input
	$("#inpArrivalDate").val("")
	$("#inpFlight_Id").val("")
	$("#inpDepartureDate").val("")
	$("#inpPrice").val("")

	action = "add";

	// set title for modal popup
	$(".modal-title").html("Add flight");
	// set button name for modal popup
	$("#btnUpdate").html("Add");
});


// show edit form
$('#tbodyData').on('click', '#btnEdit', function() {

//	loadAirplaneList()
//	loadAirportList()

	// hide error alert
	$("#alertToAirport").hide()
	$("#alertDepartureDate").hide()

	$.ajax({
		method: "GET",
		url: "/FlightTicketManagement/api/flights/" + $(this).data('id'),
		async: false,
		success: function(response) {
			console.log($(response).find("departureDate").text())
			$("#inpFlight_Id").val($(response).find("flight_Id").text())
			$("#inpArrivalDate").val($(response).find("arrivalDate").text().replace(" ","T"))
			$("#inpDepartureDate").val($(response).find("departureDate").text().replace(" ","T"))
			$("#inpPrice").val($(response).find("flight_Price").text())
			$("#selFromAirport option").each(function() {
				if ($(this).val() == $(response).find("fromAirport").find("airport_Id").text()) {
					$(this).attr("selected", "selected");
				}
			})
			$("#selToAirport option").each(function() {
				if ($(this).val() == $(response).find("toAirport").find("airport_Id").text()) {
					$(this).attr("selected", "selected");
				}
			})
			$("#selAirplane option").each(function() {
				if ($(this).val() == $(response).find("airplane").find("airplane_Id").text()) {
					$(this).attr("selected", "selected");
				}
			})
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
	action = "update";
	// set title for modal popup
	$(".modal-title").html("Edit Flight");
	// set button name for modal popup
	$("#btnUpdate").html("Update");
});





// add and update flight
$('body').on('click', '#btnUpdate', function() {

	// format yyyy-MM-ddTHH:mm -> yyyy-MM-dd HH:mm:ss
	let _arrivalDate = $("#inpArrivalDate").val().replace("T"," ").concat(":00");
	let _departureDate = $("#inpDepartureDate").val().replace("T"," ").concat(":00");

	// check airport
	if($("#selFromAirport").val() == $("#selToAirport").val()){
		// alert("The departure airport must be different from the arrival airport!");
		$("#alertToAirport").show();
		return;
	}else{
		$("#alertToAirport").hide();
	}
	if(new Date($("#inpArrivalDate").val()) >= new Date($("#inpDepartureDate").val())){
		// alert("The departure Date must be greater than the arrival Date!");
		$('#alertDepartureDate').show();
		return;
	}else{
		$("#alertDepartureDate").hide();
	}

	let flight = {
		flight_Id: $("#inpFlight_Id").val(),
		arrivalDate: _arrivalDate,
		departureDate: _departureDate,
		flight_Price: $("#inpPrice").val(),
		airplane: {
			airplane_Id: $("#selAirplane").val()
		},
		fromAirport: {
			airport_Id: $("#selFromAirport").val(),
		},
		toAirport: {
			airport_Id: $("#selToAirport").val()
		}
	};

	if (action == "update") {
		$.ajax({
			method: "PUT",
			url: "/FlightTicketManagement/api/flights/" + $("#inpFlight_Id").val(),
			contentType: "application/json",
			async: false,
			data: JSON.stringify(flight),
			dataType: "json",
			success: function(response) {
				$('.close').click();
				$('.successToast').toast('show');
				loadFlights();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('.failedToast').toast('show');
				console.log(textStatus, errorThrown);
			}
		});
	} else if (action == "add") {
		$.ajax({
			method: "POST",
			url: "/FlightTicketManagement/api/flights",
			contentType: "application/json",
			async: false,
			data: JSON.stringify(flight),
			
			dataType: "json",
			success: function(response) {
				$('.close').click();
				$('.successToast').toast('show');
				loadFlights();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('.failedToast').toast('show');
				console.log(textStatus, errorThrown);
			}
		});
	}
});

$('#updateFlightModal').on("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		$('#btnUpdate').click();
	}
});