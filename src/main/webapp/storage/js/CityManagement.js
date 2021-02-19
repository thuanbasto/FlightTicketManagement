function loadCityList() {
	$.ajax({
		url: "/FlightTicketManagement/api/cities",
		async: false,
		data: $("#loginform").serialize(),
		success: function(response) {
			var htmlStr = ``;
			$(response).find("item").each(function() {
				var cityId = $(this).find("city_Id").text();
				var cityName = $(this).find("name").text();
				htmlStr = htmlStr + `<tr><td>${cityId}</td><td>${cityName}</td><td><button id="btnEdit" data-id=${cityId} type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal"><i class="fas fa-edit"></i></button>&nbsp<button id="btnDelete" data-id=${cityId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button></td></tr>`;
				/*			console.log(`Hello ${cityName}`);
							console.log(cityId + " " + cityName);*/
			});
			$("#loadCity").html(htmlStr);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
}

loadCityList();

$('body').on('click', '#btnAdd', function() {
	if ($("#cityCode").val() == '' || $("#cityName").val() == '') {
		alert('Khong duoc chua trong!');
	} else {
		$.ajax({
			url: "/FlightTicketManagement/api/cities",
			contentType: "application/json",
			async: false,
			type: "post",
			data: JSON.stringify({ "city_Id": $("#cityCode").val(), "name": $("#cityName").val() }),
			success: function(response) {
				console.log(response);
				loadCityList();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	}

});


var cityID ; 

$('#loadCity').on('click', '#btnEdit', function() {
	console.log($(this).data('id'));
	cityID = $(this).data('id');
	$('#inpCityID').val($(this).data('id'));
});

$('body').on('click', '#btnUpdate', function() {
	console.log({ "city_Id": cityID, "name": $("#inpCityName").val() });

	$.ajax({
		url: "/FlightTicketManagement/api/cities/id",
		contentType: "application/json",
		async: false,
		type: "put",
		data: JSON.stringify({ "city_Id": cityID, "name": $("#inpCityName").val() }),
		success: function(response) {
			$('.close').click();
			loadCityList();
			$("#inpCityName").val('');	
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
});

$('#myModal').on("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		$('#btnUpdate').click();
	}
});

$('#loadCity').on('click', '#btnDelete', function() {
	console.log($(this).data('id'));
	if (confirm(`You want to delete ${$(this).data('id')} city?`)) {
		// Save it!
		$.ajax({
			url: "/FlightTicketManagement/api/cities/" + $(this).data('id'),
			contentType: "application/json",
			async: false,
			type: "delete",
			success: function(response) {
				/*				console.log(response);*/
				loadCityList();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	} else {
	}


});
