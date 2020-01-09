$(document).ready(function(){
	
	var korisniciTable = $('#korisniciTable');
	function getKorisnici(){
		$.get('SviKorisniciServlet', function(data){
			var sviKorisnici = data.sviKorisnici;
			for(korisnik in sviKorisnici){
				korisniciTable.append(
				'<tr>' +
				'<td>' + sviKorisnici[korisnik].korisnickoIme + '</td>' + 
				'<td>' + sviKorisnici[korisnik].lozinka + '</td>' + 
				'<td>' + sviKorisnici[korisnik].datumRegistracije + '</td>' + 
				'<td>' + sviKorisnici[korisnik].uloga + '</td>' + 
				'<td>' + 
					//'<form>' + 
					'<button type="button" class="btn btn-warning" id="izmeniKorisnika">Izmeni</button>' +
						//'<input type="submit" value="Izmeni">' + 
					//'</form>' + 
				'</td>' + 
				'<td>' + 
				//'<form>' + 
				'<button type="button" class="btn btn-danger" id="izbrisiKorisnika">Izbrisi</button>' +
					//'<input type="submit" value="Izbrisi">' + 
				//'</form>' + 
			'</td>' + 
			'</tr>')}
		});
	};
	getKorisnici();
});