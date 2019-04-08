package com.yky.web.dao;

import java.util.List;

import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;

public interface FillingStationOrderInfoDao {

	List<FillingStationOrderInfo> getByDate(String sid);
	List<FillingStation> getStation();
	 List<FillingStationOrderInfo> getStationOrder();
	 List<FillingStationOrderInfo> getOrderCount();
}