var cityList = []
//func lam moi thanh pho
function loadCityList() {
	$.ajax({
		url: "/FlightTicketManagement/api/cities",
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",
		success: function (response) {
			var htmlStr = ``;
			// lap qua ket qua tra ve & tao html theo mong muon
			response.forEach(value => {
				cityList.push(value)
				var cityId = value.city_Id;
				var cityName = value.name;
				htmlStr = htmlStr + `<tr class=${cityId}><td>${cityId}</td>
                <td>${cityName}</td>
                <td><button id="btnEdit" data-id=${cityId} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateCityModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${cityId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                </td></tr>`;
			});
			// console.log(cityList)
			//hien thi len
			$("#tbodyData").html(htmlStr);

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
}

loadCityList();



//su kien nut Add thanh pho
$('body').on('click', '#btnAdd', function () {

	//check id
	if(checkCityIDExists(cityList,  $("#cityCode").val())){
		return;
	}

	// check exists
	if (checkCityExists(cityList, $("#cityName").val())) {
		return;
	}

	// check validate
	$.ajax({
		url: "/FlightTicketManagement/api/cities",
		async: false,
		type: "post",
		contentType: "application/json; charset=utf-8",
		async: false,
		dataType: "json",
		// du lieu truyen vao dang json 
		data: JSON.stringify({ "city_Id": $("#cityCode").val(), "name": $("#cityName").val() }),
		success: function (response) {
			$("#cityCode").val("");
			$("#cityName").val("");
			$('.successToast').toast('show');
			console.log(response);
			cityList = []
			loadCityList();
		},
		// error: function(response) {
		// 	// $('.failedToast').children('.toast-body').html('Unsuccessful')
		// 	console.log(response);
		// }
		error: function (response) {
			console.log(response)
			let errorHtml = ``;
			Object.entries(response.responseJSON).forEach(([key, value]) => errorHtml += `<li>${value}</li>`)
			console.log(response.responseJSON)
			$('.failedToast').children('.toast-body').html(errorHtml)
			$('.failedToast').toast('show');
		}
	});
});


var cityID;

//su kien nut Edit thanh pho
$('#tbodyData').on('click', '#btnEdit', function () {
	cityID = $(this).data('id');
	$('#inpCityID').val($(this).data('id'));
	var cityName = $(this).closest('tr').children('td').eq(1).text(); // Lay ten thanh pho hien thi len inpCityName
	$("#inpCityName").val(cityName);
});

//su kien nut Update thanh pho
$('body').on('click', '#btnUpdate', function () {
	// check id
	


	// check name exists
	if (checkCityExists(cityList, $("#inpCityName").val())) {
		return;
	}
	$.ajax({
		url: "/FlightTicketManagement/api/cities/id",
		contentType: "application/json",
		async: false,
		type: "put",
		data: JSON.stringify({ "city_Id": cityID, "name": $("#inpCityName").val() }),
		success: function (response) {
			// cityList.push(data)
			$('.close').click();
			$('.successToast').toast('show');
			cityList = []
			loadCityList();
			$("#inpCityName").val('');
		},
		error: function (response, textStatus, errorThrown) {
			console.log(response)
			let errorHtml = ``;
			Object.entries(response.responseJSON).forEach(([key, value]) => errorHtml += `<li>${value}</li>`)
			$('.failedToast').children('.toast-body').html(errorHtml)
			$('.failedToast').toast('show');
			console.log(textStatus, errorThrown);
		}
	});
});

$('#updateCityModal').on("keyup", function (event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		$('#btnUpdate').click();
	}
});

//su kien nut Delete thanh pho
$('#tbodyData').on('click', '#btnDelete', function () {
	// console.log($(this).data('id'));
	if (confirm(`You want to delete ${$(this).data('id')} city?`)) {
		// get class name cua the <tr> muon xoa   
		var rawstrClass = $(this).closest('tr').attr('class');
		var strClass = '';
		console.log(rawstrClass.split(" "));
		for (var i = 0; i < rawstrClass.split(" ").length; i++) {
			strClass = strClass + '.' + rawstrClass.split(" ")[i];
		}

		$.ajax({
			//  $(this).data('id') = ma thanh pho
			url: "/FlightTicketManagement/api/cities/" + $(this).data('id'),
			contentType: "application/json",
			async: false,
			type: "delete",
			success: function (response) {
				$('.successToast').toast('show');
				//xoa the <tr>
				$("tr").remove(strClass);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				$('.failedToast').children('.toast-body').html('Unsuccessful')
				$('.failedToast').toast('show');
				console.log(textStatus, errorThrown);
			}
		});
	} else { }
});

function checkCityExists(cityList, cityName) {
	for (var element of cityList) {
		if (element.name.toLowerCase() == cityName.toLowerCase()) {
			console.log(element)
			if (element.city_Id != cityID) {
				htmlStr = `${cityName} has already exists`
				$('.failedToast').children('.toast-body').html(htmlStr)
				$('.failedToast').toast('show');
				return true;
			} else {
				return false;
			}
		}
	}
}

function checkCityIDExists(cityList, cityID) {
	for (var element of cityList) {
		if (element.city_Id.toLowerCase() == cityID.toLowerCase()) {
			htmlStr = `City already exists with ID: ${cityID}`
			$('.failedToast').children('.toast-body').html(htmlStr)
			$('.failedToast').toast('show');
			return true;
		}
	}
	return false;
}