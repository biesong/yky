<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>登录</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">

    <script src="js/jquery-2.1.4.js"></script>

<script src="js/jquery-weui.js"></script>
  </head>

   <body >
  

    <header class='demos-header'>
      <h1 class="demos-title">用户登录</h1>
    </header>
  

    <div class="weui-cells weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">用户名</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  placeholder="请输入用户名" id="name" value="admin">
        </div>
      </div>
   
     <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="password"  placeholder="请输入密码" id="pwd" value="admin">
        </div>
      </div>
      
      
    </div>
  
   


   

   

 

   


 

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary"  id="showTooltips">确定</a>
    </div>

<script type="text/javascript">
$(function () { 
	 $.toptip('${msg}');
});
	
	   $("#showTooltips").click(function() {
		   $.ajax({
		        url: 'handle?name='+$("#name").val()+'&pwd='+$('#pwd').val(),
		        success: function(res) {
		        	if(res){
		        		$(location).attr('href', 'index');
		        	}else{
		        		 $.toptip('用户名或者密码错误！');
		        	}
		        }
		    });
	      });

</script>

   
  </body>
</html>
