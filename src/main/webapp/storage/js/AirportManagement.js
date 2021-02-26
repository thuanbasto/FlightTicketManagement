//func load ddl thanh pho
function loadCityList() {
    $.ajax({
        url: "/FlightTicketManagement/api/cities",
        async: false,
        success: function(response) {
            var htmlStr = ``;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).find("item").each(function() {
                var cityId = $(this).find("city_Id").text();
                var cityName = $(this).find("name").text();
                htmlStr = htmlStr + `<option value=${cityId}>${cityName}</option>`;
            });
            //hien thi len
            $("#loadCity").html(htmlStr);
            $("#loadCityMD").html(htmlStr);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}
loadCityList()
    //func load san bay
function loadAirportList() {
    $.ajax({
        url: "/FlightTicketManagement/api/airports",
        async: false,
        success: function(response) {
            var htmlStr = ``;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).find("item").each(function() {
                var airportId = $(this).find("airport_Id").text();
                var airportName = $(this).find("name:first").text(); //:frist phan tu name(airport) dau tien
                var cityId = $(this).find("city").find("city_Id").text();
                var cityName = $(this).find("city").find("name").text();
                htmlStr = htmlStr + `<tr class=${airportId}><td>${airportId}</td>
                <td>${cityName}</td>
				<td>${airportName}</td>
                <td><button id="btnEdit" data-id=${airportId} data-ctid="${cityId}" type="button" class="btn btn-info" data-toggle="modal" data-target="#updateAirportModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${airportId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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
loadAirportList();

//su kien nut Add san bay
$('body').on('click', '#btnAdd', function() {
    // check validate
    if ($("#airportCode").val() == '' || $("#airportName").val() == '') {
        alert('Khong duoc chua trong!');
    } else {
        $.ajax({
            url: "/FlightTicketManagement/api/airports",
            contentType: "application/json",
            async: false,
            type: "post",
            // du lieu truyen vao dang json 
            data: JSON.stringify({
                "airport_Id": $("#airportCode").val(),
                "name": $("#airportName").val(),
                "city": {
                    "city_Id": $("#loadCity").val()
                }
            }),
            success: function(response) {
                $("#airportCode").val("");
                $("#airportName").val("");
                console.log(response);
                loadAirportList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }

});


var airportID;
var CITYID;

//su kien nut Edit san bay
$('#tbodyData').on('click', '#btnEdit', function() {
    AirportID = $(this).data('id'); // id san bay muon edit
    CITYID = $(this).data('ctid'); // id thanh pho muon edit
    $('#inpAirportID').val($(this).data('id')); // Lay ma san bay hien thi len inpAirportID
    var airportName = $(this).closest('tr').children('td').eq(2).text(); // Lay ten san bay hien thi len inpAirportName
    $("#inpAirportName").val(airportName);
    //Hien thi thanh pho dang chon
    $("#loadCityMD option").each(function() {
        if ($(this).val() == CITYID) {
            $(this).attr("selected", "selected");
        }
    })

});

//su kien nut Update san bay
$('body').on('click', '#btnUpdate', function() {
    $.ajax({
        url: "/FlightTicketManagement/api/airports/" + AirportID,
        contentType: "application/json",
        async: false,
        type: "put",
        data: JSON.stringify({
            "airport_Id": AirportID,
            "name": $("#inpAirportName").val(),
            "city": {
                "city_Id": $("#loadCityMD").val()
            }
        }),
        success: function(response) {
            $('.close').click();
            loadAirportList();
            $("#inpAirportName").val('');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
});

$('#updateAirportModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});

//su kien nut Delete san bay
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete ${$(this).data('id')} Airport?`)) {
        // get class name cua the <tr> muon xoa   
        var rawstrClass = $(this).closest('tr').attr('class');
        var strClass = '';
        for (var i = 0; i < rawstrClass.split(" ").length; i++) {
            strClass = strClass + '.' + rawstrClass.split(" ")[i];
        }
        $.ajax({
            //  $(this).data('id') = ma san bay
            url: "/FlightTicketManagement/api/airports/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            type: "delete",
            success: function(response) {
                //xoa the <tr>
                $("tr").remove(strClass);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});