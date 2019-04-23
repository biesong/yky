package com.yky.web.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yky.web.dao.TranSportDao;
import com.yky.web.entity.Data;
import com.yky.web.entity.TransportOrder;
import com.yky.web.entity.TruckDriverInfo;
import com.yky.web.util.DBConnection;

public class TranSportDaoImpl implements TranSportDao {
	/**
	 * 查询订单数量
	 */
	public List<TransportOrder> getOrderCount() {
		String sql = "SELECT	( SELECT c1.RealName FROM T_UserCertInfo c1 WHERE c1.UserID= d.UserID ) Fname,COUNT ( t.OrderNO ) Torder FROM T_TruckDriverInfo t,T_DeliverInfo d WHERE DateDiff(dd,t.AddTime,getdate()) = 1 AND t.DeliverID= d.DeliverID GROUP BY d.UserID";
		DBConnection db = new DBConnection();
		List<TransportOrder> list = new ArrayList<TransportOrder>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				int Torder = rs.getInt("Torder");
				String Fname = rs.getString("Fname");
				TransportOrder to = new TransportOrder();
				to.setTorder(Torder);
				to.setFname(Fname);
				list.add(to);
			}
			rs.close();
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}
	public List<Data> getOrderByDate() {
		String sql = "SELECT   COUNT ( t.OrderNO ) Torder,CONVERT(varchar(100), t.AddTime, 112)AddTime FROM T_TruckDriverInfo t WHERE month(getdate())=month(t.AddTime) and CONVERT(varchar(100), t.AddTime, 112)<CONVERT(varchar(100), getdate(), 112)  GROUP BY CONVERT(varchar(100), t.AddTime, 112)";
		DBConnection db = new DBConnection();
		List<Data> list = new ArrayList<Data>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				int y = rs.getInt("Torder");
				String x = rs.getString("AddTime");
				Data to = new Data();
				to.setY(y);
				to.setX(x);
				list.add(to);
			}
			rs.close();
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}
	
	public List<TruckDriverInfo> getTruckDriverInfo(String f,String start,String end) {
		String sql = "select * from (SELECT" + 
				"    ( SELECT c1.RealName FROM T_UserCertInfo c1 WHERE c1.UserID= d.UserID )fName," + 
				"    c.RealName zName," + 
				"    ( SELECT d.FullName FROM T_DriverInfo d WHERE d.DriverID= v.DriverID )sName," + 
				"    t.OrderPriceAmount ," + 
				"    t.CargoDamageAmount ," + 
				"    t.OrderFillingPrice  ," + 
				"    d.TaxRate ," + 
				"    CONVERT(varchar(100), t.AddTime, 20) AS AddTime," + 
				"    (case t.DeliverState when 6 then '进行中'  when 10 then '打款中'  when 11 then '打款完成' else'' end)status "+
				"    FROM  T_TruckDriverInfo t," + 
				"    T_UserCertInfo c," + 
				"    T_DeliverInfo d," + 
				"    T_VehicleInfo v" + 
				" WHERE t.DeliverState IN ( 6, 10, 11 )" + 
				"    AND CONVERT(varchar(100), t.AddTime, 23)>='"+start+"'  " + 
				"    and CONVERT(varchar(100), t.AddTime, 23)<='"+end+"' "+
				"    AND c.UserID= t.UserID" + 
				"    AND t.DeliverID= d.DeliverID" + 
				"    AND v.VehicleID= t.VehicleID" + 
				"    ) b where b.fName like '%"+f+"%'  ";
		DBConnection db = new DBConnection();
		List<TruckDriverInfo> list = new ArrayList<TruckDriverInfo>();
		try {
			PreparedStatement stmt = (PreparedStatement) db.conn.prepareStatement(sql);
			// stmt.setString(1, sid);
			ResultSet rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				
				TruckDriverInfo td = new TruckDriverInfo();
				td.setAddTime(rs.getString("AddTime"));
				td.setTaxRate(rs.getDouble("TaxRate"));
				td.setOrderFillingPrice(rs.getDouble("OrderFillingPrice"));
				td.setCargoDamageAmount(rs.getDouble("CargoDamageAmount"));
				td.setOrderPriceAmount(rs.getDouble("OrderPriceAmount"));
				td.setsName(rs.getString("sName"));
				td.setzName(rs.getString("zName"));
				td.setfName(rs.getString("fName"));
				td.setStatus(rs.getString("status"));
				list.add(td);
			}
			rs.close();
			db.close();// 关闭连接
		} catch (SQLException e) {
		}
		return list;
	}
}
