package com.yky.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yky.web.dao.BaseDao;
import com.yky.web.entity.Data;
import com.yky.web.util.DBConnection;
@Repository
public class BaseDaoImpl implements BaseDao {
	public List<Data> getData(String sql) {
		DBConnection db = new DBConnection();
		List<Data> list = new ArrayList<Data>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				String y = rs.getString("y");
				String x = rs.getString("x");
				Data to = new Data();
				to.setZ(y);
				to.setX(x);
				list.add(to);
			}
			rs.close();
			db.close();//
		} catch (SQLException e) {
		}
		return list;
	}
}
