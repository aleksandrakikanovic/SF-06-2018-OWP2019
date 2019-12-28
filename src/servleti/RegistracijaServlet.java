package servleti;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

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
			String ponoviLozinku = request.getParameter("ponoviLozinku");
			if(!lozinka.equals(ponoviLozinku)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			Uloga uloga = Uloga.KORISNIK;
			Date datumRegistracije = new Date(System.currentTimeMillis());
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga);
			KorisnikDAO.add(korisnik);
			response.sendRedirect("./Korisnici.html");
		} catch (Exception e) { //poruka greske
			e.printStackTrace();
		}
	
	}

}
