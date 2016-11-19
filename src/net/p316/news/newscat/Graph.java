package net.p316.news.newscat;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.p316.news.newscat.DAO.GraphDAO;
import net.p316.news.newscat.DAO.WordTableDAO;
import net.p316.news.newscat.DTO.GraphDTO;
import net.p316.news.newscat.DTO.WordTableDTO;
import net.p316.news.newscat.util.graph.JLink;
import net.p316.news.newscat.util.graph.JNode;
/**
 * Servlet implementation class Graph
 */
@WebServlet("/Graph")
public class Graph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Graph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check request getParam
		Date sdate = Date.valueOf("2016-10-25");
		Date edate = Date.valueOf("2016-11-13");
		
		// get DB
		GraphDAO graphDAO = new GraphDAO();
		ArrayList<GraphDTO> gto = graphDAO.getTable(sdate, edate);
		WordTableDAO wordTableDAO = new WordTableDAO();
		ArrayList<WordTableDTO> wto = wordTableDAO.getWordTable();
		
		// run Algorithm
		int size = 400;
		int[][] matrix = new int[size][size];
		String[] wordID = new String[size];
		double[] wordCate = new double[size];
		boolean[] usedID = new boolean[size];
		
		Iterator<WordTableDTO> itw = wto.iterator();
		WordTableDTO wo = null;
		while(itw.hasNext()){
			wo = itw.next();
			
			// 카테고리별 색상 넣는 부분
			// 0.0x
			// 0.1x
			// 0.2x
			// 0.3x
			// 0.4x
			// 0.5x
			// 0.6x
			// 0.7x
			// 0.8x
			// 0.9x
			double color = 0.3;
			
			wordCate[wo.getIdx()] = color;
			wordID[wo.getIdx()] = wo.getWord();
		}
		
		// create Matrix
		int w = 0;
		int maxNodes = 0;
		for(int i=1; i<gto.size(); i++){
			usedID[gto.get(i).getIdx_word()] = true;
			if(gto.get(i-1).getIdx_title() != gto.get(i).getIdx_title()){
				for(int j=w; j<i; j++){
					for(int k=w; k<i; k++){
						if(j == k) continue;
						if(gto.get(j).getIdx_word() == gto.get(k).getIdx_word()) continue;
						matrix[gto.get(j).getIdx_word()][gto.get(k).getIdx_word()]++;
						matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()]++;
						if(maxNodes < matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()])
							maxNodes = matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()];
					}
				}
				w = i;
			}
		}
		for(int j=w; j<gto.size(); j++){
			for(int k=w; k<gto.size(); k++){
				if(j == k) continue;
				if(gto.get(j).getIdx_word() == gto.get(k).getIdx_word()) continue;
				matrix[gto.get(j).getIdx_word()][gto.get(k).getIdx_word()]++;
				matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()]++;
				if(maxNodes < matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()])
					maxNodes = matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()];
			}
		}

		// make JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int dDay = 0;
		if(request.getParameterMap().containsKey("dDay")) dDay = Integer.parseInt(request.getParameter("dDay"));
		
		Gson gson = new Gson();
		
		String json = "{ \"graph\" : [], ";
		
		ArrayList<JLink> links = new ArrayList<JLink>();
		links.add(new JLink(0, 1));
		links.add(new JLink(0, 2));
		links.add(new JLink(0, 3));
		links.add(new JLink(0, 4));
		links.add(new JLink(0, 5));
		links.add(new JLink(0, 6));
		links.add(new JLink(0, 7));
		links.add(new JLink(1, 3));
		links.add(new JLink(1, 4));
		links.add(new JLink(1, 5));
		links.add(new JLink(1, 6));
		links.add(new JLink(1, 8));
		links.add(new JLink(2, 4));
		links.add(new JLink(2, 5));
		links.add(new JLink(2, 6));
		if(dDay > 3) {
			links.add(new JLink(2, 9));
			links.add(new JLink(3, 5));
			links.add(new JLink(3, 6));
			links.add(new JLink(3, 10));
			links.add(new JLink(4, 11));
			links.add(new JLink(5, 6));
			links.add(new JLink(5, 12));
			links.add(new JLink(6, 13));
		}
		
		ArrayList<JNode> nodes = new ArrayList<JNode>();
		nodes.add(new JNode(80,1,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(10,1,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(30,1,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(60,1,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(60,0.4,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(40,0.4,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(20,0.4,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(30,0.4,"테스트" + Integer.toString(dDay)));
		nodes.add(new JNode(10,0.4,"테스트" + Integer.toString(dDay)));
		if(dDay > 3){
			nodes.add(new JNode(20,0.4,"테스트" + Integer.toString(dDay)));
			nodes.add(new JNode(60,0.1,"테스트" + Integer.toString(dDay)));
			nodes.add(new JNode(60,0.1,"테스트" + Integer.toString(dDay)));
			nodes.add(new JNode(60,0.1,"테스트" + Integer.toString(dDay)));
			nodes.add(new JNode(20,0.1,"테스트" + Integer.toString(dDay)));
		}
		
		json += "\"links\" :" + new Gson().toJson(links) + ", ";
		json += "\"nodes\" :" + new Gson().toJson(nodes) + ", ";
		json += "\"directed\": false, \"multigraph\": false }";
		
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
