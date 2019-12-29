$(document).ready(function() {
	var korInput = $('#korInput');
	var lozInput = $('#lozInput');
	var loginButton = $('loginButton');
	
	$('#RegistrujButton').on('click', function(event) {
		var korIme = korInput.val();
        var loz = lozInput.val();

		params = {
			'korIme': korIme, 
            'loz': loz,

		};
		$.post('LoginServlet', params, function(data) {
			//console.log('testiranje');


			
		});
		
		
});
});