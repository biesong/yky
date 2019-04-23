package com.yky.web.service.impl;

import java.util.List;

import com.yky.web.dao.TranSportDao;
import com.yky.web.dao.impl.TranSportDaoImpl;
import com.yky.web.entity.Data;
import com.yky.web.entity.Report;
import com.yky.web.entity.TransportOrder;
import com.yky.web.entity.TruckDriverInfo;
import com.yky.web.service.TranSportService;

public class TranSportServiceImpl implements TranSportService {
	public Report getData() {
    	Report report=new Report();
		TranSportDao dao =new TranSportDaoImpl();
		List<TransportOrder> list=dao.getOrderCount();
		 String[] FillingStationName=new String[list.size()];
		 int[] OrderAmount=new int[list.size()] ;
		 for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(1).getOperate());
			 FillingStationName[i]=list.get(i).getFname();
				
			 OrderAmount[i]=list.get(i).getTorder();
				//categories[i]=list.get(1).getOperate();
				
			}
		 report.setzData(OrderAmount);
		 report.setxData(FillingStationName);
		 return report;
    }
	public Report getOrderByDate() {
    	Report report=new Report();
		TranSportDao dao =new TranSportDaoImpl();
		List<Data> list=dao.getOrderByDate();
		 String[] x=new String[list.size()];
		 int[] y=new int[list.size()] ;
		 for (int i = 0; i < list.size(); i++) {
			 x[i]=list.get(i).getX();
				
			 y[i]=list.get(i).getY();
				
			}
		 report.setzData(y);
		 report.setxData(x);
		 return report;
    }
	
	public List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end){
		TranSportDao dao =new TranSportDaoImpl();
		return dao.getTruckDriverInfo(f,start,end);
	}
}
