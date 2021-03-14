$('#printInvoice').click(function() {
    Popup($('.invoice')[0].outerHTML);

    function Popup(data) {
        window.print();
        return true;
    }
});

function formatVND(money) {
    if (money == null) {
        money = 0
    }
    return (money).toLocaleString('vi', {
        style: 'currency',
        currency: 'VND',
    });
}

let booking_Id = $('#url').val().split("=")[$('#url').val().split("=").length - 1];
$.ajax({
    url: '/FlightTicketManagement/api/bookings/' + booking_Id,
    type: 'get',
    async: false,
    contentType: 'application/json; charset=utf-8',
    dataType: "json",
    success: function(result) {
        console.log(result);
        let htmlTicket = ''
        let htmlContact = ''
        let htmlBookingTotal = ''

        // load contact infomation
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
        </div>`;




        let total_bookingPrice = 0;

        result.tickets.forEach(element => {
            // console.log(element)
            // let total_signedLuggaPrice = 0; truoc khi merge main
            // let total_taxPrice = 0;

            // element.signedluggage.signedluggagePrices.forEach(e => {
            //     total_signedLuggaPrice += e.price
            // });

            // element.taxs.forEach(tax => {
            //     tax.taxPrices.forEach(taxPrice => {
            //         total_taxPrice += taxPrice.price
            //     });

            // }); truoc khi merge main

            let total_signedLuggaPrice = element.signedluggage.signedluggagePrices;
            let total_taxPrice = 0;

            element.taxs.forEach(tax => {
                total_taxPrice += tax.taxPrices
            });

            let total_Price = element.flight.flight_Price + total_taxPrice + total_signedLuggaPrice;
            total_bookingPrice += total_Price;


            // load ticket infomation

            htmlTicket += `
                <div class="row">
                    <div class="col-sm-4" style="padding-left: 40px;">
                        <h6>Flight Price: ${formatVND(element.flight.flight_Price)}</h6>
                        <h6>Tax: ${formatVND(total_taxPrice)}</h6>
                        <h6>Signed Luggage: ${formatVND(total_signedLuggaPrice)}</h6>
                        <h6><b>Total Price:</b> ${formatVND(total_Price)}</h6>
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

        // load booking total
        htmlBookingTotal = `<tr>
        <td><b>Booking Date</b></td>
        <td>${result.bookingDate}</td>
        </tr>
        <tr>
            <td><b>Phone:</b></td>
            <td>${result.phone}</td>
        </tr>
        <tr>
            <td><b>Email:</b></td>
            <td>${result.email}</td>
        </tr>
        <tr>
            <td><b>Payment Method:</b></td>
            <td>${result.paymentMethod}</td>
        </tr>
        <tr>
            <td><b>Number of Ticket:</b></td>
            <td>${result.numberOfTicket}</td>
        </tr>
        <tr>
            <td><b>Total Booking Price:</b></td>
            <td>${formatVND(total_bookingPrice)}</td>
        </tr>`

        console.log()
        $('.contacts').html(htmlContact)
        $('#ticketData').html(htmlTicket)
        $('#bookingTotalData').html(htmlBookingTotal)
    },
    failure: function(result) {
        console.log("FAILED");
        console.log(result);
    }
});