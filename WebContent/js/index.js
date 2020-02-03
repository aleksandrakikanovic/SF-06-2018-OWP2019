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
	
	
	var projekcijeTable = $('#projekcijeTable');
	function getProjekcije(){
		$.get('SveProjekcijeServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#registracija').show();
				$('#izvestaj').hide();
				$('#logout').hide();
				$('#login').show();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#registracija').hide();
				$('#logout').show();
				$('#izvestaj').show();
				$('#login').hide();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#registracija').hide();
				$('#logout').show();
				$('#izvestaj').hide();
				$('#login').hide();

			}
			var sveProjekcije = data.sveProjekcije;
			for (projekcija in sveProjekcije) {
				projekcijeTable.append(
					'<tr>' +
					'<td><a href="Projekcija.html?id=' + sveProjekcije[projekcija].id + '">' + sveProjekcije[projekcija].film.naziv + '</a></td>' + 
						'<td>' + sveProjekcije[projekcija].tipProjekcije + '</td>' + 
						'<td>' + sveProjekcije[projekcija].sala.naziv + '</td>' + 
						'<td>' + sveProjekcije[projekcija].datum + '</td>' + 
						'<td>' + sveProjekcije[projekcija].vreme + '</td>' + 
						'<td>' + sveProjekcije[projekcija].cenaKarte + '</td>' + 
					'</tr>')}
			})};
	
	var filmCmb = $('#izaberiFilm');
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmCmb.append(
					'<option value="'+sviFilmovi[film].id+ '">' + sviFilmovi[film].naziv+ '</option>'
				
				)}
			})};	


getProjekcije();
getFilmovi();
	
});