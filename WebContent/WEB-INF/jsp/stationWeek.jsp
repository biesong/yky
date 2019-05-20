<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yky.web.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
String s=    URLEncoder.encode(request.getParameter("s"), "ISO-8859-1");
s=URLDecoder.decode(s, "UTF-8");
String sid=s.split("-")[1];
String sName=s.split("-")[0];

%>
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

 <body >

  <div class="weui-tab">
      <div class="weui-navbar">
      
    <a class="weui-navbar__item weui-bar__item--on" href="#tab1">
     <%=sName %>
        </a>
       <a class="weui-navbar__item" href="javascript:window.history.go(-1);">返回列表</a>
        <a class="weui-navbar__item" href="index">回到首页</a>
      </div>
      <div class="weui-tab__bd">
        
       
    

<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" >
           <div class="weui-cells weui-cells_form">
         <div class="weui-cell">
         <label for="" class="weui-label"></label>
        <div class="weui-cell__bd">
        </div>
      </div>
      </div>

   <div id="stationAmount"></div>
      
     
     
  
        </div>

     
      </div>
 <script>



var start='<%=StringUtil.getByCalendar(-7)%>';
var end='<%=StringUtil.getByCalendar(-1)%>';


$.ajax({
    url:"amountByDate?sid="+<%=sid%>+"&start="+start+"&end="+end,
    dataType:"json",
    success:function(data){
    	var orderAmount="";
    	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.x+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.z+"</div></a>";
    	 });
    	 $.ajax({
    	        url: 'avg?sid='+<%=sid%>+'&start='+start+'&end='+end,
    	        async: false,
    	        success: function(res) {
    	        	
    	        	 orderAmount +="<a class='weui-cell weui-cell_access' style='color:#00BFFF;font-weight: 1000;'><div class='weui-cell__bd'><p >平均金额</p></div><div class='weui-cell__ft' style='color: #00BFFF;'>"+res+"</div></a>";
    	        }
    	    });
    	 $("#stationAmount").html(orderAmount); //获得要赋值的select的id，进行赋值
    	
    }
   });


	







 </script>
  </body>
</html>
