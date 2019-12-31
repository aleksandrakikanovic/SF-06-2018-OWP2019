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
		//System.out.print(conn); 
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
		try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
		try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
		//try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
	}

		return null;
	}

	public static List<Korisnik> getAll(Korisnik korisnik) throws Exception {
		List<Korisnik> korisnici = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Korisnik";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			String korisnickoIme = rset.getString(0);
			String lozinka = rset.getString(1);
			Date datumRegistracije = rset.getDate(2);
			Uloga uloga = Uloga.valueOf(rset.getString(3));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				index = 1;
				String korIme = rset.getString(index++);
				String loz = rset.getString(index++);
				Uloga ulogaKorisnika = Uloga.valueOf(rset.getString(index++));
				Date datumRegistracijeKorisnika = rset.getDate(index++);
				Korisnik k = new Korisnik(korIme, loz, datumRegistracijeKorisnika, ulogaKorisnika);
				korisnici.add(k);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return korisnici;
	}


	

	
	public static boolean add(Korisnik korisnik) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;

		try {
			
			String query = "insert into Korisnik (korisnickoIme, lozinka, datumRegistracije,uloga) values (?, ?, ?, ?)";
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
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "update korisnik set korisnickoIme = ?, lozinka = ?, WHERE korisnickoIme = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setDate(index++, korisnik.getDatumRegistracije());
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	
	public static boolean delete(String korisnickoIme) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		//System.out.println(conn);

		PreparedStatement pstmt = null;
		try {
			String query = "delete from Korisnik where korisnickoIme = ?";
			int index=1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, korisnickoIme);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}

}
