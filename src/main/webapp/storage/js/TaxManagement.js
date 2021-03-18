var taxList = [];
var action = ""; // check update or add
var price = ""; // check update price by comparing prices

function formatVND(money) {
    return (money).toLocaleString('vi', {
        style: 'currency',
        currency: 'VND',
    });
}

//func load tax
function loadTaxList() {
    $.ajax({
        method: "GET",
        url: "/FlightTicketManagement/api/taxes",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            let htmlStr = ``;
            $.each(response,function(index,value){
                taxList.push(value);

                htmlStr += 
                `<tr class=${value.tax_Id}>
                    <td>${value.tax_Id}</td>
                    <td>${value.taxName}</td>
                    <td>${formatVND(value.taxPrices[0].price)}</td>
                    <td>${value.taxPrices[0].modifiedDate}</td>
                    <td>
                        <button id="btnEdit" data-id=${value.tax_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateTaxModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${value.tax_Id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
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

loadTaxList();

// edit tax
$('#tbodyData').on('click', '#btnEdit', function() {
    // fill input with value
    taxList.forEach(tax => {
        if (tax.tax_Id == $(this).data("id")){
            $("#inpTax_Id").val(tax.tax_Id)
            $("#inpName").val(tax.taxName)
            $("#inpPrice").val(tax.taxPrices[0].price)
            price = tax.taxPrices[0].price;
        }
    })


    action = "update";

    // set title for modal popup
    $(".modal-title").html("Edit Travel Class");
    // set button name for modal popup
    $("#btnUpdate").html("Update");
});

// before adding tax
$('#btnAdd').on('click', function() {
    // make empty input
    $("#inpTax_Id").val("")
    $("#inpName").val("")
    $("#inpPrice").val("")

    action = "add";

    // set title for modal popup
    $(".modal-title").html("Add Tax");
    // set button name for modal popup
    $("#btnUpdate").html("Add");
});

// update and add tax
$('body').on('click', '#btnUpdate', function() {
    let tax = {
        tax_Id : $("#inpTax_Id").val(),
        taxName : $("#inpName").val(),
        taxPrices : [{
            tax_Id : $("#inptax_Id").val(),
            price : $("#inpPrice").val()
        }]
    };

    console.log(tax);

    if (action == "update"){
        $.ajax({
            method: "PUT",
            url: "/FlightTicketManagement/api/taxes/" + $("#inpTax_Id").val(),
            contentType: "application/json",
            async: false,
            data: JSON.stringify(tax),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                taxList = []; // empty the old list to add a new one
                if (price == tax.taxPrices[0].price) loadTaxList();
                else {
                    tax.taxPrices[0].tax_Id = $(response)[0].tax_Id;
                    addNewPrice(tax);
                }
                },
                error: function(response, textStatus, errorThrown) {
                    let errorHtml = ``;
                    Object.entries(response.responseJSON).forEach(([key, value]) => errorHtml += `<li>${value}</li>`)
                    $('.failedToast').children('.toast-body').html(errorHtml)
                    $('.failedToast').toast('show');
                    console.log(textStatus, errorThrown);
                }
        });
    } else if (action == "add"){
        $.ajax({
            method: "POST",
            url: "/FlightTicketManagement/api/taxes",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(tax),
            dataType: "json",
            success: function(response) {
                $('.close').click();
                $('.successToast').toast('show');
                taxList = []; // empty the old list to add a new one
                tax.taxPrices[0].tax_Id = $(response)[0].tax_Id;
                addNewPrice(tax)
            },
            error: function(response, textStatus, errorThrown) {
                let errorHtml = ``;
                Object.entries(response.responseJSON).forEach(([key, value]) => errorHtml += `<li>${value}</li>`)
                $('.failedToast').children('.toast-body').html(errorHtml)
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    }
});

// add new price
function addNewPrice(tax,tax_Id) {
    $.ajax({
        method: "POST",
        url: "/FlightTicketManagement/api/taxprices",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(tax.taxPrices[0]),
        dataType: "json",
        success: function(response) {
            console.log("add new price cusscess")
            loadTaxList();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

// delete tax
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete tax with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/taxes/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function(response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                $('.failedToast').toast('show');
                console.log(textStatus, errorThrown);
            }
        });
    } else {}
});

$('#updatetaxModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});
