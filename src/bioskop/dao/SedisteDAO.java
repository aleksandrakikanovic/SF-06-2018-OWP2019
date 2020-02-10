package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Sediste;
import model.Korisnik.Uloga;

public class SedisteDAO {
	
	public static String get(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String brojSedista = "";
		try {
		String query = "select count(*) from Sediste  where salaId = ? and id not in (select idSediste from Karta where projekcijaId = ? and deleted='no')";
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
	public static Sediste getOne (int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Sediste  where id = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			int redniBroj = rset.getInt(2);
			int s = rset.getInt(3);
			String salaId = Integer.toString(s);
			Sala sala = SalaDAO.get(salaId);
			return new Sediste(id, redniBroj, sala);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}
	public static boolean get1(int id, int idProjekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean sedisteZauzeto;
		try {
		String query = "select * from Sediste where id = ? and id in(select idSediste from Karta where projekcijaId = ?)";
		pstmt = conn.prepareStatement(query);
		int index=1;
		pstmt.setInt(index++, id);
		pstmt.setInt(index++, idProjekcija);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			sedisteZauzeto = true;
			}else {
				sedisteZauzeto = false;
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return sedisteZauzeto;
	}
	public static List<Sediste> getAll() throws Exception {
		List<Sediste> svaSedista = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Sediste";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			int redniBroj = rset.getInt(2);
			int sId = rset.getInt(3);
			String salaId = Integer.toString(sId);
			Sala sala = SalaDAO.get(salaId);
			while (rset.next()) {
				int index = 1;
				id = rset.getInt(index++);
				redniBroj = rset.getInt(index++);
				Sediste s = new Sediste(id, redniBroj, sala);
				svaSedista.add(s);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return svaSedista;
	}

	public static List<Sediste> getAllZaKartu(Projekcija projekcija) throws Exception {
		List<Sediste> sedistaCmb = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Sediste where salaId = ? and  id not in (select idSediste from Karta where projekcijaId = ? and deleted='no')";
			pstmt = conn.prepareStatement(query);
			int index1=1;
			int idSala= projekcija.getSala().getId();
			pstmt.setInt(index1++, idSala);
			int idProjekcija = projekcija.getId();
			pstmt.setInt(index1++, idProjekcija);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			int redniBroj = rset.getInt(2);
			int sId = rset.getInt(3);
			String salaId = Integer.toString(sId);
			Sala sala = SalaDAO.get(salaId);
			while (rset.next()) {
				int index = 1;
				id = rset.getInt(index++);
				redniBroj = rset.getInt(index++);
				Sediste s = new Sediste(id, redniBroj, sala);
				sedistaCmb.add(s);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return sedistaCmb;
	}

}
