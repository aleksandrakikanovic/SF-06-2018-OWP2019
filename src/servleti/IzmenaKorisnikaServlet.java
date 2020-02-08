package servleti;

import java.io.IOException;
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


public class IzmenaKorisnikaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			String korisnickoImeIzbrisi = request.getParameter("korisnickoImeIzbrisi");
			KorisnikDAO.delete(korisnickoImeIzbrisi);
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(ulogovanKorisnik.getUloga().toString()=="KORISNIK") {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			}else {
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			Korisnik izabraniKorisnik = KorisnikDAO.get(korisnickoIme);
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga().toString());
			}else {
				data.put("ulogaKorisnika", "neregistrovan");
			}
			data.put("ulogovanKorisnik", ulogovanKorisnik);
			
			data.put("izabraniKorisnik", izabraniKorisnik);
			List<Karta> karteKorisnika = KartaDAO.getAllZaKorisnika(izabraniKorisnik.getKorisnickoIme().toString());
			data.put("karteKorisnika", karteKorisnika);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
