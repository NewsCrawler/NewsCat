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
	//페이지당 보여줄 레코드 갯수
	static final int PAGE_PAGECNT = 5;
	//페이지당 보여줄 페이지 갯수(ex. [1], [2], [3], [4], [5])
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
		int crtpage = 1;
		String sdate = null;
		//시작 날짜
		String edate = null;
		//끝 날짜
		String keyword = null;
		//검색어
		
		if(request.getParameter("page") != null)
		{
			crtpage = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("sdate") != null)
		{
			sdate = request.getParameter("sdate");
		}
		
		if(request.getParameter("edate") != null)
		{
			edate = request.getParameter("edate");
		}
		
		if(request.getParameter("keyword") != null)
		{
			keyword = request.getParameter("keyword");
			if(keyword == "")
			{
				keyword = null;
			}
		}
		int totalrecordcnt = conn.get_Recordcnt(sdate, edate, keyword);
		int totalpagecnt = (totalrecordcnt-1) / PAGE_RECORDCNT + 1;
		//전체 페이지 갯수
		data = conn.get_Values(crtpage, sdate, edate, keyword);
		conn.close();
		request.setAttribute("data", data);
		request.setAttribute("page_recordcnt", PAGE_RECORDCNT);
		request.setAttribute("totalrecordcnt", totalrecordcnt);
		request.setAttribute("totalpagecnt", totalpagecnt);
		request.setAttribute("page_pagecnt", PAGE_PAGECNT);
		request.setAttribute("crtpage", crtpage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/title.jsp");
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