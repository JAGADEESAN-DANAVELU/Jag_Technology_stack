package com.jpmc.trade.stock.client.testUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jpmc.trade.stock.client.model.Stock;
import com.jpmc.trade.stock.client.model.StockSymbol;
import com.jpmc.trade.stock.client.model.Trade;
import com.jpmc.trade.stock.client.model.TradeOptions;
import com.jpmc.trade.stock.client.spring.service.TradeStockClientSpringContextLoaderService;
import com.jpmc.trade.stock.client.utils.DatesUtils;

public class JPMCStockServiceTestUtil {
	private static final String TEA_BEAN_NAME = "TEA-Stock";
	private static final String POP_BEAN_NAME = "POP-Stock";
	private static final String ALE_BEAN_NAME = "ALE-Stock";
	private static final String GIN_BEAN_NAME = "GIN-Stock";
	private static final String JOE_BEAN_NAME = "JOE-Stock";
	
	public static Stock getStockDetails(StockSymbol stockSymbol) {
		Stock stock = new Stock();
		
		if(StockSymbol.TEA.equals(stockSymbol)) {
			stock = TradeStockClientSpringContextLoaderService.INSTANCE.getBean(TEA_BEAN_NAME, Stock.class);
		} else if(StockSymbol.POP.equals(stockSymbol)) {
			stock = TradeStockClientSpringContextLoaderService.INSTANCE.getBean(POP_BEAN_NAME, Stock.class);
		} else if(StockSymbol.ALE.equals(stockSymbol)) {
			stock = TradeStockClientSpringContextLoaderService.INSTANCE.getBean(ALE_BEAN_NAME, Stock.class);
		} else if(StockSymbol.GIN.equals(stockSymbol)) {
			stock = TradeStockClientSpringContextLoaderService.INSTANCE.getBean(GIN_BEAN_NAME, Stock.class);
		} else if(StockSymbol.JOE.equals(stockSymbol)) {
			stock = TradeStockClientSpringContextLoaderService.INSTANCE.getBean(JOE_BEAN_NAME, Stock.class);
		}
		
		return stock;
	}
	
	public static ArrayList<Trade> getTradeList() {
		ArrayList<Trade> tradeList = new ArrayList<Trade>();
		tradeList.add(getTradeData(StockSymbol.TEA));
		tradeList.add(getTradeData(StockSymbol.POP));
		tradeList.add(getTradeData(StockSymbol.ALE));
		tradeList.add(getTradeData(StockSymbol.GIN));
		tradeList.add(getTradeData(StockSymbol.JOE));
		return tradeList;
	}
	
	private static Trade getTradeData(StockSymbol tradeStockSymbol) {
		Trade tradeDetails = null;
		DatesUtils datesUtils = TradeStockClientSpringContextLoaderService.INSTANCE.getBean("datesUtils", DatesUtils.class);
		if(StockSymbol.TEA.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(datesUtils.getCurrentDateTimePlusOrMinusMinutes(-30), getStockDetails(StockSymbol.TEA), TradeOptions.SELL, 20, 12.23);
		} else if(StockSymbol.POP.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(datesUtils.getCurrentDateTimePlusOrMinusMinutes(-28), getStockDetails(StockSymbol.POP), TradeOptions.BUY, 80, 7.56);
		} else if(StockSymbol.ALE.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(datesUtils.getCurrentDateTimePlusOrMinusMinutes(-26), getStockDetails(StockSymbol.ALE), TradeOptions.BUY, 450, 10.20);
		} else if(StockSymbol.GIN.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(datesUtils.getCurrentDateTimePlusOrMinusMinutes(-26), getStockDetails(StockSymbol.GIN), TradeOptions.SELL, 50, 6.20);
		} else if(StockSymbol.JOE.equals(tradeStockSymbol)) {
			tradeDetails = new Trade(datesUtils.getCurrentDateTimePlusOrMinusMinutes(-30), getStockDetails(StockSymbol.JOE), TradeOptions.SELL, 214, 10.45);
		} 
		return tradeDetails;
	}
	
	public static List<String> getStockSymbols() {
		String[] stockSymbols = {"TEA", "POP", "ALE", "GIN", "JOE"};
		return Arrays.asList(stockSymbols);
	}
}
