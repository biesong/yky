package com.yky.web.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getValueByKey(String key){
		Properties properties=null;
		try {
			properties=new Properties();
			InputStream in=PropertiesUtil.class.getClassLoader().getResourceAsStream("web.properties");
			properties.load(in);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
}
