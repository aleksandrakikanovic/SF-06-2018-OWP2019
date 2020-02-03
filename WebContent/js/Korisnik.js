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
	var korIme = window.location.search.slice(1).split('&')[0].split('=')[1];
	params={
			'korIme':korIme
	};

	var tabela = $('#tabelaKorisnik');
	function getKorisnik(){
		$.post('PrikaziKorisnikaServlet',params, function(data) {
			var korisnik = data.izabraniKorisnik;
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
						  	'<td align="center">'+
							'<button type="button" class="btn btn-warning" id="izmeniKorisnika">Izmeni korisnika</button>' +
							'</td>'+
				    	  '</tr>'
					);
		});
	};
	getKorisnik();
});				
			