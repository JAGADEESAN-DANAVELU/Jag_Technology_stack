package com.jpmc.trade.stock.client.service;

import com.jpmc.trade.stock.client.model.Trade;

public interface TradeStockClientService {
	public double calculateDividendYield(String stockSymbol) throws Exception;
	public double calculatePERatio(String stockSymbol) throws Exception;
	public boolean recordTrade(Trade trade) throws Exception;
	public double calculateStockPrice(String stockSymbol) throws Exception;
	public double calculateGBCEAllShareIndex() throws Exception;
}
