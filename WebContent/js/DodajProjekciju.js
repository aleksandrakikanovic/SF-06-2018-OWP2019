$(document).ready(function() {
        
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

	var filmCmb = $('#izaberiFilm');
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmCmb.append(
					'<option value="'+sviFilmovi[film].id+ '">' + sviFilmovi[film].naziv+ '</option>'
				
				)}
			})};	
var izaberiFilm = $('#izaberiFilm');
var izaberiTipProjekcije = $('#izaberiTipProjekcije');
var izaberiSalu =  $('#izaberiSalu');
var datumInput =  $('#datumInput');
var vremeInput =  $('#vremeInput');

var cenaKarteInput =  $('#cenaKarteInput');
$('#dodajProjekcijuButton').on('click', function(event) {
    var film = izaberiFilm.val();
    var tipProjekcije = izaberiTipProjekcije.val(); 
    var sala = izaberiSalu.val(); 
    var datum = datumInput.val(); 
    var vreme = vremeInput.val(); 
    var cenaKarte = cenaKarteInput.val();
    params = {
        'film': film, 
        'tipProjekcije': tipProjekcije,
        'sala': sala, 
        'datum': datum, 
        'vreme':vreme,
        'cenaKarte': cenaKarte, 
    };
    $.post('DodajProjekcijuServlet', params, function(data) {
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