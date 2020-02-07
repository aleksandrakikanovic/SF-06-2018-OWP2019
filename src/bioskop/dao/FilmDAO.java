package bioskop.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Film;

public class FilmDAO {
	
	public static Film get(String id1) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where id = ? and deleted='no'";
		pstmt = conn.prepareStatement(query);
		int id= Integer.parseInt(id1);
		pstmt.setInt(1, id);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			String naziv = rset.getString(2);
			String reziser = rset.getString(3);
			String glumci = rset.getString(4);
			String zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			String distributer = rset.getString(7);
			String zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			String opis = rset.getString(10);
			return new Film(id, naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}

	
	public static List<Film> getAllZaAdmina() throws Exception {
		List<Film> sviFilmovi = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Film where deleted='no'";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int id = rset.getInt(1);
			String naziv = rset.getString(2);
			String reziser = rset.getString(3);
			String glumci = rset.getString(4);
			String zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			String distributer = rset.getString(7);
			String zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			String opis = rset.getString(10);
			while (rset.next()) {
				int index = 1;
				id = rset.getInt(index++);
				 naziv = rset.getString(index++);
				 reziser = rset.getString(index++);
				 glumci = rset.getString(index++);
				 zanr = rset.getString(index++);
				 trajanje = rset.getInt(index++);
				 distributer = rset.getString(index++);
				 zemljaPorekla = rset.getString(index++);
				 godinaProizvodnje = rset.getInt(index++);
				 opis = rset.getString(index++);
				 Film f = new Film( id, naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
				sviFilmovi.add(f);
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} 
		}
		
		return sviFilmovi;
	}
		
			
	public static boolean add(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Film (naziv, reziser, glumci,zanr, trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis, deleted)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(index++, "no");

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
			String query = "update Film set naziv = ?, reziser = ?, glumci = ?, zanr = ?, trajanje = ?, distributer = ?, "
					+ "zemljaPorekla = ?, godinaProizvodnje = ?, opis = ? WHERE id = ?";

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
			pstmt.setInt(index++, film.getId());

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
			String query = "update Film set deleted='yes' where id = ?";
			pstmt = conn.prepareStatement(query);
			int index=1;
			pstmt.setInt(index++, indexOf);
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}	}

	}
	public static Film nazivPretraga(String naziv) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where naziv = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, naziv);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			naziv = rset.getString(2);
			String zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			String distributer = rset.getString(7);
			String zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			return new Film(naziv, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}
		return null;
	}
	public static Film zanrPretraga(String zanr) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where zanr = ?";
		pstmt = conn.prepareStatement(query);
		int index=0;
		pstmt.setString(1, zanr);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			String naziv = rset.getString(2);
			zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			String distributer = rset.getString(7);
			String zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			return new Film(naziv, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}
		return null;
	}
	public static Film distributerPretraga(String distributer) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where distributer = ?";
		pstmt = conn.prepareStatement(query);
		int index=0;
		pstmt.setString(1, distributer);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			String naziv = rset.getString(2);
			String zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			distributer = rset.getString(7);
			String zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			return new Film(naziv, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}
		return null;
	}
	public static Film zemljaPoreklaPretraga(String zemljaPorekla) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Film where zemljaPorekla = ?";
		pstmt = conn.prepareStatement(query);
		int index=0;
		pstmt.setString(1, zemljaPorekla);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			String naziv = rset.getString(2);
			String zanr = rset.getString(5);
			int trajanje = rset.getInt(6);
			String distributer = rset.getString(7);
			zemljaPorekla = rset.getString(8);
			int godinaProizvodnje = rset.getInt(9);
			return new Film(naziv, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}
		return null;
	}
}
