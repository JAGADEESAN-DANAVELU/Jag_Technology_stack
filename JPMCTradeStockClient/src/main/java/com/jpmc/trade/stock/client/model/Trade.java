package com.jpmc.trade.stock.client.model;

import java.util.Date;

public class Trade {
	private Date timeStamp = null;
	private Stock stock = null;
	private TradeOptions tradeOptions = TradeOptions.BUY;
	private int sharesQuantity = 0;
	private double price = 0.0;

	public Trade(){
	}
	
	public Trade(Date timeStamp, Stock stock, TradeOptions tradeOptions, int sharesQuantity, double price) {
		this.timeStamp = timeStamp;
		this.stock = stock;
		this.tradeOptions = tradeOptions;
		this.sharesQuantity = sharesQuantity;
		this.price = price;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public TradeOptions getTradeIndicator() {
		return tradeOptions;
	}

	public void setTradeIndicator(TradeOptions tradeOptions) {
		this.tradeOptions = tradeOptions;
	}

	public int getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
