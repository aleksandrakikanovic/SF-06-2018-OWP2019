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
	
	function getFilm(){
		var id = window.location.search.slice(1).split('&')[0].split('=')[1];
		params = {
				'id':id
				};	
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
							  	'<td>'+'</td>'+
							  	'<td align="center">'+
								'<button type="button" class="btn btn-warning" id="kupiKartu">Kupi kartu</button>' +
								'</td>'+
					    	  '</tr>'
						);
				if (data.ulogaKorisnika == "neregistrovan") {
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').hide();
					$('#izmeniFilm').hide();
					$('#izbrisiFilm').hide();
					$('#logout').hide();
				}else if(data.ulogaKorisnika=="ADMIN"){
					$('#prikaziKorisnike').show();
					$('#profilKorisnika').show();
					$('#logout').show();
					$('#izmeniFilm').show();
					$('#izbrisiFilm').show();
				}else if(data.ulogaKorisnika=="KORISNIK"){
					$('#prikaziKorisnike').hide();
					$('#profilKorisnika').show();
					$('#logout').show();
					$('#izmeniFilm').hide();
					$('#izbrisiFilm').hide();
	
				}
			});
			
			$('#filmoviTable').on('click', '#izbrisiFilm', function(){
				$(this).closest ('tr').remove();
				params = {
						'id':izabraniFilm.id
						};
				$.get('DodajFilmServlet', params, function(data) {
					
				});
			});
			$('#filmoviTable').on('click', '#kupiKartu', function(){
				$(this).closest ('tr').remove();
				params = {
						'id':izabraniFilm.id
						};
				$.get('KupiKartuServlet', params, function(data) {
					
				});
			});
		};
	getFilm();
});				
			