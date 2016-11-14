package net.p316.news.newscat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.p316.news.newscat.data.NcTitle;

public class MySQLConnector 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://news.p316.net/news_crawler";
	static final String USER = "crawler";
	static final String PASS = "4X\"Zd@JaTs\\Yk<c]";
	
	
	private DriverManager driverManager;
	private Connection conn = null;
	
	public MySQLConnector()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL
					+ "?characterEncoding=utf8"
					+ "&user=" + USER
					+ "&password=" + PASS);
		} 
		catch(Exception ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public void close(){
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	
	public int get_Recordcnt() 
	{
		int rowcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "SELECT COUNT(*) FROM `nc_title`";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) 
			{
				rowcnt = rs.getInt(1);
			}
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		} 
		catch (Exception ex)
		{
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		}
		
		return rowcnt;
	}
	
	public ArrayList<NcTitle> get_Values(int crtpage) 
	{
		ArrayList<NcTitle> data = new ArrayList<NcTitle>();
		Statement stmt = null;
		ResultSet rs = null;
		crtpage--;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "SELECT * FROM `nc_title` LIMIT " + crtpage * 25 + ", 25";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
                NcTitle temp = new NcTitle();
                temp.set_idx(rs.getInt("idx"));
                temp.set_idx_category(rs.getInt("idx_category"));
                temp.set_url(rs.getString("url"));
                temp.set_title(rs.getString("title"));
                temp.set_company(rs.getString("company"));
                temp.set_date(rs.getDate("date"));
                data.add(temp);
			}
		} 
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		} 
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage());
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		}
		return data;
	}

}