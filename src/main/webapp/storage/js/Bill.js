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
            <div class="phoneNumber">Phone Number: ${result.phone}</div>
            <div class="email">Email:<a href="#"> ${result.email}</a></div>
        </div>
        <div class="col invoice-details">
            <h1 class="invoice-id">Booking ID #${result.booking_Id}</h1>
            <div class="date">Date of Invoice: ${result.bookingDate}</div>
        </div>`;

        let total_bookingPrice = 0;
        let total_taxPrice = 0;
        
        result.tickets.forEach(ticket => {
            let taxPrice = 0;
            let signedLuggaPrice = 0; 

            if (ticket.signedluggage != null) signedLuggaPrice = ticket.signedluggage.signedluggagePrices[0].price;

            ticket.taxs.forEach(tax=>{
                if(tax.taxPrices != null)
                    taxPrice += tax.taxPrices[0].price
            })

            total_bookingPrice += taxPrice + signedLuggaPrice + ticket.flight.flight_Price + ticket.seat.travelClass.travelClassPrices[0].price;


            htmlTicket = htmlTicket + `
            <h3>Ticket #${ticket.ticket_Id}</h3>
            <table class="table">
                <tr>
                    <td><b>From: </b>${ticket.flight.fromAirport.name}</td>
                    <td><b>To: </b>${ticket.flight.toAirport.name}</td>
                    <td><b>Flight Code: </b>${ticket.flight.flight_Id} - ${ticket.flight.airplane.name}</td> 
                </tr>
                <tr>
                    <td><b>Arrival Date: </b>${ticket.flight.arrivalDate}</td>
                    <td><b>Departure Date: </b>${ticket.flight.departureDate}</td>
                    <td><b>Seat: </b>${ticket.seat.seat_Id} - ${ticket.seat.travelClass.name}</td>
                </tr>
            </table>
            <div class="row">
                <div class="col-sm-6" style="padding-left: 40px;">
                    <h6><b>Flight Price:</b> ${formatVND(ticket.flight.flight_Price)}</h6>
                    <h6><b>Tax:</b> ${formatVND(taxPrice)}</h6>
                    <h6><b>Signed Luggage:</b> ${ticket.signedluggage != null ? formatVND(ticket.signedluggage.signedluggagePrices[0].price) : formatVND(0)}</h6>
                    <h6><b>Travel Class Price:</b> ${formatVND(ticket.seat.travelClass.travelClassPrices[0].price)}</h6>
                    <h6><b>Total Price:</b> ${ticket.ticket_PriceTotal}</h6>
                </div>
                <div class="col-sm-6">
                    <h5>Customer Information</h5>
                    <table class="table">
                        <tr>
                            <td><b>Mr/Mrs: </b> ${ticket.customer.firstName} ${ticket.customer.lastName}</td>
                        </tr>
                        <tr>
                            <td><b>Birthday: </b>${ticket.customer.birthDay}</td>
                        </tr>
                    </table>
                </div>
            </div>
            `
        });

        // load booking total
        htmlBookingTotal = `
        <tr>
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

        $('.contacts').html(htmlContact)
        $('#ticketData').html(htmlTicket)
        $('#bookingTotalData').html(htmlBookingTotal)
    },
    failure: function(result) {
        console.log("FAILED");
        console.log(result);
    }
});