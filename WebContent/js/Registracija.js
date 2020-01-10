$(document).ready(function() {
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	var ponovljenaLozinkaInput =  $('#ponovljenaLozinkaInput');
	
	var RegistrujButton =  $('#RegistrujButton');
	console.log("awfwf");

$('#RegistrujButton').on('click', function(event) {
		var korisnickoIme = korisnickoImeInput.val();
        var lozinka = lozinkaInput.val();
        var ponovljenaLozinka = ponovljenaLozinkaInput.val();

		params = {
			 'action' : add,
			'korisnickoIme': korisnickoIme, 
            'lozinka': lozinka,
            'ponovljenaLozinka': ponovljenaLozinka, 

		};
		$.post('RegistracijaServlet', params, function(data) {
			console.log(ponovljenaLozinka);


			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
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