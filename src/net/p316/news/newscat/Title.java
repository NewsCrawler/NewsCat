package net.p316.news.newscat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	//페이지당 보여줄 레코드 갯수
	static final int PAGE_PAGECNT = 5;
	//페이지당 보여줄 페이지 갯수(ex. [1], [2], [3], [4], [5])
	static final String DATEFORMAT = new String("yyyyMMdd");
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
		//전체 페이지 갯수
		int crtpage = 1;
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		Date sdate = null;
		//시작 날짜
		Date edate = null;
		//끝 날짜
		if(request.getParameter("page") != null)
		{
			crtpage = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("sdate") != null)
		{
			try 
			{
				sdate = sdf.parse(request.getParameter("sdate"));
			} 
			catch (ParseException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("edate") != null)
		{
			try 
			{
				edate = sdf.parse(request.getParameter("edate"));
			} 
			catch (ParseException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String test = request.getParameter("test");
		data = conn.get_Values(crtpage, sdate, edate);
		conn.close();
		request.setAttribute("data", data);
		request.setAttribute("crtpage", crtpage);
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