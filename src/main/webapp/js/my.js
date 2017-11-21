$(function(){
	$("#ac1").blur(checkCode);
	//这里判断有无错误信息,有则弹框 
	if($("#span1").text()){
		alert("账号不存在!");
	}
	if($("#span2").text()){
		alert("账号密码错误!");
	}
	
	
});
$(function (){
	console.log("外部js:${codeinfo }");
});
function checkCode() {
	console.log(1);
}

