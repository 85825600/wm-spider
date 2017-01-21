/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.dao.IDimVideoInfoDao.java
 *
 * @author andy
 * @date 2016年11月17日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.dao;

import java.util.List;

import com.bonc.spider.entity.DimVideoInfoEntity;

/**
 * 视频信息Dao
 * com.bonc.spider.dao.IDimVideoInfoDao.java
 * 
 * @author andy
 * @date 2016年11月17日
 *
 * @since 0.0.1
 */
public interface IDimVideoInfoDao {
	int insertList(List<DimVideoInfoEntity> list);
	
	int insertBean(DimVideoInfoEntity dimVideoInfoEntity);
}
