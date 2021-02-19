
$.ajax({
	url: "/FlightTicketManagement/api/citys",
	async: false,
	data: $("#loginform").serialize(),
	success: function(response) {
		var htmlStr = ``;
		$(response).find("item").each(function() {
			var cityId = $(this).find("city_Id").text();
			var cityName = $(this).find("name").text();
			htmlStr = htmlStr + `<tr><td>${cityId}</td><td>${cityName}</td><td><button id="btnEdit" data-id=${cityId} type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>&nbsp<button id="btnDelete" data-id=${cityId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button></td></tr>`;
/*			console.log(`Hello ${cityName}`);
			console.log(cityId + " " + cityName);*/
		});
		$("#loadCity").html(htmlStr);

	},
	error: function(jqXHR, textStatus, errorThrown) {
		console.log(textStatus, errorThrown);
	}
});

$('#loadCity').on('click','#btnEdit',function(){
	console.log($(this).data('id'));
	$.ajax({
	url: "/FlightTicketManagement/api/citys",
	async: false,
    type: "post",
	data: "city_Id=Hue&name=tpHue",
	success: function(response) {
			console.log(response);
	
	},
	error: function(jqXHR, textStatus, errorThrown) {
		console.log(textStatus, errorThrown);
	}
});
});


$('#loadCity').on('click','#btnDelete',function(){
	console.log($(this).data('id'));
});
