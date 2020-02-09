package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bioskop.dao.FilmDAO;
import bioskop.dao.ProjekcijaDAO;
import bioskop.dao.SalaDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Projekcija.ETipProjekcije;

@SuppressWarnings("serial")
public class DodajProjekcijuServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String id= request.getParameter("id");
			String f= request.getParameter("film");
			Film film = FilmDAO.get(f);
			String tip = request.getParameter("tipProjekcije");
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(tip);
			String s = request.getParameter("sala");
			Sala sala = SalaDAO.get(s);
			long d = System.currentTimeMillis(); 
	        java.sql.Date datum = new java.sql.Date(d);
			String vreme = request.getParameter("vreme");
			double cenaKarte = Double.parseDouble(request.getParameter("cenaKarte"));
			Projekcija projekcija = ProjekcijaDAO.get(id);
			projekcija.setFilm(film);
			projekcija.setSala(sala);
			projekcija.setTipProjekcije(tipProjekcije);
			projekcija.setDatum(datum);
			projekcija.setVreme(vreme);
			projekcija.setCenaKarte(cenaKarte);
			ProjekcijaDAO.update(projekcija);
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				if(!(ulogovanKorisnik==null)) {
					data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
				}else {
					data.put("ulogaKorisnika", "neregistrovan");
				}
			} catch (Exception e) { 
			e.printStackTrace(); }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String f= request.getParameter("film");
			Film film = FilmDAO.get(f);
			String tip = request.getParameter("tipProjekcije");
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(tip);
			String s = request.getParameter("sala");
			Sala sala = SalaDAO.get(s);
			long d = System.currentTimeMillis(); 
	        java.sql.Date datum = new java.sql.Date(d);
			String vreme = request.getParameter("vreme");
			double cenaKarte = Double.parseDouble(request.getParameter("cenaKarte"));
				if(film.equals("")) {
					throw new Exception("Izaberite naziv filma!");
				}if(tipProjekcije.equals("")) {
					throw new Exception("Izaberite tip projekcije!");
				}if(sala.equals("")) {
					throw new Exception("Izaberite salu!");
				}if(cenaKarte<0) {
					throw new Exception("Cena karte mora biti veca od 0!");
				}
				Projekcija projekcija = new Projekcija(film, tipProjekcije, sala, datum, vreme, cenaKarte, ulogovanKorisnik);
				boolean postoji = ProjekcijaDAO.proveriSalu(projekcija);
				ProjekcijaDAO.add(projekcija);
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("postoji", postoji);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);

				if(!(ulogovanKorisnik==null)) {
					data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
				}else {
					data.put("ulogaKorisnika", "neregistrovan");
				}
			} catch (Exception e) { 
			e.printStackTrace(); }
}}


