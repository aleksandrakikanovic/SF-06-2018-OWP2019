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

@SuppressWarnings("serial")
public class PrikaziFilmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//int id =Integer.parseInt(request.getParameter("id"));
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
			Film izabraniFilm = new Film( naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("izabraniFilm", izabraniFilm);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	}
