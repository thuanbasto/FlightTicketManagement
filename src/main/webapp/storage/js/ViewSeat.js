/*$('body').load('seatMap.html', function(sss) {
	let disableSeat = ['1E', '1D', '1B', '5A', '5B', '5C']
	let strSelector = '';
	for (i in disableSeat) {
		strSelector += '#' + disableSeat[i] + ','
	}
	strSelector = strSelector.slice(0, strSelector.length - 1);
	console.log(strSelector)
	$(strSelector).attr('disabled', 'disabled');

});*/

var seatId = '';
var cusId = '';
$("body").on("click", "input[type=checkbox]", function() {
    console.log(customerList)
    var arrNot = []; // những giá trị trong mảng này k bị xóa checked  
    seatId = $(this).attr('id'); // id ghế vừa click
    cusId = $("#customerListDDL>option:selected").val(); // id customer hiện tại
    cusName = $("#customerListDDL>option:selected").text(); // name customer hiện tại
    console.log(cusId);
    console.log(cusName);

    function check_existSeat() {
        for (i in customerList) {
            if (customerList[i].cusSeat == seatId) { // kiểm tra ghế muốn đặt đã được ai đặt chưa ?
                // console.log('--------Ghe nay da duoc dat roi--------')
                return true;
            }
        }
    }

    if (check_existSeat() != true) { // ghế có sẵn
        for (i in customerList) {
            if (customerList[i].cusId == cusId) { // Update ghế cho passenger
                customerList[i].cusSeat = seatId;
            }
        }
    } else {
        alert('Ghế này đã được chọn!')
    }
    console.log(customerList)
    var strInfo = ''
    for (i in customerList) { // cap nhat lai mang arrNot(những giá trị trong mảng này k bị xóa checked)
        if (arrNot.includes(customerList[i].cusSeat) == false && customerList[i].cusSeat != "") {
            arrNot.push(`#${customerList[i].cusSeat}`)
        }
        strInfo += `<h3 style='color:teal'>${customerList[i].firstName} ${customerList[i].lastName} (${customerList[i].cusSeat})</h3>`

    }
    //xóa những checked của các check box k có trong arrNot
    $("input[type=checkbox]").not(arrNot.join(',')).prop('checked', false);

    console.log(arrNot)
    $('#seatInfo').html(strInfo)
    $(this).prop('checked', true);
});