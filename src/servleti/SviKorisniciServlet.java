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
import model.Korisnik;

@SuppressWarnings("serial")
public class SviKorisniciServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Korisnik> sviKorisnici = KorisnikDAO.getAll();
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("sviKorisnici", sviKorisnici);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
