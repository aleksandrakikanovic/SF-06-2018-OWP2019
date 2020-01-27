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
	
	
	var projekcijeTable = $('#projekcijeTable');
	function getProjekcije(){
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

			}
			var sveProjekcije = data.sveProjekcije;
			for (projekcija in sveProjekcije) {
				projekcijeTable.append(
					'<tr>' +
						'<td>' + sveProjekcije[projekcija].film.naziv + '</td>' + 
						'<td>' + sveProjekcije[projekcija].tipProjekcije + '</td>' + 
						'<td>' + sveProjekcije[projekcija].sala.naziv + '</td>' + 
						'<td>' + sveProjekcije[projekcija].datum + '</td>' + 
						'<td>' + sveProjekcije[projekcija].vreme + '</td>' + 
						'<td>' + sveProjekcije[projekcija].cenaKarte + '</td>' + 
						'<td>' + 
						'<form>' + 
						'<button type="button" class="btn btn-danger" id="prikaziProjekciju">Prikazi</button>' +
						'</form>' + 
						'</td>' + 
					'</tr>')}
			})};
	$('#projekcijeTable').on('click', '#prikaziProjekciju', function(){
		var currentRow=$(this).closest("tr"); 
		var film = currentRow.find("td:eq(0)").text();
		var tipProjekcije = currentRow.find("td:eq(1)").text(); 
		var sala = currentRow.find("td:eq(2)").text(); 
		var datum = currentRow.find("td:eq(3)").text();
		var vreme = currentRow.find("td:eq(4)").text();
		var cenaKarte = currentRow.find("td:eq(5)").text();
        var data="Film:"+film+"\nTip projekcije: "+tipProjekcije+"\nSala: "+sala+"\nDatum i vreme: "+datum+ " " + vreme + "\nCena karte: "+cenaKarte;
        alert(data);

		 params = {
	    			'film': film, 
	                'tipProjekcije': tipProjekcije,
	                'sala': sala, 
	    			'datum': datum, 
	    			'vreme':vreme,
	    			'cenaKarte': cenaKarte, 
	    		};
			$.get('PrikaziProjekcijuServlet', params, function(data) {
				window.location.replace('Projekcija.html');

			});
	});

getProjekcije();
	
});