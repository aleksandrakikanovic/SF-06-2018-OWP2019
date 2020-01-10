$(document).ready(function() { 
	
	var filmoviTable = $('#filmoviTable');
	var izmeniFilmButton =  $('#izmeniFilm');
	var izbrisiFilmButton =  $('#izbrisiFilm');

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
			
	//brisanje iz tabele, ne iz baze(ispravicu do odbrane)
	$('#filmoviTable').on('click', '#izbrisiFilm', function(){
		$(this).closest ('tr').remove ();
		//var index = row.index() - 1; 
		params = {
			//	'action': 'remove',
				//'index': index
				};

		$.post('DodajFilmServlet', params, function(data) {
		
		});
		
});
		
	getFilmovi();
});

//console.log(sviFilmovi);