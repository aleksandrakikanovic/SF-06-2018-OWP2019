package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import model.Film;
import model.Korisnik;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String korIme = request.getParameter("korIme");
		String loz = request.getParameter("loz");
		try {
			Korisnik korisnik = KorisnikDAO.get(korIme);
			Map<String, Object> data = new LinkedHashMap<>();
			if(korisnik ==null) {
				data.put("ulogaKorisnika", "neregistrovan");
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				return;
			}else {
				data.put("ulogaKorisnika",korisnik.getUloga().toString());
			}			
			request.setAttribute("data", data);
			request.getSession().setAttribute("ulogovanKorisnik", korisnik);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
