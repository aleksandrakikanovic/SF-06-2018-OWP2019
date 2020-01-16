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
					'<button type="button" class="btn btn-warning" id="izmeniKorisnika">Izmeni</button>' +
				'</td>' + 
				'<td>' + 
				'<button type="button" class="btn btn-danger" id="izbrisiKorisnika">Izbrisi</button>' +
			'</td>' + 
			'</tr>')}
		});
	};
	//brisanje iz tabele, ne iz baze(ispravicu do odbrane)
	$('#korisniciTable').on('click', '#izbrisiKorisnika', function(){
		$(this).closest ('tr').remove ();
		params = {
				//'action': 'remove',
				};

		$.post('RegistracijaServlet', params, function(data) {
		
		});
		
});
	$('#korisniciTable').on('click', '#izmeniKorisnika', function(){
		
		params = {
				//'action': 'update',
				};
		//if (data.status == 'success') {
			window.location.replace('Registracija.html');
		//}
	//	$.post('RegistracijaServlet', params, function(data) {
		
		//});
		
});
		

	getKorisnici();
});