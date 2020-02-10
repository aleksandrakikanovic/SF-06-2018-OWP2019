$(document).ready(function(){
	$('#logout').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			console.log(data);
			if (data.status == 'unauthenticated') {
				window.location.replace('index.html');
				return;
			}
		});
	});
	var korisniciTable = $('#korisniciTable');
	var pretragaKorIme = $('#pretragaKorIme');
	var pretraziKorisnika = $('#pretraziKorisnika');

	function getKorisnici(){
		$.get('SviKorisniciServlet', function(data){
			var sviKorisnici = data.sviKorisnici;
			for(korisnik in sviKorisnici){
				korisniciTable.append(
				'<tr>' +
					'<td><a href="KorisnikZaAdmina.html?korIme=' + sviKorisnici[korisnik].korisnickoIme + '">' + sviKorisnici[korisnik].korisnickoIme + '</a></td>' + 
					'<td>' + sviKorisnici[korisnik].lozinka + '</td>' + 
					'<td>' + new Date(sviKorisnici[korisnik].datumRegistracije).toLocaleDateString() + '</td>' + 
					'<td>' + sviKorisnici[korisnik].uloga + '</td>' + 
				'</tr>')}
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#profilKorisnika').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#profilKorisnika').show();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#profilKorisnika').show();
				$('#logout').show();
			};	
		});
		
		$('#pretrazi').on('click', function(event) {
			var pretragaKorIme = pretragaKorIme.val();
			var pretragaUloga = pretraziKorisnika.val();
			params = {
					'pretragaKorIme':pretragaKorIme,
					'pretragaUloga':pretragaUloga,
			}
			$.post('SviKorisniciServlet',params, function(data) {
				var filtriraniKorisnici = data.filtriraniKorisnici;
				$("#korisniciTable > tbody").empty();
				for (korisnik in filtriraniKorisnici) {
					korisniciTable.append(
							'<tr>' +
								'<td><a href="KorisnikZaAdmina.html?korIme=' + filtriraniKorisnici[korisnik].korisnickoIme + '">' + filtriraniKorisnici[korisnik].korisnickoIme + '</a></td>' + 
								'<td>' + filtriraniKorisnici[korisnik].lozinka + '</td>' + 
								'<td>' + new Date(filtriraniKorisnici[korisnik].datumRegistracije).toLocaleDateString() + '</td>' + 
								'<td>' + filtriraniKorisnici[korisnik].uloga + '</td>' + 
							'</tr>')}
			});
		});
		
	};
	getKorisnici();
});