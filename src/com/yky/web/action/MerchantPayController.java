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
		  tQueryRequest.queryRequest.put("PayTypeID", "ImmediatePay");    //�趨��������
		  tQueryRequest.queryRequest.put("OrderNo", doQuery(n));    //�趨������� ����Ҫ��Ϣ��
		  tQueryRequest.queryRequest.put("QueryDetail", "false");//�趨��ѯ��ʽ
		  JSON json = tQueryRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000")) {
			  System.out.println("ReturnCode   = [" + ReturnCode + "]");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]");
			  String orderInfo = json.GetKeyValue("Order");
			    if (orderInfo.length() < 1)
			    {
			        System.out.println("��ѯ���Ϊ��");
			    }else {
			    	   //1����ԭ����base64�������Ϣ 
			    	Base64 tBase64 = new Base64();
			  		 orderDetail = new String(tBase64.decode(orderInfo));
			        json.setJsonString(orderDetail);
			        System.out.println("������ϸ" + orderDetail );
			        System.out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]");
			        System. out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]");
			        System.out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]");
			        System.out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]");
			        System.out.println("OrderAmount      = [" + json.GetKeyValue("OrderAmount") + "]");
			        System.out.println("Status      = [" + json.GetKeyValue("Status") + "]");
			    }
		  }else {
			//6���̻�������ѯʧ��
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
		 
		  
		  //1�������˿��������
	        RefundRequest tRequest = new RefundRequest();
	        tRequest.dicRequest.put("OrderDate", j.GetKeyValue("OrderDate"));  //�������ڣ���Ҫ��Ϣ��
	        tRequest.dicRequest.put("OrderTime", j.GetKeyValue("OrderTime")); //����ʱ�䣨��Ҫ��Ϣ��
	        //tRequest.dicRequest.put("MerRefundAccountNo", request.getParameter("txtMerRefundAccountNo"));  //�̻��˿��˺�
	        //tRequest.dicRequest.put("MerRefundAccountName", request.getParameter("txtMerRefundAccountName")); //�̻��˿���
	        tRequest.dicRequest.put("OrderNo", j.GetKeyValue("OrderNo")); //ԭ���ױ�ţ���Ҫ��Ϣ��
	        tRequest.dicRequest.put("NewOrderNo", j.GetKeyValue("OrderNo")+"_refund"); //���ױ�ţ���Ҫ��Ϣ��
	        tRequest.dicRequest.put("CurrencyCode", "156"); //���ױ��֣���Ҫ��Ϣ��
	        tRequest.dicRequest.put("TrxAmount", j.GetKeyValue("OrderAmount")); //�˻���� ����Ҫ��Ϣ��
	        tRequest.dicRequest.put("MerchantRemarks", "����");  //����
			//�����Ҫר�ߵ�ַ�����ô˷�����
			//tRequest.setConnectionFlag(true);
			
	
		
		 

	        //3�������˿�����ȡ���˻����
	        JSON json = tRequest.postRequest();

	        //4���ж��˿���״̬�����к�������
	        StringBuilder strMessage = new StringBuilder("");
	        String ReturnCode = json.GetKeyValue("ReturnCode");
	        String ErrorMessage = json.GetKeyValue("ErrorMessage");
	        if (ReturnCode.equals("0000"))
	        {
	            //5���˿�ɹ�/�˿�����ɹ�
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
	            //6���˿�ʧ��
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
		  tQueryRequest.queryRequest.put("PayTypeID", "ImmediatePay");    //�趨��������
		  tQueryRequest.queryRequest.put("OrderNo", "JZ1555725999254BD_190420100639477");    //�趨������� ����Ҫ��Ϣ��
		  tQueryRequest.queryRequest.put("QueryDetail", "false");//�趨��ѯ��ʽ
		  JSON json = tQueryRequest.postRequest();
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000")) {
			  System.out.println("ReturnCode   = [" + ReturnCode + "]");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]");
			  String orderInfo = json.GetKeyValue("Order");
			    if (orderInfo.length() < 1)
			    {
			        System.out.println("��ѯ���Ϊ��");
			    }else {
			    	   //1����ԭ����base64�������Ϣ 
			    	Base64 tBase64 = new Base64();
			  		 orderDetail = new String(tBase64.decode(orderInfo));
			        json.setJsonString(orderDetail);
			        System.out.println("������ϸ" + orderDetail );
			        System.out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]");
			        System. out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]");
			        System.out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]");
			        System.out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]");
			        System.out.println("OrderAmount      = [" + json.GetKeyValue("OrderAmount") + "]");
			        System.out.println("Status      = [" + json.GetKeyValue("Status") + "]");
			    }
		  }else {
			//6���̻�������ѯʧ��
			   System. out.println("ReturnCode   = [" + ReturnCode + "]");
			   System. out.println("ErrorMessage = [" + ErrorMessage + "]");
		  }
		 
	    	return orderDetail;
	  }
	  
	  public String  doQuery(String n) {
		  FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		  FillingStationOrderInfo o=service.getOrder(n);
		   
		//2�����ɽ�����ˮ��ѯ�������
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate",o.getPayTime());  //��ѯ����YYYY/MM/DD ����Ҫ��Ϣ��
		  tRequest.dicRequest.put("SettleStartHour",o.getPayHour());  //��ѯ��ʼʱ��Σ�0-23��
		  tRequest.dicRequest.put("SettleEndHour",o.getPayHour());  //��ѯ��ֹʱ��Σ�0-23��
		  tRequest.dicRequest.put("ZIP","0");

		  //3�����ͽ�����ˮ��ѯ����ȡ�ý�����ˮ
		  JSON json = tRequest.postRequest();

		  //4���жϽ�����ˮ��ѯ���״̬�����к�������
		  String ReturnCode = json.GetKeyValue("ReturnCode");
		  String ErrorMessage = json.GetKeyValue("ErrorMessage");
		  if (ReturnCode.equals("0000"))
		  {
		      //5��������ˮ��ѯ�ɹ������ɽ�����ˮ����
		      System.out.println("ReturnCode      = [" + json.GetKeyValue("ReturnCode") + "]<br/>");
		      System.out.println("ErrorMessage      = [" + json.GetKeyValue("ErrorMessage") + "]<br/>");
		      System.out.println("TrxType      = [" + json.GetKeyValue("TrxType") + "]<br/>");
		   		System.out.println("DetailRecords      = [" + json.GetKeyValue("DetailRecords") + "]<br/>");
		     

		  }
		  else {
		      //6��������ˮ��ѯʧ��
			  System.out.println("ReturnCode   = [" + ReturnCode + "]<br>");
			  System.out.println("ErrorMessage = [" + ErrorMessage + "]<br>");
		  }
		  //��ǰ��һ��Сʱ��ѯ
		  if(getOrderNo(json.GetKeyValue("DetailRecords"),n).equals("0")) {
			  tRequest.dicRequest.put("SettleDate",o.getQPayTime());  //��ѯ����YYYY/MM/DD ����Ҫ��Ϣ��
			  tRequest.dicRequest.put("SettleStartHour",o.getQPayHour());  //��ѯ��ʼʱ��Σ�0-23��
			  tRequest.dicRequest.put("SettleEndHour",o.getQPayHour());  //��ѯ��ֹʱ��Σ�0-23��
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
