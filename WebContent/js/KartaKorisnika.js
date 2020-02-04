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
	
	function getKarta(){
		var id = window.location.search.slice(1).split('&')[0].split('=')[1];
		params = {
				'id':id
				};	
		var tabela = $('#kartaKorisnika');
		$.get('PrikaziKorisnikaServlet',params, function(data) {
			var izabranaKarta = data.izabranaKarta;
			tabela.append(
						'<tr>'+
					     '<th scope="col">Naziv filma</th> ' + 	
						'<td><a href="Film.html?id=' + izabranaKarta.projekcija.film.id + '" id="prikaziFilm">' + izabranaKarta.projekcija.film.naziv + '</a></td>' + 
						'</tr>'+
						'<tr>'+
					     '<th scope="col">Datum i vreme projekcije</th> ' + 	
						'<td><a href="Film.html?id=' + izabranaKarta.projekcija.film.id +
						'" id="prikaziProjekciju">' + izabranaKarta.projekcija.datum + ' ' + izabranaKarta.projekcija.vreme + '</a></td>' + 
						'</tr>'+
						'<tr>'+
					     '<th scope="col">Tip projekcije</th> ' + 	
						'<td>'	+ izabranaKarta.projekcija.tipProjekcije + '</td>' +
						'</tr>'+
						'<tr>'+
					     '<th scope="col">Sala</th> ' + 	
						'<td>'	+ izabranaKarta.projekcija.sala.naziv + '</td>' +
						'</tr>'+
						'<tr>'+
					     '<th scope="col">Sediste</th> ' + 	
						'<td>'	+ izabranaKarta.sediste.redniBroj + '</td>' +
						'</tr>'+
						'<tr>'+
					     '<th scope="col">Cena karte</th> ' + 	
						'<td>'	+ izabranaKarta.projekcija.cenaKarte + '</td>' +
						'</tr>'+
						'<tr>'+
						'<td>'+
						'<button type="button" class="btn btn-warning" id="izbrisiKartu">Izbrisi</button>' +
						'</td>'+
			    	  '</tr>'
				   	); 
			$('#kartaKorisnika').on('click', '#izbrisiKartu', function(){
				params = { 'id':izabranaKarta.id };
				tabela.append(
						 '<tr>'+
						 	'<td>'+'</td>'+
							  	'<td>'+
				                  '<button class="btn btn-warning" type="submit" id="potvrdiBrisanje">Potvrdi brisanje</button>' +
								'</td>'+
						'</tr>'+
					    '<tr>' 
				);
				$('#kartaKorisnika').on('click', '#potvrdiBrisanje', function(){
					$.get('KartaIzmenaServlet', params, function(data) {
					  if (data.status == 'unauthenticated') {
				            window.location.replace('Login.html');
				            return;
				        }
				        if (data.status == 'success') {
				            window.location.replace('Korisnik.html');
				        }
				});
				});
			});
			});

		};
	getKarta();
});				
		