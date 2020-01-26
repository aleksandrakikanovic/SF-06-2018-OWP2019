package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Projekcija.ETipProjekcije;

public class ProjekcijaDAO {

	public static boolean add(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "insert into Projekcija (filmId, tipProjekcije ,salaId, datumVreme,cenaKarte,admin)"
					+ " values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, projekcija.getFilm().getId());
			pstmt.setString(index++, projekcija.getTipProjekcije().toString());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setDate(index++, projekcija.getDatum());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, "a");

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	public static List<Projekcija> getAll() throws Exception {
		List<Projekcija> sveProjekcije = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			String query = "select * from Projekcija";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			String f = rset.getString(2);
			Film film = FilmDAO.get(f);
			String t = rset.getString(3);
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(t);
			String s = rset.getString(4);
			Sala sala = SalaDAO.get(s);
//			String d = rset.getString(5);
//			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//		    java.util.Date date =  (java.util.Date) sdf1.parse(d);
//		    java.sql.Date datumVreme = new java.sql.Date(date.getTime());  
			long millis=rset.getLong(5);
		    Date datumVreme = new Date(millis);
			double cenaKarte = rset.getInt(6);
			String a = rset.getString(7);
			Korisnik admin = KorisnikDAO.get(a);
			while (rset.next()) {
				int index = 1;
				id = rset.getInt(index++);
				 f = rset.getString(index++);
				 t = rset.getString(index++);
				 s = rset.getString(index++);
				 //d = rset.getString(index++);
				 millis=rset.getLong(index++);
				 cenaKarte = rset.getInt(index++);
				 a = rset.getString(index++);
				 admin=KorisnikDAO.get(a);
				 film = FilmDAO.get(f);
				 tipProjekcije = ETipProjekcije.valueOf(t);
				  datumVreme = new Date(millis);
				 //date =  (java.util.Date) sdf1.parse(d);
				 //datumVreme = new java.sql.Date(date.getTime());
				 Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, datumVreme, cenaKarte, admin);
				 sveProjekcije.add(projekcija);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return sveProjekcije;
	}

		
			


	
	
}
