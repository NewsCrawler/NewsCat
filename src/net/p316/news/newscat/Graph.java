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
		int dDay = 0;
		if(request.getParameterMap().containsKey("dDay")) dDay = Integer.parseInt(request.getParameter("dDay"));
		
		Date sdate = Date.valueOf("2016-10-25");
		Date edate = Date.valueOf("2016-11-13");
		
		int addDay = 0;
		// TimeStamp로 바꿔야함
		if(dDay < 6){
			addDay = 26;
			addDay += dDay;
			edate = Date.valueOf("2016-10-" + Integer.toString(addDay));
		}else{
			addDay = 0;
			addDay += dDay-5;
			edate = Date.valueOf("2016-11-" + Integer.toString(addDay));
		}
		
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
		int[] cntID = new int[size];
		
		Iterator<WordTableDTO> itw = wto.iterator();
		WordTableDTO wo = null;
		while(itw.hasNext()){
			wo = itw.next();
			
			// 카테고리별 색상 넣는 부분
			// 0.0x else
			// 0.1x
			// 0.2x 동사
			// 0.3x 
			// 0.4x 지역
			// 0.5x
			// 0.6x 명사
			// 0.7x
			// 0.8x 고유명사
			// 0.9x 
			double color = 0.0;
			if(wo.getCate1().equals("동사")) color = 0.2;
			if(wo.getCate1().equals("지역")) color = 0.4;
			if(wo.getCate1().equals("명사")) color = 0.6;
			if(wo.getCate1().equals("고유명사")) color = 0.8;
			
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
					for(int k=j; k<i; k++){
						if(j == k) continue;
						if(gto.get(j).getIdx_word() == gto.get(k).getIdx_word()) continue;
						matrix[gto.get(j).getIdx_word()][gto.get(k).getIdx_word()]++;
						matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()]++;
						cntID[gto.get(j).getIdx_word()]++;
						cntID[gto.get(k).getIdx_word()]++;
						if(maxNodes < cntID[gto.get(j).getIdx_word()])
							maxNodes = cntID[gto.get(j).getIdx_word()];
						if(maxNodes < cntID[gto.get(k).getIdx_word()])
							maxNodes = cntID[gto.get(k).getIdx_word()];
					}
				}
				w = i;
			}
		}
		for(int j=w; j<=gto.size(); j++){
			for(int k=j; k<gto.size(); k++){
				if(j == k) continue;
				if(gto.get(j).getIdx_word() == gto.get(k).getIdx_word()) continue;
				matrix[gto.get(j).getIdx_word()][gto.get(k).getIdx_word()]++;
				matrix[gto.get(k).getIdx_word()][gto.get(j).getIdx_word()]++;
				cntID[gto.get(j).getIdx_word()]++;
				cntID[gto.get(k).getIdx_word()]++;
				if(maxNodes < cntID[gto.get(j).getIdx_word()])
					maxNodes = cntID[gto.get(j).getIdx_word()];
				if(maxNodes < cntID[gto.get(k).getIdx_word()])
					maxNodes = cntID[gto.get(k).getIdx_word()];
			}
		}

		// make JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		
		String json = "{ \"graph\" : [], ";
		
		ArrayList<JLink> links = new ArrayList<JLink>();
		for(int i=1; i<size; i++){
			for(int j=1; j<i; j++){
				if(matrix[i][j] > 0) {
					links.add(new JLink(i, j));
				}
			}
		}
		
		ArrayList<JNode> nodes = new ArrayList<JNode>();
		nodes.add(new JNode(60,0.4,"null"));
		for(int i=1; i<size; i++){
			
				// 노드의 크기를 평탄화 시키려면 x^2를 써야함
				int nodeSize = 10;
				if(cntID[i] > 10) nodeSize += 10;
				if(cntID[i] > 20) nodeSize += 10;
				if(cntID[i] > 30) nodeSize += 10;
				if(cntID[i] > 50) nodeSize += 10;
				if(cntID[i] > 100) nodeSize += 10;
				if(cntID[i] > 150) nodeSize += 10;
				if(cntID[i] > 500) nodeSize += 10;
				if(cntID[i] > 1000) nodeSize += 10;
				if(cntID[i] > 2000) nodeSize += 10;
				if(cntID[i] == 0) nodeSize = 1;
				nodes.add(new JNode(nodeSize, wordCate[i], wordID[i]));

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
