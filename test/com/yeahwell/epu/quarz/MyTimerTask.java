package com.yeahwell.epu.quarz;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyTimerTask implements ServletContextListener {
	
	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer.cancel();
		event.getServletContext().log("定时器销毁");
		System.out.println("停止备份程序……");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，考试,大提示可以在这里实现定时器功能
		timer = new Timer(true);
		event.getServletContext().log("定时器已启动");// 添加日志，可在tomcat日志中查看到
		// 调用 exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时；
		  //设置任务计划，启动和间隔时间
		timer.schedule(new exportHistoryBean(event.getServletContext()), 0, 5 * 1000);
	}
}
