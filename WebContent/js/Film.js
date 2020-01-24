$(document).ready(function() {
	
	$('#logout').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			if (data.status == 'unauthenticated') {
				window.location.replace('index.html');
				return;
			}
		});
	
		event.preventDefault();
		return false;
	});
	var tabela = $('#tabelaFilm');
		$.get('PrikaziFilmServlet', function(data) {
			var izabraniFilm = data.izabraniFilm;
			tabela.append(
					'<tr>' +
						'<td>' + izabraniFilm.id + '</td>' + 
						'<td>' +  izabraniFilm.naziv + '</td>' + 
						'<td>' +  izabraniFilm.reziser + '</td>' + 
						'<td>'  +  izabraniFilm.glumci + '</td>' + 
						'<td>' +  izabraniFilm.zanr + '</td>' + 
						'<td>' +  izabraniFilm.trajanje + '</td>' + 
						'<td>' +  izabraniFilm.distributer + '</td>' + 
						'<td>' +  izabraniFilm.zemljaPorekla + '</td>' + 
						'<td>' + izabraniFilm.godinaProizvodnje + '</td>' + 
						'<td>' + izabraniFilm.opis + '</td>' + 
						'<td>' + 
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeniFilm">Izmeni</button>' +
							'</form>' + 
						'</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-warning" id="izbrisiFilm">Izbrisi</button>' +
						'</form>' + 
					'</td>' + 
					'</tr>' 
					);
			if (data.ulogaKorisnika == null) {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=='ADMIN'){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#izmeniFilm').show();
				$('#izbrisiFilm').show();
			}else if(data.ulogaKorisnika=='KORISNIK'){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();

			}
		});
		
		$('#filmoviTable').on('click', '#izbrisiFilm', function(){
			$(this).closest ('tr').remove ();
			params = {
					'id':izabraniFilm.id
					};
			$.get('DodajFilmServlet', params, function(data) {
				
			});
		});
});				
			