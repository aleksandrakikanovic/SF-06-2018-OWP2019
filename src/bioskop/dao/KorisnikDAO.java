package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Korisnik.Uloga;
import bioskop.dao.ConnectionManager;

public class KorisnikDAO {
	
	public static Korisnik get(String korisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Korisnik where korisnickoIme = ? and deleted='no'";
		pstmt = conn.prepareStatement(query);
		int index=1;
		pstmt.setString(1, korisnickoIme);
		rset = pstmt.executeQuery();

		if (rset.next()) {
			Uloga uloga = Uloga.valueOf(rset.getString(4));
			korisnickoIme = rset.getString(1);
			String lozinka = rset.getString(2);
			long millis=rset.getLong(3);
		    Date datumRegistracije = new Date(millis);
			
			return new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
		try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
		try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
	}

		return null;
	}

	public static List<Korisnik> getAll() throws Exception {
		List<Korisnik> sviKorisnici = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Korisnik where deleted='no'";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			String korisnickoIme = rset.getString(1);
			String lozinka = rset.getString(2);
			long millis=rset.getLong(3);
		    Date datumRegistracije = new Date(millis);
			Uloga uloga = Uloga.valueOf(rset.getString(4));
			while (rset.next()) {
				int index = 1;
				 korisnickoIme = rset.getString(index++);
				 lozinka = rset.getString(index++);
				 millis=rset.getLong(index++);
			     datumRegistracije = new Date(millis);	
				 System.out.println(datumRegistracije);
				 uloga = Uloga.valueOf(rset.getString(index++));
				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga);
				sviKorisnici.add(k);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return sviKorisnici;
	}

	
	public static boolean add(Korisnik korisnik) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;

		try {
			
			String query = "insert into Korisnik (korisnickoIme, lozinka, datumRegistracije,uloga, deleted) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setString(index++, "no");

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
	
	
	public static boolean update(Korisnik korisnik, String firstUsername) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "update korisnik set korisnickoIme = ?, lozinka = ?, uloga = ? WHERE korisnickoIme = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setString(index++, firstUsername);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	public static boolean delete(String korisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "update Korisnik set deleted='yes' where korisnickoIme = ?";
			int index=1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, korisnickoIme);
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}

}
