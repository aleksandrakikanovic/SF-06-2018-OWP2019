package servleti;

import java.io.IOException;
import java.sql.Date;
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
		doPost(request, response);
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
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
