

$(document).ready(function() {
	$('#ftab').trigger('click');
});


{/* <script> */ }
$(document).ready(function() {
	var PassengersNumber = 1;

	$('.dropdown-content input').on('input', function(e) {
		PassengersNumber = 0;
		$(".dropdown-content input").each(function() {
			PassengersNumber = PassengersNumber + Number($(this).val());
		});
		$('#PassengersNum').text(PassengersNumber + " Passengers")

	});

});
{/* </script> */ }
// <script>
$('.btn.btn-primary.dropdown-toggle').on('click', function(e) {
	// e.stopPropagation();
	console.log('sdsd');
});
{/* </script>

<script> */}
$(document).ready(function() {
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
	});

	$('#search_btn').click(function() {
		console.log('trunghieuclick');
		console.log($('#testli').serialize() + '&chieuve=' + chieu_ve);
		$.ajax({
			type: "POST",
			url: 'datve.php',
			data: $('#testli').serialize() + '&chieuve=' + chieu_ve, // serializes the form's elements.
			success: function(data) {
				console.log(data); // show response from the php script.
			}
		});

	});

});
{/* </script> */ }