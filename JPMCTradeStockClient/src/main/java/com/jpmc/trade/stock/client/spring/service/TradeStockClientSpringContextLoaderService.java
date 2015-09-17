package com.jpmc.trade.stock.client.spring.service;

public interface TradeStockClientSpringContextLoaderService{
	public TradeStockClientSpringContextLoaderService INSTANCE = TradeStockClientSpringContextLoaderServiceImpl.getInstance();	
	public <T extends Object> T getBean(String beanName, Class<T> objectClass);
}
