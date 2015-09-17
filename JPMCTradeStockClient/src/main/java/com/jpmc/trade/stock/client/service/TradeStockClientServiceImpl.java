package com.jpmc.trade.stock.client.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.math3.stat.StatUtils;

import com.jpmc.trade.stock.client.local.manager.TradeStockClientLocalEntityManager;
import com.jpmc.trade.stock.client.model.Stock;
import com.jpmc.trade.stock.client.model.Trade;

/**
 * Service class used to implement the Trade and Stock calculation functions.
 *
 */
public class TradeStockClientServiceImpl implements TradeStockClientService {

	private TradeStockClientLocalEntityManager tradeStockClientLocalEntityManager;

	public TradeStockClientServiceImpl(){
	}
	public TradeStockClientLocalEntityManager getTradeStockClientLocalEntityManager() {
		return tradeStockClientLocalEntityManager;
	}

	public void setTradeStockClientLocalEntityManager(TradeStockClientLocalEntityManager tradeStockClientLocalEntityManager) {
		this.tradeStockClientLocalEntityManager = tradeStockClientLocalEntityManager;
	}

	public double calculateDividendYield(String stockSymbol) throws Exception{
		double dividendYield = -1.0;

		try{
			Stock stock = tradeStockClientLocalEntityManager.getStockBySymbol(stockSymbol);
			dividendYield = stock.getDividendYield();
		}catch(Exception exception){
			throw new Exception("Error calculating Dividend Yield for the stock symbol: "+stockSymbol+".", exception);
		}
		return dividendYield;
	}

	public double calculatePERatio(String stockSymbol) throws Exception{
		double peRatio = -1.0;
		try{
			Stock stock = tradeStockClientLocalEntityManager.getStockBySymbol(stockSymbol);
			peRatio = stock.getPeRatio();
		}catch(Exception exception){
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
		}
		return peRatio;
	}

	public boolean recordTrade(Trade trade) throws Exception{
		boolean recordResult = false;
		try{
			recordResult = tradeStockClientLocalEntityManager.recordTrade(trade);
			if(recordResult){
				trade.getStock().setTickerPrice(trade.getPrice());
			}
		}catch(Exception exception){
			throw new Exception("Error when trying to record a trade.", exception);
		}
		return recordResult;
	}

	/**
	 * A Predicate is the object equivalent of an if statement. 
	 * It uses the input object to return a true or false value, and is often used in validation or filtering.
	 *
	 */
	private class StockPredicate implements Predicate{
		private String stockSymbol = "";
		private Calendar dateRange = null;
		public StockPredicate(String stockSymbol, int minutesRange){
			this.stockSymbol = stockSymbol;
			if( minutesRange > 0 ){
				dateRange = Calendar.getInstance();
				dateRange.add(Calendar.MINUTE, -minutesRange);
			}
		}
		public boolean evaluate(Object tradeObject) {
			Trade trade = (Trade) tradeObject;
			boolean shouldBeInclude = trade.getStock().getStockSymbol().equals(stockSymbol);
			if(shouldBeInclude && dateRange != null){
				shouldBeInclude = dateRange.getTime().compareTo(trade.getTimeStamp())<=0;
			}
			return shouldBeInclude;
		}
	}

	private double calculateStockPriceinRange(String stockSymbol, int minutesRange) throws Exception{
		double stockPrice = 0.0;
		@SuppressWarnings("unchecked")
		Collection<Trade> trades = CollectionUtils.select(tradeStockClientLocalEntityManager.getTrades(), new StockPredicate(stockSymbol, minutesRange));
		double shareQuantityAcum = 0.0;
		double tradePriceAcum = 0.0;
		for(Trade trade : trades){
			tradePriceAcum += (trade.getPrice() * trade.getSharesQuantity());
			shareQuantityAcum += trade.getSharesQuantity();
		}
		if(shareQuantityAcum > 0.0){
			stockPrice = tradePriceAcum / shareQuantityAcum;	
		}
		return stockPrice;
	}

	public double calculateStockPrice(String stockSymbol) throws Exception{
		double stockPrice = 0.0;
		try{
			stockPrice = calculateStockPriceinRange(stockSymbol, 15);
		}catch(Exception exception){
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
		}
		return stockPrice;
	}

	public double calculateGBCEAllShareIndex() throws Exception{
		double allShareIndex = 0.0;
		HashMap<String, Stock> stocks = tradeStockClientLocalEntityManager.getStocks();
		ArrayList<Double> stockPrices = new ArrayList<Double>();
		for(String stockSymbol: stocks.keySet() ){
			double stockPrice = calculateStockPriceinRange(stockSymbol, 0);
			if(stockPrice > 0){
				stockPrices.add(stockPrice);
			}
		}
		if(stockPrices.size()>=1){
			double[] stockPricesArray = new double[stockPrices.size()];
			for(int i=0; i<=(stockPrices.size()-1); i++){
				stockPricesArray[i] = stockPrices.get(i).doubleValue();
			}
			allShareIndex = StatUtils.geometricMean(stockPricesArray);
		}
		return allShareIndex;
	}

	public int getTradesNumber() throws Exception {
		return tradeStockClientLocalEntityManager.getTrades().size();
	}

}
