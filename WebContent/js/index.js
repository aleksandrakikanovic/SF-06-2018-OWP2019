$(document).ready(function() { 
	
	$.post('LoginServlet', function(data) {
		if (data.ulogaKorisnika == null) {
			$('#prikaziKorisnike').hide();
			$('#profilKorisnika').hide();
			$('#registracija').show();
			$('#logout').hide();
			$('#login').show();
		}else if(data.ulogaKorisnika=='ADMIN'){
			$('#prikaziKorisnike').show();
			$('#profilKorisnika').show();
			$('#registracija').hide();
			$('#logout').show();
			$('#login').hide();
		}else if(data.ulogaKorisnika=='KORISNIK'){
			$('#prikaziKorisnike').hide();
			$('#profilKorisnika').show();
			$('#registracija').hide();
			$('#logout').show();
			$('#login').hide();

		}
	});
	
});