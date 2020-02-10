package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Projekcija.ETipProjekcije;

public class ProjekcijaDAO {

	public static boolean add(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "insert into Projekcija (filmId, tipProjekcije ,salaId, datum, vreme, cenaKarte,admin, deleted)"
					+ " values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			String deleted = "no";
			pstmt.setInt(index++, projekcija.getFilm().getId());
			pstmt.setString(index++, projekcija.getTipProjekcije().toString());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setDate(index++, projekcija.getDatum());
			pstmt.setString(index++, projekcija.getVreme());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, "a");
			pstmt.setString(index++, deleted);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
	
	public static Projekcija get(String id1) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			String query = "select * from Projekcija where id=?";
			pstmt = conn.prepareStatement(query);
			int id= Integer.parseInt(id1);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				String f = rset.getString(2);
				Film film = FilmDAO.get(f);
				String t = rset.getString(3);
				ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(t);
				String s = rset.getString(4);
				Sala sala = SalaDAO.get(s);
				long millis=rset.getLong(5);
				String vreme = rset.getString(6);
			    Date datum = new Date(millis);
				double cenaKarte = rset.getDouble(7);
				String a = rset.getString(7);
				Korisnik admin = KorisnikDAO.get(a);
				 return new  Projekcija(id, film, tipProjekcije, sala, datum, vreme, cenaKarte, admin);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return null;
	}


	public static List<Projekcija> getAll(String imeFilma,String tip, String sala1, double cenaMin, double cenaMax ) throws Exception {
		List<Projekcija> sveProjekcije = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Projekcija where tipProjekcije = ? and salaId = ? "
					+ "and cenaKarte > ? and cenaKarte < ? and deleted='no' and filmId in (select id from Film where naziv = ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			int s1 = Integer.parseInt(sala1);
			pstmt.setString(index++, tip);
			pstmt.setInt(index++, s1);
			pstmt.setDouble(index++, cenaMin);
			pstmt.setDouble(index++, cenaMax);
			pstmt.setString(index++, imeFilma);
			rset = pstmt.executeQuery();
			while (rset.next()) {
			     index = 1;
			     int id = rset.getInt(index++);
			     String f = rset.getString(index++);
			     String t = rset.getString(index++);
			     String s = rset.getString(index++);
			     long millis=rset.getLong(index++);
			     String vreme = rset.getString(index++);
			     double cenaKarte = rset.getInt(index++);
			     String a = rset.getString(index++);
			     Korisnik admin=KorisnikDAO.get(a);
				 Film film = FilmDAO.get(f);
				 ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(t);
				 Date datum = new Date(millis);
				 Sala sala = SalaDAO.get(s);
				 Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, datum, vreme, cenaKarte, admin);
				 sveProjekcije.add(projekcija);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return sveProjekcije;
	}
	public static List<Projekcija> getProjekcije() throws Exception {
		List<Projekcija> projekcije = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Projekcija where deleted='no' order by vreme";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			String f = rset.getString(2);
			Film film = FilmDAO.get(f);
			String t = rset.getString(3);
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(t);
			String s = rset.getString(4);
			Sala sala = SalaDAO.get(s);
			long millis=rset.getLong(5);
			String vreme = rset.getString(6);
		    Date datum = new Date(millis);
			double cenaKarte = rset.getInt(6);
			String a = rset.getString(7);
			Korisnik admin = KorisnikDAO.get(a);
			while (rset.next()) {
			     int index = 1;
					id = rset.getInt(index++);
					 f = rset.getString(index++);
					 t = rset.getString(index++);
					 s = rset.getString(index++);
					 millis=rset.getLong(index++);
					 vreme = rset.getString(index++);
					 cenaKarte = rset.getInt(index++);
					 a = rset.getString(index++);
					 admin=KorisnikDAO.get(a);
					 film = FilmDAO.get(f);
					 tipProjekcije = ETipProjekcije.valueOf(t);
					 datum = new Date(millis);
					 sala = SalaDAO.get(s);
				 Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, datum, vreme, cenaKarte, admin);
				 projekcije.add(projekcija);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return projekcije;
	}
	public static boolean proveriSalu(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean postoji;
		try {
			String query = "select * from Projekcija where datum = ? and vreme = ? and salaId = ?";
			pstmt = conn.prepareStatement(query);
			int index=1;
			pstmt.setDate(index++, projekcija.getDatum());
			pstmt.setString(index++, projekcija.getVreme().toString());
			pstmt.setInt(index++, projekcija.getSala().getId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				postoji = true;
			}else {
				postoji=false;
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return postoji;
	}
	
	public static List<Projekcija> getAllZaFilm(int filmId) throws Exception {
		List<Projekcija> projekcijeFilma = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Projekcija where filmId = ? order by datum, vreme";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, filmId);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			String f = rset.getString(2);
			Film film = FilmDAO.get(f);
			String t = rset.getString(3);
			ETipProjekcije tipProjekcije = ETipProjekcije.valueOf(t);
			String s = rset.getString(4);
			Sala sala = SalaDAO.get(s);
			long millis=rset.getLong(5);
			String vreme = rset.getString(6);
		    Date datum = new Date(millis);
			double cenaKarte = rset.getInt(6);
			String a = rset.getString(7);
			Korisnik admin = KorisnikDAO.get(a);
			while (rset.next()) {
				int index = 1;
				id = rset.getInt(index++);
				 f = rset.getString(index++);
				 t = rset.getString(index++);
				 s = rset.getString(index++);
				 millis=rset.getLong(index++);
				 vreme = rset.getString(index++);
				 cenaKarte = rset.getInt(index++);
				 a = rset.getString(index++);
				 admin=KorisnikDAO.get(a);
				 film = FilmDAO.get(f);
				 tipProjekcije = ETipProjekcije.valueOf(t);
				 datum = new Date(millis);
				 sala = SalaDAO.get(s);
				 Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, datum, vreme, cenaKarte, admin);
				 projekcijeFilma.add(projekcija);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return projekcijeFilma;
	}
	public static boolean update(Projekcija projekcija) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "update Projekcija set filmId = ?, tipProjekcije ?, salaId = ?, datum = ?, vreme = ?, cenaKarte = ?,admin = ? "
					+ "where id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, projekcija.getFilm().getId());
			pstmt.setString(index++, projekcija.getTipProjekcije().toString());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setDate(index++, projekcija.getDatum());
			pstmt.setString(index++, projekcija.getVreme());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setInt(index++, projekcija.getId());

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
		
	public static boolean delete(int indexOf) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "update Projekcija set deleted='yes' where id = ?";
			pstmt = conn.prepareStatement(query);
			int index=1;
			pstmt.setInt(index++, indexOf);
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}
	}

	
	
}
