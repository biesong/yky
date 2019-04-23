package com.yky.web.entity;

public class FillingStationOrderInfo {
	private double OrderAmount;
	private String PayTime;
	private String FillingStationName;
	private String OrderSN;
	private String PayHour;
	private String QPayTime;
	private String QPayHour;
	public String getPayHour() {
		return PayHour;
	}
	public String getQPayTime() {
		return QPayTime;
	}
	public void setQPayTime(String qPayTime) {
		QPayTime = qPayTime;
	}
	public String getQPayHour() {
		return QPayHour;
	}
	public void setQPayHour(String qPayHour) {
		QPayHour = qPayHour;
	}
	public void setPayHour(String payHour) {
		PayHour = payHour;
	}
	public String getOrderSN() {
		return OrderSN;
	}
	public void setOrderSN(String orderSN) {
		OrderSN = orderSN;
	}
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
