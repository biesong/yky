package com.yky.web.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yky.web.entity.Echarts;
import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.entity.Report;
import com.yky.web.service.FillingStationOrderInfoService;
import com.yky.web.service.impl.FillingStationOrderInfoServiceImpl;
@Controller

public class FillingStationOrderInfoAction {
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
    public List<FillingStationOrderInfo> getOrderCount(){
		FillingStationOrderInfoService service=new FillingStationOrderInfoServiceImpl();
        return service.getOrderCount();
    }
	
	@RequestMapping("/FillingStationOrder")
    public String to(){
        return "FillingStationOrder";
    }
	
}
