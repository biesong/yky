package com.yky.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yky.web.entity.Data;
import com.yky.web.entity.Echarts;
import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.entity.Report;
import com.yky.web.service.BaseService;
import com.yky.web.service.FillingStationOrderInfoService;

import com.yky.web.service.impl.FillingStationOrderInfoServiceImpl;
import com.yky.web.util.StringUtil;
@Controller

public class FillingStationOrderInfoAction {
	@Autowired
	BaseService service;
	
	@ResponseBody
	@RequestMapping("/getData")
    public Echarts getData(String sid){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
        return service.getData(sid);
    }
	@ResponseBody
	@RequestMapping("/getStation")
    public List<FillingStation> getList(){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
        return service.getList();
    }
	@ResponseBody
	@RequestMapping("/getOrder")
    public Report getOrder(){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
        return service.getData();
    }
	
	@ResponseBody
	@RequestMapping("/report")
    public List<FillingStationOrderInfo> getOrderCount(String d1,String d2){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		List<FillingStationOrderInfo> list1= service.getOrderCount(d1);
		List<FillingStationOrderInfo> list2= service.getOrderCount(d2);
		 list1.removeAll(list2);
		 return list1;
    }
	@ResponseBody
	@RequestMapping("/report2")
    public List<FillingStationOrderInfo> getReport(String d1,String d2){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		List<FillingStationOrderInfo> list1= service.getOrderCount(d1);
		List<FillingStationOrderInfo> list2= service.getOrderCount(d2);
		 list2.removeAll(list1);
		 return list2;
    }
	@ResponseBody
	@RequestMapping("/OrderCount")
    public List<FillingStationOrderInfo> getOrderCount(String d){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
        return service.getOrderCount(d);
    }
	@ResponseBody
	@RequestMapping("/avg")
    public Object getAvg(String start,String end,String sid){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
		
        return service.getAvgByDate(start,end,sid);
    }
	
	
	@RequestMapping("/FillingStationOrder")
    public String to(){
        return "FillingStationOrder";
    }
	@RequestMapping("/stationWeek")
    public String toStationWeek(){
        return "stationWeek";
    }
	@RequestMapping("/station")
    public String toStation(){
        return "station";
    }
	@RequestMapping("/appStation")
    public String toAppStation(){
        return "appStation";
    }
	@RequestMapping("/stationAmount")
    public String tostationAmount(){
        return "stationAmount";
    }
	@RequestMapping("/monthStation")
    public String toMonthStation(){
        return "monthStation";
    }
	@ResponseBody
	@RequestMapping("/active")
	public Report getByHour(String d) {
		//BaseService service=new BaseServiceImpl();
		return service.getData("SELECT CONVERT( VARCHAR ( 2 ), PayTime, 108 ) AS x,COUNT ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE convert(varchar(10),PayTime,111)='"+d+"' GROUP BY CONVERT ( VARCHAR ( 2 ), PayTime, 108 )");
	}
	@ResponseBody
	@RequestMapping("/OrderAmount")
    public List<Data> getOrderAmount(String d){
		
        return service.getDataList("SELECT sum( OrderAmount ) AS y,concat(s.FillingStationName,'-',s.FillingStationID,'-',(select  convert(decimal(18,2), sum(OrderAmount) /(DATEDIFF(day,convert(VARCHAR(10), DATEADD(day, -6, '"+d+"'),111),'"+d+"')+1)) as OrderAmount from T_FillingStationOrderInfo where  FillingStationID=s.FillingStationID and OrderState=2 and convert(varchar(10),PayTime,111) >= convert(VARCHAR(10), DATEADD(day, -6, '"+d+"'),111) and  convert(varchar(10),PayTime,111) <= '"+d+"'))x FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE convert(varchar(10),PayTime,111)='"+d+"' and s.FillingStationID=o.FillingStationID and o.OrderState=2 GROUP BY	s.FillingStationName,s.FillingStationID order by count( OrderAmount ) desc");
    }
	
	@ResponseBody
	@RequestMapping("/dayTotal")
    public List<Data> getTotalDay(String d){
		
        return service.getDataList("SELECT '加注站总金额' AS x, sum ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE  OrderState=2 and CONVERT(varchar(100), PayTime, 111)='"+d+"' GROUP BY CONVERT(varchar(100), PayTime, 111)\r\n" + 
        		"UNION all \r\n" + 
        		"SELECT  '加注站总订单量' AS x,count ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE  OrderState=2 and CONVERT(varchar(100), PayTime, 111)='"+d+"' GROUP BY CONVERT(varchar(100), PayTime, 111)\r\n" + 
        		"UNION all \r\n" + 
        		"select '新增用户量' x , count(t2.UserID)y from T_UserInfo t2 where  CONVERT ( VARCHAR ( 100 ), RegistTime, 111 )='"+d+"' group by CONVERT ( VARCHAR ( 100 ), RegistTime, 111 ) ");
    }
	
	
	@ResponseBody
	@RequestMapping("/amountMonth")
    public List<Data> getAmountMonth(){
		
        return service.getDataList("select concat (YEAR(PayTime), '/',MONTH(PayTime)) x,sum(OrderAmount)y from T_FillingStationOrderInfo where OrderState=2 GROUP BY MONTH(PayTime) ,YEAR(PayTime) order by YEAR(PayTime)desc, MONTH(PayTime) desc");
    }
	@ResponseBody
	@RequestMapping("/orderWeek")
    public Report orderWeek(){
		
        return service.getData("SELECT  CONVERT(varchar(100), PayTime, 111) AS x,count ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE DateDiff(dd,paytime,getdate())<=30 and OrderState=2 and CONVERT(varchar(100), PayTime, 111)< CONVERT(varchar(100), GETDATE(), 111) GROUP BY CONVERT(varchar(100), PayTime, 111) order by CONVERT(varchar(100), PayTime, 111)");
    }
	@ResponseBody
	@RequestMapping("/orderMonth")
    public Report orderMonth(){
		
        return service.getData("SELECT  CONVERT(varchar(100), PayTime, 111) AS x,sum ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE DateDiff(dd,paytime,getdate())<=30 and OrderState=2 and CONVERT(varchar(100), PayTime, 111)< CONVERT(varchar(100), GETDATE(), 111) GROUP BY CONVERT(varchar(100), PayTime, 111) order by CONVERT(varchar(100), PayTime, 111)");
    }
	
	@ResponseBody
	@RequestMapping("/amountByDate")
    public List<Data> getStationAmount(String sid,String start,String end){
        return service.getDataList("select   sum(OrderAmount) as y,CONVERT(varchar(100), PayTime, 111) as x from T_FillingStationOrderInfo where convert(varchar(10),PayTime,111) >='"+start+"' and convert(varchar(10),PayTime,111) <='"+end+"'" + 
        		" and FillingStationID="+sid+" group by CONVERT(varchar(100), PayTime, 111)");
    }
	
}
