package com.application.managers;

import com.application.dao.EsSearchDao;

public class ThresholdAlarmManager extends Thread{
	boolean forever = true;
	public void run() {
		try {
			while(forever) {
				System.out.println("Checking and Updating system usage");
				EsSearchDao.checkAndUpdateSystemUsage();
				Thread.sleep(20000);
			}
		} catch (InterruptedException e) {
			System.out.println("Exception while thread sleep");
			//e.printStackTrace();
		}
	}
}
