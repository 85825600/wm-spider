/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.core.SpringApplicationContext.java
 *
 * @author andy
 * @date 2016年11月17日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sring上下文工厂
 * com.bonc.spider.core.SpringApplicationContext.java
 * 
 * @author andy
 * @date 2016年11月17日
 *
 * @since 0.0.1
 */
public class SpringApplicationContext {
	
	/**
	 * 应用上下文配置路径
	 */
	private static final String APPLICATION_CONTEXT_PATH = "classpath*:/spring/applicationContext*.xml";
	
	/**
	 * 应用上下文
	 */
	private static ApplicationContext applicationContext = null;
	
	static {
		initApplicationContext();
	}
	
	/**
	 * 初始化应用上下文
	 * 
	 * @author andy
	 * @date 2016年11月17日
	 *
	 * @since 0.0.1
	 */
	private static void initApplicationContext(){
		if(applicationContext==null){
			synchronized(ApplicationContext.class){
				if(applicationContext==null){
					applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH);
				}
			}
		}
	}
	
	/**
	 * 通过类获取应用上下文中的类实例
	 * 
	 * @author andy
	 * @date 2016年11月17日
	 *
	 * @since 0.0.1
	 */
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}

}
