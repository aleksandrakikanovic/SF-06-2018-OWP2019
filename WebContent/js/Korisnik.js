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
	function getKorisnik(){
		$.post('PrikaziKorisnikaServlet', function(data) {
			var korisnik = data.izabraniKorisnik;
			tabela.append(
					'<tr>' +
						'<td>' + korisnik.korisnickoIme + '</td>' + 
						'<td>' +  korisnik.lozinka + '</td>' + 
						'<td>' +  korisnik.datumRegistracije + '</td>' + 
						'<td>'  +  korisnik.uloga + '</td>' + 
						'<td>' + 
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeni">Izmeni</button>' +
							'</form>' + 
						'</td>' + 
					'</tr>' 
					);
		});
	};
	getKorisnik();
});				
			