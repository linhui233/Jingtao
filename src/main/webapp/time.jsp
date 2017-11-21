<%@page pageEncoding="utf-8"
	contentType="text/html"
	import="java.util.*,java.text.*"%>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String now = sdf.format(date);
%>
<p><%=now%></p>