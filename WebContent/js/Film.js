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
	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');				
	var glumciInput = $('#glumciInput');
	var zanrInput = $('#zanrInput');				
	var trajanjeInput = $('#trajanjeInput');
	var distributerInput = $('#distributerInput');				
	var zemljaPoreklaInput = $('#zemljaPoreklaInput');				
	var godinaProizvodnjeInput = $('#godinaProizvodnjeInput');				
	var opisInput = $('#opisInput');				
	function getFilm(){
		var id = window.location.search.slice(1).split('&')[0].split('=')[1];
		params = {'id':id};	
		var tabela = $('#tabelaFilm');
			$.get('PrikaziFilmServlet', params, function(data) {
				var izabraniFilm = data.izabraniFilm;
				tabela.append(
							 '<tr>'+
						      '<th scope="col">Naziv filma</th> ' + 	
								'<td>' +  izabraniFilm.naziv + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Reziser</th> ' + 	
								'<td>' +  izabraniFilm.reziser + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Glumci</th> ' + 	
								'<td>'  +  izabraniFilm.glumci + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Zanr</th> ' + 	
								'<td>' +  izabraniFilm.zanr + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Trajanje</th> ' + 	
								'<td>' +  izabraniFilm.trajanje + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Distributer</th> ' + 	
								'<td>' +  izabraniFilm.distributer + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Zemlja porekla</th> ' + 	
								'<td>' +  izabraniFilm.zemljaPorekla + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Godina proizvodnje</th> ' + 	
								'<td>' + izabraniFilm.godinaProizvodnje + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
						      '<th scope="col">Opis</th> ' + 	
								'<td>' + izabraniFilm.opis + '</td>' + 
					    	  '</tr>' +
							  '<tr>'+
							  	'<td>'+
								'<td><a href="ProjekcijeFilma.html?id=' + izabraniFilm.id + '" id="kupiKartu">Kupi kartu</a></td>' + 
								'</td>'+ '<td>' + '</td>'+
					    	  '</tr>'+
					    	  '<tr>'+
							  	'<td>'+
								'<button type="button" class="btn btn-warning" id="izbrisiFilm">Izbrisi</button>' +
								'</td>'+
					    	  '</tr>'	   
						);
				nazivInput.val(izabraniFilm.naziv);
				reziserInput.val(izabraniFilm.reziser);
				glumciInput.val(izabraniFilm.glumci);
				zanrInput.val(izabraniFilm.zanr);
				trajanjeInput.val(izabraniFilm.trajanje);
				distributerInput.val(izabraniFilm.distributer);
				zemljaPoreklaInput.val(izabraniFilm.zemljaPorekla);
				godinaProizvodnjeInput.val(izabraniFilm.godinaProizvodnje);
				opisInput.val(izabraniFilm.opis);

				if (data.ulogaKorisnika == "neregistrovan") {
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').hide();
					$('#izmeniFilm').hide();
					$('#zaAdmina').hide();
					$('#kupiKartu').hide();
					$('#izbrisiFilm').hide();
					$('#logout').hide();
				}else if(data.ulogaKorisnika=="ADMIN"){
					$('#prikaziKorisnike').show();
					$('#profilKorisnika').show();
					$('#logout').show();
					$('#zaAdmina').show();
					$('#kupiKartu').hide();
					$('#izmeniFilm').show();
					$('#izbrisiFilm').show();
				}else if(data.ulogaKorisnika=="KORISNIK"){
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').show();
					$('#logout').show();
					$('#zaAdmina').hide();
					$('#izmeniFilm').hide();
					$('#izbrisiFilm').hide();
					$('#kupiKartu').show();
				};	
			$('#tabelaFilm').on('click', '#izbrisiFilm', function(){
				params = { 'id':izabraniFilm.id };
				tabela.append(
						 '<tr>'+
						 	'<td>'+'</td>'+
							'<td>'+ '<button class="btn btn-warning" type="submit" id="potvrdiBrisanje">Potvrdi brisanje</button>' +'</td>'+
						'</tr>'+
					    '<tr>' );
				$('#tabelaFilm').on('click', '#potvrdiBrisanje', function(){
					$.post('PrikaziFilmServlet', params, function(data) {
					  if (data.status == 'unauthenticated') {
				            window.location.replace('Login.html');
				            return;
				        }
				        if (data.status == 'success') {
				            window.location.replace('FIlmovi.html');
				        }
					});
				});
				
			});
			$('#izmeniFilm').on('click', function(event) {
				var naziv = nazivInput.val();
		        var reziser = reziserInput.val(); 
		        var glumci = glumciInput.val();
				var zanr = zanrInput.val();
				var trajanje = trajanjeInput.val();
				var distributer = distributerInput.val();
				var zemljaPorekla = zemljaPoreklaInput.val();
				var godinaProizvodnje = godinaProizvodnjeInput.val();
				var opis = opisInput.val();
			    if (naziv == "" || trajanje == null || distributer == "" || zemljaPorekla == ""|| godinaProizvodnje == null || opis == "") {
			        alert("Popunite sva polja!");
			        return false;
			    }
				params = {
					'id':izabraniFilm.id,
					'naziv': naziv, 
		            'reziser': reziser,
		            'glumci': glumci, 
					'zanr': zanr, 
					'trajanje': trajanje, 
					'distributer': distributer, 
					'zemljaPorekla': zemljaPorekla, 
					'godinaProizvodnje': godinaProizvodnje, 
					'opis': opis
				};
				$.get('DodajFilmServlet', params, function(data) {
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

	};	
	getFilm();
});				
			