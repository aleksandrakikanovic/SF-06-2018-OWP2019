package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;
import model.Korisnik.Uloga;
import bioskop.dao.ConnectionManager;

public class KorisnikDAO {
	
	
	public static Korisnik get(String korisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		System.out.print(conn);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Korisnik where korisnickoIme = ?";
		pstmt = conn.prepareStatement(query);
		int index=1;
		pstmt.setString(index++, korisnickoIme);
		rset = pstmt.executeQuery();

		if (rset.next()) {
			Uloga uloga = Uloga.valueOf(rset.getString(3));
			String lozinka = rset.getString(1);
			Date datumRegistracije = rset.getDate(2);

			return new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
		try {conn.close();} catch (Exception ex2) {ex2.printStackTrace();}
	}

		return null;
	}

	public static List<Korisnik> getAll(String korisnickoIme) throws Exception {
		return new ArrayList<>();
	}

	
	public static boolean add(Korisnik korisnik) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Korisnik (korisnickoIme, lozinka, datumRegistracije, uloga) values (?,?,?,?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getUloga().toString());
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
	

	public static boolean update(Korisnik korisnik) throws Exception {
		return false;
	}

	public static boolean delete(String korisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "delete from Korisnik where korisnickoIme = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}

}
