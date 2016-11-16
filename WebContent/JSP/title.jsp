<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>
<%@ page import = "java.util.*"%>
<%@ page import = "net.p316.news.newscat.data.NcTitle"%>
<h1>기사들이 있다요</h1>
<ul>
	<%
	ArrayList<NcTitle> data = (ArrayList<NcTitle>) request.getAttribute("data");
	int crtpage = (int) request.getAttribute("crtpage");
	%>
	<%
	for(int i=0; i<25; i++) 
	{
		%>
		<li>
		<%
		out.println(data.get(i).get_title());
   		%>
		</li>
		<%
   	}
  	%>
</ul>
<%@ include file="template/footer.jsp" %>