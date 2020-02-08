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
					 '<td>' +  new Date(korisnik.datumRegistracije).toLocaleDateString() + '</td>' + 
				  '</tr>' +
				   '<tr>'+			
				   '<td>'+'</td>'+
					  '<td>'+'<button type="button" class="btn btn-warning" id="izbrisiKorisnika">Izbrisi</button>' + '</td>'+
				    '</tr>');
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
			var korisnickoImeInput = $('#korImeInput');
			var izaberiUloguInput = $('#izaberiUlogu');
			var lozinkaInput = $('#lozinkaInput');
			korisnickoImeInput.val(korisnik.korisnickoIme);
			izaberiUlogu
			lozinkaInput.val(korisnik.lozinka);
			$('#izmeniKorisnika').on('click', function(event) {
				var korisnickoImeIzmena = korisnickoImeInput.val();
			    var lozinka = lozinkaInput.val();
			    var uloga = izaberiUloguInput.val();
			        if(korisnickoImeIzmena=="" || lozinka==""){ 
			        	alert("Neispravni podaci.Pokusajte opet."); }
					params = {
							'korisnickoIme':korisnik.korisnickoIme,
						'korisnickoImeIzmena': korisnickoImeIzmena, 
			            'lozinka': lozinka,
			            'uloga':uloga
							  }
					$.get('RegistracijaServlet', params, function(data) {
						$.get('LogoutServlet', function(data) {
							if (data.status == 'unauthenticated') {
								window.location.replace('index.html');
								return;
							}
						});

					});		
				});
			});
		};
	function getKarte(){
		$.post('PrikaziKorisnikaServlet', function(data) {
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
	
	getKorisnik();
	getKarte();
});				
			