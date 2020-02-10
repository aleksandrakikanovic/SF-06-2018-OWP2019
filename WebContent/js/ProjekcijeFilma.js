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
	function getAll(){
		var id = window.location.search.slice(1).split('&')[0].split('=')[1];
		params = {
				'id':id
				};	
		var f = $('#filmProjekcije');
		$.get('ProjekcijeZaFilmServlet', params, function(data) {
			var projekcijeFilma = data.projekcijeFilma;
			for (projekcija in projekcijeFilma) {
				f.append(
					'<tr>' +
					'<td><a href="Projekcija.html?id=' + projekcijeFilma[projekcija].id + '">' + projekcijeFilma[projekcija].film.naziv + '</a></td>' + 
						'<td>' + projekcijeFilma[projekcija].tipProjekcije + '</td>' + 
						'<td>' + projekcijeFilma[projekcija].sala.naziv + '</td>' + 
						'<td>' + new Date(projekcijeFilma[projekcija].datum).toLocaleDateString() + '</td>' + 
						'<td>' + projekcijeFilma[projekcija].vreme + '</td>' + 
						'<td>' + projekcijeFilma[projekcija].cenaKarte + '</td>' + 
					'</tr>')}
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#profilKorisnika').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#profilKorisnika').show();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#profilKorisnika').show();
				$('#logout').show();
			}
		})};
	getAll();

});