/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pojo.sohutv.PlayPojo.java
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
 * 播放页Pojo
 * com.bonc.spider.pojo.sohutv.PlayPojo.java
 * 
 * @author andy
 * @date 2016年11月21日
 *
 * @since 0.0.1
 */
public class PlayPojo {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * url
	 */
	private String url;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 别名
	 */
	private String alias;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 标签
	 */
	private Collection<String> labels;
	
	/**
	 * 点赞
	 */
	private String upNum;
	
	/**
	 * 踩
	 */
	private String downNum;
	
	/**
	 * 专辑今日播放量
	 */
	private String albumTodayPlay;
	
	/**
	 * 专辑总播放量
	 */
	private String albumTotalPlay;
	
	/**
	 * 本集今日播放量
	 */
	private String todayPlay;
	
	/**
	 * 本集总播放量
	 */
	private String totalPlay;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the upNum
	 */
	public String getUpNum() {
		return upNum;
	}

	/**
	 * @param upNum the upNum to set
	 */
	public void setUpNum(String upNum) {
		this.upNum = upNum;
	}

	/**
	 * @return the downNum
	 */
	public String getDownNum() {
		return downNum;
	}

	/**
	 * @param downNum the downNum to set
	 */
	public void setDownNum(String downNum) {
		this.downNum = downNum;
	}

	/**
	 * @return the albumTodayPlay
	 */
	public String getAlbumTodayPlay() {
		return albumTodayPlay;
	}

	/**
	 * @param albumTodayPlay the albumTodayPlay to set
	 */
	public void setAlbumTodayPlay(String albumTodayPlay) {
		this.albumTodayPlay = albumTodayPlay;
	}

	/**
	 * @return the albumTotalPlay
	 */
	public String getAlbumTotalPlay() {
		return albumTotalPlay;
	}

	/**
	 * @param albumTotalPlay the albumTotalPlay to set
	 */
	public void setAlbumTotalPlay(String albumTotalPlay) {
		this.albumTotalPlay = albumTotalPlay;
	}

	/**
	 * @return the todayPlay
	 */
	public String getTodayPlay() {
		return todayPlay;
	}

	/**
	 * @param todayPlay the todayPlay to set
	 */
	public void setTodayPlay(String todayPlay) {
		this.todayPlay = todayPlay;
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
