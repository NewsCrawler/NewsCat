package net.p316.news.newscat.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.p316.news.newscat.DTO.GraphDTO;
import net.p316.news.newscat.util.MySQLConnector;

public class GraphDAO {
	
	MySQLConnector conn = null;
	
	public GraphDAO(){
		
	}
	
	public ArrayList<GraphDTO> getTable(Date sdate, Date edate){
		
		ArrayList<GraphDTO> alGraph = new ArrayList<GraphDTO>();
		
		MySQLConnector conn = new MySQLConnector();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer(
				"select b.idx_title, b.idx_word, c.word"
				+ " from nc_title_1120 a"
				+ " inner join nc_counter_title_word b"
				+ " on a.idx = b.idx_title"
				+ " inner join nc_word_table c"
				+ " on b.idx_word = c.idx"
				+ " WHERE a.date BETWEEN ? AND ?"
				+ " ORDER BY `b`.`idx_title` ASC");
		
		try{
			psmt = conn.getConn().prepareStatement(sql.toString());
			psmt.setDate(1, sdate);
			psmt.setDate(2, edate);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				GraphDTO gto = new GraphDTO();
				gto.setIdx_title(rs.getInt("idx_title"));
				gto.setIdx_word(rs.getInt("idx_word"));
				alGraph.add(gto);
			}
		} catch(Exception ex){
			System.out.println("Exception: " + ex.getMessage());
		} finally{
			if(rs!=null) try{ rs.close(); }catch(SQLException ex){}
			if(psmt!=null) try{ psmt.close(); }catch(SQLException ex){}
		}
		
		if(conn!=null) conn.close();
		
		return alGraph;
	}
}
