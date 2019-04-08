package com.yky.web.entity;

public class FillingStationOrderInfo {
	private double OrderAmount;
	private String PayTime;
	private String FillingStationName;
	public String getFillingStationName() {
		return FillingStationName;
	}
	public void setFillingStationName(String fillingStationName) {
		FillingStationName = fillingStationName;
	}
	public double getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		OrderAmount = orderAmount;
	}
	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
}
