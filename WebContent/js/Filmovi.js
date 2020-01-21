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
						'<button type="button" class="btn btn-danger" id="prikaziFilm">Prikazi</button>' +
						'</form>' + 
						'</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-danger" id="izbrisiFilm">Izbrisi</button>' +
						'</form>' + 
					'</td>' + 
					'</tr>')}
			})};
			
	//brisanje iz tabele, ne iz baze(ispravicu do odbrane)
	$('#filmoviTable').on('click', '#izbrisiFilm', function(){
		$(this).closest ('tr').remove ();
		params = {
			//	'action': 'remove',
				//'index': index
				};
		$.post('DodajFilmServlet', params, function(data) {
		});
});
	 
	$('#filmoviTable').on('click', '#prikaziFilm', function(){
        var currentRow=$(this).closest("tr"); 
        var id = currentRow.find("td:eq(0)").text(); 
        var naziv = currentRow.find("td:eq(1)").text();
        var reziser = currentRow.find("td:eq(2)").text(); 
        var glumci = currentRow.find("td:eq(3)").text(); 
        var zanr = currentRow.find("td:eq(4)").text();
        var trajanje = currentRow.find("td:eq(5)").text();
        var distributer = currentRow.find("td:eq(6)").text(); 
        var zemljaPorekla = currentRow.find("td:eq(7)").text();
        var godinaProizvodnje = currentRow.find("td:eq(8)").text(); 
        var opis = currentRow.find("td:eq(9)").text(); 
        var data="Id:"+id+"\nNaziv: "+naziv+"\nReziser: "+reziser+"\nGlumci: "+glumci+"\nZanr: "+zanr+"\nTrajanje: "+trajanje+
        "\nDistributer: "+distributer+"\nZemlja porekla: "+zemljaPorekla+"\nGodina proizvodnje: "+godinaProizvodnje+"\nOpis: "+opis;
        alert(data);
    
        params = {
    			'id' : id,
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
		$.post('PrikaziFilmServlet', params, function(data) {
			window.location.replace('Film.html');

		});
});
	
	getFilmovi();
	
});

