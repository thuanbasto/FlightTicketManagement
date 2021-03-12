$('#printInvoice').click(function() {
    Popup($('.invoice')[0].outerHTML);

    function Popup(data) {
        window.print();
        return true;
    }
});


$.ajax({
    url: '/FlightTicketManagement/api/bookings/2',
    type: 'get',
    async: false,
    contentType: 'application/json; charset=utf-8',
    dataType: "json",
    success: function(result) {
        console.log(result);
        let htmlTicket = ''
        let htmlContact = ''
        htmlContact = `<div class="col invoice-to">
            <div class="text-gray-light">INVOICE TO:</div>
            <h2 class="to">${result.tickets[0].customer.firstName} ${result.tickets[0].customer.lastName}</h2>
            <div class="address">Adress: ${result.tickets[0].customer.address}</div>
            <div class="phoneNumber">Phone Number: ${result.phone}</div>
            <div class="email">Email:<a href="#"> ${result.email}</a></div>
        </div>
        <div class="col invoice-details">
            <h1 class="invoice-id">INVOICE #${result.booking_Id}</h1>
            <div class="date">Date of Invoice: ${result.bookingDate}</div>
            <div class="date">Due Date: 30/10/2018</div>
        </div>`;



        result.tickets.forEach(element => {
            // console.log(element)
            let total_signedLuggaPrice = 0;
            let total_taxPrice = 0;
            element.signedluggage.signedluggagePrices.forEach(e => {
                total_signedLuggaPrice += e.price
            });

            element.taxs.forEach(tax => {
                tax.taxPrices.forEach(taxPrice => {
                    total_taxPrice += taxPrice.price
                });

            });


            htmlTicket += `
                <div class="row">
                    <div class="col-sm-4" style="padding-left: 40px;">
                        <h6>Flight Price: ${element.flight.flight_Price}</h6>
                        <h6>Tax: ${total_taxPrice}</h6>
                        <h6>Signed Luggage: ${total_signedLuggaPrice}</h6>
                        <h6><b>Total Price:</b> ${element.ticket_PriceTotal}</h6>
                    </div>
                    <div class="col-sm-8">
                        <h5>Customer Information</h5>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td>Mr/Mrs: ${element.customer.firstName} ${element.customer.lastName}</td>
                                    <td>Address: ${element.customer.address}</td>
                                </tr>
                                <tr>
                                    <td>Birthday: ${element.customer.birthDay}</td>
                                    <td>Identity Number: ${element.customer.identityNumber}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            <hr>`;
        });

        console.log()
        $('.contacts').html(htmlContact)
        $('#ticketData').html(htmlTicket)
    },
    failure: function(result) {
        console.log("FAILED");
        console.log(result);
    }
});