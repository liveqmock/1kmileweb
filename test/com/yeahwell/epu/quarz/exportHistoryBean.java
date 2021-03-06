package com.yeahwell.epu.quarz;

import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

public class exportHistoryBean extends TimerTask {
	
	private static final int C_SCHEDULE_HOUR = 0;
	
	private static boolean isRunning = false;
	
	private ServletContext context = null;

	public exportHistoryBean(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		Calendar c = Calendar.getInstance();
		if (!isRunning) {
			if (C_SCHEDULE_HOUR == c.get(Calendar.HOUR_OF_DAY)) {
				isRunning = true;
				context.log("开始执行指定任务");
				isRunning = false;
				context.log("指定任务执行结束");
			} else {
				context.log("上一次任务执行还未结束");
			}
		}
	}
	
}