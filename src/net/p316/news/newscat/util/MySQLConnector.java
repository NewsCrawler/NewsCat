package net.p316.news.newscat.util;

import java.sql.*;

public class MySQLConnector {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://news.p316.net/news_crawler";
	static final String USER = "crawler";
	static final String PASS = "4X\"Zd@JaTs\\Yk<c]";

	private DriverManager driverManager;
	private Connection conn = null;
	
	public MySQLConnector(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL
					+ "?characterEncoding=utf8"
					+ "&user=" + USER
					+ "&password=" + PASS);
		} catch(Exception ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public void get_Values() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "SELECT * FROM `nc_title`";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
                System.out.println(rs.getString("title") + " | " + rs.getString("company") + " | " + rs.getString("date"));
            }
		} catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		} catch (Exception ex){
			
		}
	}

}