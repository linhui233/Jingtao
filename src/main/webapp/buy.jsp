<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>这是购物车</title>
<style>
	
	.d1 {
		border: 1px solid  grey;
		width: 1200px;
		height: 50px;
		margin: 10px auto;
		line-height: 50px;
	}

	.d2 {
		border: 1px solid grey;
		height: 300px;
		margin: 10px auto;
	} 
	 .d3 {
		border: 1px solid grey;
		margin: 10px auto;
		text-align: center;
		height: 1200px;
		background-image: url("img/background.jpg");
	} 
	.user {
		float: right;
	}
	/*图片隐藏*/
	.hide {
		display: none;
	}
	
	#lunbo>img {
		width: 1900px;
		height: 300px;
	}
	.dian {
		position: relative;
		top:-50px;
		
	}
	ul .on {
   	 background-position: 0 0;
	}
	ul {
		text-align: center;
  	  	padding-top: 5px;
  	  	position: relative;
	}
	 ul li {
	    cursor: pointer;
	    display: inline-block;
	    zoom: 1;
	    width: 8px;
	    height: 8px;
	    margin: 5px;
	    background: url(img/tg_flash_p.png) -18px 0;
	    overflow: hidden;
	    font-size: 0;
	}
	
	
   
    table {
      margin:0 auto;
      width: 1200px;
      border:2px solid #aaa;
      border-collapse:collapse;
      background-color: rgb(255,234,180);
    }
    table th, table td {
      border:1px solid #aaa;
    }

    th {
      background-color:#eee;
    }
    .images{
    
    	width: 200px;
    	height: 100px;
    }
    #buy1 {
    	position: fixed;
    	right: 20px;
    	bottom: 20px;
    	margin-top: 200px;
    	width: 300px;
    	height: 300px;
    	float: right;
    }
    /*分页区*/
   .page {
		text-align: center;
	
	}
	.page a {
		line-height: 40px;
		text-decoration: none;
		margin: 0;
		padding: 5px 10px;
		
		color: black;
	    font-weight:bold;
	}
	
	.page a:hover {
		border: 1px solid #97B9C9;
		color: #067DB5;
		background-color: #CDDDE4;
		cursor: pointer;
	}
	.page .current_page {
		border: 1px solid  #97B9C9;
		font-weight: bold;
		background-color:#fff;
	}
   
</style>
<script src="js/jquery-1.11.1.js"></script>
<script>
	//页面加载之后
	window.onload = function() {
		start();
		//设置分页背景
		var page = ${page};
		var a = $(".page").children();
		
		for(var i =0;i<a.length;i++) {
			console.log(1);
			var x = a[i];
			console.log(x.text);
			if(x.text==page) {
				x.setAttribute("class","current_page");
			}
		}
		
	};
	var id;
	var n = 0;
	function start() {
		
		//启动定时器,间隔为2S
		id = setInterval(function(){
			n++;
			//获取所有图片
			var imgs = document.getElementsByName("lunbo1");
			//将他们都隐藏
			for(var i=0;i<imgs.length;i++) {
				imgs[i].className = "hide";
			}
			//找出下一个图片
			var next = n%imgs.length;
			//将这个图片显示
			imgs[next].className = "";
		},2500);
		
	}
	
	function stop(){
		clearInterval(id);
	}
	
	// 下面是添加商品的逻辑
	function add_shoppingcart(btn) { //btn为dom对象
    		//obj.eq(i) == $(obj[i])
    		//获取按钮的父亲的大哥的内容
    		var name = $(btn).parent().siblings().eq(0).html();
    		var price = $(btn).parent().siblings().eq(1).html();
    		//创建一个新的行
    		
    		var tr = $(//apend可以添加 jquery元素,dom元素 和html元素
    				
	    		'<tr>'+
		          '<td><input name="name1" type="text" hidden="true" value="'+name+'"/>'+name+'</td>'+
		            '<td><input name="price1" type="text" hidden="true" value="'+price+'"/>'+price+'</td>'+
		            '<td align="center">'+
		              '<input type="button" value="-" onclick="decrease(this)"/>'+
		              '<input name="buySum" type="text" size="3"  value="1" onblur="setsum(this)"/>'+
		              '<input type="button" value="+" onclick="increase(this)"/>'+
		            '</td>'+
		            '<td>'+price+'</td>'+
		            '<td align="center"><input type="button" value="x" onclick="removex(this)"/></td>'+
	          '</tr>');
    		$("#goods").append(tr);
    		sum();
    		
    	}
   	//加法
   	function setsum(btn){
   		var num = $(btn).val();
   		var price = $(btn).parent().prev().html();
   		$(btn).parent().next().html(num*price);
   		sum();
   	}
   	function increase(btn) {
   		var text = $(btn).prev();
   		var num = $(btn).prev().val();
   		console.log(num);
   		num++;
   		text.val(num);
   		var price = $(btn).parent().prev().text();
   		console.log(price);
   		console.log(price*num);
   		$(btn).parent().next().text(num*price);
   		console.log($(btn).parent().next());
   		sum();
   	}
   	function decrease(btn) {
 			var text = $(btn).next();  		
 			var num = text.val();
 			if(num>0) {
 				num--;
 	  			text.val(num);
 	  			var tds = $(btn).parent().siblings();
 	  			var price = tds.eq(1).text();
 	  			tds.eq(2).text(price * num);
 			}
 			sum();
   	}
   	function removex(btn) {
   		var a = $(btn).parent().parent().remove();
   		sum();
   	}
   	function sum() {
   		var total = $("#total");
   		var trs = $("#goods").children();
   		var sum = 0;
   		for(var i=0;i<trs.length;i++) {
   			var cost = trs.eq(i).children().eq(3).html();
   			sum += parseFloat(cost);
   		}
   		if(trs.length){
    		total.html(sum);
   		} else {
   			total.empty();
   		}
   	}
   	
    //购买计算
    function buy() {
    	var money = ${money };
    	var sum = $("#total").html();
    	console.log(sum);
    	if(money < sum) {
    		alert("余额不足");
    	} else {
    		alert("购买成功"); //后续是写个Servlet进行余额的修改 ,当前页余额也变化.
    	}
    }
    var maxpage = ${maxpage};
    //上一页下一页
    function hrefadd(){
    	console.log(1);
    	
    	var page = ${page};
    	if(page<maxpage) {
    		
    		location.href="shop.shop?page="+(page+1); 
    	}
    }
    function hrefdecrease(){
    	console.log(2);
    	var page = ${page};
    	if(page>1){
    		location.href="shop.shop?page="+(page-1); 
    	}
    }
</script>
</head>
<body>
	 <div class="d1">
			
		<b><span style="font-size:25px ">购物车</span></b>
		<audio src="musics\Kiss The Rain-Yiruma.mp3" controls></audio>
		<div class="user">
			<span class="s1">你的账号:${code }</span>
			|
			<span class="s2">你的金额:${money }</span>
			<a href="relogin.login">退出</a>
			
		</div>
		
	</div>
	<div class="d2">
		<!-- 轮播在day07 demo4 -->
		<div onmouseover="stop()" onmouseout="start()" id="lunbo">
			<img src="images/leishe.jpg" class="i1" name="lunbo1"/>
			<img src="images/erji.jpg" class="hide" name="lunbo1"/>
			<img src="images/shubiao.jpg" class="hide" name="lunbo1"/>
			<img src="images/kongkuang.jpg" class="hide" name="lunbo1"/>
			<img src="images/bkpay.jpg" class="hide" name="lunbo1"/>
			<img src="images/jianpan.jpg" class="hide" name="lunbo1"/>
			<!-- <div class="dian">
				<ul>
					<li class="on">1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
				</ul>
			</div> -->
			
		</div>
		
	<!-- 	 自动点击在jquery demo5
		验证在day06 demo1 -->
	</div> 
	<div class="d3">
		<form action="savebuy.shop?page=${page }" method="post">
			<input type="button" value="查询商品" onclick="location.href='shop.shop?page=1';" />
			<img src="img/timg.jpg" id="buy1"/>
			<table class="t1">
		      <tr>
		      	
		        <th>商品</th>
		        <th>单价(元)</th>
		        <th>颜色</th>
		        <th>库存</th>
		        <th>好评率</th>
		        <th>图片</th>
		        <th>操作</th>
		      </tr>   
		      <!-- <tr>
		        <td>罗技M185鼠标</td>
		        <td>80</td>
		        <td>黑色</td>
		        <td>893</td>
		        <td>98%</td>
		        <td align="center">
		          <input type="button" value="加入购物车" onclick="add_shoppingcart(this);"/>
		        </td>
		      </tr> -->
		      <c:forEach items="${shops }" var="s">
		      	<tr>
		      		
		      		<td>${s.name }</td>
			        <td>${s.money }</td>
			        <td>${s.color }</td>
			        <td>${s.sum }</td>
			        <td>${s.nice }</td>
			        <td style="width:200px"><img src="shubiao/${s.imgId }.jpg" class="images"/></td>
			        <td align="center">
		          		<input type="button" value="加入购物车" onclick="add_shoppingcart(this);"/>
		        	</td>
		      	</tr>
		      </c:forEach>
		     
		      
		    </table>
     <!--分页-->
    	
	    <div class="page">
	      <%-- 	<c:set var="maxpage" value="${maxpage }" scope="page"/> --%>
	      	
	     	<a onclick="hrefdecrease()">上一页</a>
	     	<!-- 循环分页 -->
	     	<c:forEach begin="1" end="${maxpage}" var="i">
	     		<a href="shop.shop?page=${i }">${i }</a>
	     	</c:forEach>
	     	<a onclick="hrefadd()" >下一页</a>
	     </div> 
        
  
	    <h1>购物车</h1>
	    
	    	<table class="t2">
		      <thead>
		        <tr>
		          <th>商品</th>
		          <th>单价(元)</th>
		          <th>数量</th>
		          <th>金额(元)</th>
		          <th>删除</th>
		        </tr>
		      </thead>
		      <tbody id="goods">
		        <c:forEach items="${buyshops1 }" var="shop">
			      	<tr>
			          <td><input name="name1" type="text" hidden="true" value="${shop.name }"/>${shop.name }</td>
			          <td><input name="price1" type="text" hidden="true" value="${shop.money }"/>${shop.money }</td>
			            <td align="center">
			             <input type="button" value="-" onclick="decrease(this)"/>
			              <input name="buySum" type="text" size="3"  value="${shop.buySum }" onblur="setsum(this)"/>
			              <input type="button" value="+" onclick="increase(this)"/>
			            </td>
			            <td>${shop.sumprice }</td>
			            <td align="center"><input type="button" value="x" onclick="removex(this)"/></td>
		          	</tr>
		      	</c:forEach> 
		        
		      </tbody>
		      <tfoot>
		        <tr>
		          <td colspan="3" align="right">总计</td>
		          <td id="total">${totalPrice }</td>
		          <td><input type="submit" value="保存" /></td>
		        </tr>
		      </tfoot>
		    </table>
		    
	    </form>
	    
	    <input type="button" style="width:100px; height:30px" value="购买" onclick="buy()"/>
	</div>
</body>
</html>