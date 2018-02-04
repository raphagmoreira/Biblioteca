$(document).ready(function () {
	var urlBase = "http://localhost:8080";

	$('#login').on('click', function() {
		var data0 = {"username":"admin", "password":"123456"};
		var json = JSON.stringify(data0);

		$.ajax({
			type: "POST",
			url: urlBase + "/user/autentication",
			data: json,
			success: function(msg) {
				alert('In Ajax');
			}
		});
	});
});