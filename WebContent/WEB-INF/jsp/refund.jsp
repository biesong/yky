<%@page import="com.yky.web.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>退款</title>
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

<script src="js/jquery-weui.js"></script>
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
  background-color:#1AAD19;
  border:1px solid #1AAD19;
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

		
.ui-datepicker-calendar {
    display: none;
    }
</style>
  </head>

   <body onload="search();">
  

    <header class='demos-header'>
      <h1 class="demos-title">农行操作</h1>
    </header>

 <div class="weui-flex">
      <div class="weui-flex__item">  <div class="weui-cells weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">订单号</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  placeholder="请输入订单号" id="n" >
        </div>
      </div>
  
    </div>
  

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary"  id="showTooltips">查询</a>
    </div>
 
  <div class="weui-cells__title">查询结果</div>
    <div class="weui-cells weui-cells_form">
      <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">交易编号</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  id="OrderNo" >
        </div>
       
      </div>
      <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  id="OrderDate" >
        </div>
       
      </div>
      <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">时间</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  id="OrderTime" >
        </div>
       
      </div>
       <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">订单金额</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  id="OrderAmount" >
        </div>
       
      </div>
       <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">状态</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  id="Status" >
        </div>
       
      </div>
    </div>
 
  <div class="weui-cells__tips">01：账单已建立，等待支付
02：消费者已支付，等待支付结果
03：账单已支付（支付成功）
04：账单已结算（支付成功，已清算至商户）
05：账单已退款
99: 账单支付失败</div>
  <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary"  id="btnrefund">退款</a>
    </div></div>
    
      <div class="weui-flex__item"><div class="placeholder">
     
   <div class="weui-cells weui-cells_form">
   
  <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">订单号</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input"  placeholder="请输入订单号" id="f" >
        </div>
      </div>
       
     <div class="weui-cell">
        <div class="weui-cell__hd"><label for="time-format" class="weui-label">开始日期</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="start" type="text" value="">
        </div>
      </div>
      
        <div class="weui-cell">
        <div class="weui-cell__hd"><label for="time-format" class="weui-label">结束日期</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="end" type="text" value="">
        </div>
      </div>
      
      </div>
       <a href="javascript:search();" class="weui-btn weui-btn_mini weui-btn_primary">查询</a>
        <a href="javascript:exportExcel();" class="weui-btn weui-btn_mini weui-btn_primary">导出Excel</a>
      
      <table class=" dataintable"  id="table2excel">
			<thead>
			  <tr>
			   <th >序号</th>
                  <th >加注站</th>
                  <th >订单号</th>
                   <th >退款方式</th>
                  <th >用户</th>
                  <th >订单金额</th>
                  <th >折扣金额</th>
                   <th >支付时间</th>
                  
                </tr>
			</thead>
			<tbody id='tb'>
               <tr id="template">
                 <td ></td>
               <td id="fillingStationName"></td>
               <td id="orderSN"></td>
                <td id="refundType"></td>
               <td id="loginName"></td>
               <td id="orderAmount"></td>
               <td id="discount"></td>
               <td id="payTime"></td>
           
               </tr> 
			</tbody>
			
		</table></div></div>
    </div>
  
 
<script type="text/javascript">
$(function () { 
	 $.toptip('${msg}');
});
	
	   $("#showTooltips").click(function() {
		   $.ajax({
		        url: "query?n="+$("#n").val(),
		        success: function(res) {
		        	res= eval('(' + res + ')'); 
		        	
		        	$("#OrderNo").val(res.OrderNo);
		        	$("#OrderDate").val(res.OrderDate);
		        	$("#OrderTime").val(res.OrderTime);
		        	$("#OrderAmount").val(res.OrderAmount);
		        	$("#Status").val(res.Status);
		        }
		    });
	      });
	   $("#btnrefund").click(function() {
		   $.ajax({
		        url: "dorefund?n="+$("#n").val(),
		        success: function(res) {
		        	 $.toast(res);
		        }
		    });
	      });
	   
		
	   $("#start").datetimePicker({
	        title: '选择月份',
	        times:function(){
	        	return []
	        }

	      });   
	   $("#end").datetimePicker({
	        title: '选择月份',
	        times:function(){
	        	return []
	        }
	      });  
	   $('#start').val('<%=StringUtil.getByCalendarH(-7)%>');
	   $('#end').val('<%=StringUtil.getByCalendarH(-1)%>');
	
	   function search(){     
	  	 $("#tb tr").not(':eq(0)').remove();
	   $.ajax({
	                  url:"getrefund?f="+$("#f").val()+'&start='+$('#start').val()+'&end='+$('#end').val(),
	                  type:"get",
	                  dataType:"json",
	                  success:function(data){
	                  	   
	                  	  $.each(data, function (i, n) {
	                  		    var row = $("#template").clone();
	                  		    row.find("#fillingStationName").text(n.fillingStationName);
	                  		    row.find("#orderSN").text(n.orderSN);
	                  		    row.find("#loginName").text(n.loginName);
	                  		    row.find("#orderAmount").text(n.orderAmount);
	                  		  row.find("#discount").text(n.discount);
	                  		    row.find("#payTime").text(n.payTime);
	                  		   if(n.payType=='5'){
	                  			 row.find("#refundType").text("加注金");
	                  		   }else{
	                  			  $.ajax({
	  	              		        url: "query?n="+n.orderSN,
	  	              		        success: function(res) {
	  	              		        	res= eval('(' + res + ')'); 
	  	              		        	if(res.Status=='05'){
	  	              		        		row.find("#refundType").text("原路返");
	  	              		        	}else{
	  	              		        		row.find("#refundType").text("加注金");
	  	              		        	}
	  	              		        	
	  	              		        
	  	              		        }
	  	              		    });
	                  		   }
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
	    				filename: "退款数据" + (new Date()).toLocaleString(),
	    				fileext: ".xls",
	    				exclude_img: true,
	    				exclude_links: true,
	    				exclude_inputs: true
	    			});
	            } 
	     
	  		
</script>

   
  </body>
</html>
