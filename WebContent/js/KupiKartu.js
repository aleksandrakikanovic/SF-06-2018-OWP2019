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
	
	
	var tabela = $('#tabelaProjekcija');
	$.get('KupiKartuServlet', function(data) {
		var kartaZaProjekciju = data.kartaZaProjekciju;	
		tabela.append(
			'<tr>'+
			      '<th scope="col">Naziv filma</th> ' + 	
					'<td><a href="Film.html?id=' + kartaZaProjekciju.film.id  + '" id="prikaziFilm">' + kartaZaProjekciju.film.naziv + '</a></td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Datum i vreme projekcije</th> ' + 	
					'<td><a href="Projekcija.html?id=' + kartaZaProjekciju.id  + '" id="prikaziFilm">' +
					kartaZaProjekciju.datum + ' ' + kartaZaProjekciju.vreme + '</a></td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Sala</th> ' + 	
					'<td>' + kartaZaProjekciju.sala.naziv + '</td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Tip projekcije</th> ' + 	
					'<td>' + kartaZaProjekciju.tipProjekcije + '</td>' + 
		    '</tr>' +
			'<tr>'+
			      '<th scope="col">Cena karte</th> ' + 	
					'<td>' + kartaZaProjekciju.cenaKarte + '</td>' + 
		    '</tr>' +
			'<tr>'+
				'<select name="" id="cmbSediste" class="combobox1">' +  '<option value="">Izaberi sediste</option>' +

		    '</tr>' +
			'<tr>'+
				 '<td>'+'</td>'+
				  '<td align="center">'+
					'<button type="button" class="btn btn-warning" id="kupiKartu">Kupi kartu</button>' +
				'</td>'+
		    '</tr>'+
		    '<tr>' );
		
	});
	
	var cmbSediste = $('#cmbSediste');
	function getSediste(){
		$.get('KupiKartuServlet', function(data) {
			var svaSedista = data.svaSedista;
			for (sediste in svaSedista) {
				cmbSediste.append(
					'<option value="'+svaSedista[sediste].id+ '">' + svaSedista[sediste].redniBroj+ '</option>'
				
				)}
			})};	
	
getSediste();
	
});