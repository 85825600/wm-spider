/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pageprocessor.SohuTvAnnotationPageProcessor.java
 *
 * @author andy
 * @date 2016年11月16日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.pageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bonc.spider.core.SpringApplicationContext;
import com.bonc.spider.dao.IDimVideoInfoDao;
import com.bonc.spider.entity.DimVideoInfoEntity;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 搜狐视频PageProcessor
 * com.bonc.spider.pageprocessor.SohuTvAnnotationPageProcessor.java
 * 
 * @author andy
 * @date 2016年11月16日
 *
 * @since 0.0.1
 */
@TargetUrl("http://tv.sohu.com/\\w+/\\w+.shtml")
@HelpUrl("http://so.tv.sohu.com/list\\w+.html")
public class SohuTvAnnotationPageProcessor implements AfterExtractor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * url
	 */
	@ExtractByUrl
	private String url;
	
	/**
	 * 
	 */
	@ExtractBy("//div[@id='crumbsBar']/div/div[1]/h2")
	private String video_name;

	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.model.AfterExtractor#afterProcess(us.codecraft.webmagic.Page)
	 */
	@Override
	public void afterProcess(Page page) {
		DimVideoInfoEntity dimVideoInfoEntity = new DimVideoInfoEntity();
		dimVideoInfoEntity.setId(url);
		dimVideoInfoEntity.setProdDm(url);
		dimVideoInfoEntity.setProdMc(url);
		dimVideoInfoEntity.setTypeDm(url);
		dimVideoInfoEntity.setTypeMc(url);
		dimVideoInfoEntity.setName(url);
		dimVideoInfoEntity.setUrl(url);
		dimVideoInfoEntity.setClicknum(url);
		dimVideoInfoEntity.setUptimes(url);
		dimVideoInfoEntity.setDowntimes(url);;
		dimVideoInfoEntity.setTotaltime(url);
		dimVideoInfoEntity.setInfo(url);
		dimVideoInfoEntity.setLabel(url);
		dimVideoInfoEntity.setInsertDate(url);
		logger.info(dimVideoInfoEntity.toString());
		SpringApplicationContext.getBean(IDimVideoInfoDao.class).insertBean(dimVideoInfoEntity);
	}

}
