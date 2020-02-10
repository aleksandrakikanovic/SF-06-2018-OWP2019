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
import bioskop.dao.ProjekcijaDAO;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class SviKorisniciServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga().toString());
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
		try {
			String pretragaKorIme= request.getParameter("pretragaKorIme");
			String pretragaUloga = request.getParameter("pretragaUloga");
			pretragaKorIme = (pretragaKorIme != null? pretragaKorIme: "");			
			pretragaUloga = (pretragaUloga != null? pretragaUloga:"");			
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Korisnik> filtriraniKorisnici = KorisnikDAO.getFiltriraniKorisnici(pretragaKorIme, pretragaUloga);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filtriraniKorisnici", filtriraniKorisnici);
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
