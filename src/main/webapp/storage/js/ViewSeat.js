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

var chosenSeatList ;
function check_existSeat(seat_Id) {
	for (i in chosenSeatList) {
		if (chosenSeatList[i] == seat_Id) { // kiểm tra ghế muốn đặt đã được ai đặt chưa ?
			// console.log('--------Ghe nay da duoc dat roi--------')
			return true;
		}
	}
}

$("body").on("click", "input[type=checkbox]", function() {
	
	seat_Id = $(this).attr('id'); // id ghế vừa click
	customer_Id = $("#customerListDDL").val(); // id customer hiện tại
	customerName = $("#customerListDDL>option:selected").text().trim(); // name customer hiện tại
	
	if (check_existSeat(seat_Id) != true) { // ghế có sẵn
		chosenSeatList = []; // arr ghế đang được chọn trong phiên
		for (i in ticketList) {
			if (ticketList[i].customer.customer_Id == customer_Id){
				ticketList[i].seat.seat_Id = seat_Id;
			}
			chosenSeatList.push(ticketList[i].seat.seat_Id);
		}
		
		// alert('Chọn ghế thành công!')
	} else {
		alert('Ghế này đã được chọn!')
	}
	$(this).prop('checked', true);

	loadSeatInfo()
});

function loadSeatInfo(){
	var checkedList = []; // những giá trị trong mảng này k bị xóa checked  
	var seatInfo = '';

	for (i in chosenSeatList) { // cap nhat lai mang arrNot(những giá trị trong mảng này k bị xóa checked)
		if (ticketList[i].seat.seat_Id != "") {
			checkedList.push(`#${chosenSeatList[i]}`)
		}
		seatInfo += `<h2 style='color:teal'>${ticketList[i].customer.firstName} ${ticketList[i].customer.lastName} (${ticketList[i].seat.seat_Id})</h2>`

	}
	//xóa những checked của các check box k có trong checkedList
	$("input[type=checkbox]").not(checkedList.join(',')).prop('checked', false);

	$('#seatInfo').html(seatInfo)
}