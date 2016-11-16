<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>
<%@ page import = "java.util.*"%>
<%@ page import = "net.p316.news.newscat.data.NcTitle"%>
<ul>
	<%
	ArrayList<NcTitle> data = (ArrayList<NcTitle>) request.getAttribute("data");
	int totalpagecnt = (int) request.getAttribute("totalpagecnt");
	int page_pagecnt = (int) request.getAttribute("page_pagecnt");
	int page_recordcnt = (int) request.getAttribute("page_recordcnt");
	int crtpage = (int) request.getAttribute("crtpage");
	%>
	<%
	for(int i=0; i<page_recordcnt; i++) 
	{
		%>
		<li><a href="<%data.get(i).get_url();%>" target="_blank">
		<%
		out.println(data.get(i).get_title());
   		%>
		</a></li>
		<%
   	}
  	%></ul>
  	<br><br>
  	<nav aria-label="Page navigation">
	<ul class="pagination">
	<%
	if(crtpage>page_pagecnt)
	{
  	%>
    <li>
      <a href="?page=<%=crtpage/page_pagecnt*page_pagecnt%>" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <%
	}
    %>    
    <%
    	for(int i=((crtpage-1)/page_pagecnt)*page_pagecnt; i<((crtpage-1)/page_pagecnt)*page_pagecnt+page_pagecnt; i++)
    	{
    %>
    		<li><a href="?page=<%=i+1%>"><%=i+1%></a></li>
    <%
    	}
    %>
    <%
	if((crtpage-1)/page_pagecnt != totalpagecnt/page_pagecnt)
	{
  	%>
    <li>
      <a href="?page=<%=crtpage/page_pagecnt*page_pagecnt+6%>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <%
    }
    %>
  </ul>
</nav>
</ul>
<%@ include file="template/footer.jsp" %>