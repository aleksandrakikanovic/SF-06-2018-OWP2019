package bioskop.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Film;

public class FilmDAO {
	
	public static Film get(String id) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where id = ?";
		pstmt = conn.prepareStatement(query);
		int index=0;
		int id1= Integer.parseInt(id);
		pstmt.setString(index++, id);
		rset = pstmt.executeQuery();

		if (rset.next()) {
			String naziv = rset.getString(1);
			String reziser = rset.getString(2);
			String glumci = rset.getString(3);
			String zanr = rset.getString(4);
			int trajanje = rset.getInt(5);
			String distributer = rset.getString(6);
			String zemljaPorekla = rset.getString(7);
			int godinaProizvodnje = rset.getInt(8);
			String opis = rset.getString(9);

			return new Film(id1, naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}

	
	public static List<Film> getAllZaAdmina(Film film) throws Exception {
		List<Film> filmovi = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Film";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			int id = rset.getInt(0);
			String naziv = rset.getString(1);
			String reziser = rset.getString(2);
			String glumci = rset.getString(3);
			String zanr = rset.getString(4);
			int trajanje = rset.getInt(5);
			String distributer = rset.getString(6);
			String zemljaPorekla = rset.getString(7);
			int godinaProizvodnje = rset.getInt(8);
			String opis = rset.getString(9);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				index = 1;
				int idFilma = rset.getInt(index++);
				String nazivFilma = rset.getString(index++);
				String reziserFilma = rset.getString(index++);
				String glumciFilma = rset.getString(index++);
				String zanrFilma = rset.getString(index++);
				int trajanjeFilma = rset.getInt(index++);
				String distributerFilma = rset.getString(index++);
				String zemljaPoreklaFilma = rset.getString(index++);
				int godinaProizvodnjeFilma = rset.getInt(index++);
				String opisFilma = rset.getString(index++);
				Film f = new Film(idFilma, nazivFilma, reziserFilma, glumciFilma, zanrFilma, trajanjeFilma,
						distributerFilma, zemljaPoreklaFilma, godinaProizvodnjeFilma, opisFilma);
				filmovi.add(f);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return filmovi;
	}

		
			
	public static List<Film> getAllZaKorisnike(int id) throws Exception {
		////
		return new ArrayList<>();
	}

	
	public static boolean add(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Film (naziv, reziser, glumci,zanr, trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis)"
					+ " values (?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanr());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	
	public static boolean update(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "update film set naziv = ?, reziser = ?, glumci = ?, zanr = ?, trajanje = ?, distributer = ? "
					+ "zemljaPorekla = ?, godinaProizvodnje = ?, opis = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, film.getId());
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanr());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}

	public static boolean delete(int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "delete from Film where id = ?";
			pstmt = conn.prepareStatement(query);
			int index=1;
			pstmt.setInt(index++, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}
}
