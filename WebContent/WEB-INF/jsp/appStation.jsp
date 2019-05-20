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

 <body onload="search();searchTotal();">

  <div class="weui-tab">
      <div class="weui-navbar">
        <a class="weui-navbar__item weui-bar__item--on" href="#tab1">
       
        加注站消费
        
        </a>
         <a class="weui-navbar__item" href="#tab2">
       合计
        </a>
        <a class="weui-navbar__item" href="index">
          首页
        </a>
      </div>
      <div class="weui-tab__bd">
        <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
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
     <div class="weui-cells__tips">点击列表显示该加注站近七天数据，“↓”标识低于七日平均金额</div>
     
           <div id="orderAmount"></div>
        </div>
       
       
     
     
     
     
       <div id="tab2" class="weui-tab__bd-item ">
         <div class="weui-cells weui-cells_form">
         <div class="weui-cell">
         <label for="" class="weui-label">查询日期：</label>
        <div class="weui-cell__bd">
           <input type="text"  id="t" class="weui-input"/>
           
        </div>
        
         
         
      </div>
      </div>
        <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:searchTotal();" >查询</a>
     
    </div>
         <div id="total"></div>
        </div>
        
         
      </div>
    </div>
     
 <script>
 function toWeek(s){
	 location.href='stationWeek?s='+s;
 }
 var temp='<%=StringUtil.getByCalendar(-1)%>';

 $("#d").calendar();

 $('#d').val(temp);
 $("#t").calendar();

 $('#t').val(temp);
 function search(){
	
	$.ajax({
	    url:"OrderAmount?d="+$('#d').val(),
	    dataType:"json",
	    success:function(data){
	    	var orderAmount="";
	    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		var s=(value.x).split('-');
	    		 if(value.z<s[2]){//低于7日平均值
	    			
	    			 orderAmount += " <a class='weui-cell weui-cell_access' href='javascript:toWeek(\""+value.x+"\");'><div class='weui-cell__bd'><p>"+s[0]+"<font style='color:red;'>↓</font></p> </div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
	    		 }else{
	    			 orderAmount += " <a class='weui-cell weui-cell_access' href='javascript:toWeek(\""+value.x+"\");'><div class='weui-cell__bd'><p>"+s[0]+"</p> </div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
	    		 }
	    		 
	    	 });
	    	
	    	 $("#orderAmount").html(orderAmount); 
	    }
	   });
 }
 
 function searchTotal(){
	 
	 $.ajax({
		    url:"dayTotal?d="+$('#t').val(),
		    dataType:"json",
		    success:function(data){
		    	var orderAmount="";
		    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
		    		
		    	
		    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.x+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
		    	 });
		    	
		    	 $("#total").html(orderAmount); 
		    }
		   });
 }

 
 </script>
  </body>
</html>
