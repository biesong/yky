package com.yky.web.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yky.web.entity.Data;
import com.yky.web.entity.Report;
import com.yky.web.entity.TruckDriverInfo;
import com.yky.web.service.BaseService;
import com.yky.web.service.TranSportService;
import com.yky.web.service.impl.TranSportServiceImpl;

@Controller
public class TranSportAction {
	@Autowired
	BaseService service;
	
	
	@RequestMapping("/transportOrder")
    public String totransportOrder(){
        return "transportOrder";
    }
	
	@RequestMapping("/deliver")
    public String toDeliver(){
        return "deliver";
    }
	
	@RequestMapping("/appDeliver")
    public String toAppDeliver(){
        return "appDeliver";
    }
	
	
	@ResponseBody
	@RequestMapping("/tOrder")
    public Report getOrder(){
		TranSportService service=new TranSportServiceImpl();
        return service.getData();
    }
	@ResponseBody
	@RequestMapping("/tOrderByDate")
    public Report getOrderByDate(){
		TranSportService service=new TranSportServiceImpl();
        return service.getOrderByDate();
    }
	
	

	
	@ResponseBody
	@RequestMapping("/one")
    public List<Data> getOne(String d){
		
        return service.getDataList("SELECT	( SELECT c1.RealName FROM T_UserCertInfo c1 WHERE c1.UserID= d.UserID ) x,COUNT ( t.OrderNO ) y FROM T_TruckDriverInfo t,T_DeliverInfo d WHERE convert(varchar(10),t.addTime,111)='"+d+"' AND t.DeliverID= d.DeliverID GROUP BY d.UserID");
    }
	@ResponseBody
	@RequestMapping("/second")
    public List<Data> getSecond(String d){
		
        return service.getDataList("SELECT	( SELECT c1.RealName FROM T_UserCertInfo c1 WHERE c1.UserID= d.UserID ) x,COUNT ( t.OrderNO ) y FROM T_TruckDriverInfo t,T_DeliverInfo d WHERE convert(varchar(10),t.addTime,111)='"+d+"' AND t.DeliverID= d.DeliverID GROUP BY d.UserID");
    }
	
	@ResponseBody
	@RequestMapping("/deliverMonth")
    public List<Data> getDeliverMonth(){
		
        return service.getDataList("SELECT 	concat( YEAR(t.AddTime),'/', MONTH(t.AddTime))x,COUNT ( t.OrderNO ) y FROM T_TruckDriverInfo t  GROUP BY  MONTH(t.AddTime),YEAR(t.AddTime) order by YEAR(AddTime)desc, MONTH(AddTime) desc");
    }
	
	
	
	@ResponseBody
	@RequestMapping("/truckDriverInfo")
    public List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end) throws IOException {
	
		f = URLEncoder.encode(f, "ISO-8859-1");
	
		
		TranSportService service=new TranSportServiceImpl();
        return service.getTruckDriverInfo(URLDecoder.decode(f, "UTF-8"),start,end);
    }
	@RequestMapping("/wujie")
    public String toWujie(){
        return "wujie";
    }
}
