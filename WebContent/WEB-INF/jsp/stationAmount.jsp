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

 <body onload="getByDate();getData();">

  <div class="weui-tab">
      <div class="weui-navbar">
        <a class="weui-navbar__item weui-bar__item--on" href="#tab3">
        单日加注金额对比
        </a> 
    <a class="weui-navbar__item " href="#tab1">
      七日加注金额
        </a>
      
        <a class="weui-navbar__item" href="index">回到首页</a>
      </div>
      <div class="weui-tab__bd">
        
       
        <div id="tab3" class="weui-tab__bd-item weui-tab__bd-item--active">


<div class="weui-flex">

      <div class="weui-flex__item"><div id="orderCount"></div></div>
      
     
      <div class="weui-flex__item"><div id="orderAmount"></div></div>
      <div class="weui-flex__item">  
       <div class="weui-cell">
         <label for="" class="weui-label">查询日期一：</label>
        <div class="weui-cell__bd">
           <input type="text"  id="second"/>
        </div>
      </div>
        <div class="weui-cell">
         <label for="" class="weui-label">查询日期二：</label>
        <div class="weui-cell__bd">
           <input type="text"  id="date"/>
        </div>
      </div>
      
      
       <div class="weui-cell__bd">
        <a href="javascript:getByDate();" class="weui-btn weui-btn_primary">查询</a>
      </div>
        <div class="weui-cell">
          <label for="" class="weui-label">对比新增：</label>
        <div class="weui-cell__bd" id="reportAdd" style="color: blue;">
           
        </div>
      </div>
       <div class="weui-cell">
          <label for="" class="weui-label">未消费：</label>
        <div class="weui-cell__bd" id="report" style="color: blue;">
           
        </div>
      </div>
      </div>
    </div>


</div>

<div id="tab1" class="weui-tab__bd-item " >
         <div class="weui-flex">

      <div class="weui-flex__item"><div id="stationAmount"></div></div>
      
     
     
      <div class="weui-flex__item">  
     <div class="weui-cell weui-cell_select weui-cell_select-after">
         <label for="" class="weui-label">加注站名称：</label>
        <div class="weui-cell__bd">
          <select class="weui-select" id="station">
          </select>
        </div>
        
        
        
      </div>
     
      <div class="weui-cell">
         <label for="" class="weui-label">开始日期：</label>
        <div class="weui-cell__bd">
         <input type="text" id='start' value=''/>
        </div>
      </div>
     
      <div class="weui-cell">
         <label for="" class="weui-label">结束日期：</label>
        <div class="weui-cell__bd">
           <input type="text"  id="end"/>
        </div>
      </div>
       <div class="weui-cell__bd">
        <a href="javascript:getData();" class="weui-btn weui-btn_primary">查询</a>
      </div></div>
    </div>
        </div>

        </div>
      </div>
 <script>

 var optionstring="";
 $("#station").empty();
 $.ajax({
      url:'getStation',   
      type:'post', 
      success:function(data){
     	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
     		 optionstring += "<option value=\"" + value.fillingStationID + "\" >" + value.fillingStationName + "</option>";
     	 });
     	 $("#station").html("<option value='93'>勉县新街子十天高速加气站</option> "+optionstring); //获得要赋值的select的id，进行赋值
      }

 	 });

$("#date").calendar();
$("#second").calendar();
$('#second').val('<%=StringUtil.getByCalendar(-2)%>');
$('#date').val('<%=StringUtil.getByCalendar(-1)%>');

$("#start").calendar();
$("#end").calendar();  
$('#start').val('<%=StringUtil.getByCalendar(-7)%>');
$('#end').val('<%=StringUtil.getByCalendar(-1)%>');

function getData(){
	var sid=$("#station").val();
	 if(!sid){
		 sid=93;
	 }
	$.ajax({
	    url:"amountByDate?sid="+sid+"&start="+$("#start").val()+"&end="+$("#end").val(),
	    dataType:"json",
	    success:function(data){
	    	var orderAmount="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.x+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
	    	 });
	    	
	    	 $("#stationAmount").html(' <div  style="font-weight: 1000;font-size: larger;">'+$("#station").find("option:selected").text()+'</div>'+orderAmount); //获得要赋值的select的id，进行赋值
	    	
	    }
	   });
}

function getByDate(){
	
	$.ajax({
	    url:"report?d1="+$('#second').val()+"&d2="+$('#date').val(),
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
	    url:"report2?d1="+$('#second').val()+"&d2="+$('#date').val(),
	    dataType:"json",
	    success:function(data){
	    	var report="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 report += value.fillingStationName+"<br/>";
	    	 });
	    	
	    	 $("#reportAdd").html(report); //获得要赋值的select的id，进行赋值
	    	
	    }
	   });
	$.ajax({
	    url:"OrderCount?d="+$('#second').val(),
	    dataType:"json",
	    success:function(data){
	    	var orderAmount="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.fillingStationName+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.orderAmount+"</div></a>";
	    	 });
	    	
	    	 $("#orderCount").html(' <div  style="font-weight: 1000;font-size: larger;">'+$("#second").val()+'</div>'+orderAmount); //获得要赋值的select的id，进行赋值
	    	
	    }
	   });
	 
	$.ajax({
	    url:"OrderAmount?d="+$('#date').val(),
	    dataType:"json",
	    success:function(data){
	    	var orderAmount="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.x+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
	    	 });
	    	
	    	 $("#orderAmount").html(' <div  style="font-weight: 1000;font-size: larger;">'+$("#date").val()+'</div>'+orderAmount); 
	    }
	   });
}




 </script>
  </body>
</html>
