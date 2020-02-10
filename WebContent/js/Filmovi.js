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
	var filmoviTable = $('#filmoviTable');
	var pretragaFilm = $('#pretragaFilm');
	var pretragaZanr = $('#pretragaZanr');
	var pretragaDistributer = $('#pretragaDistributer');
	var pretragaZemljaPorekla = $('#pretragaZemljaPorekla');
	var pretragaTrajanje1 = $('#pretragaTrajanje1');
	var pretragaTrajanje2 = $('#pretragaTrajanje2');
	var pretragaGodinaProizvodnje = $('#pretragaGodinaProizvodnje');
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#dodajFilm').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#dodajFilm').show();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#dodajFilm').hide();
				$('#logout').show();
			}
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmoviTable.append(
					'<tr>' +
					'<td><a href="Film.html?id=' + sviFilmovi[film].id + '" id="prikaziFilm">' + sviFilmovi[film].naziv + '</a></td>' + 
						'<td>' + sviFilmovi[film].zanr + '</td>' + 
						'<td>' + sviFilmovi[film].trajanje + '</td>' + 
						'<td>' + sviFilmovi[film].distributer + '</td>' + 
						'<td>' + sviFilmovi[film].zemljaPorekla + '</td>' + 
						'<td>' + sviFilmovi[film].godinaProizvodnje + '</td>' + 	
					'</tr>')}
			})
			$('#pretraziFilm').on('click', function(event) {
				var film = pretragaFilm.val();
				var zanr = pretragaZanr.val();
				var distributer = pretragaDistributer.val();
				var zemljaPorekla = pretragaZemljaPorekla.val();
				var trajanje1 = pretragaTrajanje1.val();
				var trajanje2 = pretragaTrajanje2.val();
				var godinaProizvodnje = pretragaGodinaProizvodnje.val();

				params = {
						'film':film,
						'zanr':zanr,
						'distributer':distributer,
						'zemljaPorekla':zemljaPorekla,
						'trajanje1':trajanje1,
						'trajanje2':trajanje2,
						'godinaProizvodnje':godinaProizvodnje,
				}
				$.post('SviFilmoviServlet',params, function(data) {
					var filtriraniFilmovi = data.filtriraniFilmovi;
					$("#filmoviTable > tbody").empty();
					for (film in filtriraniFilmovi) {
						filmoviTable.append(
								'<tr>' +
								'<td><a href="Film.html?id=' + filtriraniFilmovi[film].id + '" id="prikaziFilm">' + filtriraniFilmovi[film].naziv + '</a></td>' + 
									'<td>' + filtriraniFilmovi[film].zanr + '</td>' + 
									'<td>' + filtriraniFilmovi[film].trajanje + '</td>' + 
									'<td>' + filtriraniFilmovi[film].distributer + '</td>' + 
									'<td>' + filtriraniFilmovi[film].zemljaPorekla + '</td>' + 
									'<td>' + filtriraniFilmovi[film].godinaProizvodnje + '</td>' + 	
								'</tr>')}
				});
			});
			
			
			
	};	
	getFilmovi();
});