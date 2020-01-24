package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Korisnik;

@SuppressWarnings("serial")
public class DodajProjekcijuServlet extends HttpServlet {
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
			}else {
				data.put("ulogaKorisnika", "neregistrovan");

			}		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			data.put("ulogaKorisnika", ulogovanKorisnik.getUloga());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
