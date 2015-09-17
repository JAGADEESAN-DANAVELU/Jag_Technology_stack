package com.jpmc.trade.stock.client.local.manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.jpmc.trade.stock.client.model.Stock;
import com.jpmc.trade.stock.client.model.Trade;

/**
 * Local Session entity class to load and save all stock data and acts like persistence class  
 *
 */
public class TradeStockClientLocalEntityManagerImpl implements TradeStockClientLocalEntityManager {
	private HashMap<String, Stock> stocks = null;
	private ArrayList<Trade> trades = null;
	public TradeStockClientLocalEntityManagerImpl(){
		trades = new ArrayList<Trade>();
		stocks = new HashMap<String, Stock>();
	}

	public HashMap<String, Stock> getStocks() {
		return stocks;
	}
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}
	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	public boolean recordTrade(Trade trade) throws Exception{
		boolean result = false;
		try{
			result = trades.add(trade);
		}catch(Exception exception){
			throw new Exception("An error has occurred recording a trade in the system's backend.", exception);
		}
		return result;
	}

	public int getTradesNumber() {
		return trades.size();
	}
	public Stock getStockBySymbol(String stockSymbol){
		Stock stock = null;
		try{
			if(stockSymbol!=null){
				stock = stocks.get(stockSymbol);
			}
		}catch(Exception exception){
		}
		return stock;
	}
}
