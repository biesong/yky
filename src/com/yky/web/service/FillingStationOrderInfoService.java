package com.yky.web.service;

import java.util.List;

import com.yky.web.entity.Echarts;
import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.entity.Report;

public interface FillingStationOrderInfoService {

	Echarts getData(String sid);
	List<FillingStation> getList();
	Report getData();
	List<FillingStationOrderInfo> getOrderCount(String d);
	String getAvgByDate(String start,String end,String sid);
	public FillingStationOrderInfo getOrder(String OrderSN);
}