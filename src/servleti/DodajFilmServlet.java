package servleti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bioskop.dao.FilmDAO;
import model.Film;


@SuppressWarnings("serial")
public class DodajFilmServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			System.out.print(action);
			switch(action) {
		case "add": {
			String naziv = request.getParameter("naziv");
			String reziser = request.getParameter("reziser"); //opciono
			String glumci = request.getParameter("glumci"); //opciono
			String zanr = request.getParameter("zanr"); //opciono
			int trajanje = Integer.parseInt(request.getParameter("trajanje"));
			String distributer = request.getParameter("distributer");
			String zemljaPorekla = request.getParameter("zemljaPorekla");
			int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
			String opis = request.getParameter("opis"); //opciono
				if(naziv.equals("")) {
					throw new Exception("Unesite naziv filma!");
				}if(distributer.equals("")) {
					throw new Exception("Unesite distributera filma!");
				}if(zemljaPorekla.equals("")) {
					throw new Exception("Unesite zemlju porekla!");
				}if(trajanje<0) {
					throw new Exception("Trajanje filma mora biti vece od 0!");
				}if(godinaProizvodnje<0) {
					throw new Exception("Godina proizvodnje mora biti veca od 0!");
				}
				Film film = new Film(naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
				FilmDAO.add(film);
				break; }
			case "remove" :{
				int index = Integer.parseInt(request.getParameter("index"));
					FilmDAO.delete(index);
				break;}
			}} catch (Exception e) { //poruka greske
			e.printStackTrace(); }

			//response.sendRedirect("./Filmovi.html");
	
}}


