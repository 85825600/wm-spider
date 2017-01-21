/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.core.Proxy.java
 *
 * @author andy
 * @date 2016年11月30日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bonc.spider.util.PropertiesFactory;

/**
 * 代理 com.bonc.spider.core.Proxy.java
 * 
 * @author andy
 * @date 2016年11月30日
 *
 * @since 0.0.1
 */
public class Proxy {

	private static final String DEFAULT_FILE = "proxy.txt";

	private static List<String[]> proxyList = new ArrayList<String[]>();

	static {
		initProxyList(DEFAULT_FILE);
	}

	/**
	 * 初始化代理列表
	 * 
	 * @param file
	 * @author andy
	 */
	private static void initProxyList(String filePath) {
		if (proxyList.isEmpty()) {
			synchronized (Proxy.class) {
				if (proxyList.isEmpty()) {
					BufferedReader br = null;
					InputStreamReader isr = null;
					try {
						isr = new InputStreamReader(
								PropertiesFactory.class.getClassLoader().getResourceAsStream(filePath));
						br = new BufferedReader(isr);
						String line = null;
						while ((line = br.readLine()) != null) {
							if (!line.startsWith("##")) {
								System.out.println("代理服务器[IP:PORT]: [" + line + "]");
								String[] proxy = line.split(":");
								proxyList.add(proxy);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (isr != null) {
								isr.close();
							}
							if (br != null) {
								br.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	/**
	 * 获取代理列表
	 * 
	 * @param filePath
	 * @return List<String[]>
	 * @author andy
	 */
	public static List<String[]> getProxyList() {
		return proxyList;
	}

}
