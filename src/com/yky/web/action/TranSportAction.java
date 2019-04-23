package com.yky.web.action;


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
	@RequestMapping("/truckDriverInfo")
    public List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end){
		TranSportService service=new TranSportServiceImpl();
        return service.getTruckDriverInfo(f,start,end);
    }
	@RequestMapping("/wujie")
    public String toWujie(){
        return "wujie";
    }
}
