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
	
	
	var izvestajTable = $('#izvestajTable');
	function getIzvestaj(){
		$.get('SveProjekcijeServlet', function(data) {
			var izvestaj = data.izvestaj;
			for (projekcija in izvestaj) {
				izvestajTable.append(
					'<tr>' +
					'<td><a href="Film.html?id=' + izvestaj[projekcija].film.id + '">' + izvestaj[projekcija].film.naziv + '</a></td>' + 
						'<td>' + izvestaj[projekcija].brojProjekcija + '</td>' + 
						'<td>' + izvestaj[projekcija].brojKarata + '</td>' + 
						'<td>' + izvestaj[projekcija].ukupnaCenaKarata + '</td>' + 
					'</tr>')}
		})};
	getIzvestaj();		
});