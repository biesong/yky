<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>货运订单数据</title>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/util.js"></script>
 <script src="js/jquery-2.1.4.js"></script>
  
    <script src="js/jquery.table2excel.min.js"></script>
     <script src="js/jquery-ui.min.js"></script>
  <style type="text/css">
table.dataintable {
  margin-top:15px;
  border-collapse:collapse;
  border:1px solid #aaa;
  width:100%;
}
table.dataintable th {
  vertical-align:baseline;
  padding:5px 15px 5px 6px;
  background-color:#3F3F3F;
  border:1px solid #3F3F3F;
  text-align:left;
  color:#fff;
}
table.dataintable td {
  vertical-align:text-top;
  padding:6px 15px 6px 6px;
  border:1px solid #aaa;
}
table.dataintable tr:nth-child(odd) {
  background-color:#F5F5F5;
}
table.dataintable tr:nth-child(even) {
  background-color:#fff;
}
</style>
 
</head>
<body>
<body onload="search();">

<div data-role="page">
  <div data-role="header">
    <a href="index">主页</a>
   
   
    发货方：<input type="text" id="f">
      开始日期：<input type="text" id="start">
       结束日期：<input type="text" id="end">
       <button onclick="search();">查询</button>
        <a href="javascript:exportExcel();" >导出Excel</a>
  </div>

   <div data-role="main" class="ui-content">
  <table class=" dataintable"  id="table2excel">
			<thead>
			  <tr>
			   <th >序号</th>
                  <th >发货方</th>
                  <th >车主</th>
                  <th >司机</th>
                  <th >运费</th>
                  <th >货损金额</th>
                   <th >加注金</th>
                    <th >税率</th>
                     <th >状态</th>
                     <th >添加时间</th>
                </tr>
			</thead>
			<tbody id='tb'>
               <tr id="template">
                 <td ></td>
               <td id="fName"></td>
               <td id="zName"></td>
               <td id="sName"></td>
               <td id="OrderPriceAmount"></td>
               <td id="CargoDamageAmount"></td>
               <td id="OrderFillingPrice"></td>
               <td id="TaxRate"></td>
               <td id="status"></td>
               <td id="AddTime"></td>
               </tr> 
			</tbody>
			
		</table>
  </div>
</div>
 <script type="text/javascript">
 $( "#start" ).datepicker({
	 dateFormat:'yy-mm-dd',
 });	
 $( "#end" ).datepicker({
	 dateFormat:'yy-mm-dd',
 });	
 $('#start').val( (new Date().getFullYear()) + "-" +appendZero( (new Date().getMonth() + 1) )+ "-" +appendZero( (new Date().getDate() - 7)));
 $('#end').val((new Date().getFullYear()) + "-" +  appendZero( (new Date().getMonth() + 1)) + "-" + appendZero((new Date().getDate() -1)));
 function search(){     
	 $("#tb tr").not(':eq(0)').remove();
 $.ajax({
                url:"truckDriverInfo?f="+$('#f').val()+"&start="+$('#start').val()+"&end="+$('#end').val(),
                type:"get",
                dataType:"json",
                success:function(data){
                	   
                	  $.each(data, function (i, n) {
                		    var row = $("#template").clone();
                		    row.find("#fName").text(n.fName);
                		    row.find("#zName").text(n.zName);
                		    row.find("#sName").text(n.sName);
                		    row.find("#OrderPriceAmount").text(n.orderPriceAmount);
                		    row.find("#CargoDamageAmount").text(n.cargoDamageAmount);
                		    row.find("#OrderFillingPrice").text(n.orderFillingPrice);
                		    row.find("#TaxRate").text(n.taxRate);
                		    row.find("#status").text(n.status);
                		    row.find("#AddTime").text(n.addTime);
                		    row.appendTo("#table2excel");//添加到模板的容器中
                		  });
                		$("tr:odd").css("background-color", "#eeeeee");
                	  $("#tb").find("tr:first").remove();
                	  var len = $('table tr').length;
                      for(var i = 1;i<len;i++){
                          $('table tr:eq('+i+') td:first').text(i);
                      }
                }	
           
               }); 
 }
          function exportExcel(){
        	  $("#table2excel").table2excel({
  				exclude: ".noExl",
  				name: "Excel Document Name",
  				filename: "货运订单数据" + (new Date()).toLocaleString(),
  				fileext: ".xls",
  				exclude_img: true,
  				exclude_links: true,
  				exclude_inputs: true
  			});
          } 
   
		</script>
</body>
</body>
</html>