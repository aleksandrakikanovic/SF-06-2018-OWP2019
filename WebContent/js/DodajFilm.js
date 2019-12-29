$(document).ready(function() { 
	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');
	var glumciInput =  $('#glumciInput');
	var zanrInput =  $('#zanrInput');
	var trajanjeInput =  $('#trajanjeInput');
	var distributerInput =  $('#distributerInput');
	var zemljaPoreklaInput =  $('#zemljaPoreklaInput');
	var godinaProizvodnjeInput =  $('#godinaProizvodnjeInput');
	var opisInput =  $('#reziserInput');
	var dodajFilmButton =  $('#dodajFilmButton');
	console.log("provera");


$('#dodajFilmButton').on('click', function(event) {
		var naziv = nazivInput.val();
        var reziser = reziserInput.val();
        var glumci = glumciInput.val();
		var zanr = zanrInput.val();
		var trajanje = trajanjeInput.val();
		var distributer = distributerInput.val();
		var zemljaPorekla = zemljaPoreklaInput.val();
		var godinaProizvodnje = godinaProizvodnjeInput.val();
		var opis = opisInput.val();
		console.log('naziv: ' + naziv);
		console.log('reziser: ' + reziser);

		params = {
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
		$.post('DodajFilmServlet', params, function(data) {
			console.log('test')
			console.log(data);
			console.log(reziserInput.val());


			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('index.html');
			}
		});
		
		event.preventDefault();
		return false;
});
});