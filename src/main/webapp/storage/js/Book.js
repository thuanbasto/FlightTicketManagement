$('#ftab').trigger('click');

$('#search_btn').on('click', function(e) {
    e.preventDefault();
    let number = parseInt($('#adult').val()) + parseInt($('#child').val())
    console.log('?' + $('#flightSearchForm').serialize() + `&number=${number}`);
    window.location.href = `searchFlight?${$('#flightSearchForm').serialize()}&number=${number}`;
});





function loadCitySelect() {
    $.ajax({
        url: "/FlightTicketManagement/api/cities",
        async: false,
        success: function(response) {
            var htmlStr = `<option value="0"></option>`;
            // lap qua ket qua tra ve & tao html theo mong muon
            $(response).find("item").each(function() {
                var cityId = $(this).find("city_Id").text();
                var cityName = $(this).find("name").text();
                htmlStr = htmlStr + `<option value="${cityId}">${cityName} (${cityId})</option>`;
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

loadCitySelect();

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
    console.log('sdsd');
});


$('#ngayden').hide();

var chieu_ve = 'ow';
$('ul[data-tag="channelList"] > li').click(function() {

    $('ul[data-tag="channelList"] li').each(function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass("selected");
        }
    });

    $(this).addClass("selected");
    chieu_ve = $(this).data('id');

    if ($(this).data('id') == 'ow') {
        $('#ngayden').hide();
    } else {
        $('#ngayden').show();
    }
    console.log($(this).val());


    $('#search_btn').click(function() {
        console.log('trunghieuclick');
        console.log($('#testli').serialize() + '&chieuve=' + chieu_ve);
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