package net.p316.news.newscat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.p316.news.newscat.data.NcTitle;
import net.p316.news.newscat.util.MySQLConnector;

/**
 * Servlet implementation class Title
 */
@WebServlet("/Title")
public class Title extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Title() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		MySQLConnector conn = new MySQLConnector();
		ArrayList<NcTitle> data = new ArrayList<NcTitle>();
		int pagecnt;
		pagecnt = conn.get_Pagecount();
		data = conn.get_Values();
		request.setAttribute("data", data);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/test.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}