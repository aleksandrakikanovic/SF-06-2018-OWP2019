package servleti;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bioskop.dao.KartaDAO;
import bioskop.dao.ProjekcijaDAO;
import bioskop.dao.SedisteDAO;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sediste;

public class KupiKartuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
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
			String projekcijaId = request.getParameter("id");
			Projekcija kartaZaProjekciju = ProjekcijaDAO.get(projekcijaId);
			int sedisteId = Integer.parseInt(request.getParameter("sediste"));
			Sediste sediste = SedisteDAO.getOne(sedisteId);
			long millis=System.currentTimeMillis();  
		    Date datumKupovine = new Date(millis);
		    Karta karta = new Karta(kartaZaProjekciju, sediste, datumKupovine, ulogovanKorisnik);
			KartaDAO.add(karta);
			request.setAttribute("data", data);
			data.put("kartaZaProjekciju", kartaZaProjekciju);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
				Korisnik ulogovanKorisnik = (Korisnik) request.getSession().getAttribute("ulogovanKorisnik");
				String sediste = request.getParameter("sediste");
				Map<String, Object> data = new LinkedHashMap<>();
				request.setAttribute("data", data);
				data.put("sediste", sediste);
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
