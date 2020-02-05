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
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	var ponovljenaLozinkaInput =  $('#ponovljenaLozinkaInput');

	
$('#RegistrujButton').on('click', function(event) {
		var korisnickoIme = korisnickoImeInput.val();
        var lozinka = lozinkaInput.val();
        var ponovljenaLozinka = ponovljenaLozinkaInput.val();
        if(korisnickoIme=="" || lozinka=="" || ponovljenaLozinka=="" || lozinka!=ponovljenaLozinka){
        	alert("Neispravni podaci.Pokusajte opet.");
        }

		params = {
			'korisnickoIme': korisnickoIme, 
            'lozinka': lozinka,
            'ponovljenaLozinka': ponovljenaLozinka, 
		};
		$.post('RegistracijaServlet', params, function(data) {
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
		});
		
	});
	event.preventDefault();
	return false;
});