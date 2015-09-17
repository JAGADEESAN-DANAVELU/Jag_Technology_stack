package com.jpmc.trade.stock.client.factory;

import com.jpmc.trade.stock.client.service.TradeStockClientService;

/**
 * Singleton Factory Interface is used to load Stock Service client
 *
 */
public interface TradeStockClientFactory {
	public TradeStockClientFactory INSTANCE = TradeStockClientFactoryImpl.getInstance();
	public TradeStockClientService getTradeStockClientService();
}
