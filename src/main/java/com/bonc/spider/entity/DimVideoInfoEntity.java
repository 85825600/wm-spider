/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.entity.DimVideoInfoEntity.java
 *
 * @author andy
 * @date 2016年11月16日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.alibaba.fastjson.JSONObject;

/**
 * 视频信息Entity
 * com.bonc.spider.entity.DimVideoInfoEntity.java
 * 
 * @author andy
 * @date 2016年11月16日
 *
 * @since 0.0.1
 */
public class DimVideoInfoEntity {
	
	/**
	 * 视频id
	 */
	private String id;
	
	/**
	 * 网站编码
	 */
	private String prodDm;
	
	/**
	 * 网站名称
	 */
	private String prodMc;
	
	/**
	 * 分类编码
	 */
	private String typeDm;
	
	/**
	 * 分类名称
	 */
	private String typeMc;
	
	/**
	 * 视频名称
	 */
	private String name;
	
	/**
	 * 视频url
	 */
	private String url;

	/**
	 * 点击量
	 */
	private String clicknum;
	
	/**
	 * 点赞数
	 */
	private String uptimes;
	
	/**
	 * 踩数
	 */
	private String downtimes;
	
	/**
	 * 总播放时长
	 */
	private String totaltime;
	
	/**
	 * 视频信息
	 */
	private String info;
	
	/**
	 * 视频导航
	 */
	private String label;
	
	/**
	 * 插入日期
	 */
	private String insertDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());;

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
	 * @return the prodDm
	 */
	public String getProdDm() {
		return prodDm;
	}

	/**
	 * @param prodDm the prodDm to set
	 */
	public void setProdDm(String prodDm) {
		this.prodDm = prodDm;
	}

	/**
	 * @return the prodMc
	 */
	public String getProdMc() {
		return prodMc;
	}

	/**
	 * @param prodMc the prodMc to set
	 */
	public void setProdMc(String prodMc) {
		this.prodMc = prodMc;
	}

	/**
	 * @return the typeDm
	 */
	public String getTypeDm() {
		return typeDm;
	}

	/**
	 * @param typeDm the typeDm to set
	 */
	public void setTypeDm(String typeDm) {
		this.typeDm = typeDm;
	}

	/**
	 * @return the typeMc
	 */
	public String getTypeMc() {
		return typeMc;
	}

	/**
	 * @param typeMc the typeMc to set
	 */
	public void setTypeMc(String typeMc) {
		this.typeMc = typeMc;
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
	 * @return the clicknum
	 */
	public String getClicknum() {
		return clicknum;
	}

	/**
	 * @param clicknum the clicknum to set
	 */
	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}

	/**
	 * @return the uptimes
	 */
	public String getUptimes() {
		return uptimes;
	}

	/**
	 * @param uptimes the uptimes to set
	 */
	public void setUptimes(String uptimes) {
		this.uptimes = uptimes;
	}

	/**
	 * @return the downtimes
	 */
	public String getDowntimes() {
		return downtimes;
	}

	/**
	 * @param downtimes the downtimes to set
	 */
	public void setDowntimes(String downtimes) {
		this.downtimes = downtimes;
	}

	/**
	 * @return the totaltime
	 */
	public String getTotaltime() {
		return totaltime;
	}

	/**
	 * @param totaltime the totaltime to set
	 */
	public void setTotaltime(String totaltime) {
		this.totaltime = totaltime;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the insertDate
	 */
	public String getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("prodDm", prodDm);
		json.put("prodMc", prodMc);
		json.put("typeDm", typeDm);
		json.put("typeMc", typeMc);
		json.put("name", name);
		json.put("url", url);
		json.put("clicknum", clicknum);
		json.put("uptimes", uptimes);
		json.put("downtimes", downtimes);
		json.put("totaltime", totaltime);
		json.put("info", info);
		json.put("label", label);
		json.put("insertDate", insertDate);
		return "DimVideoInfoEntity: "+json.toString();
	}
	
}
