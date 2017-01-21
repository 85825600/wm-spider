/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.FileUtil.java
 *
 * @author andy
 * @date 2016年11月21日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 读取文件工具类
 * com.bonc.spider.util.FileUtil.java
 * 
 * @author andy
 * @date 2016年11月21日
 *
 * @since 0.0.1
 */
public class FileUtil {

	public static final String DEFAULT_ENCODING = "utf-8";

	/**
	 * 读取工程内部文件
	 * 
	 * @param filePath
	 * @return String
	 * @author andy
	 */
	public static String getInsideFile(String filePath) {
		StringBuffer strBuf = new StringBuffer();
		BufferedReader br = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(
					PropertiesFactory.class.getClassLoader().getResourceAsStream(filePath));
			br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				strBuf.append(line+"\n");
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
		return strBuf.toString();
	}
	
	/**
	 * 获取文件内容 
	 * 
	 * @param file
	 * @return String
	 * @author andy
	 */
	public static String getFileContent(File file) {
		StringBuffer strBuf = new StringBuffer();
		if (file.isFile() && file.exists()) {
			BufferedReader br = null;
			InputStreamReader isr = null;
			try {
				isr = new InputStreamReader(new FileInputStream(file), DEFAULT_ENCODING);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					strBuf.append(line+"\n");
				}
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
					if (br != null) {
						br.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strBuf.toString();
	}
	
	/**
	 * 读取工程外部文件
	 * 
	 * @param filePath
	 * @return String
	 * @author andy
	 */
	public static String getOutsideFile(String filePath) {
		File file = new File(filePath);
		return getFileContent(file);
	}

}
