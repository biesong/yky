package com.yky.web.service;

import java.util.List;

import com.yky.web.entity.Report;
import com.yky.web.entity.TruckDriverInfo;

public interface TranSportService {

	Report getData();
	Report getOrderByDate();
	List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end);
}