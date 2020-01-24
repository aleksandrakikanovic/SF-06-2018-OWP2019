$(document).ready(function() {
	$('#logout').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('index.html');
				return;
			}
		});
	
		event.preventDefault();
		return false;
	});
	var korInput = $('#korInput');
	var lozInput = $('#lozInput');
	var loginButton = $('loginButton');
	
	$('#loginButton').on('click', function(event) {
		var korIme = korInput.val();
        var loz = lozInput.val();
		params = {
			'korIme': korIme, 
            'loz': loz
		};
		$.post('LoginServlet', params, function(data) {
			if (data.status == 'failure') {
				korInput.val('');
				lozInput.val('');
				alert('Pogresni podaci.Pokusajte ponovo!');
				return;
			}
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
		});		
		event.preventDefault();
		return false;
});
});