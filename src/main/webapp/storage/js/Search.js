//su kien nut Update thanh pho
$('body').on('click', '#btnSearch', function() {
    $.ajax({
        url: "/FlightTicketManagement/api/search/di/den/date/idtax",
        contentType: "application/json",
        async: false,
        type: "get",
        success: function(response) {
            $('.close').click();
            loadCityList();
            $("#inpCityName").val('');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
});