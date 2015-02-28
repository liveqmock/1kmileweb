package com.yeahwell.epu.quarz;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimerTask {
	public static void main(String[] args) {
		Date now = new Date();
		Timer timer = new Timer();
		//从现在开始，间隔1000 * 60 * 60 * 24调用一次Task里面的run()方法。
//		timer.schedule(new Task(), now, 1000 * 60 * 60 * 24);
		timer.schedule(new Task(), now, 60 * 60);
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {
			timer.cancel();
		}
	}
}

class Task extends TimerTask {
	public void run() {
		System.out.println("start");
	}
}