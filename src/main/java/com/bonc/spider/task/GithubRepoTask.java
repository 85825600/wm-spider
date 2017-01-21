/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.task.GithubRepoTask.java
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

import com.bonc.spider.pageprocessor.GithubRepoAnnotationPageProcessor;
import com.bonc.spider.pageprocessor.GithubRepoPageProcessor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

/**
 * Github仓库爬取任务
 * com.bonc.spider.task.GithubRepoTask.java
 * 
 * @author andy
 * @date 2016年11月18日
 *
 * @since 0.0.1
 */
@Component
public class GithubRepoTask implements ITask {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String sourceUrl = "https://github.com/code4craft";
	
	@Resource
	GithubRepoPageProcessor githubRepoPageProcessor;

	/**
	 * 爬取
	 * 
	 * @author andy
	 * @date 2016年11月18日
	 * 
	 * @since 0.0.1
	 */
	public void crawl() throws JMException {
		logger.info("Github仓库爬取任务开始...");
		Spider spider = Spider.create(githubRepoPageProcessor).addUrl(sourceUrl).thread(5);
		SpiderMonitor.instance().register(spider);
		spider.start();
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
		logger.info("注解Github仓库爬取任务开始...");
		Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
		Spider spider = OOSpider.create(site, GithubRepoAnnotationPageProcessor.class).addUrl(sourceUrl).thread(5);
		SpiderMonitor.instance().register(spider);
		spider.start();
	}
	
	/* (non-Javadoc)
	 * @see com.bonc.spider.task.ITask#start()
	 */
	@Override
	public void start() {
		try {
			crawl();
			crawlWithAnnotation();
		} catch (JMException e) {
			e.printStackTrace();
		}
	}
	
}
