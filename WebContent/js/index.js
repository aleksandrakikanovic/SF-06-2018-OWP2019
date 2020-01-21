$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	$('#prikazi').on('click', function(event) {
		params = {
				'id':id
		};
		
		$.post('SviFilmoviServlet', params, function(data) {
			console.log(data.status);
			window.location.replace('Film.html');
		});
		
		
	
	});
	event.preventDefault();
	return false;
	
});