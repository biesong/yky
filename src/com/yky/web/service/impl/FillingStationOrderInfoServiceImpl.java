package com.yky.web.service.impl;

import java.util.List;

import com.yky.web.dao.FillingStationOrderInfoDao;
import com.yky.web.dao.impl.FillingStationOrderInfoDaoImpl;
import com.yky.web.entity.Echarts;
import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.entity.Report;
import com.yky.web.service.FillingStationOrderInfoService;

public class FillingStationOrderInfoServiceImpl implements FillingStationOrderInfoService {
	public Echarts getData(String sid) {
		Echarts echarts=new Echarts();
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		List<FillingStationOrderInfo> list=dao.getByDate(sid);
		 String[] PayTime=new String[list.size()];
		 double[] OrderAmount=new double[list.size()] ;
		 for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(1).getOperate());
			 PayTime[i]=list.get(i).getPayTime();
				
			 OrderAmount[i]=list.get(i).getOrderAmount();
				//categories[i]=list.get(1).getOperate();
				
			}
		 echarts.setOrderAmount(OrderAmount);
		 echarts.setPayTime(PayTime);
		 return echarts;
	}
    public Report getData() {
    	Report report=new Report();
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		List<FillingStationOrderInfo> list=dao.getStationOrder();
		 String[] FillingStationName=new String[list.size()];
		 double[] OrderAmount=new double[list.size()] ;
		 for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(1).getOperate());
			 FillingStationName[i]=list.get(i).getFillingStationName();
				
			 OrderAmount[i]=list.get(i).getOrderAmount();
				//categories[i]=list.get(1).getOperate();
				
			}
		 report.setyData(OrderAmount);
		 report.setxData(FillingStationName);
		 return report;
    }
	public List<FillingStation> getList() {
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		return dao.getStation();
	}
	public List<FillingStationOrderInfo> getOrderCount(String d){
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		return dao.getOrderCount(d);
	}
	public String getAvgByDate(String start,String end,String sid) {
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		return dao.getAvgByDate(start, end, sid);
	}
	public FillingStationOrderInfo getOrder(String OrderSN) {
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		return dao.getOrder(OrderSN);
	}
	public List<FillingStationOrderInfo> getRefund(String f,String start,String end) {
		FillingStationOrderInfoDao dao =new FillingStationOrderInfoDaoImpl();
		return dao.getRefund(f,start,end);
	}
}
