package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Projekcija;

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
			//pstmt.setString(index++, projekcija.getAdmin().getKorisnickoIme());
			pstmt.setString(index++, "a");

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	

	
	
}
