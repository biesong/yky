package com.yky.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {
	public static String getYesterdayByCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		String yesterday = new SimpleDateFormat("yyyy/MM/dd").format(time);
		return yesterday;
	}

	public static String getByCalendar(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		Date time = calendar.getTime();
		String yesterday = new SimpleDateFormat("yyyy/MM/dd").format(time);
		return yesterday;
	}

	public static String getByCalendarH(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		Date time = calendar.getTime();
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(time);
		return yesterday;
	}

	public static String getPreHour(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = new Date();
		//d.setHours(d.getHours() );
		return sdf.format(d);
	}

public static String getSpecifiedDayBefore(String specifiedDay) {
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy/MM/dd").parse(specifiedDay);
 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 6);
 
		String dayBefore = new SimpleDateFormat("yyyy/MM/dd").format(c.getTime());
		return dayBefore;
	}


	public static void main(String[] args) {
		System.out.println(getSpecifiedDayBefore("2019/05/17"));
	}

}
