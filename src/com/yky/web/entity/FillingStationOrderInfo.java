package com.yky.web.entity;

public class FillingStationOrderInfo {
	private double OrderAmount;
	private String PayTime;
	private String FillingStationName;
	private String OrderSN;
	private String PayHour;
	private String QPayTime;
	private String QPayHour;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FillingStationName == null) ? 0 : FillingStationName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FillingStationOrderInfo other = (FillingStationOrderInfo) obj;
		if (FillingStationName == null) {
			if (other.FillingStationName != null)
				return false;
		} else if (!FillingStationName.equals(other.FillingStationName))
			return false;
		return true;
	}
	private String LoginName;
	private String Discount;
	private String PayType;
	public String getPayType() {
		return PayType;
	}
	public void setPayType(String payType) {
		PayType = payType;
	}
	public String getDiscount() {
		return Discount;
	}
	public void setDiscount(String discount) {
		Discount = discount;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
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
