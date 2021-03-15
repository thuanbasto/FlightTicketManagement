//func load danh sach may bay 
function loadAirplaneList() {
	$.ajax({
		url: "/FlightTicketManagement/api/airplanes",
		async: false,
		success: function(response) {
			var htmlStr = ``;
			// lap qua ket qua tra ve & tao html theo mong muon
			$(response).find("item").each(function() {
				var airplaneId = $(this).find("airplane_Id").text();
				var airplaneName = $(this).find("name").text();
				htmlStr = htmlStr + `<tr class=${airplaneId}><td>${airplaneId}</td>
                <td>${airplaneName}</td>
                <td><button id="btnEdit" data-id=${airplaneId} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateAirplaneModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${airplaneId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                </td></tr>`;
			});
			//hien thi len
			$("#tbodyData").html(htmlStr);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
}
loadAirplaneList();


//su kien nut Add san bay
$('body').on('click', '#btnAdd', function() {

	// check validate
	if ($("#airplaneCode").val() == '' || $("#airplaneName").val() == '') {
		alert('Khong duoc chua trong!');
	} else {
		$.ajax({
			url: "/FlightTicketManagement/api/airplanes",
			contentType: "application/json",
			async: false,
			type: "post",
			// du lieu truyen vao dang json 
			data: JSON.stringify({
				"airplane_Id": $("#airplaneCode").val(),
				"name": $("#airplaneName").val(),
			}),
			success: function(response) {
				$("#airplaneCode").val("");
				$("#airplaneName").val("");
				$('.successToast').toast('show');
				console.log(response);
				loadAirplaneList();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('.failedToast').children('.toast-body').html('Unsuccessful')
				console.log(textStatus, errorThrown);
			}
		});
	}

});


var AirplaneID;

//su kien nut Edit san bay
$('#tbodyData').on('click', '#btnEdit', function() {
	AirplaneID = $(this).data('id'); // id san bay muon edit
	$('#inpAirplaneID').val($(this).data('id')); // Lay ma may bay hien thi len inpAirportID
	var airplaneName = $(this).closest('tr').children('td').eq(1).text(); // Lay ten may bay hien thi len inpAirportName
	$("#inpAirplaneName").val(airplaneName);
});

//su kien nut Update san bay
$('body').on('click', '#btnUpdate', function() {
	$.ajax({
		url: "/FlightTicketManagement/api/airplanes/" + AirplaneID,
		contentType: "application/json",
		async: false,
		type: "put",
		data: JSON.stringify({
			"airplane_Id": $("#inpAirplaneID").val(),
			"name": $("#inpAirplaneName").val(),
		}),
		success: function(response) {
			$('.close').click();
			$('.successToast').toast('show');
			loadAirplaneList();
			$("#inpAirplaneName").val('');
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('.failedToast').children('.toast-body').html('Unsuccessful')
			console.log(textStatus, errorThrown);
		}
	});
});

$('#updateAirplaneModal').on("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		$('#btnUpdate').click();
	}
});

//su kien nut Delete san bay
$('#tbodyData').on('click', '#btnDelete', function() {
	if (confirm(`You want to delete ${$(this).data('id')} Airplane?`)) {
		// get class name cua the <tr> muon xoa   
		var rawstrClass = $(this).closest('tr').attr('class');
		var strClass = '';
		for (var i = 0; i < rawstrClass.split(" ").length; i++) {
			strClass = strClass + '.' + rawstrClass.split(" ")[i];
		}
		$.ajax({
			//  $(this).data('id') = ma san bay
			url: "/FlightTicketManagement/api/airplanes/" + $(this).data('id'),
			contentType: "application/json",
			async: false,
			type: "delete",
			success: function(response) {
				//xoa the <tr>
				$('.successToast').toast('show');
				$("tr").remove(strClass);
			},
			error: function(jqXHR, textStatus, errorThrown) {
			$('.failedToast').children('.toast-body').html('Unsuccessful')
				console.log(textStatus, errorThrown);
			}
		});
	} else { }
});