package com.candidate.dannychung.progchallenge.model;

public class PortfolioItemConstantVO {
	private String ticker;
	private String stockName;
	private int type;
	private float expectedReturn;
	private float annualizedSd;
	private String relatedCommonStockTicker;
	private int timeToMaturity;
	private double longVolume;
	private double shortVolume;
	private double currentPrice;
	private double strikePrice;
	private double callOptionPrice;
	private double putOptionPrice;
	
	
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
	public float getExpectedReturn() {
		return expectedReturn;
	}
	public void setExpectedReturn(float expectedReturn) {
		this.expectedReturn = expectedReturn;
	}
	public float getAnnualizedSd() {
		return annualizedSd;
	}
	public void setAnnualizedSd(float annualizedSd) {
		this.annualizedSd = annualizedSd;
	}
	public String getRelatedCommonStockTicker() {
		return relatedCommonStockTicker;
	}
	public void setRelatedCommonStockTicker(String relatedCommonStockTicker) {
		this.relatedCommonStockTicker = relatedCommonStockTicker;
	}
	public int getTimeToMaturity() {
		return timeToMaturity;
	}
	public void setTimeToMaturity(int timeToMaturity) {
		this.timeToMaturity = timeToMaturity;
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
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public double getCallOptionPrice() {
		return callOptionPrice;
	}
	public void setCallOptionPrice(double callOptionPrice) {
		this.callOptionPrice = callOptionPrice;
	}
	public double getPutOptionPrice() {
		return putOptionPrice;
	}
	public void setPutOptionPrice(double putOptionPrice) {
		this.putOptionPrice = putOptionPrice;
	}
	@Override
	public String toString() {
		return "PortfolioItemConstantVO [ticker=" + ticker + ", stockName=" + stockName + ", type=" + type
				+ ", expectedReturn=" + expectedReturn + ", annualizedSd=" + annualizedSd
				+ ", relatedCommonStockTicker=" + relatedCommonStockTicker + ", timeToMaturity=" + timeToMaturity
				+ ", longVolume=" + longVolume + ", shortVolume=" + shortVolume + ", currentPrice=" + currentPrice
				+ ", strikePrice=" + strikePrice + ", callOptionPrice=" + callOptionPrice + ", putOptionPrice="
				+ putOptionPrice + "]";
	}

	
}
