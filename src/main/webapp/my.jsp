
<!-- 这个jsp用来测试作业随机数和time -->
<%@page pageEncoding="utf-8"
	contentType="text/html"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>随机数</title>
	</head>
	<body>
		<!-- jsp声明 -->
		<%!
			public double bai(double n) {
				return n*100;
			}
		%>
		<ol>
			<%
				for(int i=0;i<10;i++){
			%>
				<li><%=bai(Math.random())%></li>
			<% 
				}
			%>
		</ol>
		<%@include file="time.jsp" %>
	</body>
</html>
