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
	var filmoviTable = $('#filmoviTable');
	function getFilmovi(){
		$.get('SviFilmoviServlet', function(data) {
			if (data.ulogaKorisnika == "neregistrovan") {
				$('#prikaziKorisnike').hide();
				$('#dodajProjekciju').hide();
				$('#profilKorisnika').hide();
				$('#dodajFilm').hide();
				$('#logout').hide();
			}else if(data.ulogaKorisnika=="ADMIN"){
				$('#prikaziKorisnike').show();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').show();
				$('#dodajFilm').show();
				$('#logout').show();
			}else if(data.ulogaKorisnika=="KORISNIK"){
				$('#prikaziKorisnike').hide();
				$('#profilKorisnika').show();
				$('#dodajProjekciju').hide();
				$('#dodajFilm').hide();
				$('#logout').show();
			}
			var sviFilmovi = data.sviFilmovi;
			for (film in sviFilmovi) {
				filmoviTable.append(
					'<tr>' +
					'<td><a href="Film.html?id=' + sviFilmovi[film].id + '" id="prikaziFilm">' + sviFilmovi[film].naziv + '</a></td>' + 
						'<td>' + sviFilmovi[film].zanr + '</td>' + 
						'<td>' + sviFilmovi[film].trajanje + '</td>' + 
						'<td>' + sviFilmovi[film].distributer + '</td>' + 
						'<td>' + sviFilmovi[film].zemljaPorekla + '</td>' + 
						'<td>' + sviFilmovi[film].godinaProizvodnje + '</td>' + 
						
					'</tr>')}
			})};
	 
	 /*$('#filmoviTable').on('click', '#prikaziFilm', function(){
        var currentRow=$(this).closest("tr"); 
        var id =  currentRow.find("td:eq(0)").text();
        alert(id);
       // var naziv = currentRow.find("td:eq(1)").text();
        var zanr = currentRow.find("td:eq(4)").text();
        var trajanje = currentRow.find("td:eq(5)").text();
        var distributer = currentRow.find("td:eq(6)").text(); 
        var zemljaPorekla = currentRow.find("td:eq(7)").text();
        var godinaProizvodnje = currentRow.find("td:eq(8)").text(); 
    
        params = {
    			'id':id,
    			'naziv': naziv, 
    			'zanr': zanr, 
    			'trajanje': trajanje, 
    			'distributer': distributer, 
    			'zemljaPorekla': zemljaPorekla, 
    			'godinaProizvodnje': godinaProizvodnje, 

    		};
		$.post('PrikaziFilmServlet', params, function(data) {
			window.location.replace('Film.html');

		}); 
}); */
	
	getFilmovi();
	
});