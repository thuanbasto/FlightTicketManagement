/*$('.plane').load('seatMap.html', function(sss) {
        let disableSeat = ['1E', '1D', '1B', '5A', '5B', '5C']
        let strSelector = '';
        for (i in disableSeat) {
            strSelector += '#' + disableSeat[i] + ','
        }
        strSelector = strSelector.slice(0, strSelector.length - 1);
        console.log(strSelector)
        $(strSelector).attr('disabled', 'disabled');

    });
*/
    var seatId = '';
    var arrSeatChoose = []; // arr ghế đang được chọn trong phiên
    var cusId = '';
    $(".plane").on("click", "input[type=checkbox]", function() {
        var arrNot = []; // những giá trị trong mảng này k bị xóa checked  
        seatId = $(this).attr('id'); // id ghế vừa click
        cusId = $("#CUS>option:selected").val(); // id customer hiện tại
        cusName = $("#CUS>option:selected").text(); // name customer hiện tại

        function check_existSeat() {
            for (i in arrSeatChoose) {
                if (arrSeatChoose[i].cusSeat == seatId) { // kiểm tra ghế muốn đặt đã được ai đặt chưa ?
                    // console.log('--------Ghe nay da duoc dat roi--------')
                    return true;
                }
            }
        }

        function check_existCustomer() {
            for (i in arrSeatChoose) {
                if (arrSeatChoose[i].cusId == cusId) { // kiểm tra chủ nhân vé này đã chọn ghế chưa ?
                    // console.log('--------cus nay da chon ve--------')
                    return true;
                }
            }
        }

        if (check_existSeat() != true) { // ghế có sẵn
            if (check_existCustomer()) { // kiểm tra chủ nhân vé này đã chọn ghế rồi thì update ghê 
                for (i in arrSeatChoose) {
                    if (arrSeatChoose[i].cusId == cusId) { // Update ghế
                        arrSeatChoose[i].cusSeat = seatId;
                    }
                }
            } else { // chu ve chua chon ghe thi chon ve
                arrSeatChoose.push({
                    'cusId': cusId,
                    'cusName': cusName,
                    'cusSeat': seatId
                });
                alert('Chọn ghế thành công!')
            }
        } else {
            alert('Ghế này đã được chọn!')
        }
        console.log(arrSeatChoose)
        var strInfo = ''
        for (i in arrSeatChoose) { // cap nhat lai mang arrNot(những giá trị trong mảng này k bị xóa checked)
            if (arrNot.includes(arrSeatChoose[i].cusSeat) == false) {
                arrNot.push(`#${arrSeatChoose[i].cusSeat}`)
            }
            strInfo += `<h2 style='color:teal'>${arrSeatChoose[i].cusName} (${arrSeatChoose[i].cusSeat})</h2>`

        }
        //xóa những checked của các check box k có trong arrNot
        $("input[type=checkbox]").not(arrNot.join(',')).prop('checked', false);

        console.log(arrNot)
        $('#seatInfo').html(strInfo)
        $(this).prop('checked', true);
    });