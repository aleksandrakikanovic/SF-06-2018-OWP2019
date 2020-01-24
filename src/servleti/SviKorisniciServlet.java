package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bioskop.dao.KorisnikDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class SviKorisniciServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
			}else {
				data.put("ulogaKorisnika", "neregistrovan");

			}			List<Korisnik> sviKorisnici = KorisnikDAO.getAll();
			data.put("sviKorisnici", sviKorisnici);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
