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
	
	$.post('LoginServlet', function(data) {
		if (data.ulogaKorisnika == 'neregistrovan') {
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
	
	var projekcijeTable = $('#projekcijeTable');
	function getProjekcije(){
		$.get('SveProjekcijeServlet', function(data) {
			var sveProjekcije = data.sveProjekcije;
			for (projekcija in sveProjekcije) {
				projekcijeTable.append(
					'<tr>' +
						'<td>' + sveProjekcije[projekcija].film.naziv + '</td>' + 
						'<td>' + sveProjekcije[projekcija].tipProjekcije + '</td>' + 
						'<td>' + sveProjekcije[projekcija].sala.naziv + '</td>' + 
						'<td>' + sveProjekcije[projekcija].datumVreme + '</td>' + 
						'<td>' + sveProjekcije[projekcija].cenaKarte + '</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-danger" id="prikaziFilm">Prikazi</button>' +
						'</form>' + 
						'</td>' + 
					'</tr>')}
			})};

getProjekcije();
	
	
});