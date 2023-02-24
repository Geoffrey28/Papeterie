package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
	
	private static Connection connect;
	
	private final static String URL_DB = Settings.getProperty("url");
	private final static String USER_DB = Settings.getProperty("user");
	private final static String PASSWORD_DB = Settings.getProperty("password");
	
	public static Connection getConnection() throws SQLException {
		if (connect == null) {
			connect = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
		}
		return connect;
	}
}
