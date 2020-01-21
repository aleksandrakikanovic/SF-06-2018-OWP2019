package servleti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KorisnikDAO;
import model.Korisnik;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String korIme = request.getParameter("korIme");
		String loz = request.getParameter("loz");
		System.out.println(korIme);
		try {
			Korisnik korisnik = KorisnikDAO.get(korIme);
			if(korisnik ==null) {
				System.out.println("null");
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				return;
			}else {
				System.out.println("not null");

			}

			request.getSession().setAttribute("ulogovanKorisnik", korisnik.getKorisnickoIme());
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
