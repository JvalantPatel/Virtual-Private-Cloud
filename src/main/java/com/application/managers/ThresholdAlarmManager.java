package com.application.managers;

import com.application.dao.EsSearchDao;

public class ThresholdAlarmManager extends Thread{
	
	public void run() {
		try {
			System.out.println("Checking and Updating system usage");
			EsSearchDao.checkAndUpdateSystemUsage();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Exception while thread sleep");
			//e.printStackTrace();
		}
	}
}
