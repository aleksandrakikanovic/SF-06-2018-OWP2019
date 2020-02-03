package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Projekcija;
import model.Sediste;

public class SedisteDAO {
	
	public static String get(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String brojSedista = "";
		try {
		String query = "select count(*) from Sediste  where salaId = ? and id not in (select idSediste from Karta where projekcijaId = ?)";
		pstmt = conn.prepareStatement(query);
		int index=1;
		int idSala= projekcija.getSala().getId();
		pstmt.setInt(index++, idSala);
		int idProjekcija = projekcija.getId();
		pstmt.setInt(index++, idProjekcija);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			return brojSedista = rset.getString(1);
			
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return brojSedista;
	}
}
