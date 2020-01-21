(document).ready(function() { 
		$.post('SviFilmoviServlet', function(data) {
			var izabranFilm = $('izabranFilm');
			var film = data.izabranFilm;
			izabranFilm.append(
					'<ul>' +
						'<li>' + '<p>id</p>' + film.id + '</li>' + 
						'<li>' +'<p>naziv </p>' +  film.naziv + '</li>' + 
						'<li>' +'<p> reziser</p>' +  film.reziser + '</li>' + 
						'<li>' +'<p>glumci </p>' +  film.glumci + '</li>' + 
						'<li>' +'<p> zanr</p>' +  film.zanr + '</li>' + 
						'<li>' +'<p>trajanje </p>' +  film.trajanje + '</li>' + 
						'<li>' +'<p>distributer </p>' +  film.distributer + '</li>' + 
						'<li>' +'<p>zemljaPorekla </p>' +  film.zemljaPorekla + '</li>' + 
						'<li>' + '<p>godinaProizvodnje </p>' + film.godinaProizvodnje + '</li>' + 
						'<li>' + '<p>opis </p>' + film.opis + '</td>' + 
						'<li>' + 
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeni">Izmeni</button>' +
							'</form>' + 
						'</li>' + 
					'</ul>');
		});
});
						
			