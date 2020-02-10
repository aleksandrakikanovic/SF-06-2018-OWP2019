package servleti;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KorisnikDAO;
import model.Korisnik;
import model.Korisnik.Uloga;

public class RegistracijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String korisnickoIme = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			Korisnik korisnik = KorisnikDAO.get(korisnickoIme);
			korisnik.setLozinka(lozinka);
			String uloga = request.getParameter("uloga");
			if(uloga!=null) {
				Uloga u = Uloga.valueOf(uloga);
				korisnik.setTip(u);
			}
			KorisnikDAO.update(korisnik);
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String korisnickoIme = request.getParameter("korisnickoIme");
			if(KorisnikDAO.get(korisnickoIme) !=null) {
				throw new Exception("Korisnicko ime vec postoji!"); 
			}
			if(korisnickoIme.equals("")) {
				throw new Exception("Unesite korisnicko ime!");
			}
			String lozinka = request.getParameter("lozinka");
			if(lozinka.equals("")) {
				throw new Exception("Unesite lozinku!");
			}
			String ponovljenaLozinka = request.getParameter("ponovljenaLozinka");
			if(!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			 long millis=System.currentTimeMillis();  
		        java.sql.Date date = new java.sql.Date(millis);
		        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, date, Uloga.KORISNIK);
		        KorisnikDAO.add(korisnik);
				Map<String, Object> data = new LinkedHashMap<>();
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);

		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
