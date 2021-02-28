function LuggageList() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        async: false,
        success: function(response) {
            var htmlStr = ``;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).each(function(key, value) {
                var id = value.signedLuggage_Id;
                var name = value.name;
                var weight = value.weight;
                var price;
                if(value.signedluggagePrices == null) {
                     price = "Chưa có giá trị"
                }else{
                     price = value.signedluggagePrices[0].price;
                }
                htmlStr = htmlStr + `
                <tr class=${id}>
                <td>${id}</td>
                <td>${name}</td>
                <td>${weight}</td>
                <td>${price}</td>
                <td>
                    <button id="btnEdit" data-id=${id} type="button" class="btn btn-info" ><i class="fas fa-edit" data-toggle="modal" data-target="#updateLuggageModal"></i></button>&nbsp
                    <button id="btnDelete" data-id=${id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                </td>
                </tr>`;
            });
            //hien thi len
            $("#tbodyData").html(htmlStr);

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}
LuggageList();

$('body').on('click', '#btnAdd', function() {

    if ($("#luggageName").val() == '' || $("#luggageWeight").val() == '') {
        alert('Do not leave the field blank');
    } else {
        $.ajax({
            url: "/FlightTicketManagement/api/signedluggage",
            contentType: "application/json",
            async: false,
            type: "post",
            data: JSON.stringify({
                "name": $("#luggageName").val(),
                "weight": $("#luggageWeight").val(),
            }),
            success: function(response) {
                console.log(response);
                LuggageList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }

});

var LuggugeId;

$('#tbodyData').on('click', '#btnEdit', function() {
    console.log("asc");
    LuggugeId = $(this).data('id'); // 
    $('#inpLuggageID').val($(this).data('id')); 
    var luggugeName = $(this).closest('tr').children('td').eq(1).text();
    $("#inpLuggageName").val(luggugeName);
    var luggugeWeight = $(this).closest('tr').children('td').eq(2).text();
    $("#inpLuggageWeight").val(luggugeWeight);
});


$('body').on('click', '#btnUpdate', function() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage/",
        contentType: "application/json",
        async: false,
        type: "put",
        data: JSON.stringify({
            "signedLuggage_Id": $("#inpLuggageID").val(),
            "name": $("#inpLuggageName").val(),
            "weight": $("#inpLuggageWeight").val(),
        }),
        success: function(response) {
            $('.close').click();
            LuggageList();
            $("#inpLuggageName").val('');
            $("#inpLuggageWeight").val('');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
});


$('#updateLuggageModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});

$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete ${$(this).data('id')} Lugguge ?`)) {
        var rawstrClass = $(this).closest('tr').attr('class');
        console.log(rawstrClass);
        var strClass = '';
        for (var i = 0; i < rawstrClass.split(" ").length; i++) {
            strClass = strClass + '.' + rawstrClass.split(" ")[i];
            console.log(strClass);
        }
        $.ajax({
            url: "/FlightTicketManagement/api/signedluggage/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            type: "delete",
            success: function(response) {
                $("tr").remove(strClass);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});