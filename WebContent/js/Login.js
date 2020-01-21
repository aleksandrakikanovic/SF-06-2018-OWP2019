$(document).ready(function() {
	var korInput = $('#korInput');
	var lozInput = $('#lozInput');
	var loginButton = $('loginButton');
	
	$('#loginButton').on('click', function(event) {
		var korIme = korInput.val();
        var loz = lozInput.val();

		params = {
			'korIme': korIme, 
            'loz': loz,

		};
		$.post('LoginServlet', params, function(data) {
			console.log('testiranje');
			window.location.replace('Filmovi.html');
			
		});
		
		event.preventDefault();
		return false;
});
});