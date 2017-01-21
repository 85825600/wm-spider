/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.JavaScript.java
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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * JavaScript工具类
 * com.bonc.spider.util.JavaScript.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class JavaScript {
	
	/**
	 * 读取工程内部properties文件生成Properties对象
	 * 
	 * @param file
	 * @return Properties
	 * @author andy
	 */
	public static Object exec(String javascript) throws ScriptException{
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
		return scriptEngine.eval(javascript);
	}

}
