package com.jpmc.trade.stock.client.local.manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.jpmc.trade.stock.client.model.Stock;
import com.jpmc.trade.stock.client.model.Trade;

public interface TradeStockClientLocalEntityManager {
	public boolean recordTrade(Trade trade) throws Exception;
	public ArrayList<Trade> getTrades();
	public Stock getStockBySymbol(String stockSymbol);
	public HashMap<String, Stock> getStocks();
}
