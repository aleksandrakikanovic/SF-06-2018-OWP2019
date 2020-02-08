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
	var korisnickoIme = window.location.search.slice(1).split('&')[0].split('=')[1];
	params = {'korisnickoIme':korisnickoIme};	
	var tabela = $('#tabelaKorisnik');
	function getKorisnik(){
		$.post('IzmenaKorisnikaServlet',params, function(data) {
			var korisnik = data.izabraniKorisnik;
			var karteKorisnika = data.karteKorisnika;
			tabela.append(
				 '<tr>'+
					 '<th scope="col">Korisnicko ime</th> ' + 	
					 '<td>' + korisnik.korisnickoIme + '</td>' + 
				  '</tr>' +
				  '<tr>'+
					 '<th scope="col">Datum registracije</th> ' + 	
					 '<td>' +  new Date(korisnik.datumRegistracije).toLocaleDateString() + '</td>' + 
				  '</tr>' +
				  '<tr>'+
					 '<th scope="col">Uloga</th> ' + 	
					 '<td>' + korisnik.uloga + '</td>' + 
				   '</tr>' +
				   '<tr>'+			
				   '<td>'+'</td>'+
					  '<td>'+'<button type="button" class="btn btn-warning" id="izbrisiKorisnika">Izbrisi</button>' + '</td>'+
				    '</tr>');
			
			var korisnickoImeInput = $('#korImeInput');
			var lozinkaInput = $('#lozinkaInput');
			korisnickoImeInput.val(korisnik.korisnickoIme);
			lozinkaInput.val(korisnik.lozinka);
			$('#izmeniKorisnika').on('click', function(event) {
				var korisnickoImeIzmena = korisnickoImeInput.val();
			    var lozinka = lozinkaInput.val();
			        if(korisnickoImeIzmena=="" || lozinka==""){ 
			        	alert("Neispravni podaci.Pokusajte opet."); }
					params = {
							'korisnickoIme':korisnik.korisnickoIme,
						     'korisnickoImeIzmena': korisnickoImeIzmena, 
			                  'lozinka': lozinka,
							  };
					$.get('RegistracijaServlet', params, function(data) {
				        if (data.status == 'success') {
				            window.location.replace('Korisnici.html');
				        }

					});		
				});
			
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#izaberiUlogu').show();
				$('#tabelaKarte').hide();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#izaberiUlogu').hide();
				$('#logout').show();
			};
		$('#tabelaKorisnik').on('click', '#izbrisiKorisnika', function(){
				params = { 'korisnickoImeIzbrisi':korisnik.korisnickoIme };
				tabela.append(
						 '<tr>'+
						 	'<td>'+'</td>'+
							'<td>'+ '<button class="btn btn-warning" type="submit" id="potvrdiBrisanje">Potvrdi brisanje</button>' +'</td>'+
						'</tr>'+
					    '<tr>' );
				$('#tabelaKorisnik').on('click', '#potvrdiBrisanje', function(){
					$.get('IzmenaKorisnikaServlet', params, function(data) {
					  if (data.status == 'unauthenticated') {
				            window.location.replace('Login.html');
				            return;
				        }
				        if (data.status == 'success') {
				            window.location.replace('index.html');
				        }
					});
				});
			});
		}); 
		
		function getKarte(){
			$.post('IzmenaKorisnikaServlet', function(data) {
				var karteKorisnika = data.karteKorisnika;
				if(karteKorisnika.length==0){
					tabelaKarte.append(
							'<tr>'+
								'<td>'+ '</td>' + 
								'<td>Nema kupljene karte</td>' +
							'</tr>'); 
				}else{
					for (karta in karteKorisnika){
						tabelaKarte.append(
									'<tr>'+
										'<td>' + karteKorisnika[karta].korisnik.korisnickoIme + '</td>' + 
										'<td><a href="KartaKorisnika.html?id=' + karteKorisnika[karta].id + '">'+ new Date(karteKorisnika[karta].datum).toLocaleDateString() + '</a></td>' +
									'</tr>'); 
				}
				};
			});
		};
	};

getKorisnik();
getKarte();

});				
