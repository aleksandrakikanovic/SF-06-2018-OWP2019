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
		String query = "select filmId, count(filmId), count(Karta.id), sum(cenaKarte) from Projekcija, Karta where filmId = ? and"
		+ " Projekcija.id=Karta.projekcijaId";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, idF);
		rset = pstmt.executeQuery();
		
		if (rset.next()) {
			int filmId = rset.getInt(1);
			String f = Integer.toString(filmId);
			Film film = FilmDAO.get(f);
			int brojProjekcija = rset.getInt(2);
			int brojKarata = rset.getInt(3);
			double ukupnaCenaKarata = rset.getDouble(4);
			Projekcija p = new Projekcija(film, brojProjekcija, brojKarata, ukupnaCenaKarata);
			return p;
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}
	

}
