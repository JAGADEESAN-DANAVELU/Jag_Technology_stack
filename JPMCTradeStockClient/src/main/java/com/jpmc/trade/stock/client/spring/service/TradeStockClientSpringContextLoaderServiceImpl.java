package com.jpmc.trade.stock.client.spring.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Context loader to load the Stock details in Context
 *
 */
public class TradeStockClientSpringContextLoaderServiceImpl implements TradeStockClientSpringContextLoaderService {
	private static final String SPRING_CONTEXT_LOADER = "classpath*:*tradeStock-*-SpringService-Context.xml";

	private AbstractApplicationContext springContext = null;
	private TradeStockClientSpringContextLoaderServiceImpl(){
		try {
			springContext = new ClassPathXmlApplicationContext(SPRING_CONTEXT_LOADER);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			if(null != springContext) {
				springContext.registerShutdownHook();
			}
		}
	}
	
	private static class TradeStockClientSpringContextLoaderServiceHolder{
		private static final TradeStockClientSpringContextLoaderService INSTANCE = new TradeStockClientSpringContextLoaderServiceImpl();
	}
	public static TradeStockClientSpringContextLoaderService getInstance(){
		return TradeStockClientSpringContextLoaderServiceHolder.INSTANCE;
	}	
	public <T> T getBean(String beanName, Class<T> objectClass) {
		return springContext.getBean(beanName, objectClass);
	}
}
