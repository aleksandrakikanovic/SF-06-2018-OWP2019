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
	var izaberiFilm = $('#izaberiFilm');
	var izaberiTipProjekcije = $('#izaberiTipProjekcije');
	var izaberiSalu =  $('#izaberiSalu');
	var datumInput =  $('#datumInput');
	var vremeInput =  $('#vremeInput');
	var cenaKarteInput =  $('#cenaKarteInput');
	var filmCmb = $('#izaberiFilm');
	
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmCmb.append(
					'<option value="'+sviFilmovi[film].id+ '">' + sviFilmovi[film].naziv+ '</option>'
				)}
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
			})};	
		$('#dodajProjekcijuButton').on('click', function(event) {
		    var film = izaberiFilm.val();
		    var tipProjekcije = izaberiTipProjekcije.val(); 
		    var sala = izaberiSalu.val(); 
		    var datum = datumInput.val(); 
		    var vreme = vremeInput.val(); 
		    var cenaKarte = cenaKarteInput.val();
		    if(film=="" || tipProjekcije == "" || sala == "" ||  datum == "" || vreme == "" || cenaKarte==null){
		    	alert('Popunite sva polja!');
		    }
		    params = {
		        'film': film, 
		        'tipProjekcije': tipProjekcije,
		        'sala': sala, 
		        'datum': datum, 
		        'vreme':vreme,
		        'cenaKarte': cenaKarte, 
		    };
		    $.post('DodajProjekcijuServlet', params, function(data) {
		    	var postoji = data.postoji;
		    	if(postoji==true){
		    		alert('Sala je zauzeta!')
		    	}
		        if (data.status == 'unauthenticated') {
		            window.location.replace('Login.html');
		            return;
		        }
		        if (data.status == 'success') {
		            window.location.replace('index.html');
		        }
		    });
		});
	getFilmovi();
});