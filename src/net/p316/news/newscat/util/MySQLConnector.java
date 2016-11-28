package net.p316.news.newscat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

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
		if (conn != null) 
			try 
			{ 
				conn.close(); 
			} 
		catch(SQLException ex) 
		{
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public Connection getConn()
	{
		return conn; 
	}
	
	public String make_Sql(String sql, String sdate, String edate, String keyword)
	{
		if(keyword == null)
		{
			if(sdate==null && edate==null)
			{
				sql += " WHERE `date` BETWEEN '2016-10-25 00:00:00' AND '2016-11-20 23:59:59'";
			}
			else
			{
				sql += " WHERE `date` BETWEEN " +sdate+ " AND " + edate;
			}
		}
		else
		{
			sql += " WHERE `title` LIKE '%" + keyword + "%'";
			if(sdate==null && edate==null)
			{
				sql += " AND `date` BETWEEN '2016-10-25 00:00:00' AND '2016-11-20 23:59:59'";
			}
			else
			{
				sql += " AND `date` `date` BETWEEN " +sdate+ " AND " + edate;
			}
		}
		return sql;
	}
	
	public int get_Recordcnt(String sdate, String edate, String keyword) 
	{
		int recordcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM `nc_title_1120`";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			sql = make_Sql(sql, sdate, edate, keyword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) 
			{
				recordcnt = rs.getInt(1);
			}
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("here 1");
		} 
		catch (Exception ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		} 
		finally 
		{
			if (rs != null) 
				try 
				{ 
					rs.close(); 
				} 
			catch(SQLException ex) 
			{
			}
	        if (stmt != null) 
	        	try 
	        	{ 
	        		stmt.close(); 
	        	} 
	        catch(SQLException ex) 
	        {
	        	System.out.println("SQLException: " + ex.getMessage());
	        }
		}
		System.out.println("sql in get_recordcnt : " + sql);
		System.out.println("recordcnt : " + recordcnt);
		
		return recordcnt;
	}
	
	public ArrayList<NcTitle> get_Values(int crtpage, String sdate, String edate, String keyword) 
	{
		ArrayList<NcTitle> data = new ArrayList<NcTitle>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `nc_title_1120`";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			sql = make_Sql(sql, sdate, edate, keyword);
			sql += " LIMIT " + (crtpage - 1) * 25 + ", 25";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
                NcTitle temp = new NcTitle();
                temp.set_Nc_idx(rs.getInt("idx"));
                temp.set_Nc_idx_category(rs.getInt("idx_category"));
                temp.set_Nc_url(rs.getString("url"));
                temp.set_Nc_title(rs.getString("title"));
                temp.set_Nc_company(rs.getString("company"));
                temp.set_Nc_date(rs.getTimestamp("date"));
                data.add(temp);
                System.out.println(temp.get_Nc_date());
			}
			
		} 
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
		} 
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage());
		} 
		finally 
		{
			if (rs != null) 
				try 
				{ 
					rs.close(); 
				} 
			catch(SQLException ex) 
			{
				System.out.println("SQLException: " + ex.getMessage());
			}
	        if (stmt != null) 
	        	try 
	        	{ 
	        		stmt.close(); 
	        	} 
	        catch(SQLException ex) 
	        {
	        		System.out.println("SQLException: " + ex.getMessage());
	        }
		}
		System.out.println("sql in get_values : " + sql);
		return data;
	}

}