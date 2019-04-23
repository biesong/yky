package com.yky.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yky.web.dao.BaseDao;
import com.yky.web.dao.impl.BaseDaoImpl;
import com.yky.web.entity.Data;
import com.yky.web.entity.Report;
import com.yky.web.service.BaseService;
@Service
public class BaseServiceImpl implements BaseService {
	@Autowired
	BaseDao dao;
	public Report getData(String sql) {
    	Report report=new Report();
		BaseDao dao =new BaseDaoImpl();
		List<Data> list=dao.getData(sql);
		 String[] x=new String[list.size()];
		 String[] z=new String[list.size()] ;
		 for (int i = 0; i < list.size(); i++) {
			 x[i]=list.get(i).getX();
				
			 z[i]=list.get(i).getZ();
				
			}
		 report.setmData(z);
		 report.setxData(x);
		 return report;
    }
	public List<Data> getDataList(String sql){
		//BaseDao dao =new BaseDaoImpl();
		return dao.getData(sql);
	}
}
