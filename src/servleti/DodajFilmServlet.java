package servleti;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import model.Projekcija.ETipProjekcije;
import model.Sala;

@SuppressWarnings("serial")
public class DodajFilmServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String naziv = request.getParameter("naziv");
			String reziser = request.getParameter("reziser"); //opciono
			String glumci = request.getParameter("glumci"); //opciono
			String zanr = request.getParameter("zanr"); //opciono
			int trajanje = Integer.parseInt(request.getParameter("trajanje"));
			String distributer = request.getParameter("distributer");
			String zemljaPorekla = request.getParameter("zemljaPorekla");
			int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
			String opis = request.getParameter("opis"); //opciono
				if(naziv.equals("")) {
					throw new Exception("Unesite naziv filma!");
				}if(distributer.equals("")) {
					throw new Exception("Unesite distributera filma!");
				}if(zemljaPorekla.equals("")) {
					throw new Exception("Unesite zemlju porekla!");
				}if(trajanje<0) {
					throw new Exception("Trajanje filma mora biti vece od 0!");
				}if(godinaProizvodnje<0) {
					throw new Exception("Godina proizvodnje mora biti veca od 0!");
				}
				Film film = new Film(naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
				FilmDAO.add(film);
				Map<String, Object> data = new LinkedHashMap<>();
				request.setAttribute("data", data);
				if(!(ulogovanKorisnik==null)) {
					data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
				}
			} catch (Exception e) { 
			e.printStackTrace(); }
		}}