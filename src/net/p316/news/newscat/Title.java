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
	static final int PAGE_RECORDCNT = 25;
	//�������� ������ ���ڵ� ����
	static final int PAGE_PAGECNT = 5;
	//�������� ������ ������ ����(ex. [1], [2], [3], [4], [5])
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
		int totalpagecnt = conn.get_Recordcnt() / PAGE_RECORDCNT + 1;
		//��ü ������ ����
		int crtpage = 1;
		if(request.getParameter("page") != null)
		{
			crtpage = Integer.parseInt(request.getParameter("page"));
		}
		String test = request.getParameter("test");
		data = conn.get_Values(crtpage);
		conn.close();
		request.setAttribute("data", data);
		request.setAttribute("crtpage", crtpage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/test.jsp"); //<-- �̰� test.jsp���� title.jsp�� �ٲ�ߵǿ�
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