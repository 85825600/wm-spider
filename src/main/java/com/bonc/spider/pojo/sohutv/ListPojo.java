/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pojo.sohutv.ListPojo.java
 *
 * @author andy
 * @date 2016年11月21日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.pojo.sohutv;

import java.util.Collection;

/**
 * 列表页Pojo
 * com.bonc.spider.pojo.sohutv.ListPojo.java
 * 
 * @author andy
 * @date 2016年11月21日
 *
 * @since 0.0.1
 */
public class ListPojo {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 分类
	 */
	private Collection<String> types;
	
	/**
	 * 标签
	 */
	private Collection<String> labels;
	
	/**
	 * 发布时间
	 */
	private String releaseDate;
	
	/**
	 * 周播放量
	 */
	private String weeklyPlay;
	
	/**
	 * 总播放量
	 */
	private String totalPlay;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the types
	 */
	public Collection<String> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(Collection<String> types) {
		this.types = types;
	}

	/**
	 * @return the labels
	 */
	public Collection<String> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(Collection<String> labels) {
		this.labels = labels;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the weeklyPlay
	 */
	public String getWeeklyPlay() {
		return weeklyPlay;
	}

	/**
	 * @param weeklyPlay the weeklyPlay to set
	 */
	public void setWeeklyPlay(String weeklyPlay) {
		this.weeklyPlay = weeklyPlay;
	}

	/**
	 * @return the totalPlay
	 */
	public String getTotalPlay() {
		return totalPlay;
	}

	/**
	 * @param totalPlay the totalPlay to set
	 */
	public void setTotalPlay(String totalPlay) {
		this.totalPlay = totalPlay;
	}

}
