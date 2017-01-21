/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pojo.sohutv.ItemPojo.java
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
 * 条目页Pojo
 * com.bonc.spider.pojo.sohutv.ItemPojo.java
 * 
 * @author andy
 * @date 2016年11月21日
 *
 * @since 0.0.1
 */
public class ItemPojo {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 别名
	 */
	private String alias;
	
	/**
	 * 标签
	 */
	private Collection<String> labels;
	
	/**
	 * 发布时间
	 */
	private String releaseDate;
	
	/**
	 * 地区
	 */
	private String area;
	
	/**
	 * 导演
	 */
	private Collection<String> directors;
	
	/**
	 * 主演
	 */
	private Collection<String> actors;
	
	/**
	 * 总时长
	 */
	private String totalTime;

	/**
	 * 总播放量
	 */
	private String totalPlay;
	
	/**
	 * 今日播放量
	 */
	private String todayPlay;
	
	/**
	 * 评论数
	 */
	private String commentNum;
	
	/**
	 * 评分
	 */
	private String grade;
	
	/**
	 * 评分人数
	 */
	private String gradeNum;
	
	/**
	 * 点赞
	 */
	private String upNum;
	
	/**
	 * 踩
	 */
	private String downNum;

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
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the directors
	 */
	public Collection<String> getDirectors() {
		return directors;
	}

	/**
	 * @param directors the directors to set
	 */
	public void setDirectors(Collection<String> directors) {
		this.directors = directors;
	}

	/**
	 * @return the actors
	 */
	public Collection<String> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(Collection<String> actors) {
		this.actors = actors;
	}
	
	/**
	 * @return the totalTime
	 */
	public String getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
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
	 * @return the commentNum
	 */
	public String getCommentNum() {
		return commentNum;
	}

	/**
	 * @param commentNum the commentNum to set
	 */
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the gradeNum
	 */
	public String getGradeNum() {
		return gradeNum;
	}

	/**
	 * @param gradeNum the gradeNum to set
	 */
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
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
	
}
