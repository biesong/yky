package com.yky.web.task;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.QueryTrnxRecords;
import com.yky.web.entity.Data;
import com.yky.web.service.BaseService;
import com.yky.web.service.EmailService;
import com.yky.web.util.StringUtil;
@Component
public class Task {
	@Autowired
	BaseService service;
	@Autowired
	EmailService emailService;
	@ResponseBody
	@RequestMapping("/send")
    public void sendEmail() {
     StringBuilder content=new StringBuilder("<table border=\"1\" cellspacing=\"0\">");
     List<Data> list=service.getDataList("SELECT sum( OrderAmount ) AS y,s.FillingStationName x FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE convert(varchar(10),PayTime,111)='"+StringUtil.getYesterdayByCalendar()+"' and s.FillingStationID=o.FillingStationID and o.OrderState=2 GROUP BY	s.FillingStationName order by count( OrderAmount ) desc");
     content.append("<tr bgcolor=\"#A9A9A9\"><th>加注站名称</th><th>金额</th></tr>");
     for (Data data : list) {
    	 content.append("<tr><td>"+data.getX()+"</td><td>"+data.getZ()+"</td></tr>");
	}
     content.append("</table>");
    //emailService.sendMailSimple(null, StringUtil.getYesterdayByCalendar()+"加注记录", content.toString());
    }
   public void abc() {
	   NumberFormat numberFormat = NumberFormat.getInstance();
	   numberFormat.setMaximumFractionDigits(2);
		  int sum=0;//总量
		  int error=0;//无响应
		  StringBuilder order=new StringBuilder("<table border='1' cellspacing='0'><tr bgcolor='#A9A9A9'><td>time</td><td>总请求</td><td>无响应</td><td>占比</td></tr>");
		  List<String> results = new ArrayList<String>();
	      for (int h = 0; h <24; h++) {
		  QueryTrnxRecords tRequest = new QueryTrnxRecords();
		  tRequest.dicRequest.put("SettleDate",StringUtil.getByCalendar(-1));  
		  tRequest.dicRequest.put("SettleStartHour",h+"");  
		  tRequest.dicRequest.put("SettleEndHour",h+"");  
		  tRequest.dicRequest.put("ZIP","0");
		  JSON json = tRequest.postRequest();
			String[] strA=json.GetKeyValue("DetailRecords").split("\\^\\^");
			List<String> res=new ArrayList<String>();
			for (String sa : strA) {
				res.add(sa);
			}
			List<String> temp=new ArrayList<String>();
			   Pattern pattern = Pattern.compile("ZY");
			   for(int i=0; i < res.size(); i++){
			      Matcher matcher = pattern.matcher( res.get(i));
			      if(matcher.find()){
			         results.add(res.get(i));
			         temp.add(res.get(i));
			      }
			   }
			  
			   
			   String scale=numberFormat.format(((float)temp.size()/(float)strA.length)*100)+"%";
			   sum=sum+res.size();
			   error=error+temp.size();
			  
			   
			   order.append("<tr><td>"+h+"</td><td>"+res.size()+"</td><td>"+temp.size()+"</td><td>"+scale+"</td></tr>"); 
	      }
	      String scaleTotal= numberFormat.format(((float)error/(float)sum)*100)+"%";
	      order.append("<tr><td>total</td><td>"+sum+"</td><td>"+error+"</td><td>"+scaleTotal+"</td></tr></table>");
	      emailService.sendMailSimple(null, StringUtil.getYesterdayByCalendar()+"交易记录", order.toString());
				
   }
}
