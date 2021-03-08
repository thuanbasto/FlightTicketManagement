var travelClassList = [];
var action = "";
var price = ""; // check update price by comparing prices

// load data body of table
function loadTravelClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/travelclasses",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            let htmlStr = ``;
            $.each(response,function(index,value){
                travelClassList.push(value);

                htmlStr += 
                `<tr class=${value.travelClass_Id}>
                    <td>${value.travelClass_Id}</td>
                    <td>${value.name}</td>
                    <td>${value.travelClassPrices[0].price}</td>
                    <td>${value.travelClassPrices[0].modifiedDate}</td>
                    <td>
                        <button id="btnEdit" data-id=${value.travelClass_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateTravelClassModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${value.travelClass_Id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                    </td>
                </tr>`;
            });
            $("#tbodyData").html(htmlStr);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadTravelClassList()

// edit travelClass
$('#tbodyData').on('click', '#btnEdit', function() {
    // fill input with value
    travelClassList.forEach(travelClass => {
        if (travelClass.travelClass_Id == $(this).data("id")){
            $("#inpTravelClass_Id").val(travelClass.travelClass_Id)
            $("#inpName").val(travelClass.name)
            $("#inpPrice").val(travelClass.travelClassPrices[0].price)
            $("#inpQuantity").val(travelClass.quantity)
            price = travelClass.travelClassPrices[0].price;
        }
    })

    action = "update";

    // set title for modal popup
    $(".modal-title").html("Edit Travel Class");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding travelClass
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpTravelClass_Id").val("")
    $("#inpName").val("")
    $("#inpPrice").val("")
    $("#inpQuantity").val("")

    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add Travel Class");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});

// update and add travelClass
$('body').on('click', '#btnUpdate', function() {
    let travelClass = {
        travelClass_Id : $("#inpTravelClass_Id").val(),
        name : $("#inpName").val(),
        quantity : $("#inpQuantity").val(),
        travelClassPrices : [{
            travelClass_Id : $("#inpTravelClass_Id").val(),
            price : $("#inpPrice").val()
        }]
    };

    console.log(travelClass);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/travelclasses/" + $("#inpTravelClass_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(travelClass),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                travelClassList = []; // empty the old list to add a new one
                if (price == travelClass.travelClassPrices[0].price) loadTravelClassList()
                else {
                    travelClass.travelClassPrices[0].travelClass_Id = $(response)[0].travelClass_Id;
                    addNewPrice(travelClass);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/travelclasses",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(travelClass),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                travelClassList = []; // empty the old list to add a new one
                travelClass.travelClassPrices[0].travelClass_Id = $(response)[0].travelClass_Id;
                addNewPrice(travelClass)
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }
});

// add new price
function addNewPrice(travelClass) {
    $.ajax({
        method: "POST",
        url: "/FlightTicketManagement/api/travelclassprices",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(travelClass.travelClassPrices[0]),
        dataType: "json",
        success: function(response) {
            console.log("add new price cusscess")
            loadTravelClassList();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

// delete travelClass
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete travel class with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/travelclasses/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function(response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});

$('#updateTravelClassModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});
