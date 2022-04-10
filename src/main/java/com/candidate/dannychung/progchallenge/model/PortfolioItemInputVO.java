package com.candidate.dannychung.progchallenge.model;

public class PortfolioItemInputVO {
	
	private String ticker;
	private String stockName;
	private int type;
	private double longVolume;
	private double shortVolume;
	private double updatedPrice;
	private double nav;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getLongVolume() {
		return longVolume;
	}
	public void setLongVolume(double longVolume) {
		this.longVolume = longVolume;
	}
	public double getShortVolume() {
		return shortVolume;
	}
	public void setShortVolume(double shortVolume) {
		this.shortVolume = shortVolume;
	}
	
	public double getUpdatedPrice() {
		return updatedPrice;
	}
	public void setUpdatedPrice(double updatedPrice) {
		this.updatedPrice = updatedPrice;
	}
	
	public double getNav() {
		return nav;
	}
	public void setNav(double nav) {
		this.nav = nav;
	}
	@Override
	public String toString() {
		return "PortfolioItemInputVO [ticker=" + ticker + ", stockName=" + stockName + ", type=" + type
				+ ", longVolume=" + longVolume + ", shortVolume=" + shortVolume + ", updatedPrice=" + updatedPrice
				+ ", nav=" + nav + "]";
	}
	
	
	

}
