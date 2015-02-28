package com.yeahwell.epu.scheduling.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpireJobTask {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ExpireJobTask.class);

	public void doBiz() {
		// 执行业务逻辑
		logger.info("执行任务");
	}
}