<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>
<%@ page import = "java.util.*"%>
<%@ page import = "net.p316.news.newscat.data.NcTitle"%>
<%
ArrayList<NcTitle> data = (ArrayList<NcTitle>) request.getAttribute("data");
int totalpagecnt = Integer.parseInt(request.getAttribute("totalpagecnt").toString());
int totalrecordcnt = Integer.parseInt(request.getAttribute("totalrecordcnt").toString());
int page_pagecnt = Integer.parseInt(request.getAttribute("page_pagecnt").toString());
int page_recordcnt = Integer.parseInt(request.getAttribute("page_recordcnt").toString());
int crtpage = Integer.parseInt(request.getAttribute("crtpage").toString());
String psdate = request.getParameter("sdate");
String pedate = request.getParameter("edate");
String keyword = request.getParameter("keyword");
String sdate = "2016-11-09";
String edate = "2016-11-09";
if(psdate != null)
{
	sdate = psdate;
}
if(pedate != null)
{
	edate = pedate;
}
%>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			
			<div class="col-xs-12 col-md-6 col-md-offset-3">
				<h1>뉴스크롤러 기사 검색</h1>
				<form>
					<div class="form-group">
						<label for="datepicker">날짜</label>
						<div class="input-daterange input-group" id="datepicker">
						    <input type="text" class="input-sm form-control" name="sdate" value="sdate" />
						    <span class="input-group-addon">to</span>
						    <input type="text" class="input-sm form-control" name="edate" value="edate" />
						</div>
					</div>
					<div class="form-group">
						<label for="keyword">키워드</label>
						<input type="text" class="form-control" id="keyword" name="keyword" placeholder="키워드 입력" value="keyword">
					</div>
					<button type="submit" class="btn btn-primary btn-block">검색</button>
				</form>
			</div>
		</div>
	
		<div class="col-md-12" style="margin-top:50px;">
			<h3>
			<%=sdate%>에서 <%=edate%>까지의
			<%
			if(keyword != null && keyword.length() != 0) 
			{
			%>
			<%=keyword%>에 대한
			<%
			}
			%>
			검색 목록
			</h3>
		
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>기사</th>
						<th>언론사</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
				<%
				int recordcnt = 0;
				for(int i=0; i<page_recordcnt; i++) 
				{
				%>
					<tr>
						<td></td>
						<td><a href="<%=data.get(i).get_url()%>" target="_blank">
						<%
						out.println(data.get(i).get_title());
				   		%>
						</a></td>
						<td></td>
						<td></td>
					</tr>
				<%
				if(totalrecordcnt == recordcnt++)
				{
					break;
				}
			   	}
			  	%>
				</tbody>
			</table>
			
			
			<nav aria-label="Page navigation" style="text-align: center;">
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
					int endindex = ((crtpage-1)/page_pagecnt)*page_pagecnt+page_pagecnt;
					for(int i=((crtpage-1)/page_pagecnt)*page_pagecnt; i<endindex; i++)
					{
						if((totalpagecnt-1)/page_pagecnt == (crtpage-1)/page_pagecnt)
						{
							endindex = totalpagecnt - (totalpagecnt-1)/page_pagecnt*page_pagecnt;
						}
						else
						{
							endindex = ((crtpage-1)/page_pagecnt)*page_pagecnt+page_pagecnt;
						}
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
		</div>
	</div>
</div>

<%@ include file="template/footer.jsp" %>