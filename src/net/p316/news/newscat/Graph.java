package net.p316.news.newscat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		
		String json = "{ \"graph\" : [], ";
		
		ArrayList<JLink> links = new ArrayList<JLink>();
		links.add(new JLink());
		links.add(new JLink());
		links.add(new JLink());
		
		ArrayList<JNode> nodes = new ArrayList<JNode>();
		nodes.add(new JNode());
		nodes.add(new JNode());
		nodes.add(new JNode());
		nodes.add(new JNode());
		
		json += new Gson().toJson(links);
		json += new Gson().toJson(nodes);
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
