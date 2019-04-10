<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>报表</title>
<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.min.css">
<script src="js/util.js"></script>
<script src="js/jquery-2.1.4.js"></script>
<script src="js/echarts.min.js"></script>
</head>
<body onload="javascript:getData('93','勉县新街子十天高速加气站')">

	<div class="weui-flex">
      <div class="weui-flex__item"><div id="order" style="width: 600px; height: 400px;"></div></div>
      <div class="weui-flex__item">
       <div class="weui-cells" id="stationLi">
        </div>
      </div>
    </div>


<hr style="height:5px;border:none;border-top:5px ridge green;" />
   

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
      
      
        <div class="weui-flex__item"><div id="avg"></div></div>
      </div>
  

	<script type="text/javascript">
	 
	
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
	

	 
	
    var orderChart = echarts.init(document.getElementById('order'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: s1+"加气记录"
        },
        toolbox:{
          feature:{saveAsImage:{},
        	  dataView : {
					show : true,
					readOnly : false
				}
        }
        },
        tooltip: {},
        legend: {
            data:['金额']
        },
        xAxis: {
            axisLabel:{ interval:0,rotate:20},
            data: []
        },
        yAxis: {},
        series: [{
            name: '金额',
            type: 'bar',
            data: []
        }
        ]
    };	
	 
    // 异步加载数据
    $.get('getOrder').done(function (data) {
       
        
        orderChart.setOption({
            xAxis: {
                data: data.xData
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '金额',
                data: data.yData
            }]
        });
    });
            // 使用刚指定的配置项和数据显示图表。
  orderChart.setOption(option);	
//获取列表
 $.ajax({
        url:"OrderCount",
        dataType:"json",
        success:function(data){
        	var orderAmount="";
        	 $.each(data,function(key,value){  //循环遍历后台传过来的json数据
	    		 orderAmount += " <a class='weui-cell weui-cell_access' ><div class='weui-cell__bd'><p>"+value.fillingStationName+"</p></div><div class='weui-cell__ft' style='color: red;'>"+value.orderAmount+"</div></a>";
	    	 });
        	
	    	 $("#stationLi").html(orderAmount); //获得要赋值的select的id，进行赋值
        }
       });

		
	</script>
<script src="js/jquery-weui.min.js"></script>
<script type="text/javascript">
$("#start").calendar();
$("#end").calendar();
$('#start').val( (new Date().getFullYear()) + "/" +appendZero( (new Date().getMonth() + 1) )+ "/" +appendZero( (new Date().getDate() - 7)));
$('#end').val((new Date().getFullYear()) + "/" +  appendZero( (new Date().getMonth() + 1)) + "/" + appendZero((new Date().getDate() -1)));
function getAvg(){
	$.ajax({
        url: 'avg?sid='+$("#station").val()+'&start='+$('#start').val()+'&end='+$('#end').val(),
        success: function(res) {
        	
        	 $("#avg").html("平均金额："+res+"<br/>时间段："+$("#start").val()+"~"+$('#end').val());
        }
    });
}
</script>
</body>
</html>