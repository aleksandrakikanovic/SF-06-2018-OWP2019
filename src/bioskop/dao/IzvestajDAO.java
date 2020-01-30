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
		List<Projekcija> izvestajLista = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select count(film.Id), count(Karta.id), sum(cenaKarte)"
					+ " from Projekcija, Karta, Film where Projekcija.filmId=? and Projekcija.id=Karta.projekcijaId and Projekcija.filmId=Film.id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idF);
			rset = pstmt.executeQuery();
			String f = Integer.toString(idF);
			Film film = FilmDAO.get(f);
			if (rset.next()) {
				int brojProjekcija = rset.getInt(1);
				int brojKarata = rset.getInt(2);
				double ukupnaCenaKarata = rset.getDouble(3);
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