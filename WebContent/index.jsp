<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>jQuery WeUI</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="css/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">

  </head>

  <body ontouchstart>


    <header class='demos-header'>
      <h1 class="demos-title">测试加注站</h1>
    </header>
    
  
    

    <div class="weui-cells weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">加注金额</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入加注金额">
        </div>
      </div>
      <div class="weui-cell weui-cell_warn">
        <div class="weui-cell__hd"><label for="" class="weui-label">9.5折</label></div>
        
        
      </div>
     <div class="weui-cells weui-cells_checkbox">
      <label class="weui-cell weui-check__label" for="s11">
        <div class="weui-cell__hd">
          <input type="checkbox" class="weui-check" name="checkbox1" id="s11" checked="checked">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
          <p>加注金余额支付</p>
        </div>
      </label>
      <label class="weui-cell weui-check__label" for="s12">
        <div class="weui-cell__hd">
          <input type="checkbox" name="checkbox1" class="weui-check" id="s12">
          <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd">
          <p>微信支付</p>
        </div>
      </label>
     
    </div>
     
   
      
    </div>


    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">支付</a>
    </div>

    <script src="js/jquery-2.1.4.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="js/jquery-weui.js"></script>


    <script>
      $("#showTooltips").click(function() {
        var tel = $('#tel').val();
        var code = $('#code').val();
        if(!tel || !/1[3|4|5|7|8]\d{9}/.test(tel)) $.toptip('请输入手机号');
        else if(!code || !/\d{6}/.test(code)) $.toptip('请输入六位手机验证码');
        else $.toptip('提交成功', 'success');
      });
    </script>
  </body>
</html>
