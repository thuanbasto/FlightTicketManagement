var customerList = [];
var action = "";

// load data body of table
function loadCustomerList() {
    $.ajax({
        url: "/FlightTicketManagement/api/customers",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            let htmlStr = ``;
            $.each(response,function(index,value){
                customerList.push(value);

                htmlStr += 
                `<tr class=${value.customer_Id}>
                    <td>${value.customer_Id}</td>
                    <td>${value.firstName}</td>
                    <td>${value.lastName}</td>
                    <td>${value.birthDay}</td>
                    <td>${value.address}</td>
                    <td>
                        <button id="btnEdit" data-id=${value.customer_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateCustomerModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${value.customer_Id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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

loadCustomerList();


// edit customer
$('#tbodyData').on('click', '#btnEdit', function() {
    // make empty input
    $("#inpCustomer_Id").val("")
    $("#inpFirstName").val("")
    $("#inpLastName").val("")
    $("#inpBirthday").val("")
    $("#inpAddress").val("")

    // fill input with value
    customerList.forEach(customer => {
        if (customer.customer_Id == $(this).data("id")){
            $("#inpCustomer_Id").val(customer.customer_Id)
            $("#inpFirstName").val(customer.firstName)
            $("#inpLastName").val(customer.lastName)
            $("#inpBirthday").val(customer.birthDay)
            $("#inpAddress").val(customer.address)
        }
    })

    action = "update";

    // set title for modal popup
    $(".modal-title").html("Edit customer");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding customer
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpCustomer_Id").val("")
    $("#inpFirstName").val("")
    $("#inpLastName").val("")
    $("#inpBirthday").val("")
    $("#inpAddress").val("")

    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add customer");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});

// update and add customer
$('body').on('click', '#btnUpdate', function() {
    let customer = {
        customer_Id : $("#inpCustomer_Id").val(),
        firstName : $("#inpFirstName").val(),
        lastName : $("#inpLastName").val(),
        birthDay : $("#inpBirthday").val(),
        address : $("#inpAddress").val(),
    };

    console.log(customer);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/customers/" + $("#inpCustomer_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(customer),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                customerList = []; // empty the old list to add a new one
                loadCustomerList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                console.log(textStatus, errorThrown);
            }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/customers",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(customer),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                customerList = []; // empty the old list to add a new one
                loadCustomerList();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                console.log(textStatus, errorThrown);
            }
        });
    }
});

// delete Customer
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete Customer with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/customers/" + $(this).data('id'),
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

$('#updateCustomerModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});