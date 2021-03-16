$('#ftab').trigger('click');

$('#btnSearch').on('click', function(e) {
    e.preventDefault();
    if ($("#from").val() == 0 || $("#to").val() == 0 || $("#departureDate").val() == "" || $("#adult").val() == 0) {
        alert("Please fill full field!")
    } else {
        let number = parseInt($('#adult').val());
        // console.log('?' + $('#flightSearchForm').serialize() + `&number=${number}`);
        window.location.href = `searchFlight?${$('#flightSearchForm').serialize()}&number=${number}`;
    }
});

$('.findBooking').on('click', function(e) {
    e.preventDefault();
    if ($("#email").val() == "" || $("#booking_Id").val() == "") {
        alert("Please fill full field!")
    } else {
        window.location.href = `bill?${$('#findBookingForm').serialize()}`;
    }
});




function loadCities() {
    $.ajax({
        url: "/FlightTicketManagement/api/airports",
        async: false,
        dataType: "JSON",
        success: function(response) {
            var htmlStr = `<option value="0">Select city</option>`;
            // lap qua ket qua tra ve & tao html theo mong muon
            $.each(response, function(index, value) {
                var airport_Id = value.airport_Id;
                var cityName = value.city.name;

                htmlStr = htmlStr + `<option value="${airport_Id}">${cityName} (${airport_Id})</option>`;
            });
            //hien thi len
            $("#from").html(htmlStr);
            $("#to").html(htmlStr);

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadCities();

$('#from').on('change', function(e) { // hide fromcity when selected in tocity
    $(`#to option`).show();
    var ctc = $(this).val();
    $(`#to option[value=${ctc}]`).hide();
});



var PassengersNumber = 1;
$('.dropdown-content input').on('input', function(e) {
    PassengersNumber = 0;
    $(".dropdown-content input").each(function() {
        PassengersNumber = PassengersNumber + Number($(this).val());
    });
    $('#PassengersNum').text(PassengersNumber + " Passengers")

});



$('.btn.btn-primary.dropdown-toggle').on('click', function(e) {
    // e.stopPropagation();
    // console.log('sdsd');
});


$('#ngayden').hide();

var chieu_ve = 'oneway';
$('ul[data-tag="channelList"] > li').click(function() {

    $('ul[data-tag="channelList"] li').each(function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass("selected");
        }
    });

    $(this).addClass("selected");
    chieu_ve = $(this).data('id');

    if ($(this).data('id') == 'oneway') {
        // $('#ngayden').hide();
    } else {
        // $('#ngayden').show();
    }
    // console.log($(this).val());


    $('#btnSearch').click(function() {
        // console.log('trunghieuclick');
        // console.log($('#testli').serialize() + '&chieuve=' + chieu_ve);
        $.ajax({
            type: "POST",
            url: 'search',
            data: $('#testli').serialize() + '&chieuve=' + chieu_ve, // serializes the form's elements.
            success: function(data) {
                // console.log(data); // show response from the php script.
            }
        });

    });
});