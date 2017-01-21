/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pageprocessor.SohuTvPageProcessor.java
 *
 * @author andy
 * @date 2016年11月17日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.pageprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bonc.spider.core.Proxy;
import com.bonc.spider.entity.DimVideoInfoEntity;
import com.bonc.spider.pojo.sohutv.ItemPojo;
import com.bonc.spider.pojo.sohutv.ListPojo;
import com.bonc.spider.pojo.sohutv.NavPojo;
import com.bonc.spider.pojo.sohutv.PlayPojo;
import com.bonc.spider.util.EmptyUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 搜狐视频PageProcessor
 * com.bonc.spider.pageprocessor.SohuTvPageProcessor.java
 * 
 * @author andy
 * @date 2016年11月17日
 *
 * @since 0.0.1
 */
@Component
public class SohuTvPageProcessor  implements PageProcessor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String LIST_URL = "http://so.tv.sohu.com/list\\w+.html*";
	private static final String ITEM_URL1 = "http://tv.sohu.com/item/\\w+==.html*";
	private static final String ITEM_URL2 = "http://tv.sohu.com/s\\w{4}/\\w+/";
	private static final String PLAY_URL = "http://tv.sohu.com/\\d+/n\\d+.shtml*";

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setHttpProxyPool(Proxy.getProxyList()).setTimeOut(1000*60).setRetryTimes(3).setSleepTime(1000);
//	private Site site = Site.me().setHttpProxy(new HttpHost("117.90.7.25", 9000)).setCharset("utf-8").setTimeOut(1000*60).setRetryTimes(3).setSleepTime(1000);
	
	private void parseListPage(Page page) {
		Html pageHtml = page.getHtml();
		Selectable navSelectable = pageHtml.xpath("//div[@class='wrap']/div[@class='sort-filter area']");
		// 页面导航
		String nav = navSelectable.xpath("//div[@class='sort-nav cfix']/ul[@class='l sn-1']/li[@class='son']/a/text()").toString();
		// 视频类别
		String category = navSelectable.xpath("//div[@class='sort-type']/dl[1]/dd[1]/a[@class='aon']/text()").toString();
		// 视频地区
		String area = navSelectable.xpath("//div[@class='sort-type']/dl[2]/dd[1]/a[@class='aon']/text()").toString();
		if(EmptyUtil.isNotEmpty(nav)
				&& EmptyUtil.isNotEmpty(category)
				&& EmptyUtil.isNotEmpty(area)
				&& !"全部".equals(category)
				&& !"全部".equals(area)){
			NavPojo navPojo = new NavPojo();
			navPojo.setNav(nav);
			navPojo.setCategory(category);
			navPojo.setArea(area);
			List<Selectable> selectableList = pageHtml.xpath("//div[@class='wrap']/div[@class='sort-column area']/div[@class='column-bd cfix']/ul[@class='st-list cfix']/li").nodes();
			logger.info("SohuTvPageProcessor.parseListPage add "+selectableList.size()+" item or play urls");
			for(Selectable selectable : selectableList){
				// 定义如何抽取页面信息，并保存下来
				ListPojo listPojo = new ListPojo();
				listPojo.setName(selectable.xpath("//strong/a/text()").toString());
				listPojo.setTypes(selectable.xpath("//div[@class='list-hover']/p[@class='col3a lh-area']/a/text()").all());
				listPojo.setLabels(selectable.xpath("//div[@class='list-hover']/p[@class='lh-type']/a/text()").all());
				listPojo.setReleaseDate(selectable.xpath("//p[@class='time-fb']/text()").toString());
				listPojo.setWeeklyPlay(selectable.xpath("//p[@class='num-bf']/text()").toString());
				listPojo.setTotalPlay(selectable.xpath("//div[@class='list-hover']/div[@class='lh-mor cfix']/a[@class='acount l']/text()").toString());
				Map<String, Object> extras = new HashMap<String, Object>();
				extras.put("nav", navPojo);
				extras.put("list", listPojo);
				// 从页面发现后续的url地址来抓取（条目页）
				String item_url = selectable.links().regex(ITEM_URL1+"|"+ITEM_URL2).toString();
				if(EmptyUtil.isNotEmpty(item_url)){
					Request request = new Request();
					request.setUrl(item_url);
					extras.put("pageType", "item");
					logger.info("SohuTvPageProcessor.parseListPage: "+JSON.toJSONString(extras));
					request.setExtras(extras);
					page.addTargetRequest(request);
				}
				// 从页面发现后续的url地址来抓取（播放页）
				String play_url = selectable.links().regex(PLAY_URL).toString();
				if(EmptyUtil.isNotEmpty(play_url)){
					Request request = new Request();
					request.setUrl(play_url);
					extras.put("pageType", "play");
					logger.info("SohuTvPageProcessor.parseListPage: "+JSON.toJSONString(extras));
					request.setExtras(extras);
					page.addTargetRequest(request);
				}
			}
		} else {
			logger.debug("SohuTvPageProcessor.parseListPage miss "+page.getUrl().toString());
		}
		// 从页面发现后续的url地址来抓取（列表页）
		List<String> list_urls = pageHtml.links().regex(LIST_URL).all();
		logger.debug("SohuTvPageProcessor.parseListPage add "+list_urls.size()+" list urls");
		page.addTargetRequests(list_urls);
	}
	
	private void parseItemPage(Page page) {
		Html pageHtml = page.getHtml();
		Request pageRequest = page.getRequest();
		Map<String, Object> extras = pageRequest.getExtras();
		
		// 首先找到的条目页
		if("item".equals(pageRequest.getExtra("pageType"))){
			// 定义如何抽取页面信息，并保存下来
			ItemPojo itemPojo = new ItemPojo();
			itemPojo.setName(pageHtml.xpath("//div[@class='wrapper']/div[@class='area rel cfix']/h2/span[@class='vname']/text()").toString());
			Selectable itemSelectable = pageHtml.xpath("//div[@class='wrapper']/div[@class='cfix area']/div[@class='dramaL']/div[@class='cfix drama-info']/div[@class='drama-infoR']/ul[@class='cfix']");
			itemPojo.setAlias(itemSelectable.xpath("//li[1]/text()").toString());
			itemPojo.setLabels(itemSelectable.xpath("//li[4]/a/text()").all());
			itemPojo.setReleaseDate(itemSelectable.xpath("//li[2]/text()").toString());
			itemPojo.setArea(itemSelectable.xpath("//li[3]/a/text()").toString());
			itemPojo.setDirectors(itemSelectable.xpath("//li[5]/a/text()").all());
			itemPojo.setActors(itemSelectable.xpath("//li[6]/a/text()").all());
			itemPojo.setTotalTime("");
			itemPojo.setTotalPlay(itemSelectable.xpath("//li[8]/em/text()").toString());
			itemPojo.setTodayPlay(itemSelectable.xpath("//li[9]/em/text()").toString());
			itemPojo.setCommentNum(itemSelectable.xpath("//li[10]/em/text()").toString());
			itemPojo.setGrade(itemSelectable.xpath("//li[7]/strong/text()").toString());
			itemPojo.setGradeNum(itemSelectable.xpath("//li[7]/em[2]/text()").toString());
			itemPojo.setUpNum("");
			itemPojo.setDownNum("");
			extras.put("item", itemPojo);
			// 从页面发现后续的url地址来抓取
			List<String> play_urls = pageHtml.links().regex(PLAY_URL).all();
			logger.info("SohuTvPageProcessor.parseItemPage add "+play_urls.size()+" play urls");
			logger.info("SohuTvPageProcessor.parseItemPage: "+JSON.toJSONString(extras));
			for(String play_url : play_urls){
				pageRequest.setUrl(play_url);
				page.addTargetRequest(pageRequest);
			}
		}
		// 首先找到的播放页
		else {
			// 定义如何抽取页面信息，并保存下来
			ItemPojo itemPojo = new ItemPojo();
			itemPojo.setName(pageHtml.xpath("//div[@class='wrapper']/div[@class='area rel cfix']/h2/text()").toString());
			Selectable item1Selectable = pageHtml.xpath("//div[@class='wrapper']/div[@class='cfix area']/div[@class='movieL']/div[@class='cfix movie-info']/div[@class='movie-infoR']/ul[1]");
			Selectable item2Selectable = pageHtml.xpath("//div[@class='wrapper']/div[@class='cfix area']/div[@class='movieL']/div[@class='cfix movie-info']/div[@class='movie-infoR']/ul[2]");
			Selectable item3Selectable = pageHtml.xpath("//div[@class='wrapper']/div[@class='cfix area']/div[@class='movieL']/div[@class='cfix movie-info']/div[@class='colL']/p[@class='ta-c']");
			itemPojo.setAlias("");
			itemPojo.setLabels(item1Selectable.xpath("//li[4]/a/text()").all());
			itemPojo.setReleaseDate(item1Selectable.xpath("//li[1]/span/text()").toString());
			itemPojo.setArea(item1Selectable.xpath("//li[3]/a/text()").toString());
			itemPojo.setDirectors(item1Selectable.xpath("//li[5]/a/text()").all());
			itemPojo.setActors(item1Selectable.xpath("//li[6]/a/text()").all());
			itemPojo.setTotalTime(item1Selectable.xpath("//li[2]/span/text()").toString());
			itemPojo.setTotalPlay(item2Selectable.xpath("//li[1]/em/text()").toString());
			itemPojo.setTodayPlay(item2Selectable.xpath("//li[2]/em/text()").toString());
			itemPojo.setCommentNum(item2Selectable.xpath("//li[3]/em/text()").toString());
			itemPojo.setGrade(item2Selectable.xpath("//li[4]/strong/text()").toString());
			itemPojo.setGradeNum(item2Selectable.xpath("//li[4]/em[2]/text()").toString());
			itemPojo.setUpNum(item3Selectable.xpath("//a[@class='like']/text()").toString());
			itemPojo.setDownNum(item3Selectable.xpath("//a[@class='unlike']/text()").toString());
			extras.put("item", itemPojo);
			logger.info("SohuTvPageProcessor.parseItemPage: "+JSON.toJSONString(extras));
			saveInfo(page, extras);
		}
	}
	
	private void parsePlayPage(Page page) {
		Selectable pageUrl = page.getUrl();
		Html pageHtml = page.getHtml();
		Request pageRequest = page.getRequest();
		Map<String, Object> extras = pageRequest.getExtras();
		// 定义如何抽取页面信息，并保存下来
		PlayPojo playPojo = new PlayPojo();
		extras.put("play", playPojo);
		playPojo.setId(pageUrl.regex("http://tv.sohu.com/\\w+/(\\w+).shtml").toString());
		playPojo.setUrl(pageUrl.toString());
		playPojo.setName(pageHtml.xpath("//*[@id='crumbsBar']/div/div[1]/h2/text()").toString());
		List<String> labelList = pageHtml.xpath("//*[@id='crumbsBar']/div/div[1]/div/a/text()").all();
		playPojo.setAlias(labelList.get(labelList.size()-1));
		playPojo.setType(labelList.get(0));
		playPojo.setLabels(labelList.subList(1, labelList.size()-1));
		playPojo.setUpNum(pageHtml.xpath("//*[@id='playtoolbar']/div[1]/a/em/i/text()").toString());
		playPojo.setDownNum(pageHtml.xpath("//*[@id='playtoolbar']/div[2]/a/em/i/text()").toString());
		// 首先找到的条目页
		if("item".equals(pageRequest.getExtra("pageType"))){
			playPojo.setAlbumTodayPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl[1]/dd/span[1]/em/text()").toString());
			playPojo.setAlbumTotalPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl[1]/dd/span[2]/em/text()").toString());
			playPojo.setTodayPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl[2]/dd/span[1]/em/text()").toString());
			playPojo.setTotalPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl[2]/dd/span[2]/em/text()").toString());
			logger.info("SohuTvPageProcessor.parsePlayPage: "+JSON.toJSONString(extras));
			saveInfo(page, extras);
		}
		// 首先找到的播放页
		else {
			playPojo.setAlbumTodayPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl/dd/span[1]/em/text()").toString());
			playPojo.setAlbumTotalPlay(pageHtml.xpath("//*[@id='playtoolbar']/div[8]/div/dl/dd/span[2]/em/text()").toString());
			playPojo.setTodayPlay(playPojo.getAlbumTodayPlay());
			playPojo.setTotalPlay(playPojo.getAlbumTotalPlay());
			// 从页面发现后续的url地址来抓取
			String item_url = pageHtml.xpath("//*[@id='crumbsBar']/div/div[1]/div").links().regex(ITEM_URL1+"|"+ITEM_URL2).toString();
			if(EmptyUtil.isNotEmpty(item_url)){
				pageRequest.setUrl(item_url);
				logger.info("SohuTvPageProcessor.parsePlayPage: "+JSON.toJSONString(extras));
				page.addTargetRequest(pageRequest);
			}
		}
	}
	
	private void saveInfo(Page page, Map<String, Object> extras){
		PlayPojo playPojo = (PlayPojo)extras.get("play");
		DimVideoInfoEntity dimVideoInfoEntity = new DimVideoInfoEntity();
		dimVideoInfoEntity.setId(playPojo.getId());
		dimVideoInfoEntity.setProdDm("");
		dimVideoInfoEntity.setProdMc("搜狐");
		dimVideoInfoEntity.setTypeDm("");
		dimVideoInfoEntity.setTypeMc("");
		dimVideoInfoEntity.setName(playPojo.getName());
		dimVideoInfoEntity.setUrl(playPojo.getUrl());
		dimVideoInfoEntity.setClicknum(playPojo.getTotalPlay());
		dimVideoInfoEntity.setUptimes(playPojo.getUpNum());
		dimVideoInfoEntity.setDowntimes(playPojo.getDownNum());
		dimVideoInfoEntity.setTotaltime("");
		dimVideoInfoEntity.setInfo(JSON.toJSONString(extras));
		StringBuffer strBuf = new StringBuffer();
		List<String> labels = (List<String>)playPojo.getLabels();
		for(int i=0; i<labels.size()-1; i++){
			strBuf.append(labels.get(i)).append("/");
		}
		if(labels.size()>0){
			strBuf.append(labels.get(labels.size()-1));
		}
		dimVideoInfoEntity.setLabel(strBuf.toString());
		page.putField("dimVideoInfoEntity", dimVideoInfoEntity);
	}
	
	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.processor.PageProcessor#process(us.codecraft.webmagic.Page)
	 */
	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()){
			parseListPage(page);
		} else if(page.getUrl().regex(ITEM_URL1+"|"+ITEM_URL2).match()) {
			parseItemPage(page);
		} else {
			parsePlayPage(page);
		}
	}
	
	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.processor.PageProcessor#getSite()
	 */
	@Override
	public Site getSite() {
		return site;
	}

}
