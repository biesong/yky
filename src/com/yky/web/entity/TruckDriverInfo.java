package com.yky.web.entity;

public class TruckDriverInfo {
	private String fName;
	private String zName;
	private String sName;
	private double OrderPriceAmount;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getzName() {
		return zName;
	}
	public void setzName(String zName) {
		this.zName = zName;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public double getOrderPriceAmount() {
		return OrderPriceAmount;
	}
	public void setOrderPriceAmount(double orderPriceAmount) {
		OrderPriceAmount = orderPriceAmount;
	}
	public double getCargoDamageAmount() {
		return CargoDamageAmount;
	}
	public void setCargoDamageAmount(double cargoDamageAmount) {
		CargoDamageAmount = cargoDamageAmount;
	}
	public double getOrderFillingPrice() {
		return OrderFillingPrice;
	}
	public void setOrderFillingPrice(double orderFillingPrice) {
		OrderFillingPrice = orderFillingPrice;
	}
	public double getTaxRate() {
		return TaxRate;
	}
	public void setTaxRate(double taxRate) {
		TaxRate = taxRate;
	}
	public String getAddTime() {
		return AddTime;
	}
	public void setAddTime(String addTime) {
		AddTime = addTime;
	}
	private double CargoDamageAmount;
	private double OrderFillingPrice;
    private double TaxRate;
    private String AddTime;
}
