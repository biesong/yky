package com.yky.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yky.web.dao.FillingStationOrderInfoDao;
import com.yky.web.entity.FillingStation;
import com.yky.web.entity.FillingStationOrderInfo;
import com.yky.web.util.DBConnection;

public class FillingStationOrderInfoDaoImpl implements FillingStationOrderInfoDao {
	@Override
	/**
	 * ���ݼ�עվ��ѯ��7���ע��¼
	 */
	public List<FillingStationOrderInfo> getByDate(String sid) {
		String sql = "select sum(OrderAmount) as OrderAmount,CONVERT(varchar(100), PayTime, 112) as PayTime from T_FillingStationOrderInfo where FillingStationID=? and DateDiff(dd,PayTime,getdate())<7 group by CONVERT(varchar(100), PayTime, 112)";
		DBConnection db = new DBConnection();
		List<FillingStationOrderInfo> list = new ArrayList<FillingStationOrderInfo>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			 stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				double OrderAmount = rs.getDouble("OrderAmount");
				String PayTime = rs.getString("PayTime");
				FillingStationOrderInfo fso = new FillingStationOrderInfo();
				fso.setOrderAmount(OrderAmount);
				fso.setPayTime(PayTime);
				list.add(fso);
			}
			  rs.close();
	            db.close();//�ر����� 
		} catch (SQLException e) {
		}
		return list;
	}

	@Override
	/**
	 * ��ѯ��עվ��Ϣ
	 */
	public List<FillingStation> getStation() {
		String sql = "select FillingStationID,FillingStationName from T_FillingStationInfo where IsAuthentication=1";
		DBConnection db = new DBConnection();
		List<FillingStation> list = new ArrayList<FillingStation>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setInt(1, type);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				int FillingStationID = rs.getInt("FillingStationID");
				String FillingStationName = rs.getString("FillingStationName");
				FillingStation fs = new FillingStation();
				fs.setFillingStationID(FillingStationID);
				fs.setFillingStationName(FillingStationName);
				list.add(fs);
			}
			  rs.close();
	            db.close();//�ر����� 
		} catch (SQLException e) {
		}
		return list;
	}
	/**
	 * ��ѯ����ļ�ע��¼
	 */
	public List<FillingStationOrderInfo> getStationOrder() {
		String sql="SELECT sum	( OrderAmount ) AS OrderAmount,s.FillingStationName FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE DateDiff(dd,PayTime,getdate()) = 1 and s.FillingStationID=o.FillingStationID GROUP BY	s.FillingStationName";
		DBConnection db = new DBConnection();
		List<FillingStationOrderInfo> list = new ArrayList<FillingStationOrderInfo>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				double OrderAmount = rs.getDouble("OrderAmount");
				String FillingStationName = rs.getString("FillingStationName");
				FillingStationOrderInfo fso = new FillingStationOrderInfo();
				fso.setOrderAmount(OrderAmount);
				fso.setFillingStationName(FillingStationName);
				list.add(fso);
			}
			  rs.close();
	            db.close();//�ر����� 
		} catch (SQLException e) {
		}
		return list;
	}
	/**
	 * 
	 */
	public List<FillingStationOrderInfo> getOrderCount() {
		String sql="SELECT count( OrderAmount ) AS OrderAmount,s.FillingStationName FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE DateDiff(dd,PayTime,getdate()) = 1 and s.FillingStationID=o.FillingStationID GROUP BY	s.FillingStationName order by count( OrderAmount ) desc";
		DBConnection db = new DBConnection();
		List<FillingStationOrderInfo> list = new ArrayList<FillingStationOrderInfo>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				double OrderAmount = rs.getDouble("OrderAmount");
				String FillingStationName = rs.getString("FillingStationName");
				FillingStationOrderInfo fso = new FillingStationOrderInfo();
				fso.setOrderAmount(OrderAmount);
				fso.setFillingStationName(FillingStationName);
				list.add(fso);
			}
			  rs.close();
	            db.close();//�ر����� 
		} catch (SQLException e) {
		}
		return list;
	}
}