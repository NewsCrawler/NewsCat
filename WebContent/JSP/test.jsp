<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.*"%>
   	<%@ page import = "net.p316.news.newscat.data.NcTitle"
     %>
    <%
    	ArrayList<NcTitle> data = (ArrayList<NcTitle>) request.getAttribute("data");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for(int i=0; i<25; i++) 
		{
			out.println(data.get(i).get_title());
	%>
			<br>
	<%
		}
	%>
</body>
</html>