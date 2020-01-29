package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Projekcija;
import model.Sediste;

public class SedisteDAO {
	
	public static int get(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int brojSedista = 0;
		try {
		String query = "count(*) from Sediste where  where salaId = ? and id not in (select idSediste from Karta where projekcijaId != ?)";
		pstmt = conn.prepareStatement(query);
		int idSala= projekcija.getSala().getId();
		pstmt.setInt(1, idSala);
		int idProjekcija = projekcija.getId();
		pstmt.setInt(1, idProjekcija);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			brojSedista = rset.getInt(1);
			
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return brojSedista;
	}
	
}
