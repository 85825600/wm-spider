/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.TaskManager.java
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

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bonc.spider.task.ITask;

/**
 * 任务管理器
 * com.bonc.spider.TaskManager.java
 * 
 * @author andy
 * @date 2016年11月18日
 *
 * @since 0.0.1
 */
@Component
public class TaskManager {
	
	/**
	 * 任务列表
	 */
	private List<ITask> taskList = new ArrayList<ITask>();
	
	public TaskManager(){}
	
	public TaskManager(ITask task){
		taskList.add(task);
	}
	
	/**
	 * 链式添加任务到任务列表
	 * 
	 * @param task 任务
	 * @author andy
	 * @date 2016年11月18日
	 * 
	 * @since 0.0.1
	 */
	public TaskManager addTask(ITask task) {
		taskList.add(task);
		return this;
	}
	
	/**
	 * 执行任务
	 * 
	 * @author andy
	 * @date 2016年11月18日
	 * 
	 * @since 0.0.1
	 */
	public void start() {
		for(ITask task: taskList){
			task.start();
		}
	}

}
