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
	function getKorisnici(){
		$.get('SviKorisniciServlet', function(data){
			var sviKorisnici = data.sviKorisnici;
			for(korisnik in sviKorisnici){
				korisniciTable.append(
				'<tr>' +
					'<td><a href="Korisnik.html?korIme=' + sviKorisnici[korisnik].korisnickoIme + '">' + sviKorisnici[korisnik].korisnickoIme + '</a></td>' + 
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
	};
	getKorisnici();
});