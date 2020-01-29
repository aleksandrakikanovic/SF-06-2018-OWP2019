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
	
	
	var izvestaj = $('#izvestajTable');
	function getIzvestaj(){
		$.get('SveProjekcijeServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#registracija').show();
				$('#logout').hide();
				$('#login').show();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#registracija').hide();
				$('#logout').show();
				$('#login').hide();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#registracija').hide();
				$('#logout').show();
				$('#login').hide();

			}var izvestaj = data.izvestaj;
			for (p in izvestaj) {
				izvestaj.append(
					'<tr>' +
					'<td><a href="Film.html?id=' + izvestaj[p].film.id + '">' + izvestaj[p].film.naziv + '</a></td>' + 
						'<td>' + izvestaj[p].brojProjekcija + '</td>' + 
						'<td>' + izvestaj[p].brojKarata + '</td>' + 
						'<td>' + izvestaj[p].ukupnaCenaKarata + '</td>' + 
					'</tr>')}
		})};
	getIzvestaj();		
});