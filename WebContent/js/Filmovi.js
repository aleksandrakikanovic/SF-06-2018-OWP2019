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
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#dodajProjekciju').hide();
				$('#profilKorisnika').hide();
				$('#dodajFilm').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').show();
				$('#dodajFilm').show();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').hide();
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
			})};
			
	getFilmovi();
	
});