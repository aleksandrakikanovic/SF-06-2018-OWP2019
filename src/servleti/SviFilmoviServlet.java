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
import model.Film;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class SviFilmoviServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Film> sviFilmovi = FilmDAO.getAllZaAdmina();
			List<Projekcija> izvestaj = new ArrayList<>();

			for(Film f : sviFilmovi) {
				Projekcija p = IzvestajDAO.getIzvestaj(f.getId());
				izvestaj.add(p);
			}
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("sviFilmovi", sviFilmovi);
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
		doGet(request, response);
	}
}
