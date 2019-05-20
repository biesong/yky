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

 <body onload="javascript:getData('93','勉县新街子十天高速加气站');getByDate();active();">

  <div class="weui-tab">
      <div class="weui-navbar">
        <a class="weui-navbar__item " href="#tab1">
         昨日加注记录
        </a>
        <a class="weui-navbar__item weui-bar__item--on" href="#tab2">
        近七日加注记录
        </a>
        <a class="weui-navbar__item" href="#tab3">
         昨日加注订单量
        </a>
        <a class="weui-navbar__item" href="index">回到首页</a>
      </div>
      <div class="weui-tab__bd">
        <div id="tab1" class="weui-tab__bd-item " style="width: 1000px; height: 600px;">
         
        </div>
        <div id="tab2" class="weui-tab__bd-item weui-tab__bd-item--active">
         
         
         <div class="weui-flex">
      <div class="weui-flex__item"><div id="main" style="width: 600px; height: 300px;"></div></div>
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
        <a href="javascript:getAvg();" class="weui-btn weui-btn_primary">查询</a>
      </div>
     
       
      </div>
      
      
        <div class="weui-flex__item"><div id="avg"> </div></div>
      </div>
         <hr style="height:5px;border:none;border-top:5px ridge green;" />
         
         <div class="weui-flex">
   
      <div class="weui-flex__item">  <input type="text" id="activeText" style="font-size: large;"/><div id="active" style="width: 800px; height: 400px;"></div></div>
      <div class="weui-flex__item"><div id="order" style="width: 800px; height: 400px;"></div></div>
    </div>  
         
        </div>
        <div id="tab3" class="weui-tab__bd-item">


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
      </div></div>
    </div>


</div>
        </div>
      </div>
 <script>
 var orderChart = echarts.init(document.getElementById('tab1'));


 var servicedata=[]; 
 // 异步加载数据
 $.get('getOrder').done(function (data) {
	 for(var i=0;i<data.xData.length;i++){
         var obj=new Object();
         obj.name=data.xData[i]; 
         obj.value=data.yData[i];
         servicedata[i]=obj;
         
     }
    // alert(servicedata);
   
    orderChart.setOption({
        title : {  
            text: s1+'加注占比',  
            //subtext: '饼图',  
            x:'center'  
        },  
        tooltip : {  
            trigger: 'item',  
            formatter: "{b} <br/>{c} : {d} %"      //a 系列名称，b 数据项名称，c 数值，d 百分比
        },  
        legend: {  
            orient : 'vertical',  
            x : 'left',  
            data:data.xData  
        },
        toolbox: {  
            show : true,  
            feature : {  
                mark : {show: true},  
                dataView : {show: true, readOnly: false},  
               
                restore : {show: true},  
                saveAsImage : {show: true}  
            }  
        },  
        calculable : true,  
        series : [  
            {  
                name:'加注量',  
                type:'pie',  
                radius : '55%',//饼图的半径大小  
                center: ['50%', '60%'],//饼图的位置  
                data:servicedata
            }  
        ]    
    })

   
 });

     //加注订单量    

   

     
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
$('#station').on('change', function() {
    var sid = $(this).val();
    var stationname=$("#station").find("option:selected").text();
    getData(sid,stationname);
});
function getData(sid,stationname){
	$.ajax({
        url: 'getData?sid='+sid,
        success: function(res) {
        	var myChart = echarts.init(document.getElementById('main')); 
            myChart.setOption({
            	title : {
    				text : '加注站记录'
    			},
    			tooltip : {},
    			toolbox : {
    				feature : {
    					saveAsImage : {},
    					
    					dataView : {
    						show : true,
    						readOnly : false
    					}
    				}
    			},
    			legend : {
    				data : [ stationname]
    			},
    			xAxis : {
    				axisLabel : {
    					interval : 0,
    					rotate : 40
    				},
    				data : res.payTime
    			},
    			yAxis : {},
    			series : [ {
    				name : stationname,
    				type : 'line',
    				data : res.orderAmount
    			} ]
            });
        }
    });
	
	
}
$("#start").calendar();
$("#end").calendar();
$("#date").calendar();
$("#second").calendar();
$('#second').val('<%=StringUtil.getByCalendar(-2)%>');
$('#date').val('<%=StringUtil.getByCalendar(-1)%>');
$('#start').val('<%=StringUtil.getByCalendar(-7)%>');
$('#end').val('<%=StringUtil.getByCalendar(-1)%>');
function getAvg(){
	$.ajax({
        url: 'avg?sid='+$("#station").val()+'&start='+$('#start').val()+'&end='+$('#end').val(),
        success: function(res) {
        	
        	 $("#avg").html("平均金额："+res+"<br/>时间段："+$("#start").val()+"~"+$('#end').val());
        }
    });
}

function getByDate(){
	
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
$("#activeText").calendar();
$('#activeText').val('<%=StringUtil.getByCalendar(-1)%>');
function active(){
	//活跃度
	$.ajax({
        url: 'active?d='+$('#activeText').val(),
        success: function(res) {
        	var myChart = echarts.init(document.getElementById('active')); 
            myChart.setOption({
            	
    			tooltip : {},
    			toolbox : {
    				feature : {
    					saveAsImage : {},
    					
    					dataView : {
    						show : true,
    						readOnly : false
    					}
    				}
    			},
    			legend : {
    				data : [ "加注站每小时活跃度"]
    			},
    			xAxis : {
    				axisLabel : {
    					interval : 0,
    					rotate : 40
    				},
    				data : res.xData
    			},
    			yAxis : {},
    			series : [ {
    				name :  "加注站每小时活跃度",
    				type : 'line',
    				data : res.mData
    			} ]
            });
        }
    });
}
$("#activeText").change(function(){
	active();
	});

//订单量
	$.ajax({
        url: 'orderMonth',
        success: function(res) {
        	
        	var myChart = echarts.init(document.getElementById('order')); 
            myChart.setOption({
            	title : {
    				text : "当月订单量"
    			},
    			tooltip : {},
    			toolbox : {
    				feature : {
    					saveAsImage : {},
    					
    					dataView : {
    						show : true,
    						readOnly : false
    					}
    				}
    			},
    			legend : {
    				data : [ "当月订单量"]
    			},
    			xAxis : {
    				axisLabel : {
    					interval : 0,
    					rotate : 40
    				},
    				data : res.xData
    			},
    			yAxis : {},
    			series : [ {
    				name :  "当月订单量",
    				type : 'line',
    				data : res.mData
    			} ]
            });
        }
    });

 </script>
  </body>
</html>
