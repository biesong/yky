package com.yky.web.entity;

public class Report {
	private String[] xData;
	private String[] mData;
	public String[] getmData() {
		return mData;
	}
	public void setmData(String[] mData) {
		this.mData = mData;
	}
	private double[] yData ;
	private int[]zData;
	public int[] getzData() {
		return zData;
	}
	public void setzData(int[] zData) {
		this.zData = zData;
	}
	public String[] getxData() {
		return xData;
	}
	public void setxData(String[] xData) {
		this.xData = xData;
	}
	public double[] getyData() {
		return yData;
	}
	public void setyData(double[] yData) {
		this.yData = yData;
	}
}
