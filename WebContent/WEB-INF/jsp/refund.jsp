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

    <script src="js/jquery-2.1.4.js"></script>

<script src="js/jquery-weui.js"></script>
  </head>

   <body >
  

    <header class='demos-header'>
      <h1 class="demos-title">农行操作</h1>
    </header>
  

    <div class="weui-cells weui-cells_form">
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
</script>

   
  </body>
</html>
