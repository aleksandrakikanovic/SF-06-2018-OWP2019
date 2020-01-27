package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.Korisnik;

@SuppressWarnings("serial")
public class PrikaziFilmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			Map<String, Object> data = new LinkedHashMap<>();
			request.setAttribute("data", data);
			if(!(ulogovanKorisnik==null)) {
				data.put("ulogaKorisnika", ulogovanKorisnik.getUloga().toString());
			}else {
				data.put("ulogaKorisnika", "neregistrovan");
			}
			String naziv = request.getParameter("naziv");
			String reziser = request.getParameter("reziser");
			String glumci = request.getParameter("glumci"); 
			String zanr = request.getParameter("zanr"); 
			String t = request.getParameter("trajanje");
			int trajanje = 0;
			String distributer = request.getParameter("distributer");
			String zemljaPorekla = request.getParameter("zemljaPorekla");
			//int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
			int godinaProizvodnje = 0;
			String opis = request.getParameter("opis"); 
			Film izabraniFilm = new Film(naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			data.put("izabraniFilm", izabraniFilm);
			//System.out.println(zanr.trim());
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	}

