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
	$.post('SviFilmoviServlet', function(data) {
		if (data.ulogaKorisnika == "neregistrovan") {
			$('#prikaziKorisnike').hide();
			$('#profilKorisnika').hide();
			$('#logout').hide();
		}else if(data.ulogaKorisnika=="ADMIN"){
			$('#prikaziKorisnike').show();
			$('#profilKorisnika').show();
			$('#logout').show();
		}else if(data.ulogaKorisnika=="KORISNIK"){
			$('#prikaziKorisnike').hide();
			$('#profilKorisnika').show();
			$('#logout').show();
		}
	});
	$('#dodajFilmButton').on('click', function(event) {
		var naziv = nazivInput.val();
        var reziser = reziserInput.val(); //opciono
        var glumci = glumciInput.val(); //opciono
		var zanr = zanrInput.val(); //opciono
		var trajanje = trajanjeInput.val();
		var distributer = distributerInput.val();
		var zemljaPorekla = zemljaPoreklaInput.val();
		var godinaProizvodnje = godinaProizvodnjeInput.val();
		var opis = opisInput.val(); //opciono
	    if (naziv == "" || trajanje == null || distributer == "" || zemljaPorekla == ""|| godinaProizvodnje == null
	    	|| opis == "") {
	        alert("Popunite sva polja!");
	        return false;
	    }
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
			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if (data.status == 'success') {
				window.location.replace('Filmovi.html');
			}
		});
		
	});
});