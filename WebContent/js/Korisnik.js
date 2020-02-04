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

	var tabela = $('#tabelaKorisnik');
	var tabelaKarte = $('#tabelaKarte');

	function getKorisnik(){
		$.post('PrikaziKorisnikaServlet', function(data) {
			var korisnik = data.ulogovanKorisnik;
			tabela.append(
						 '<tr>'+
					      '<th scope="col">Korisnicko ime</th> ' + 	
							'<td>' + korisnik.korisnickoIme + '</td>' + 
				    	  '</tr>' +
						  '<tr>'+
					      '<th scope="col">Datum registracije</th> ' + 	
							'<td>' +  korisnik.datumRegistracije + '</td>' + 
				    	  '</tr>' +
						  '<tr>'+
					      '<th scope="col">Uloga</th> ' + 	
							'<td>' + korisnik.uloga + '</td>' + 
				    	  '</tr>' +
						  '<tr>'+
						  	'<td>'+'</td>'+
						  	'<td align="right">'+
							'<button type="button" class="btn btn-warning" id="izmeniKorisnika">Izmeni</button>' +
							'</td>'+
				    	  '</tr>'
					);
		});
	};
	
	function getKarte(){
		$.post('PrikaziKorisnikaServlet', function(data) {
			var karteKorisnika = data.karteKorisnika;
			for (karta in karteKorisnika){
			tabelaKarte.append(
						'<tr>'+
							'<td>' + karteKorisnika[karta].korisnik.korisnickoIme + '</td>' + 
							'<td><a href="KartaKorisnika.html?id=' + karteKorisnika[karta].id + '">'
							+ karteKorisnika[karta].datum + '</a></td>' +
						'</tr>'
							
				    	 					); 
			};
		});
	};
	getKorisnik();
	getKarte();
});				
			