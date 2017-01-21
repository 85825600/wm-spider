/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.TaskExecutor.java
 *
 * @author andy
 * @date 2016年11月18日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bonc.spider.core.SpringApplicationContext;
import com.bonc.spider.task.GithubRepoTask;
import com.bonc.spider.task.SohuTvTask;

/**
 * 任务执行器
 * com.bonc.spider.TaskExecutor.java
 * 
 * @author andy
 * @date 2016年11月18日
 *
 * @since 0.0.1
 */
public class TaskExecutor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private void start() {
		logger.info("任务开始...");
		TaskManager taskManager = SpringApplicationContext.getBean(TaskManager.class);
//		taskManager.addTask(SpringApplicationContext.getBean(GithubRepoTask.class));
		taskManager.addTask(SpringApplicationContext.getBean(SohuTvTask.class));
		taskManager.start();
	}

	/**
	 * 爬虫程序主入口
	 * 
	 * @author andy
	 * @date 2016年11月15日
	 *
	 * @since 0.0.1
	 */
	public static void main(String[] args) {
		new TaskExecutor().start();
	}

}
