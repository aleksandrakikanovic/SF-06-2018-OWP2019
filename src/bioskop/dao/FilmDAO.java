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

			return new Film(naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}

	public static List<Film> getAll(int id) throws Exception {
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
		
		return false;
	}

	public static boolean delete(int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "delete from Film where id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}
}
