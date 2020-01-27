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
	
	var tabela = $('#tabelaProjekcija');
		$.get('PrikaziProjekcijuServlet', function(data) {
			var izabranaProjekcija = data.izabranaProjekcija;
			tabela.append(
					'<tr>' +
					'<td>' + izabranaProjekcija + '</td>' + 
					//'<td>' + izabranaProjekcija.tipProjekcije + '</td>' + 
					//'<td>' + izabranaProjekcija.sala.naziv + '</td>' + 
					//'<td>' + izabranaProjekcija.datumVreme + '</td>' + 
					//'<td>' + izabranaProjekcija.cenaKarte + '</td>' +
					'<td>' +
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeniProjekciju">Izmeni</button>' +
							'</form>' + 
						'</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-warning" id="izbrisiProjekciju">Izbrisi</button>' +
						'</form>' + 
					'</td>' + 
					'</tr>' 
					);
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#izmeniFilm').show();
				$('#izbrisiFilm').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();

			}
		});
		
		$('#tabelaProjekcija').on('click', '#izbrisiProjekciju', function(){
			$(this).closest ('tr').remove ();
			params = {
					'id':izabranaProjekcija.id
					};
			$.get('DodajProjekcijuServlet', params, function(data) {
				
			});
		});
});				
			