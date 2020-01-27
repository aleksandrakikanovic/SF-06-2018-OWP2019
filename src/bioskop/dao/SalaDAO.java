package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Sala;
import model.Projekcija.ETipProjekcije;

public class SalaDAO {
	

	public static Sala get(String id1) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Sala where id = ?";
		pstmt = conn.prepareStatement(query);
		int id= Integer.parseInt(id1);
		pstmt.setInt(1, id);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			String naziv = rset.getString(2);
			String tip = rset.getString(3);
			ETipProjekcije tipProjekcije;
			if(tip=="1") {tipProjekcije=ETipProjekcije.dvaD;}
			else if(tip=="2"){tipProjekcije=ETipProjekcije.triD;}
			else{tipProjekcije=ETipProjekcije.cetiriD;}
			String ostaleProjekcije = rset.getString(4);
				return new Sala(id, naziv, tipProjekcije, ostaleProjekcije);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}
	
	public static Sala pretragaNaziv(String naziv) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "select * from Sala where naziv = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, naziv);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			int id = rset.getInt(1);
			String tip = rset.getString(3);
			ETipProjekcije tipProjekcije;
			if(tip=="1") {tipProjekcije=ETipProjekcije.dvaD;}
			else if(tip=="2"){tipProjekcije=ETipProjekcije.triD;}
			else{tipProjekcije=ETipProjekcije.cetiriD;}
			String ostaleProjekcije = rset.getString(4);
				return new Sala(id, naziv, tipProjekcije, ostaleProjekcije);
			}
	} finally {
		try {pstmt.close();} catch (Exception ex) {ex.printStackTrace();}
		try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		try {conn.close();} catch (Exception ex) {ex.printStackTrace();}
	}

		return null;
	}


}
