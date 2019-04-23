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
<script src="js/echarts-all.js"></script>
<script src="js/jquery-weui.js"></script>
  </head>

 <body >

 <div class="weui-flex">
      <div class="weui-flex__item"><div id="echartsPie" style="width: 600px; height: 400px;"></div>  </div>
      <div class="weui-flex__item"><div id="orderLine" style="width: 800px; height: 400px;"></div></div>
    </div>
   
 <script type="text/javascript">
 var echartsPie = echarts.init(document.getElementById('echartsPie'));  
 
     $.post("tOrder", function(data) {
    
      var servicedata=[];
      for(var i=0;i<data.xData.length;i++){
          var obj=new Object();
          obj.name=data.xData[i]; 
          obj.value=data.zData[i];
          servicedata[i]=obj;
          
      }
     // alert(servicedata);
    
     echartsPie.setOption({
         title : {  
             text: s1+'货运订单',  
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
                
                 dataView : {show: true, readOnly: false},  
                
                 restore : {show: true},  
                 saveAsImage : {show: true}  
             }  
         },  
         calculable : true,  
         series : [  
             {  
                 name:'货运订单量',  
                 type:'pie',  
                 radius : '55%',//饼图的半径大小  
                 center: ['50%', '60%'],//饼图的位置  
                 data:servicedata
             }  
         ]    
     })
})
 
  

   
    $.get('tOrderByDate').done(function (data) {
    	var orderChart = echarts.init(document.getElementById('orderLine'));
    	orderChart.setOption({
         	title : {
 				text : '货运订单量'
 			},
 			tooltip : {},
 			toolbox : {
 				  show : true,  
 	             feature : {  
 	                
 	                 dataView : {show: true, readOnly: false},  
 	                
 	                 restore : {show: true},  
 	                 saveAsImage : {show: true}  
 	             }  
 			},
 			legend : {
 				data : [ '货运订单量']
 			},
 			xAxis : {
 				axisLabel : {
 					interval : 0,
 					rotate : 40
 				},
 				data : data.xData
 			},
 			yAxis : {},
 			series : [ {
 				name : "货运订单量",
 				type : 'line',
 				data : data.zData
 			} ]
         });
       
    });
         
 
 </script>
  </body>
</html>
