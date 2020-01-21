$(document).ready(function() {
	var tabela = $('#tabelaFilm');
	function getFilm(){
		$.post('PrikaziFilmServlet', function(data) {
			var film = data.izabraniFilm;
			tabela.append(
					'<tr>' +
						'<td>' + film.id + '</td>' + 
						'<td>' +  film.naziv + '</td>' + 
						'<td>' +  film.reziser + '</td>' + 
						'<td>'  +  film.glumci + '</td>' + 
						'<td>' +  film.zanr + '</td>' + 
						'<td>' +  film.trajanje + '</td>' + 
						'<td>' +  film.distributer + '</td>' + 
						'<td>' +  film.zemljaPorekla + '</td>' + 
						'<td>' + film.godinaProizvodnje + '</td>' + 
						'<td>' + film.opis + '</td>' + 
						'<td>' + 
							'<form>' + 
							'<button type="button" class="btn btn-warning" id="izmeni">Izmeni</button>' +
							'</form>' + 
						'</td>' + 
					'</tr>' 
					);
		});
	};
	getFilm();
});				
			