package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Projekcija;

public class IzvestajDAO {
	
	public static Projekcija get(String idF) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "count(filmId), count(Karta.id), sum(cenaKarte) from Projekcija, Karta where filmId = ? and"
		+ " Projekcija.id=Karta.projekcijaId";
		pstmt = conn.prepareStatement(query);
		int idFilma= Integer.parseInt(idF);
		pstmt.setInt(1, idFilma);
		rset = pstmt.executeQuery();
		if (rset.next()) {
				////dodati u listu
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}
	

}
