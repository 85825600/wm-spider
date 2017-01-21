/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.task.SohuTvTask.java
 *
 * @author andy
 * @date 2016年11月18日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.task;

import javax.annotation.Resource;
import javax.management.JMException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bonc.spider.pageprocessor.SohuTvAnnotationPageProcessor;
import com.bonc.spider.pageprocessor.SohuTvPageProcessor;
import com.bonc.spider.pipeline.DimVideoInfoPipeline;
import com.bonc.spider.util.PropertiesUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 搜狐视频爬取任务
 * com.bonc.spider.task.SohuTvTask.java
 * 
 * @author andy
 * @date 2016年11月18日
 *
 * @since 0.0.1
 */
@Component
public class SohuTvTask implements ITask {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String sourceUrl = "http://so.tv.sohu.com/list_p1101_p2101100_p31000_p4_p5_p6_p77_p8_p9_p10_p11_p12_p13.html";
	
	@Resource
	SohuTvPageProcessor SohuTvPageProcessor;
	
	@Resource
	DimVideoInfoPipeline dimVideoInfoPipeline;
	
	/**
	 * 爬取
	 * 
	 * @author andy
	 * @date 2016年11月18日
	 * 
	 * @since 0.0.1
	 */
	public void crawl() throws JMException {
		logger.info("搜狐视频爬取任务开始...");
		RedisScheduler redisScheduler = new RedisScheduler(new JedisPool(new JedisPoolConfig(), PropertiesUtil.getProperty("redis.ip"), Integer.valueOf(PropertiesUtil.getProperty("redis.port"))));
		Spider spider = Spider.create(SohuTvPageProcessor).addUrl(sourceUrl).setScheduler(redisScheduler).addPipeline(dimVideoInfoPipeline).thread(5);
//		Spider spider = Spider.create(SohuTvPageProcessor).addUrl(sourceUrl).addPipeline(dimVideoInfoPipeline).thread(5);
		SpiderMonitor.instance().register(spider);
		spider.run();
		logger.info("搜狐视频爬取任务结束.");
	}
	
	/**
	 * 爬取
	 * 
	 * @author andy
	 * @date 2016年11月18日
	 * 
	 * @since 0.0.1
	 */
	public void crawlWithAnnotation() throws JMException {
		logger.info("注解搜狐视频爬取任务开始...");
		Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
		RedisScheduler redisScheduler = new RedisScheduler(new JedisPool(new JedisPoolConfig(), PropertiesUtil.getProperty("redis.ip"), Integer.valueOf(PropertiesUtil.getProperty("redis.port"))));
		Spider spider = OOSpider.create(site, SohuTvAnnotationPageProcessor.class).addUrl(sourceUrl).setScheduler(redisScheduler).thread(5);
//		Spider spider = OOSpider.create(site, SohuTvAnnotationPageProcessor.class).addUrl(sourceUrl).thread(5);
		SpiderMonitor.instance().register(spider);
		spider.run();
		logger.info("注解搜狐视频爬取任务结束.");
	}

	/* (non-Javadoc)
	 * @see com.bonc.spider.task.ITask#start()
	 */
	@Override
	public void start() {
		try {
			crawl();
//			crawlWithAnnotation();
		} catch (JMException e) {
			e.printStackTrace();
		}
	}
	
}
