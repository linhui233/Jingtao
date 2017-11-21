<%@page pageEncoding="utf-8" %>
<!Doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>注册成功页</title>
<style>

	.d1 {
		border: 1px solid  grey;
		width: 1200px;
		height: 100px;
		margin: 10px auto;
		text-align: right;
		line-height: 100px;
		background-image: url(img/bkpay.jpg);
	}
	a {
		text-decoration: none;
	    cursor: pointer;
	    color: black;
	}
	a:hover {
		color: #0080ff;
	}
	
	/*主要区*/
	.main {
		width: 800px;
		height: 300px;
		background-image: url(img/buy.jpg);
		margin: 0px auto;
	}
	.info {
		height: 200px;
		margin:0 100px 0 100px;
		text-align:center;
	}
	.info a:hover {
		text-decoration: underline;
	}
	
</style>
<script>
	
</script>
</head>
<body>
	<div class="d1">
		<a href="tologin.login">登陆</a>
		<a href="zhuce.html">注册</a>
	</div>
	<div>
		
	</div>
	<div class="main">
		<div class="info">
			<h1>
				<% 
					String zcinfo = (String)request.getAttribute("zcinfo");
					String account = (String)request.getAttribute("account");
				%>
				<%=zcinfo %>
				
			</h1>
				<p style="font-size : 25px">
					<%=account==null?"":"账号:"+account%>
				</p>
				<a href="tologin.login">返回首页</a>
			
		</div>
	</div>
	
</body>
</html>