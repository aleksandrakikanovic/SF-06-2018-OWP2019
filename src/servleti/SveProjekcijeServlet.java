package servleti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.IzvestajDAO;
import bioskop.dao.ProjekcijaDAO;
import bioskop.dao.SalaDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Projekcija.ETipProjekcije;

public class SveProjekcijeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Projekcija> sveProjekcije = ProjekcijaDAO.getProjekcije();
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("sveProjekcije", sveProjekcije);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			double minCena = 0.0;
			double maxCena = Double.MAX_VALUE;
		try {
			String imeFilma= request.getParameter("pretragaFilm");
			String tipProjekcije = request.getParameter("pretragaTip");
			String sala = request.getParameter("pretragaSala");
			minCena= Double.parseDouble(request.getParameter("pretragaCenaMin"));
			maxCena= Double.parseDouble(request.getParameter("pretragaCenaMax"));
			imeFilma = (imeFilma != null? imeFilma: "");			
			tipProjekcije = (tipProjekcije != null? tipProjekcije:"1");			
			sala = (sala != null? sala: "1");
			minCena = (minCena >= 0.0? minCena: 0.0);
			maxCena = (maxCena >= 0.0? maxCena: Double.MAX_VALUE);
			Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
			List<Projekcija> filtriraneProjekcije = ProjekcijaDAO.getAll(imeFilma, tipProjekcije, sala, minCena, maxCena);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filtriraneProjekcije", filtriraneProjekcije);
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
