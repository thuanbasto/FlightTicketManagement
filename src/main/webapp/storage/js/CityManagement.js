//func lam moi thanh pho
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
                htmlStr = htmlStr + `<tr class=${cityId}><td>${cityId}</td>
                <td>${cityName}</td>
                <td><button id="btnEdit" data-id=${cityId} type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${cityId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                </td></tr>`;
            });
            //hien thi len
            $("#loadCity").html(htmlStr);

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadCityList();

//su kien nut Add thanh pho
$('body').on('click', '#btnAdd', function() {
    // check validate
    if ($("#cityCode").val() == '' || $("#cityName").val() == '') {
        alert('Khong duoc chua trong!');
    } else {
        $.ajax({
            url: "/FlightTicketManagement/api/cities",
            contentType: "application/json",
            async: false,
            type: "post",
            // du lieu truyen vao dang json 
            data: JSON.stringify({ "city_Id": $("#cityCode").val(), "name": $("#cityName").val() }),
            success: function(response) {
		        $("#cityCode").val("");
		        $("#cityName").val("");
                console.log(response);
                loadCityList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }

});


var cityID;

//su kien nut Edit thanh pho
$('#loadCity').on('click', '#btnEdit', function() {
    console.log($(this).data('id'));
    cityID = $(this).data('id');
    $('#inpCityID').val($(this).data('id'));
});

//su kien nut Update thanh pho
$('body').on('click', '#btnUpdate', function() {
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

//su kien nut Delete thanh pho
$('#loadCity').on('click', '#btnDelete', function() {
    // console.log($(this).data('id'));
    if (confirm(`You want to delete ${$(this).data('id')} city?`)) {
		// get class name cua the <tr> muon xoa   
        var rawstrClass = $(this).closest('tr').attr('class');
        var strClass ='';
        console.log(rawstrClass.split(" "));
        for (var i = 0 ; i < rawstrClass.split(" ").length ; i++){
        	strClass = strClass+'.'+rawstrClass.split(" ")[i];
        }

        $.ajax({
            //  $(this).data('id') = ma thanh pho
            url: "/FlightTicketManagement/api/cities/" + $(this).data('id'),
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