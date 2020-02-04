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
	var tabela = $('#kupiKartuFilm2');
	
	function kupiKartu(){
		$.post('PrikaziProjekcijuServlet',params, function(data) {
			var izabranaProjekcija = data.zaKartu;
			$.post('KupiKartuServlet',params, function(data) {
				var sediste = data.sediste;
				alert(sediste);

			
				tabela.append(
				'<tr>'+
				      '<th scope="col">Naziv filma</th> ' + 	
						'<td><a href="Film.html?id=' + izabranaProjekcija.film.id  + '" id="prikaziFilm">' + izabranaProjekcija.film.naziv + '</a></td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Datum i vreme projekcije</th> ' + 	
						'<td><a href="Projekcija.html?id=' + izabranaProjekcija.id  + '" id="prikaziFilm">' + 'Datum: ' + 
						izabranaProjekcija.datum + ' Vreme: ' + izabranaProjekcija.vreme + '</a></td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Sala</th> ' + 	
						'<td>' + izabranaProjekcija.sala.naziv + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Tip projekcije</th> ' + 	
						'<td>' + izabranaProjekcija.tipProjekcije + '</td>' + 
			    '</tr>' +
				'<tr>'+
				      '<th scope="col">Cena karte</th> ' + 	
						'<td>' + izabranaProjekcija.cenaKarte + '</td>' + 
			    '</tr>' +
				'<tr>'+
			      '<th scope="col">Broj sedista</th> ' + 	
					'<td>'+ sediste + '</td>'+
			    '</tr>' +
			    '<tr>'+
				 	'<td>'+'</td>'+
					  	'<td>'+
		                  '<button class="btn btn-sm" type="submit" id="potvrdiKupovinuKarte">Potvrdi kupovinu</button>' +
						'</td>'+
				'</tr>'+
			    '<tr>' );
			
	});

		});
	
};
kupiKartu();
});