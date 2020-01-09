$(document).ready(function() { 
	
	var filmoviTable = $('#filmoviTable');
	
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmoviTable.append(
						
					'<tr>' +
						'<td>' + sviFilmovi[film].id + '</td>' + 
						'<td>' + sviFilmovi[film].naziv + '</td>' + 
						'<td>' + sviFilmovi[film].reziser + '</td>' + 
						'<td>' + sviFilmovi[film].glumci + '</td>' + 
						'<td>' + sviFilmovi[film].zanr + '</td>' + 
						'<td>' + sviFilmovi[film].trajanje + '</td>' + 
						'<td>' + sviFilmovi[film].distributer + '</td>' + 
						'<td>' + sviFilmovi[film].zemljaPorekla + '</td>' + 
						'<td>' + sviFilmovi[film].godinaProizvodnje + '</td>' + 
						'<td>' + sviFilmovi[film].opis + '</td>' + 
						'<td>' + 
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeniFilm">Izmeni</button>' +
								//'<input type="submit" value="Izmeni">' + 
							'</form>' + 
						'</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-danger" id="izbrisiFilm">Izbrisi</button>' +
							//'<input type="submit" value="Izbrisi">' + 
						'</form>' + 
					'</td>' + 

					'</tr>')}
			})};
	getFilmovi();
});

//console.log(sviFilmovi);