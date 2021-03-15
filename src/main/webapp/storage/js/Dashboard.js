function getBookingPriceInYearAndMonth() {
    $.ajax({
        url: "/FlightTicketManagement/api/bookinginmonthyear",
        async: false,
        type: "GET",
        dataType: "JSON",
        success: function(response) {
            $(".EarningsMonthly").html(formatVND(response))
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

function getBookingPriceInYear() {
    $.ajax({
        url: "/FlightTicketManagement/api/bookinginyear",
        async: false,
        type: "GET",
        dataType: "JSON",
        success: function(response) {
            $(".EarningsAnnual").html(formatVND(response))
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    })
}

getBookingPriceInYearAndMonth();
getBookingPriceInYear();

function formatVND(money) {
    return (money).toLocaleString('vi', {
        style: 'currency',
        currency: 'VND',
    });
}