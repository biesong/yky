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
<link rel="icon" href="favicon.ico" type="image/x-icon" />

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

<link rel="bookmark" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">

  </head>

 <body >
<a href="logout"><strong>退出</strong></a>
    <header class='demos-header'>
      <h1 class="demos-title">Business Intelligence</h1>
    </header>

    <div class="weui-grids">
 
      <a href="stationAmount" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
          庞峰
        </p>
      </a>
      
      <a href="deliver" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_toast.png" alt="">
        </div>
        <p class="weui-grid__label">
          李总
        </p>
      </a>
      
         <a href="wujie" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
           吴洁
        </p>
      </a>
      <a href="station" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_button.png" alt="">
        </div>
        <p class="weui-grid__label">
          加注站
        </p>
      </a>
      <a href="transportOrder" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
         发货
        </p>
      </a>
    
   
     
    
   
      <a href="refund" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_dialog.png" alt="">
        </div>
        <p class="weui-grid__label">
          退款
        </p>
      </a>
       
      <a href="user" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_progress.png" alt="">
        </div>
        <p class="weui-grid__label">
          用户
        </p>
      </a>
        
      <a href="flow" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_new.png" alt="">
        </div>
        <p class="weui-grid__label">
         告警
        </p>
      </a>

        <a href="appStation" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-grid__label">
          app加注站
        </p>
      </a>
      <a href="appDeliver" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-grid__label">
          app货运订单
        </p>
      </a>
       <a href="monthStation" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_panel.png" alt="">
        </div>
        <p class="weui-grid__label">
          月报
        </p>
      </a>
    </div>
    
           <div class="weui-cells__title" style="color: red;">昨日新增</div>
    <div class="weui-cells weui-cells_form">
      <div class="weui-cells__title" id="reportAdd">
       
      </div>
    </div>
     
         <div class="weui-cells__title" style="color: red;">未消费</div>
    <div class="weui-cells weui-cells_form">
      <div class="weui-cells__title" id="report">
       
      </div>
    </div>
  
    
    
<div class="weui-footer weui-footer_fixed-bottom">
  <p class="weui-footer__text">航安能源</p>
</div>

    <script src="js/jquery-2.1.4.js"></script>

<script src="js/jquery-weui.js"></script>
<script type="text/javascript">
var y='<%=StringUtil.getByCalendar(-1)%>';
var p='<%=StringUtil.getByCalendar(-2)%>';
$.ajax({
    url:"report?d1="+p+"&d2="+y,
    dataType:"json",
    success:function(data){
    	var report="";
    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
    		 report += value.fillingStationName+"<br/>";
    	 });
    	
    	 $("#report").html(report); //获得要赋值的select的id，进行赋值
    	
    }
   });

$.ajax({
    url:"report2?d1="+p+"&d2="+y,
    dataType:"json",
    success:function(data){
    	var report="";
    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
    		 report += value.fillingStationName+"<br/>";
    	 });
    	
    	 $("#reportAdd").html(report); //获得要赋值的select的id，进行赋值
    	
    }
   });

</script>
  </body>
</html>
