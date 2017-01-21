/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.pipeline.DimVideoInfoPipeline.java
 *
 * @author andy
 * @date 2016年11月17日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.pipeline;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bonc.spider.dao.IDimVideoInfoDao;
import com.bonc.spider.entity.DimVideoInfoEntity;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 视频信息Pipeline
 * com.bonc.spider.pipeline.DimVideoInfoPipeline.java
 * 
 * @author andy
 * @date 2016年11月17日
 *
 * @since 0.0.1
 */
@Component
public class DimVideoInfoPipeline implements Pipeline {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IDimVideoInfoDao iDimVideoInfoDao;

	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.pipeline.Pipeline#process(us.codecraft.webmagic.ResultItems, us.codecraft.webmagic.Task)
	 */
	@Override
	public void process(ResultItems resultItems, Task task) {
		DimVideoInfoEntity dimVideoInfoEntity = (DimVideoInfoEntity)resultItems.get("dimVideoInfoEntity");
		if(dimVideoInfoEntity != null){
			logger.warn(dimVideoInfoEntity.toString());
//			iDimVideoInfoDao.insertBean(dimVideoInfoEntity);
		}
	}

}
