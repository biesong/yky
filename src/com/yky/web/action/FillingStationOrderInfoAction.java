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
	
	@RequestMapping("/station")
    public String toStation(){
        return "station";
    }
	
	@RequestMapping("/stationAmount")
    public String tostationAmount(){
        return "stationAmount";
    }
	
	@ResponseBody
	@RequestMapping("/active")
	public Report getByHour() {
		//BaseService service=new BaseServiceImpl();
		return service.getData("SELECT CONVERT( VARCHAR ( 2 ), PayTime, 108 ) AS x,COUNT ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE DateDiff(dd,PayTime,getdate()) = 1 GROUP BY CONVERT ( VARCHAR ( 2 ), PayTime, 108 )");
	}
	@ResponseBody
	@RequestMapping("/OrderAmount")
    public List<Data> getOrderAmount(String d){
		
        return service.getDataList("SELECT sum( OrderAmount ) AS y,s.FillingStationName x FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE convert(varchar(10),PayTime,111)='"+d+"' and s.FillingStationID=o.FillingStationID and o.OrderState=2 GROUP BY	s.FillingStationName order by count( OrderAmount ) desc");
    }
	
	@ResponseBody
	@RequestMapping("/orderWeek")
    public Report orderWeek(){
		
        return service.getData("SELECT  CONVERT(varchar(100), PayTime, 111) AS x,COUNT ( OrderAmount ) y FROM T_FillingStationOrderInfo WHERE month(PayTime)= month(getdate()) and CONVERT(varchar(100), PayTime, 111)< CONVERT(varchar(100), GETDATE(), 111) GROUP BY CONVERT(varchar(100), PayTime, 111)");
    }
	
	
	@ResponseBody
	@RequestMapping("/amountByDate")
    public List<Data> getStationAmount(String sid,String start,String end){
		
        return service.getDataList("select   sum(OrderAmount) as y,CONVERT(varchar(100), PayTime, 111) as x from T_FillingStationOrderInfo where convert(varchar(10),PayTime,111) >='"+start+"' and convert(varchar(10),PayTime,111) <='"+end+"'" + 
        		" and FillingStationID="+sid+" group by CONVERT(varchar(100), PayTime, 111)");
    }
	
}
