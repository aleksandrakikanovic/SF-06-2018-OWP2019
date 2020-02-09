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
	var filmInput = $('#pretragaFilm');
	var salaInput = $('#salaCombobox');
	var tipInput = $('#tipProjekcijeCombobox');
	var cenaMinInput = $('#pretragaCenaMin');
	var cenaMaxInput = $('#pretragaCenaMax');


	function getProjekcije(){
		$.get('SveProjekcijeServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').hide();
				$('#registracija').show();
				$('#dodajProjekciju').hide();
				$('#izvestaj').hide();
				$('#logout').hide();
				$('#login').show();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').show();
				$('#registracija').hide();
				$('#logout').show();
				$('#izvestaj').show();
				$('#login').hide();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').hide();
				$('#registracija').hide();
				$('#logout').show();
				$('#izvestaj').hide();
				$('#login').hide();
			}
			var sveProjekcije = data.sveProjekcije;
			for (projekcija in sveProjekcije) {
				projekcijeTable.append(
					'<tbody>'+'<tr id="remove">' +
						'<td><a href="Film.html?id=' + sveProjekcije[projekcija].film.id + '">' + sveProjekcije[projekcija].film.naziv + '</a></td>' + 
						'<td>' + sveProjekcije[projekcija].tipProjekcije + '</td>' + 
						'<td>' + sveProjekcije[projekcija].sala.naziv + '</td>' + 
						'<td><a href="Projekcija.html?id=' + sveProjekcije[projekcija].id + '">' 
						+ new Date(sveProjekcije[projekcija].datum).toLocaleDateString() + " " +  sveProjekcije[projekcija].vreme + '</a></td>' + 
						'<td>' + sveProjekcije[projekcija].cenaKarte + '</td>' + 
					'</tr>' + '</tbody>')}
			})};
	function getFiltriraneProjekcije(){
		var pretragaFilm = filmInput.val();
		var pretragaSala = salaInput.val();
		var pretragaTip = tipInput.val();
		var pretragaCenaMin = cenaMinInput.val();
		var pretragaCenaMax = cenaMaxInput.val();
		params = {
				'pretragaFilm':pretragaFilm,
				'pretragaSala':pretragaSala,
				'pretragaTip':pretragaTip,
				'pretragaCenaMin':pretragaCenaMin,
				'pretragaCenaMax':pretragaCenaMax
		}
		$.post('SveProjekcijeServlet',params, function(data) {
			var filtriraneProjekcije = data.filtriraneProjekcije;
			$("#remove").empty();
			for (projekcija in filtriraneProjekcije) {
				projekcijeTable.append(
					'<tr>' +
						'<td><a href="Film.html?id=' + filtriraneProjekcije[projekcija].film.id + '">' + filtriraneProjekcije[projekcija].film.naziv + '</a></td>' + 
						'<td>' + filtriraneProjekcije[projekcija].tipProjekcije + '</td>' + 
						'<td>' + filtriraneProjekcije[projekcija].sala.naziv + '</td>' + 
						'<td><a href="Projekcija.html?id=' + filtriraneProjekcije[projekcija].id + '">' 
						+ new Date(filtriraneProjekcije[projekcija].datum).toLocaleDateString() + " " +  filtriraneProjekcije[projekcija].vreme + '</a></td>' + 
						'<td>' + filtriraneProjekcije[projekcija].cenaKarte + '</td>' + 
						'</tr>')}
			
		});
	};
	$('#projekcijeTable').on('click', '#pretraziProjekcije', function(){
		getFiltriraneProjekcije();
	});
	
	nameFilterInput.on('keyup', function(event) {
		getProducts();

		event.preventDefault();
		return false;
	});
	
getProjekcije();
	
});