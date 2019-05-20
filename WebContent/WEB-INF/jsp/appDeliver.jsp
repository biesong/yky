<%@page import="com.yky.web.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Business Intelligence</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">
<script src="js/util.js"></script>
   <script src="js/jquery-2.1.4.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/jquery-weui.js"></script>
  </head>

 <body onload="search();">

  <div class="weui-tab">
      <div class="weui-navbar">
       
  
        <a class="weui-navbar__item weui-bar__item--on" href="#tab3">
       货运订单统计
        </a>
        <a class="weui-navbar__item" href="index">回到首页</a>
      </div>
      <div class="weui-tab__bd">
        
       
        <div id="tab3" class="weui-tab__bd-item weui-tab__bd-item--active">

<div class="weui-cells weui-cells_form">
         <div class="weui-cell">
         <label for="" class="weui-label">查询日期：</label>
        <div class="weui-cell__bd">
           <input type="text"  id="d" class="weui-input"/>
        </div>
        
         
         
      </div>
      </div>
        <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:search();" >查询</a>
    </div>


 
     
      
     
     <div id="orderAmount"></div>

  


</div>
        </div>
      </div>
 <script>


 var d='<%=StringUtil.getByCalendar(-1)%>';

 $("#d").calendar();

 $('#d').val(d);







	
function search(){
	 
	$.ajax({
	    url:"second?d="+$('#d').val(),
	    dataType:"json",
	    success:function(data){
	    	var orderAmount="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.x+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
	    	 });
	    	
	    	 $("#orderAmount").html(orderAmount); 
	    }
	   });

}
 </script>
  </body>
</html>
