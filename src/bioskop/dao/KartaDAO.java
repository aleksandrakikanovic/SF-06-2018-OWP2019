package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Sediste;

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
	public static Karta get(int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Karta where id = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			int kartaId = rset.getInt(1);
			int projekcijaId = rset.getInt(2);
			Projekcija p = ProjekcijaDAO.get(Integer.toString(projekcijaId));
			int sedisteId = rset.getInt(3);
			Sediste s = SedisteDAO.getOne(sedisteId);
			long millis = rset.getLong(4);
			Date datumVremeProdaje= new Date(millis);
			String imeKorisnika = rset.getString(5);
			Korisnik k = KorisnikDAO.get(imeKorisnika);
			return new Karta(kartaId, p, s, datumVremeProdaje, k);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}
	
	
	public static List<Karta> getAllZaKorisnika(String korisnickoIme) throws Exception {
		List<Karta> karteKorisnika = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Karta where imeKorisnika = ? and deleted='no'";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int kartaId = rset.getInt(1);
				int projekcijaId = rset.getInt(2);
				Projekcija p = ProjekcijaDAO.get(Integer.toString(projekcijaId));
				int sedisteId = rset.getInt(3);
				Sediste s = SedisteDAO.getOne(sedisteId);
				long millis = rset.getLong(4);
				Date datumVremeProdaje= new Date(millis);
				String imeKorisnika = rset.getString(5);
				Korisnik k = KorisnikDAO.get(imeKorisnika);
				Karta karta = new Karta(kartaId, p, s, datumVremeProdaje, k);
				karteKorisnika.add(karta);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return karteKorisnika;
	}
	public static boolean delete(int indexOf) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "update Karta set deleted='yes' where id = ?";
			pstmt = conn.prepareStatement(query);
			int index=1;
			pstmt.setInt(index++, indexOf);
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}
	
}
