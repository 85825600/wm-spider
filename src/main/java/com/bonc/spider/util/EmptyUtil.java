/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.EmptyUtil.java
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 空值校验工具类
 * com.bonc.spider.util.EmptyUtil.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class EmptyUtil {
	
	/**
	 * 是否空值
	 * 
	 * @param obj 需要校验的对象
	 * @return Boolean
	 */
	public static Boolean isEmpty(Object obj) {
		Boolean result = false;
		if (obj == null) {
			result = true;
		} else if(obj instanceof String) {
			result = "".equals(((String)obj).trim());
		} else if(obj instanceof Collection) {
			result = ((Collection<?>)obj).isEmpty();
		} else if(obj instanceof Map) {
			result = ((Map<?, ?>)obj).isEmpty();
		} else if(obj.getClass().getSimpleName().endsWith("[]")) {
			List<Object> list = Arrays.asList(new Object[] { obj });
			Object[] objs = (Object[])list.get(0);
			result = objs.length == 0;
		}
		return result;
	}
	
	/**
	 * 是否非空
	 * 
	 * @param obj 需要校验的对象
	 * @return Boolean
	 */
	public static Boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

}
