package com.yky.web.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	String driver=PropertiesUtil.getValueByKey("driver");
    String url= PropertiesUtil.getValueByKey("url");
    String user = PropertiesUtil.getValueByKey("user");
    String password = PropertiesUtil.getValueByKey("password");
    public Connection conn;
    public DBConnection() {

        try {
            Class.forName(driver);// 加载驱动程序
            conn = (Connection) DriverManager.getConnection(url, user, password);// 连续数据库
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
