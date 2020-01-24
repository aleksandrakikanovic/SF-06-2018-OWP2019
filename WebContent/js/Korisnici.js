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
				'<td>' + sviKorisnici[korisnik].korisnickoIme + '</td>' + 
				'<td>' + sviKorisnici[korisnik].lozinka + '</td>' + 
				'<td>' + sviKorisnici[korisnik].datumRegistracije + '</td>' + 
				'<td>' + sviKorisnici[korisnik].uloga + '</td>' + 
				'<td>' + 
					'<button type="button" class="btn btn-warning" id="prikaziKorisnika">Prikazi</button>' +
				'</td>' + 
			'</tr>')}
		});
	};
	//brisanje iz tabele, ne iz baze(ispravicu do odbrane)
	$('#korisniciTable').on('click', '#izbrisiKorisnika', function(){
		$(this).closest ('tr').remove();
		params = {
				//'action': 'remove',
				};

		$.post('RegistracijaServlet', params, function(data) {
		
		});
		
});
	$('#korisniciTable').on('click', '#prikaziKorisnika', function(){
        var currentRow=$(this).closest("tr"); 
        var korisnickoIme = currentRow.find("td:eq(0)").text(); 
        var lozinka = currentRow.find("td:eq(1)").text();
        var datumRegistracije = currentRow.find("td:eq(2)").text(); 
        var data = 'Korisnicko ime:' + korisnickoIme + '\nLozinka: '+ lozinka + '\nDatum registracije: ' + datumRegistracije + '\nUloga: Korisnik';
        alert(data);
    
        params = {
    			'korisnickoIme' : korisnickoIme,
    			'lozinka': lozinka, 
                'datumRegistracije': datumRegistracije,

    		};
		$.post('PrikaziKorisnikaServlet', params, function(data) {
			window.location.replace('Korisnik.html');

		});
});

		

	getKorisnici();
});