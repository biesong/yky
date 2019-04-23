package com.yky.web.action;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.pay.client.Base64;
import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.AuthenMerchantQueryRequest;
import com.abc.pay.client.ebus.QueryOrderRequest;
import com.abc.pay.client.ebus.QueryTrnxRecords;
import com.abc.pay.client.ebus.RefundRequest;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.service.FillingStationOrderInfoService;
import com.yky.web.service.impl.FillingStationOrderInfoServiceImpl;



@Controller
public class MerchantPayController {
	  @RequestMapping("/refund")
	    public String toRefund() {
	    	return "refund";
	    }
	  
	  public String queryByOrderSN(String n) {
		  String orderDetail="";
		  QueryOrderRequest tQueryRequest = new QueryOrderRequest();
		  tQueryRequest.queryRequest.put("PayTypeID", "ImmediatePay");    //设定交易类型
		  tQueryRequest.queryRequest.put("OrderNo", doQuery(n));    //设定订单编号 （必要信息）
		  tQueryRequest.queryRequest.put("QueryDetail", "false");//设定查询方式
		  JSON json = tQueryRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000")) {
			  System.out.println("ReturnCode   = [" + ReturnCode + "]");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]");
			  String orderInfo = json.GetKeyValue("Order");
			    if (orderInfo.length() < 1)
			    {
			        System.out.println("查询结果为空");
			    }else {
			    	   //1、还原经过base64编码的信息 
			    	Base64 tBase64 = new Base64();
			  		 orderDetail = new String(tBase64.decode(orderInfo));
			        json.setJsonString(orderDetail);
			        System.out.println("订单明细" + orderDetail );
			        System.out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]");
			        System. out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]");
			        System.out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]");
			        System.out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]");
			        System.out.println("OrderAmount      = [" + json.GetKeyValue("OrderAmount") + "]");
			        System.out.println("Status      = [" + json.GetKeyValue("Status") + "]");
			    }
		  }else {
			//6、商户订单查询失败
			   System. out.println("ReturnCode   = [" + ReturnCode + "]");
			   System. out.println("ErrorMessage = [" + ErrorMessage + "]");
		  }
		 
	    	return orderDetail;
	  }
	  
	  @ResponseBody
	  @RequestMapping("/query")
	    public Object QueryOrder(String n) {
		return queryByOrderSN(n); 
	    }
	  @ResponseBody
	  @RequestMapping("/dorefund")
	  public Object doRefund(String n) {
		  
		 JSON j=new JSON(queryByOrderSN(n));
		 
		  
		  //1、生成退款请求对象
	        RefundRequest tRequest = new RefundRequest();
	        tRequest.dicRequest.put("OrderDate", j.GetKeyValue("OrderDate"));  //订单日期（必要信息）
	        tRequest.dicRequest.put("OrderTime", j.GetKeyValue("OrderTime")); //订单时间（必要信息）
	        //tRequest.dicRequest.put("MerRefundAccountNo", request.getParameter("txtMerRefundAccountNo"));  //商户退款账号
	        //tRequest.dicRequest.put("MerRefundAccountName", request.getParameter("txtMerRefundAccountName")); //商户退款名
	        tRequest.dicRequest.put("OrderNo", j.GetKeyValue("OrderNo")); //原交易编号（必要信息）
	        tRequest.dicRequest.put("NewOrderNo", j.GetKeyValue("OrderNo")+"_refund"); //交易编号（必要信息）
	        tRequest.dicRequest.put("CurrencyCode", "156"); //交易币种（必要信息）
	        tRequest.dicRequest.put("TrxAmount", j.GetKeyValue("OrderAmount")); //退货金额 （必要信息）
	        tRequest.dicRequest.put("MerchantRemarks", "测试");  //附言
			//如果需要专线地址，调用此方法：
			//tRequest.setConnectionFlag(true);
			
	
		
		 

	        //3、传送退款请求并取得退货结果
	        JSON json = tRequest.postRequest();

	        //4、判断退款结果状态，进行后续操作
	        StringBuilder strMessage = new StringBuilder("");
	        String ReturnCode = json.GetKeyValue("ReturnCode");
	        String ErrorMessage = json.GetKeyValue("ErrorMessage");
	        if (ReturnCode.equals("0000"))
	        {
	            //5、退款成功/退款受理成功
	             System.out.println("ReturnCode   = [" + ReturnCode + "]<br/>");
	             System.out.println("ErrorMessage = [" + ErrorMessage + "]<br/>");
	             System.out.println("OrderNo   = [" + json.GetKeyValue("OrderNo") + "]<br/>");
	             System.out.println("NewOrderNo   = [" + json.GetKeyValue("NewOrderNo") + "]<br/>");
	             System.out.println("TrxAmount = [" + json.GetKeyValue("TrxAmount") + "]<br/>");
	             System.out.println("BatchNo   = [" + json.GetKeyValue("BatchNo") + "]<br/>");
	             System.out.println("VoucherNo = [" + json.GetKeyValue("VoucherNo") + "]<br/>");
	             System.out.println("HostDate  = [" + json.GetKeyValue("HostDate") + "]<br/>");
	             System.out.println("HostTime  = [" + json.GetKeyValue("HostTime") + "]<br/>");
	             System.out.println("iRspRef  = [" + json.GetKeyValue("iRspRef") + "]<br/>");
	        }       
	        else
	        {
	            //6、退款失败
	        	System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
	        	System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
			}
		  return ReturnCode;
	  }
	  @ResponseBody
	  @RequestMapping("/flow")
	  public Object queryFlow() {
		  String orderDetail="";
		  QueryOrderRequest tQueryRequest = new QueryOrderRequest();
		  tQueryRequest.queryRequest.put("PayTypeID", "ImmediatePay");    //设定交易类型
		  tQueryRequest.queryRequest.put("OrderNo", "JZ1555725999254BD_190420100639477");    //设定订单编号 （必要信息）
		  tQueryRequest.queryRequest.put("QueryDetail", "false");//设定查询方式
		  JSON json = tQueryRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000")) {
			  System.out.println("ReturnCode   = [" + ReturnCode + "]");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]");
			  String orderInfo = json.GetKeyValue("Order");
			    if (orderInfo.length() < 1)
			    {
			        System.out.println("查询结果为空");
			    }else {
			    	   //1、还原经过base64编码的信息 
			    	Base64 tBase64 = new Base64();
			  		 orderDetail = new String(tBase64.decode(orderInfo));
			        json.setJsonString(orderDetail);
			        System.out.println("订单明细" + orderDetail );
			        System.out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]");
			        System. out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]");
			        System.out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]");
			        System.out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]");
			        System.out.println("OrderAmount      = [" + json.GetKeyValue("OrderAmount") + "]");
			        System.out.println("Status      = [" + json.GetKeyValue("Status") + "]");
			    }
		  }else {
			//6、商户订单查询失败
			   System. out.println("ReturnCode   = [" + ReturnCode + "]");
			   System. out.println("ErrorMessage = [" + ErrorMessage + "]");
		  }
		 
	    	return orderDetail;
	  }
	  
	  public String  doQuery(String n) {
		  FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		  FillingStationOrderInfo o=service.getOrder(n);
		   
		//2、生成交易流水查询请求对象
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate",o.getPayTime());  //查询日期YYYY/MM/DD （必要信息）
		  tRequest.dicRequest.put("SettleStartHour",o.getPayHour());  //查询开始时间段（0-23）
		  tRequest.dicRequest.put("SettleEndHour",o.getPayHour());  //查询截止时间段（0-23）
		  tRequest.dicRequest.put("ZIP","0");

		  //3、传送交易流水查询请求并取得交易流水
		  JSON json = tRequest.postRequest();

		  //4、判断交易流水查询结果状态，进行后续操作
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000"))
		  {
		      //5、交易流水查询成功，生成交易流水对象
		      System.out.println("ReturnCode      = [" + json.GetKeyValue("ReturnCode") + "]<br/>");
		      System.out.println("ErrorMessage      = [" + json.GetKeyValue("ErrorMessage") + "]<br/>");
		      System.out.println("TrxType      = [" + json.GetKeyValue("TrxType") + "]<br/>");
		   		System.out.println("DetailRecords      = [" + json.GetKeyValue("DetailRecords") + "]<br/>");
		     

		  }
		  else {
		      //6、交易流水查询失败
			  System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
		  }
		  //向前推一个小时查询
		  if(getOrderNo(json.GetKeyValue("DetailRecords"),n).equals("0")) {
			  tRequest.dicRequest.put("SettleDate",o.getQPayTime());  //查询日期YYYY/MM/DD （必要信息）
			  tRequest.dicRequest.put("SettleStartHour",o.getQPayHour());  //查询开始时间段（0-23）
			  tRequest.dicRequest.put("SettleEndHour",o.getQPayHour());  //查询截止时间段（0-23）
			  tRequest.dicRequest.put("ZIP","0");

			   
			  return getOrderNo(tRequest.postRequest().GetKeyValue("DetailRecords"),n);
		  }else {
			  return getOrderNo(json.GetKeyValue("DetailRecords"),n);
		  }
		 
	  }
	  public String getOrderNo(String DetailRecords,String n) {
			String[] strA=DetailRecords.split("\\^\\^");
			List<String> res=new ArrayList<String>();
			for (String sa : strA) {
				res.add(sa);
			}
			 List<String> results = new ArrayList<String>();
			   Pattern pattern = Pattern.compile(n);
			   for(int i=0; i < res.size(); i++){
			      Matcher matcher = pattern.matcher( res.get(i));
			      if(matcher.find()){
			         results.add(res.get(i));
			      }
			   }
			   if(results.size()==0) {
				   return "0";
			   }else {
				   String[] s1=results.get(0).split("\\|");
					  return s1[1];
			   }
			  
	  }
}
