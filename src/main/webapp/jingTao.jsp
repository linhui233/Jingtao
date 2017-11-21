<%@page pageEncoding="utf-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="my.css">
<script src="js/jquery-1.11.1.js"></script>
<script src="js/my.js"></script>
<script>
	$(function (){
		console.log("内部js:${codeinfo }");
	});
</script>
</head>
<body>
	<div class="all">
		<div class="d1">
			<img src='img/waishe.jpg' id="i1"/>
			<a href="zhuce.html">注册</a>
			<a href="tologin.login">登陆</a>
		</div>
		<div class="d2">
			<form action="login.login" method="post" id="form">
				<p style="color:black"><b>密码登陆</b></p>
				<p>
					<input type="text" name="code" placeholder="输入账号" class="acc" value="${param.code }" id="ac1"/>
				</p>
				<p>
					<input type="password" name="pwd" placeholder="输入密码" class="acc"  id="pwd"/>
                     
                     
				</p>
				<p>
					<span>验证码:</span>
					<input style="width: 50px" name="imgCheck" type="text" />
					<img src="createimage.login" alt="验证码" onclick="this.setAttribute('src','createimage.login?x=' + Math.random());" title="点击更换" />
				</p>
					<%
                      String codeinfo = (String)request.getAttribute("codeinfo");
                     %>
                     <span style="color:red;" id="span1"><%=codeinfo==null?"":codeinfo%></span>
                     <%
                     	String pwdinfo =(String)request.getAttribute("pwdinfo");
                     
                     %>
                     <span style="color:red;" id="span2"><%=pwdinfo==null?"":pwdinfo %></span>
                     <span style="color:red;" >${error==null?"":error }</span>
				
				<p>
					<input type="checkbox" />记住密码
				</p>
				<p>
					<input type="submit" value="登陆" id="submit"/>
				</p>
			</form>
		</div>
		<div class="d3">
			<span>登陆后即可查看商品</span>
			<!-- <img src="img/timg.jpg " id="dont"/> -->
		</div>
	</div>
	
</body>
</html>