package servleti;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bioskop.dao.SedisteDAO;
import model.Korisnik;

public class ProveraKarteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
				Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
				Map<String, Object> data = new LinkedHashMap<>();
				int sedisteId = Integer.parseInt(request.getParameter("sediste"));
				int projekcijaId = Integer.parseInt(request.getParameter("id"));
				boolean zauzetoSediste = SedisteDAO.get1(sedisteId, projekcijaId);
				System.out.println(zauzetoSediste);
				data.put("zauzetoSediste", zauzetoSediste);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
