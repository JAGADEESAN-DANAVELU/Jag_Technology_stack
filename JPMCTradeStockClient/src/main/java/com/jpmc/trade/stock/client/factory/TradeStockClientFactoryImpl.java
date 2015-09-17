package com.jpmc.trade.stock.client.factory;

import com.jpmc.trade.stock.client.service.TradeStockClientService;
import com.jpmc.trade.stock.client.spring.service.TradeStockClientSpringContextLoaderService;

/**
 * Singleton Factory Implementation class is used to load Stock Service client
 *
 */
public class TradeStockClientFactoryImpl implements TradeStockClientFactory {
	private TradeStockClientFactoryImpl(){
		TradeStockClientSpringContextLoaderService.INSTANCE.getClass();
	}
	
	/**
	 * Bill Pugh's Single to Implementation to achieve multi-concurrency  
	 *
	 */
	private static class TradeStockClientServicesFactoryHolder{
		private static final TradeStockClientFactory INSTANCE = new TradeStockClientFactoryImpl();
	}
	public static TradeStockClientFactory getInstance(){
		return TradeStockClientServicesFactoryHolder.INSTANCE;
	}
	public TradeStockClientService getTradeStockClientService() {
		return TradeStockClientSpringContextLoaderService.INSTANCE.getBean("tradeStockClientService", TradeStockClientService.class);
	}
}
