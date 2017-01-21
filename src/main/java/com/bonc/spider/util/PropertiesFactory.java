/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.PropertiesFactory.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 获取properties文件实例工厂类 com.bonc.spider.util.PropertiesFactory.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class PropertiesFactory {

	public static final String DEFAULT_ENCODING = "utf-8";

	/**
	 * 读取工程内部properties文件生成Properties对象
	 * 
	 * @param filePath
	 * @return Properties
	 * @author andy
	 */
	public static Properties getInsideInstance(String filePath) {
		Properties instance = new Properties();
		InputStreamReader isr = new InputStreamReader(
				PropertiesFactory.class.getClassLoader().getResourceAsStream(filePath));
		try {
			instance.load(isr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * 读取properties文件生成Properties对象
	 * 
	 * @param file
	 * @return Properties
	 * @author andy
	 */
	public static Properties getInstance(File file) {
		Properties instance = new Properties();
		if (file.isFile() && file.exists()) {
			InputStreamReader isr = null;
			try {
				isr = new InputStreamReader(new FileInputStream(file), DEFAULT_ENCODING);
				instance.load(isr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (isr != null) {
						isr.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

	/**
	 * 读取工程外部properties文件生成Properties对象
	 * 
	 * @param filePath
	 * @return Properties
	 * @author andy
	 */
	public static Properties getOutsideInstance(String filePath) {
		File file = new File(filePath);
		return getInstance(file);
	}

}
