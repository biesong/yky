package com.yky.web.dao;

import java.util.List;

import com.yky.web.entity.Data;

public interface BaseDao {

	List<Data> getData(String sql);

}