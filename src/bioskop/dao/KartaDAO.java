package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Film;
import model.Karta;

public class KartaDAO {

	public static boolean add(Karta karta) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Karta (projekcijaId, idSediste, datumVremeProdaje, imeKorisnika, deleted)"
					+ " values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, karta.getProjekcija().getId());
			pstmt.setInt(index++, karta.getSediste().getId());
			pstmt.setDate(index++, karta.getDatum());
			pstmt.setString(index++, karta.getKorisnik().getKorisnickoIme());
			pstmt.setString(index++, "no");
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
	
}
