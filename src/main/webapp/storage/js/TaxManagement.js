//func show noti success 
function showNotiSuccess (content){
    $("#notiAlert").show();
	$( "#notiAlert" ).addClass("alert-success");
    $("#notiAlert").html(`<strong>${content}</strong> has been added.`);
    setTimeout(function() {
        $("#notiAlert").hide();
        $( "#notiAlert" ).removeClass("alert-success");
    }, 1000);
}

//func show noti error 
function showNotiError (){
    $("#notiAlert").show();
	$( "#notiAlert" ).addClass("alert-danger");
    $("#notiAlert").html(`<strong>Error!</strong> An error occurred. Please try again later.`);
    setTimeout(function() {
        $("#notiAlert").hide();
    	$( "#notiAlert" ).removeClass( "alert-danger" );        
    }, 1000);
}


//func load tax
function loadTaxList() {
    $.ajax({
        url: "/FlightTicketManagement/api/tax",
        async: false,
        success: function(response) {
            var htmlStr = ``;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).find("item").each(function() {
                var taxId = $(this).find("tax_Id").text();
                var taxName = $(this).find("taxName").text();
                htmlStr = htmlStr + `<tr class=${taxId}><td>${taxId}</td>
                <td>${taxName}</td>
                <td><button id="btnEdit" data-id=${taxId} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateTaxModal"><i class="fas fa-edit"></i></button>&nbsp
                <button id="btnDelete" data-id=${taxId} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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

loadTaxList();

//su kien nut Add tax
$('body').on('click', '#btnAdd', function() {
    // check validate
    if ($("#taxName").val() == '') {
        alert('Khong duoc chua trong!');
    } else {
    var tax_name = $("#taxName").val();
        $.ajax({
            url: "/FlightTicketManagement/api/tax",
            contentType: "application/json",
            async: false,
            type: "post",
            // du lieu truyen vao dang json 
            data: JSON.stringify({
                "taxName": tax_name
            }),
            success: function(response) {
                console.log(response);
                $("#taxName").val('');
                showNotiSuccess(tax_name);
                loadTaxList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
                showNotiError();
            }
        });
    }

});


var TaxID;

//su kien nut Edit tax
$('#tbodyData').on('click', '#btnEdit', function() {
    TaxID = $(this).data('id'); // id tax muon edit
    $('#inpTaxID').val($(this).data('id')); // Lay ma may bay hien thi len inpAirportID
    var taxName = $(this).closest('tr').children('td').eq(1).text(); // Lay ten tax hien thi len inpTaxName
    $("#inpTaxName").val(taxName);
});

//su kien nut Update tax
$('body').on('click', '#btnUpdate', function() {
    $.ajax({
        url: "/FlightTicketManagement/api/tax/" + TaxID,
        contentType: "application/json",
        async: false,
        type: "put",
        data: JSON.stringify({
            "tax_Id": $("#inpTaxID").val(),
            "taxName": $("#inpTaxName").val(),
        }),
        success: function(response) {
            $('.close').click();
            loadTaxList();
            showNotiSuccess("Updated!");
            $("#inpTaxName").val('');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
});


//su kien nut Delete tax
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete ${$(this).closest('tr').children('td').eq(1).text()} tax?`)) {
        // get class name cua the <tr> muon xoa   
        var rawstrClass = $(this).closest('tr').attr('class');
        var strClass = '';
        console.log(rawstrClass.split(" "));
        for (var i = 0; i < rawstrClass.split(" ").length; i++) {
            strClass = strClass + '.' + rawstrClass.split(" ")[i];
        }

        $.ajax({
            //  $(this).data('id') = ma tax
            url: "/FlightTicketManagement/api/tax/" + $(this).data('id'),
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