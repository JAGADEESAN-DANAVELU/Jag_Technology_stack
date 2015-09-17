package com.jpmc.trade.stock.client.testSuite;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.trade.stock.client.factory.TradeStockClientFactory;
import com.jpmc.trade.stock.client.local.manager.TradeStockClientLocalEntityManager;
import com.jpmc.trade.stock.client.model.Trade;
import com.jpmc.trade.stock.client.service.TradeStockClientService;
import com.jpmc.trade.stock.client.spring.service.TradeStockClientSpringContextLoaderService;
import com.jpmc.trade.stock.client.testUtils.JPMCStockServiceTestUtil;

public class TradeStockClientFactoryTest {

	private TradeStockClientFactory factoryInstance;
	private TradeStockClientService tradeStockClientService;
	private TradeStockClientLocalEntityManager tradeStockClientLocalEntityManager;
	private TradeStockClientSpringContextLoaderService tradeStockClientSpringContextLoaderService;
	private List<String> stockSymbols;
	
	@Before
	public void setup() {
		factoryInstance = TradeStockClientFactory.INSTANCE;
		tradeStockClientService = factoryInstance.getTradeStockClientService();
		tradeStockClientSpringContextLoaderService = TradeStockClientSpringContextLoaderService.INSTANCE;
		tradeStockClientLocalEntityManager = tradeStockClientSpringContextLoaderService.getBean("tradeStockClientLocalEntityManager", TradeStockClientLocalEntityManager.class);
		stockSymbols = JPMCStockServiceTestUtil.getStockSymbols();
	}
	
	@Test
	public void isStockServicesASingleton(){
		TradeStockClientService tradeStockClientService = factoryInstance.getTradeStockClientService();
		Assert.assertNotNull(tradeStockClientService);
	}

	@Test
	public void recordATradeTest() throws Exception{
		ArrayList<Trade> tradeList = 	JPMCStockServiceTestUtil.getTradeList();
		Assert.assertNotNull(tradeList);
		int tradesNumber = tradeStockClientLocalEntityManager.getTrades().size();
		Assert.assertEquals(tradesNumber, 0);
		for(Trade trade: tradeList){
			boolean result = tradeStockClientService.recordTrade(trade);
			Assert.assertTrue(result);
		}
		tradesNumber = tradeStockClientLocalEntityManager.getTrades().size();
		Assert.assertEquals(tradesNumber, tradeList.size());
	}	

	@Test
	public void calculateDividendYieldTest() throws Exception{
		for(String stockSymbol: stockSymbols){
			double dividendYield = tradeStockClientService.calculateDividendYield(stockSymbol);
			Assert.assertTrue(dividendYield >= 0.0);
		}
	}

	@Test
	public void calculatePERatioTest() throws Exception{
		for(String stockSymbol: stockSymbols){
			double peRatio = tradeStockClientService.calculatePERatio(stockSymbol);
			Assert.assertTrue(peRatio >= 0.0);
		}
	}

	@Test
	public void calculateStockPriceTest() throws Exception{
		for(String stockSymbol: stockSymbols){
			double stockPrice = tradeStockClientService.calculateStockPrice(stockSymbol);
			Assert.assertTrue(stockPrice >= 0.0);
		}
	}

	@Test
	public void calculateGBCEAllShareIndexTest() throws Exception{
		double GBCEAllShareIndex = tradeStockClientService.calculateGBCEAllShareIndex();
		Assert.assertTrue(GBCEAllShareIndex >= 0.0);
	}

}
