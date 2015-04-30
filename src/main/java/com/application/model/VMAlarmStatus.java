package com.application.model;

public class VMAlarmStatus {
	
	/*  
	 *  Alarm Status info :
	 *  0  = Alarm isn't triggered
	 *  1  = Alarm triggered
	 * -1  = Alarm not set
	 *  
	 */
	
	private String VMName;
	private int cpuAlarm;
	private int memoryAlarm;
	private int netAlarm;
	private int diskReadAlarm;
	private int diskWriteAlarm;
	
	public String getVMName() {
		return VMName;
	}
	public void setVMName(String vMName) {
		VMName = vMName;
	}
	public int getCpuAlarm() {
		return cpuAlarm;
	}
	public void setCpuAlarm(int cpuAlarm) {
		this.cpuAlarm = cpuAlarm;
	}
	public int getMemoryAlarm() {
		return memoryAlarm;
	}
	public void setMemoryAlarm(int memoryAlarm) {
		this.memoryAlarm = memoryAlarm;
	}
	public int getNetAlarm() {
		return netAlarm;
	}
	public void setNetAlarm(int netAlarm) {
		this.netAlarm = netAlarm;
	}
	public int getDiskReadAlarm() {
		return diskReadAlarm;
	}
	public void setDiskReadAlarm(int diskReadAlarm) {
		this.diskReadAlarm = diskReadAlarm;
	}
	public int getDiskWriteAlarm() {
		return diskWriteAlarm;
	}
	public void setDiskWriteAlarm(int diskWriteAlarm) {
		this.diskWriteAlarm = diskWriteAlarm;
	}
	
	

}
