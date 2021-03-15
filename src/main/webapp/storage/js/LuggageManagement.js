var luggageList = [];
var action = ""; // check update or add
var price = ""; // check update price by comparing prices

function loadLuggageList() {
    $.ajax({
        url: "/FlightTicketManagement/api/signedluggage",
        async: false,
        success: function(response) {
            var htmlStr = ``;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).each(function(key, value) {
                luggageList.push(value);
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
loadLuggageList();

// edit 
$('#tbodyData').on('click', '#btnEdit', function() {
    // fill input with value
    luggageList.forEach(luggage => {
        if (luggage.signedLuggage_Id == $(this).data("id")){
            $("#inpLuggageID").val(luggage.signedLuggage_Id)
            $("#inpLuggageName").val(luggage.name)
            $("#inpLuggageWeight").val(luggage.weight)
            $("#inpPrice").val(luggage.signedluggagePrices[0].price)
            price = luggage.signedluggagePrices[0].price;
        }
    })


    action = "update";

    // set title for modal popup
    $(".modal-title").html("Edit Lugguage");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding 
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpLuggageID").val("")
    $("#inpLuggageName").val("")
    $("#inpLuggageWeight").val("")
    $("#inpPrice").val("")
    console.log("aa");

    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add Lugguage");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});


// update and add 
$('body').on('click', '#btnUpdate', function() {
    let luggage = {
        signedLuggage_Id : $("#inpLuggageID").val(),
        name : $("#inpLuggageName").val(),
        weight : $("#inpLuggageWeight").val(),
        signedluggagePrices : [{
            tax_Id : $("#inpLuggageID").val(),
            price : $("#inpPrice").val()
        }]
    };

    console.log(luggage);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/signedluggage/" + $("#inpLuggageID").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(luggage),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                luggageList = []; // empty the old list to add a new one
                if (price == luggage.signedluggagePrices[0].price) loadLuggageList();
                else {
                    luggage.signedluggagePrices[0].signedLuggage_Id = $(response)[0].signedLuggage_Id;
                    addNewPrice(luggage);
                }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $('.failedToast').children('.toast-body').html('Unsuccessful')
                    console.log(textStatus, errorThrown);
                }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/signedluggage",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(luggage),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                luggageList = []; // empty the old list to add a new one
                luggage.signedluggagePrices[0].signedLuggage_Id = $(response)[0].signedLuggage_Id;;
                addNewPrice(luggage)
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                console.log(textStatus, errorThrown);
            }
        });
    }
});

// add new price
function addNewPrice(luggage) {
    $.ajax({
        method: "POST",
        url: "/FlightTicketManagement/api/signedluggageprice",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(luggage.signedluggagePrices[0]),
        dataType: "json",
        success: function(response) {
            console.log("add new price cusscess")
            loadLuggageList();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

// delete 
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete luggage with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/signedluggage/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function(response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});

$('#updateLuggageModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});

