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
	function getProjekcija(){
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
							'<td>' + new Date(izabranaProjekcija.datum).toLocaleDateString() + '</td>' + 
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
							'<button type="button" class="btn btn-warning" id="izbrisiProjekciju">Izbrisi</button>' +
					     '</td>'+
					'</tr>'
						);
				if(brojSedista==0){
					$('#kupovinaKarte').remove();
				}
				if (data.ulogaKorisnika == "neregistrovan") {
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').hide();
					$('#izmeniProjekciju').hide();
					$('#zaAdmina').hide();
					$('#izbrisiProjekciju').hide();
					$('#kupiKartu').hide();
					$('#logout').hide();
				}else if(data.ulogaKorisnika=="ADMIN"){
					$('#prikaziKorisnike').show();
					$('#profilKorisnika').show();
					$('#logout').show();
					$('#zaAdmina').show();
					$('#kupiKartu').hide();
					$('#izmeniProjekciju').show();
					$('#izbrisiProjekciju').show();
				}else if(data.ulogaKorisnika=="KORISNIK"){
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').show();
					$('#zaAdmina').hide();
					$('#logout').show();
					$('#kupiKartu').show();
					$('#izmeniProjekciju').hide();
					$('#izbrisiProjekciju').hide();
				}
				$('#tabelaProjekcija').on('click', '#izbrisiProjekciju', function(){
					params = { 'id':izabranaProjekcija.id };
					tabela.append(
							'<tr>'+
							 	'<td>'+'</td>'+
								 '<td>'+
					                  '<button class="btn btn-warning" type="submit" id="potvrdiBrisanje">Potvrdi brisanje</button>' +
								  '</td>'+
							'</tr>'+
						    '<tr>' );
					$('#tabelaProjekcija').on('click', '#potvrdiBrisanje', function(){
						$.post('ProjekcijeZaFilmServlet', params, function(data) {
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
		};
	getProjekcija();
});				
			