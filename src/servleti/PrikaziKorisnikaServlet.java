package servleti;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.Korisnik;
import model.Korisnik.Uloga;


@SuppressWarnings("serial")
public class PrikaziKorisnikaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String korisnickoIme = request.getParameter("naziv");
			String lozinka = request.getParameter("reziser");
			Date datumRegistracije = null;
			Korisnik izabraniKorisnik = new Korisnik(korisnickoIme, lozinka, datumRegistracije, Uloga.KORISNIK);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("izabraniKorisnik", izabraniKorisnik);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	}

