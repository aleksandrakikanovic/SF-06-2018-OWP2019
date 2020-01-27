package servleti;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import bioskop.dao.SalaDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Projekcija.ETipProjekcije;


public class PrikaziProjekcijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String f= request.getParameter("film");
			Film film = FilmDAO.nazivPretraga(f);
			String tip = request.getParameter("tipProjekcije");
			System.out.println(tip);
			System.out.println(f);
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(tip);
			String s = request.getParameter("sala");
			Sala sala = SalaDAO.pretragaNaziv(s);
			String vreme = request.getParameter("vreme");
			int cena = Integer.parseInt(request.getParameter("cenaKarte"));
			double cenaKarte = cena;
			long d = Long.parseLong((request.getParameter("datumVreme"))); 
	        java.sql.Date datum = new java.sql.Date(d);
		    Korisnik admin = KorisnikDAO.get("aleksandra");
		Projekcija izabranaProjekcija = new Projekcija(film, tipProjekcije, sala, datum, vreme, cenaKarte, admin);
		Map<String, Object> data = new LinkedHashMap<>();
		request.setAttribute("data", data);
		data.put("izabranaProjekcija", izabranaProjekcija);
		if(!(ulogovanKorisnik==null)) {
			data.put("ulogaKorisnika", ulogovanKorisnik.getUloga().toString());
		}else {
			data.put("ulogaKorisnika", "neregistrovan");
		}

		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
