package bioskop.dao;

import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class ConnectionManager {
	
	private static final String DATABASE_NAME = "bioskopPrizma.db";
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String WINDOWS_PATH = "C:" + FILE_SEPARATOR + "Users" +FILE_SEPARATOR 
			+ "asus" + FILE_SEPARATOR + "Desktop" + FILE_SEPARATOR + "SQLite" + FILE_SEPARATOR + DATABASE_NAME;
	private static final String LINUX_PATH = "SQLite" + FILE_SEPARATOR + DATABASE_NAME;
	//za linux u c:/Temp/SQLite napraviti folder //malim slovima bioskopprizma.db
	// sa �emom baze  na Linux sistemu nalazi na ~/SQLite/)
	public static final String PATH = WINDOWS_PATH;	
	private static DataSource dataSource;

	public static void open() {
		try {
			Properties dataSourceProperties = new Properties();
			dataSourceProperties.setProperty("driverClassName", "org.sqlite.JDBC");
			dataSourceProperties.setProperty("url", "jdbc:sqlite:" + PATH);
			
			dataSource = BasicDataSourceFactory.createDataSource(dataSourceProperties); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
