package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Projekcija;

public class IzvestajDAO {
	
	public static Projekcija getIzvestaj(int idF) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select count(Projekcija.Id), count(Karta.id), sum(cenaKarte)"+
                     " from Projekcija LEFT OUTER JOIN Karta ON Projekcija.id=Karta.projekcijaId where Projekcija.filmId=? and Karta.deleted='no'";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idF);
			rset = pstmt.executeQuery();
			String f = Integer.toString(idF);
			Film film = FilmDAO.get(f);
			if (rset.next()) {
				int brojProjekcija = rset.getInt(1);
				int brojKarata = rset.getInt(2);
				double ukupnaCenaKarata = rset.getDouble(3);
				if(brojKarata==0) {
					ukupnaCenaKarata = 0.0;
				}
				return new Projekcija(film, brojProjekcija, brojKarata, ukupnaCenaKarata);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return null;
	}
}