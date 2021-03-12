var seatList = [];
var travelClassList = [];
var action = "";

// load data body of table
function loadSeatList() {
    $.ajax({
        url: "/FlightTicketManagement/api/seats",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            let htmlStr = ``;
            $.each(response,function(index,value){
                seatList.push(value);

                htmlStr += 
                `<tr class=${value.seat_Id}>
                    <td>${value.seat_Id}</td>
                    <td>${value.travelClass.name}</td>
                    <td>${value.travelClass.travelClassPrices[0].price}</td>
                    <td>
                        <button id="btnEdit" data-id=${value.seat_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateSeatModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${value.seat_Id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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

// load travel class data
function loadTravelClassList() {
    $.ajax({
        url: "/FlightTicketManagement/api/travelclasses",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            $.each(response,function(index,value){
                travelClassList.push(value);

                $("#inpTravelClass").append(`<option value='${value.travelClass_Id}'>${value.name}</option>`)
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadSeatList();
loadTravelClassList()

// edit seat
$('#tbodyData').on('click', '#btnEdit', function() {
    // make empty input
    // $("#inpSeat_Id").val("")
    // $("#inpTravelClass").val("")
    // $("#inpPrice").val("")

    // fill input with value
    $("#inpSeat_Id").prop("disabled", true);

    seatList.forEach(seat => {
        if (seat.seat_Id == $(this).data("id")){
            $("#inpSeat_Id").val(seat.seat_Id)
            $("#inpTravelClass").val(seat.travelClass.travelClass_Id)
            $("#inpPrice").val(seat.travelClass.travelClassPrices[0].price)
        }
    })

    action = "update";

    // set title for modal popup
    $(".modal-title").html("Edit seat");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding seat
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpSeat_Id").prop("disabled", false);
    $("#inpSeat_Id").val("")
    $("#inpTravelClass").val("")
    $("#inpPrice").val("")
    console.log("11111");

    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add seat");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});

// update and add seat
$('body').on('click', '#btnUpdate', function() {
    let seat = {
        seat_Id : $("#inpSeat_Id").val(),
        travelClass : {
            travelClass_Id : $("#inpTravelClass").val(),
        }
    };

    console.log(seat);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/seats/" + $("#inpSeat_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(seat),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                seatList = []; // empty the old list to add a new one
                loadSeatList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/seats",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(seat),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                seatList = []; // empty the old list to add a new one
                loadSeatList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }
});

// delete seat
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete seat with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/seats/" + $(this).data('id'),
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

$('#updateSeatModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});

$('#inpTravelClass').on('change', function (event) {
    travelClassList.forEach(travelClass => {
        if (travelClass.travelClass_Id == this.value){
            $('#inpPrice').val(travelClass.travelClassPrices[0].price);
        }
    })
});
$(".toast").toast('show');
        $(".toast").toast('hide');