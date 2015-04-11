package com.application.model;

public class VMStat {
	private String name;
	private String guestOS;
	private String vMVersion;
	private Integer numCpu;
	private Integer memoryAllocated;
	private String iPAddress;
	private String state;
	private Long consumedMemory;
	private Long cpuAllocated;
	private Long cpuUsage;
	
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
