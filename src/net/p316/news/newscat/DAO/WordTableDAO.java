package net.p316.news.newscat.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.p316.news.newscat.DTO.WordTableDTO;
import net.p316.news.newscat.util.MySQLConnector;

public class WordTableDAO {

	MySQLConnector conn = null;
	
	// It's better not to query the DB
	public ArrayList<WordTableDTO> getWordTable(){
		ArrayList<WordTableDTO> wordTable = new ArrayList<WordTableDTO>();
		
		MySQLConnector conn = new MySQLConnector();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("SELECT `idx`,`word`,`cate1` FROM `nc_word_table`");
		
		try{
			psmt = conn.getConn().prepareStatement(sql.toString());
			rs = psmt.executeQuery();
			
			while(rs.next()){
				WordTableDTO wto = new WordTableDTO();
				wto.setIdx(rs.getInt("idx"));
				wto.setWord(rs.getString("word"));
				wto.setCate1(rs.getString("cate1"));
				wordTable.add(wto);
			}
		} catch(Exception ex){
			System.out.println("Exception: " + ex.getMessage());
		} finally{
			if(rs!=null) try{ rs.close(); }catch(SQLException ex){}
			if(psmt!=null) try{ psmt.close(); }catch(SQLException ex){}
		}
		
		if(conn!=null) conn.close();
		
		return wordTable;
	}
}
