package com.yky.web.service;

import java.util.List;

import com.yky.web.entity.Data;
import com.yky.web.entity.Report;

public interface BaseService {

	Report getData(String sql);
	List<Data> getDataList(String sql);
}