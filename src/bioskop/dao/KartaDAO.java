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
	public static List<Karta> getAllZaKorisnika(String korisnickoIme) throws Exception {
		List<Karta> karteKorisnika = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Karta where imeKorisnika = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();
			int kartaId = rset.getInt(1);
			int projekcijaId = rset.getInt(2);
			Projekcija p = ProjekcijaDAO.get(Integer.toString(projekcijaId));
			int sedisteId = rset.getInt(3);
			Sediste s = SedisteDAO.getOne(sedisteId);
			Date datumVremeProdaje = rset.getDate(4);
			String imeKorisnika = rset.getString(5);
			Korisnik k = KorisnikDAO.get(imeKorisnika);
			while (rset.next()) {
				int index=1;
				 kartaId = rset.getInt(index++);
				 projekcijaId = rset.getInt(index++);
				 p = ProjekcijaDAO.get(Integer.toString(projekcijaId));
				 sedisteId = rset.getInt(index++);
				 s = SedisteDAO.getOne(sedisteId);
				 datumVremeProdaje = rset.getDate(index++);
				 imeKorisnika = rset.getString(index++);
				 k = KorisnikDAO.get(imeKorisnika);
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
	
}
