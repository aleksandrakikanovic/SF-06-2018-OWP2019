package servleti;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import bioskop.dao.ProjekcijaDAO;
import bioskop.dao.SalaDAO;
import bioskop.dao.SedisteDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Sediste;
import model.Projekcija.ETipProjekcije;


public class PrikaziProjekcijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String id = request.getParameter("id");
		    Projekcija izabranaProjekcija = ProjekcijaDAO.get(id);
		    System.out.println(izabranaProjekcija.getCenaKarte());
		    String slobodnaSedista = SedisteDAO.get(izabranaProjekcija);
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			data.put("izabranaProjekcija", izabranaProjekcija);
			data.put("slobodnaSedista", slobodnaSedista);

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
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String id = request.getParameter("id");
			Projekcija zaKartu = ProjekcijaDAO.get(id);
			List<Sediste> svaSedista = SedisteDAO.getAllZaKartu(zaKartu);
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			data.put("zaKartu", zaKartu);
			data.put("svaSedista", svaSedista);
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
