/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.PropertiesUtil.java
 *
 * @author andy
 * @date 2016年11月20日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取properties文件工具类
 * com.bonc.spider.util.PropertiesUtil.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class PropertiesUtil {
	
	private static final String DEFAULT_FILE = "config.properties";
	
	/**
	 * 系统配置文件缓存propertiesMap
	 */
	private static Map<String, Object> propertiesMap = new HashMap<String, Object>();
	
	static {
		initProperties(DEFAULT_FILE);
	}
	
	/**
	 * 初始化系统配置文件缓存
	 * 
	 * @param file
	 * @author andy
	 */
	private static void initProperties(String file){
		if(propertiesMap.get(file)==null){
			synchronized(PropertiesUtil.class){
				if(propertiesMap.get(file)==null){
					Properties properties = PropertiesFactory.getInsideInstance(file);
					if(properties!=null && !properties.isEmpty()){
						propertiesMap.put(file, properties);
					}
				}
			}
		}
	}
	
	/**
	 * 获取properties实例
	 * 
	 * @param file
	 * @return Properties
	 * @author andy
	 */
	public static Properties getProperties(String file){
		initProperties(file);
		return (Properties)propertiesMap.get(file);
	}
	
	/**
	 * 获取properties实例
	 * 
	 * @return Properties
	 * @author andy
	 */
	public static Properties getProperties(){
		return getProperties(DEFAULT_FILE);
	}
	
	/**
	 * 读取配置项
	 * 
	 * @param file
	 * @param key
	 * @return String
	 * @author andy
	 */
	public static String getProperty(String file, String key){
		String property = "";
		Properties properties = getProperties(file);
		if(properties!=null && !properties.isEmpty()){
			String value = properties.getProperty(key);
			if(value!=null){
				property = value.replace("\"", "");
			}
		}
		return property;
	}
	
	/**
	 * 读取配置项
	 * 
	 * @param key
	 * @return String
	 * @author andy
	 */
	public static String getProperty(String key){
		return getProperty(DEFAULT_FILE, key);
	}
	
}
