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
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	params={'id':id };
	var tabela = $('#tabelaProjekcija');
		$.get('PrikaziProjekcijuServlet',params, function(data) {
			var izabranaProjekcija = data.izabranaProjekcija;	
			var brojSedista = data.slobodnaSedista;
			tabela.append(
				'<tr>'+
				      '<th scope="col">Naziv filma</th> ' + 	
				      '<td>' + izabranaProjekcija.film.naziv + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Tip projekcije</th> ' + 	
						'<td>' + izabranaProjekcija.tipProjekcije + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Sala</th> ' + 	
						'<td>' + izabranaProjekcija.sala.naziv + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Datum</th> ' + 	
						'<td>' + izabranaProjekcija.datum + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Vreme</th> ' + 	
						'<td>' + izabranaProjekcija.vreme + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Cena karte</th> ' + 	
						'<td>' + izabranaProjekcija.cenaKarte + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Slobodna sedista</th> ' + 	
						'<td>' + brojSedista + '</td>' +
			    '</tr>' +
				'<tr id="kupovinaKarte">'+
					'<td>' + '</td>'+ 
						'<td><a href="KupiKartu.html?id=' + izabranaProjekcija.id + '" id="kupiKartu">Kupi kartu</a></td>' + 
			    '</tr>'+
			    '<tr>'+
			    	'<td>'+'</td>'+
			    	'<td align="center">'+
						'<button type="button" class="btn btn-warning" id="izmeniFilm">Izmeni</button>' +
					'</td>'+
				'</tr>'+
			    '<tr>'+
			    	'<td>'+'</td>'+
			    	'<td align="center">'+
						'<button type="button" class="btn btn-warning" id="izbrisiFilm">Izbrisi film</button>' +
				     '</td>'+
				'</tr>'
					);
			if(brojSedista==0){
				$('#kupovinaKarte').remove();
		}
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();
				$('#kupiKartu').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#kupiKartu').hide();
				$('#izmeniFilm').show();
				$('#izbrisiFilm').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#logout').show();
				$('#kupiKartu').show();
				$('#izmeniFilm').hide();
				$('#izbrisiFilm').hide();
			}
			
		$('#tabelaProjekcija').on('click', '#izbrisiProjekciju', function(){
			$(this).closest ('tr').remove();
			params = {
					'id':izabranaProjekcija.id
					};
			$.get('DodajProjekcijuServlet', params, function(data) {
				
			});
		});

			$('#tabelaProjekcija').on('click', '#izmeniProjekciju', function(){
				params = {
						'id':izabranaProjekcija.id
						};
				$.get('DodajProjekcijuServlet', params, function(data) {
					
				});
		});
	});		
});				
			