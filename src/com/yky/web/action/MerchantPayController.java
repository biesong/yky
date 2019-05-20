package com.yky.web.action;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.pay.client.Base64;
import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.QueryOrderRequest;
import com.abc.pay.client.ebus.QueryTrnxRecords;
import com.abc.pay.client.ebus.RefundRequest;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.service.FillingStationOrderInfoService;
import com.yky.web.service.impl.FillingStationOrderInfoServiceImpl;
import com.yky.web.util.StringUtil;



@Controller
public class MerchantPayController {
	  @RequestMapping("/refund")
	    public String toRefund() {
	    	return "refund";
	    }
	  
	  public String queryByOrderSN(String n) {
		  String orderDetail="";
		  QueryOrderRequest tQueryRequest = new QueryOrderRequest();
		  tQueryRequest.queryRequest.put("PayTypeID", "ImmediatePay");    
		  tQueryRequest.queryRequest.put("OrderNo", doQuery(n));    
		  tQueryRequest.queryRequest.put("QueryDetail", "false");
		  JSON json = tQueryRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000")) {
			  System.out.println("ReturnCode   = [" + ReturnCode + "]");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]");
			  String orderInfo = json.GetKeyValue("Order");
			    if (orderInfo.length() < 1)
			    {
			        
			    }else {
			    	
			    	Base64 tBase64 = new Base64();
			  		 orderDetail = new String(tBase64.decode(orderInfo));
			        json.setJsonString(orderDetail);
			    
			        System.out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]");
			        System. out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]");
			        System.out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]");
			        System.out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]");
			        System.out.println("OrderAmount      = [" + json.GetKeyValue("OrderAmount") + "]");
			        System.out.println("Status      = [" + json.GetKeyValue("Status") + "]");
			    }
		  }else {
		
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
		 
		  
	
	        RefundRequest tRequest = new RefundRequest();
	        tRequest.dicRequest.put("OrderDate", j.GetKeyValue("OrderDate"));  
	        tRequest.dicRequest.put("OrderTime", j.GetKeyValue("OrderTime")); 
	       
	        tRequest.dicRequest.put("OrderNo", j.GetKeyValue("OrderNo")); 
	        tRequest.dicRequest.put("NewOrderNo", j.GetKeyValue("OrderNo")+"_refund"); 
	        tRequest.dicRequest.put("CurrencyCode", "156"); 
	        tRequest.dicRequest.put("TrxAmount", j.GetKeyValue("OrderAmount")); 
	      
		
			//tRequest.setConnectionFlag(true);
			
	
		
		 

	     
	        JSON json = tRequest.postRequest();

	        StringBuilder strMessage = new StringBuilder("");
	        String ReturnCode = json.GetKeyValue("ReturnCode");
	        String ErrorMessage = json.GetKeyValue("ErrorMessage");
	        if (ReturnCode.equals("0000"))
	        {
	           
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
	         
	        	System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
	        	System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
			}
		  return ReturnCode;
	  }
	  @ResponseBody
	  @RequestMapping(value="/flow")
	  public Object queryFlow() {
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate",StringUtil.getByCalendar(0));  
		  tRequest.dicRequest.put("SettleStartHour",StringUtil.getPreHour("HH"));  
		  tRequest.dicRequest.put("SettleEndHour",StringUtil.getPreHour("HH"));  
		  tRequest.dicRequest.put("ZIP","0");
		  JSON json = tRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		 
		  if (ReturnCode.equals("0000"))
		  {
		     
		      System.out.println("ReturnCode      = [" + json.GetKeyValue("ReturnCode") + "]<br/>");
		      System.out.println("ErrorMessage      = [" + json.GetKeyValue("ErrorMessage") + "]<br/>");
		      System.out.println("TrxType      = [" + json.GetKeyValue("TrxType") + "]<br/>");
		   		System.out.println("DetailRecords      = [" + json.GetKeyValue("DetailRecords") + "]<br/>");
		     

		  }
		  else {
			  
			
			  System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
			 
		  }
		
			String[] strA=json.GetKeyValue("DetailRecords").split("\\^\\^");
			 StringBuilder order=new StringBuilder("<table border='1' cellspacing='0'><tr bgcolor='#A9A9A9'><td>Type</td><td>Time</td><td>Amount</td><td>Status</td></tr>");
			for (String sa : strA) {
				String[] td=sa.split("\\|");
				order.append("<tr><td>"+td[0]+"</td><td>"+td[2]+"</td><td>"+td[3]+"</td><td>"+td[4]+"</td></tr>");
			}
			order.append("</table>");
		   return  order.toString();
			  
	    	
	  }
	  @ResponseBody
	  @RequestMapping("/all")
	  public Object queryALL() {
		
		
		
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate","2019/05/16");  
		  tRequest.dicRequest.put("SettleStartHour","1");  
		  tRequest.dicRequest.put("SettleEndHour","1");  
		  tRequest.dicRequest.put("ZIP","0");

		
		  JSON json = tRequest.postRequest();

	
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000"))
		  {
		     
		      System.out.println("ReturnCode      = [" + json.GetKeyValue("ReturnCode") + "]<br/>");
		      System.out.println("ErrorMessage      = [" + json.GetKeyValue("ErrorMessage") + "]<br/>");
		      System.out.println("TrxType      = [" + json.GetKeyValue("TrxType") + "]<br/>");
		   		System.out.println("DetailRecords      = [" + json.GetKeyValue("DetailRecords") + "]<br/>");
		     

		  }
		  else {
			  
		    
			  System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
		  }
		
		
			String[] strA=json.GetKeyValue("DetailRecords").split("\\^\\^");
			 StringBuilder order=new StringBuilder("<table border='1' cellspacing='0'><tr bgcolor='#A9A9A9'><td>Type</td><td>Time</td><td>Amount</td><td>Status</td></tr>");
			for (String sa : strA) {
				String[] td=sa.split("\\|");
				order.append("<tr><td>"+td[0]+"</td><td>"+td[2]+"</td><td>"+td[3]+"</td><td>"+td[4]+"</td></tr>");
			}
			order.append("</table>");
		   return  order.toString();
			  
	    	
	  }
	  public String  doQuery(String n) {
		  FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		  FillingStationOrderInfo o=service.getOrder(n);
		   
		
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate",o.getPayTime());  
		  tRequest.dicRequest.put("SettleStartHour",o.getPayHour());  
		  tRequest.dicRequest.put("SettleEndHour",o.getPayHour());  
		  tRequest.dicRequest.put("ZIP","0");

		
		  JSON json = tRequest.postRequest();

	
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000"))
		  {
		     
		      System.out.println("ReturnCode      = [" + json.GetKeyValue("ReturnCode") + "]<br/>");
		      System.out.println("ErrorMessage      = [" + json.GetKeyValue("ErrorMessage") + "]<br/>");
		      System.out.println("TrxType      = [" + json.GetKeyValue("TrxType") + "]<br/>");
		   		System.out.println("DetailRecords      = [" + json.GetKeyValue("DetailRecords") + "]<br/>");
		     

		  }
		  else {
			  
		    
			  System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
		  }
		
		  if(getOrderNo(json.GetKeyValue("DetailRecords"),n).equals("0")) {
			  tRequest.dicRequest.put("SettleDate",o.getQPayTime());  
			  tRequest.dicRequest.put("SettleStartHour",o.getQPayHour());  
			  tRequest.dicRequest.put("SettleEndHour",o.getQPayHour());  
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
	  @ResponseBody
	  @RequestMapping("/getrefund")
	public List<FillingStationOrderInfo> getrefund(String f,String start,String end){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		return service.getRefund(f,start,end);
	}
}
