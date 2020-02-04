$(document).ready(function() {
	$('#logout').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			if (data.status == 'unauthenticated') {
				window.location.replace('index.html');
				return;
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	params={'id':id };
	var tabela = $('#kupiKartuFilm');
	function kupiKartu(){
	$.post('PrikaziProjekcijuServlet',params, function(data) {
		var izabranaProjekcija = data.zaKartu;
		var id =izabranaProjekcija.id;
		var svaSedista = data.svaSedista;
		tabela.append(
			'<tr>'+
			      '<th scope="col">Naziv filma</th> ' + 	
					'<td><a href="Film.html?id=' + izabranaProjekcija.film.id  + '" id="prikaziFilm">' + izabranaProjekcija.film.naziv + '</a></td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Datum i vreme projekcije</th> ' + 	
					'<td><a href="Projekcija.html?id=' + izabranaProjekcija.id  + '" id="prikaziFilm">' + 'Datum: ' + 
					izabranaProjekcija.datum + ' Vreme: ' + izabranaProjekcija.vreme + '</a></td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Sala</th> ' + 	
					'<td>' + izabranaProjekcija.sala.naziv + '</td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Tip projekcije</th> ' + 	
					'<td>' + izabranaProjekcija.tipProjekcije + '</td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Cena karte</th> ' + 	
					'<td>' + izabranaProjekcija.cenaKarte + '</td>' + 
		    '</tr>' +
			'<tr  id="cmb">'+
		      '<th scope="col">Sediste</th> ' + 	
				'<td>'+ '<select name="" id="cmbSediste" class="combobox1">' + '</td>'+
		    '</tr>' +
		    '<tr id="btn">'+
			 	'<td>'+'</td>'+
				  	'<td align="center">'+
	                  '<button class="btn btn-sm" type="submit" id="kupiKartu">Dalje</button>' +

					'</td>'+
			'</tr>'+
		    '<tr>' );
		var cmbSediste = $('#cmbSediste');
		for (sediste in svaSedista) {
			cmbSediste.append(
				'<option value="'+svaSedista[sediste].id+ '">' + svaSedista[sediste].redniBroj+ '</option>'
			)}
		
	});
	
	$('#kupiKartuFilm').on('click', '#kupiKartu', function(){
		var cmb = $('#cmbSediste');
	    var sediste = cmb.val();
	    var sedisteName = cmb.name();x
	    params = {'sediste':sediste,
	    		'id': id};
	    $('#cmb').remove();
	    $('#btn').remove();
		tabela.append(
				'<tr>'+
			      '<th scope="col">Izabrano sediste</th> ' + 	
					'<td>'+ sedisteName + '</td>'+
			    '</tr>' +
			    '<tr>'+
				 	'<td>'+'</td>'+
					  	'<td align="center">'+
		                  '<button class="btn btn-sm" type="submit" id="potvrdiKupovinuKarte">Potvrdi kupovinu</button>' +
						'</td>'+
				'</tr>'+
			    '<tr>' );
	$('#kupiKartuFilm').on('click', '#potvrdiKupovinuKarte', function(){
		 $.get('KupiKartuServlet', params, function(data) {
		        if (data.status == 'unauthenticated') {
		            window.location.replace('Login.html');
		            return;
		        }
		        if (data.status == 'success') {
		        	alert('success');
		            window.location.replace('index.html');
		        }
		    });
		
		});

	});

};	
kupiKartu();
});