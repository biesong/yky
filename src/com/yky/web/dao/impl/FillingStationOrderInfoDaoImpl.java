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
	/**
	 * 根据加注站查询近7天加注记录
	 */
	public List<FillingStationOrderInfo> getByDate(String sid) {

		String sql = "select top 7  sum(OrderAmount) as OrderAmount,CONVERT(varchar(100), PayTime, 112) as PayTime from T_FillingStationOrderInfo where   DateDiff(dd,PayTime,getdate())<=7 and FillingStationID=? group by CONVERT(varchar(100), PayTime, 112)";

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
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}

	/**
	 * 平均值
	 * 
	 * @param sid
	 * @return
	 */
	public String getAvgByDate(String start,String end,String sid) {

		String sql = "select  convert(decimal(18,2), sum(OrderAmount) /(DATEDIFF(day,'"+start+"','"+end+"')+1)) as OrderAmount from T_FillingStationOrderInfo where  FillingStationID="+sid+" and convert(varchar(10),PayTime,111) >= '"+start+"' and  convert(varchar(10),PayTime,111) <= '"+end+"'";

		DBConnection db = new DBConnection();
		String avg="";
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				avg=rs.getString(1);
			}
			rs.close();
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return avg;
	}
    public FillingStationOrderInfo getOrder(String OrderSN) {
    	FillingStationOrderInfo o=new FillingStationOrderInfo();
    	String sql = "select OrderSN,convert(char(10),PayTime,111)PayTime,Datename(hour,PayTime)PayHour,CONVERT ( CHAR ( 10 ), (DATEADD( hh,- 1, PayTime ) ), 111 ) QPayTime,Datename( HOUR, (DATEADD( hh,- 1, PayTime ) ) ) QPayHour from T_FillingStationOrderInfo where OrderSN=?";

		DBConnection db = new DBConnection();
		
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			stmt.setString(1, OrderSN);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				o.setOrderSN(rs.getString("OrderSN"));
				o.setPayTime(rs.getString("PayTime"));
				o.setPayHour(rs.getString("PayHour"));
				o.setQPayHour(rs.getString("QPayHour"));
				o.setQPayTime(rs.getString("QPayTime"));
			}
			rs.close();
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
    	return o;
    }
	/**
	 * 查询加注站信息
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
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}

	/**
	 * 查询昨天的加注记录
	 */
	public List<FillingStationOrderInfo> getStationOrder() {
		String sql = "SELECT sum	( OrderAmount ) AS OrderAmount,s.FillingStationName FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE DateDiff(dd,PayTime,getdate()) = 1 and s.FillingStationID=o.FillingStationID GROUP BY	s.FillingStationName";
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
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}

	/**
	 * 查询订单数量
	 */
	public List<FillingStationOrderInfo> getOrderCount(String d) {
		String sql = "SELECT sum( OrderAmount ) AS OrderAmount,s.FillingStationName FROM T_FillingStationOrderInfo o,T_FillingStationInfo s WHERE convert(varchar(10),PayTime,111)=? and s.FillingStationID=o.FillingStationID and o.OrderState=2 GROUP BY	s.FillingStationName order by count( OrderAmount ) desc";
		DBConnection db = new DBConnection();
		List<FillingStationOrderInfo> list = new ArrayList<FillingStationOrderInfo>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			 stmt.setString(1, d);
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
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}
	
	 
}
