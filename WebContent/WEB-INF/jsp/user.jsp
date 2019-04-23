<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">
<script src="js/util.js"></script>
   

<script src="js/echarts-all.js"></script>
<script src="js/jquery-2.1.4.js"></script>
   <script type="text/javascript" src="js/jquery.table2excel.min.js"> </script>
<script src="js/jquery-weui.js"></script>
  </head>

 <body >

 <div class="weui-flex">
 <div class="weui-flex__item"><div id="orderLine" style="width: 800px; height: 400px;"></div></div>
      <div class="weui-flex__item"><div id="echartsPie" style="width: 600px; height: 400px;"></div>  </div>
      
    </div>
   
 <script type="text/javascript">
var vxData;
 $.ajax({
	    url:"gevxtuser",
	    async: false,
	    success:function(data){
	    	vxData= data.mData;
	    }
	   });
 var orderData;
 $.ajax({
	    url:"orderWeek",
	    async: false,
	    success:function(data){
	    	orderData= data.mData;
	    }
	   });
 var vcodeData;
 $.ajax({
	    url:"getvcode",
	    async: false,
	    success:function(data){
	    	vcodeData= data.mData;
	    }
	   }); 
 
 
    $.get('getuser').done(function (data) {
    	
    	var orderChart = echarts.init(document.getElementById('orderLine'));
    	orderChart.setOption({
         	title : {
 				text : '新用户'
 			},
 			tooltip : {},
 			toolbox : {
 				
 				  show : true,  
 				 feature:{
 		              dataView : {show: true, lang: ['数据视图', '关闭', '导出Excel'],
 		              contentToOption: function (opts) {
 		                $("#tableExcel_Day").table2excel({
 		                    exclude: ".noExl", //过滤位置的 css 类名
 		                    filename:  '用户数量加注订单量对比' + ".xls", //文件名称
 		                    name: "Excel Document Name.xls",
 		                    exclude_img: true,
 		                    exclude_links: true,
 		                    exclude_inputs: true
 		                });
 		            },
 		            optionToContent: function (opt) {
 		               // console.log(opt);
 		 
 		                var axisData = opt.xAxis[0].data; //坐标数据
 		                var series = opt.series; //折线图数据
 		                var tdHeads = '<td  style="padding: 0 10px;background-color:#A9A9A9;">时间</td>'; //表头第一列
 		                var tdBodys = ''; //表数据
 		                //组装表头
 		                var nameData = new Array('新用户','微信用户','验证码','加注订单量');
 		                for (var i = 0; i < nameData.length; i++) {
 		                    tdHeads += '<td style="padding: 0 10px;background-color:#A9A9A9;">' + nameData[i] + '</td>';
 		                }
 		                var table = '<table id="tableExcel_Day" border="1" class="table-bordered table-striped" style="width:100%;text-align:center" ><tbody><tr>' + tdHeads + ' </tr>';
 		                //组装表数据
 		                for (var i = 0, l = axisData.length; i <l; i++) {
 		                    for (var j = 0; j < series.length ; j++) {                          
 		                        var temp = series[j].data[i];
 		                        if (temp != null && temp != undefined) {                                     
 		                            tdBodys += '<td>' + temp + '</td>';      
 		                        } else {
 		                            tdBodys += '<td></td>';
 		                        }
 		                    }
 		                    table += '<tr><td style="padding: 0 10px">' + axisData[i] + '</td>' + tdBodys + '</tr>';
 		                    tdBodys = '';
 		                }
 		                table += '</tbody></table>';                                  
 		               // console.log(table);
 		                return table;
 		                
 		            }
 		              
 		              },
 		              magicType: {show: true, type: ['line', 'bar']},
 		              saveAsImage:{}
 		              }
 			},
 			legend : {
 				data : [ '新用户','微信用户','验证码','加注订单量']
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
 				name : "新用户",
 				type : 'line',
 				data : data.mData
 			},{
 				name : "微信用户",
 				type : 'line',
 				data : vxData
 			},
 			{
 				name : "验证码",
 				type : 'line',
 				data : vcodeData
 			},
 			{
 				name : "加注订单量",
 				type : 'line',
 				data : orderData
 			}]
         });
       
    });
         
 
 </script>
  </body>
</html>
