package servleti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.IzvestajDAO;
import bioskop.dao.ProjekcijaDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class SviFilmoviServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Film> sviFilmovi = FilmDAO.getAllZaAdmina();
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("sviFilmovi", sviFilmovi);
			List<Projekcija> izvestaj  = new ArrayList<>();
			for(Film f : sviFilmovi) {
				Projekcija projekcijaIz = IzvestajDAO.getIzvestaj(f.getId());
				izvestaj.add(projekcijaIz);
			}
			data.put("izvestaj", izvestaj);
			request.setAttribute("data", data);
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
		try {
			String film= request.getParameter("film");
			String zanr = request.getParameter("zanr");
			String distributer = request.getParameter("distributer");
			String zemljaPorekla= request.getParameter("zemljaPorekla");
			int trajanje1 = Integer.parseInt(request.getParameter("trajanje1")); 
			int trajanje2 = Integer.parseInt(request.getParameter("trajanje2")); 
			int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje")); //
			
			film = (film != null? film: "");			
			zanr = (zanr != null? zanr: "");	
			distributer = (distributer != null? distributer: "");			
			zemljaPorekla = (zemljaPorekla != null? zemljaPorekla: "");			
			trajanje1 = (trajanje1 >= 0? trajanje1: 0);
			trajanje2 = (trajanje2 >= 0? trajanje2: Integer.MAX_VALUE);
			godinaProizvodnje = (godinaProizvodnje >= 0? godinaProizvodnje: 0);
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Film> filtriraniFilmovi = FilmDAO.getFiltriraniFilmovi(film, zanr, trajanje1, trajanje2, distributer, zemljaPorekla, godinaProizvodnje);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filtriraniFilmovi", filtriraniFilmovi);
			request.setAttribute("data", data);
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
}
