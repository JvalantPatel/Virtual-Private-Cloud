package com.application.viops;

public class VMStat {
	String name;
	String guestOS;
	String vMVersion;
	Integer numCpu;
	Integer memoryAllocated;
	String iPAddress;
	String state;
	Long consumedMemory;
	Long cpuAllocated;
	Long cpuUsage;
	
	public String getGuestOS() {
		return guestOS;
	}
	public void setGuestOS(String guestOS) {
		this.guestOS = guestOS;
	}
	public String getvMVersion() {
		return vMVersion;
	}
	public void setvMVersion(String vMVersion) {
		this.vMVersion = vMVersion;
	}
	public Integer getNumCpu() {
		return numCpu;
	}
	public void setNumCpu(Integer numCpu) {
		this.numCpu = numCpu;
	}
	public Integer getMemoryAllocated() {
		return memoryAllocated;
	}
	public void setMemoryAllocated(Integer memoryAllocated) {
		this.memoryAllocated = memoryAllocated;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getConsumedMemory() {
		return consumedMemory;
	}
	public void setConsumedMemory(Long consumedMemory) {
		this.consumedMemory = consumedMemory;
	}
	public Long getCpuAllocated() {
		return cpuAllocated;
	}
	public void setCpuAllocated(Long cpuAllocated) {
		this.cpuAllocated = cpuAllocated;
	}
	public Long getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(Long cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
