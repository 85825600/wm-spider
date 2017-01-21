/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pageprocessor.GithubRepoAnnotationPageProcessor.java
 *
 * @author andy
 * @date 2016年11月15日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.pageprocessor;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Github仓库PageProcessor
 * com.bonc.spider.pageprocessor.GithubRepoAnnotationPageProcessor.java
 * 
 * @author andy
 * @date 2016年11月15日
 *
 * @since 0.0.1
 */
@TargetUrl("https://github\\.com/\\w+/\\w+")
@HelpUrl("https://github\\.com/\\w+")
public class GithubRepoAnnotationPageProcessor {
	
	@ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
	private String name;
	
	@ExtractByUrl("https://github\\.com/(\\w+)/.*")
	private String author;
	
	@ExtractBy("//div[@id='readme']/tidyText()")
	private String readme;

}
