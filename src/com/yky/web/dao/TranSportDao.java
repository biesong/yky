package com.yky.web.dao;

import java.util.List;

import com.yky.web.entity.Data;
import com.yky.web.entity.TransportOrder;
import com.yky.web.entity.TruckDriverInfo;

public interface TranSportDao {

	/**
	 * 
	 */
	List<TransportOrder> getOrderCount();
	List<Data> getOrderByDate();
	List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end);
}