package servleti;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KartaDAO;
import bioskop.dao.KorisnikDAO;
import model.Karta;
import model.Korisnik;
import model.Korisnik.Uloga;


@SuppressWarnings("serial")
public class PrikaziKorisnikaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga().toString());
			}else {
				data.put("ulogaKorisnika", "neregistrovan");
			}
			data.put("ulogovanKorisnik", ulogovanKorisnik);
			List<Karta> karteKorisnika = KartaDAO.getAllZaKorisnika(ulogovanKorisnik.getKorisnickoIme());
			data.put("karteKorisnika", karteKorisnika);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	}

