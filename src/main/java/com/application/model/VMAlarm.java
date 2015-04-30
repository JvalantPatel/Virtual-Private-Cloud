package com.application.model;

public class VMAlarm {
	
	private String VMName;
	private Long cpuThreshold;
	private Long memoryThreshold;
	private Long netThreshold;
	private Long diskReadThreshold;
	private Long diskWriteThreshold;
	public String getVMName() {
		return VMName;
	}
	public void setVMName(String vMName) {
		VMName = vMName;
	}
	public Long getCpuThreshold() {
		return cpuThreshold;
	}
	public void setCpuThreshold(Long cpuThreshold) {
		this.cpuThreshold = cpuThreshold;
	}
	public Long getMemoryThreshold() {
		return memoryThreshold;
	}
	public void setMemoryThreshold(Long memoryThreshold) {
		this.memoryThreshold = memoryThreshold;
	}
	public Long getNetThreshold() {
		return netThreshold;
	}
	public void setNetThreshold(Long netThreshold) {
		this.netThreshold = netThreshold;
	}
	public Long getDiskReadThreshold() {
		return diskReadThreshold;
	}
	public void setDiskReadThreshold(Long diskReadThreshold) {
		this.diskReadThreshold = diskReadThreshold;
	}
	public Long getDiskWriteThreshold() {
		return diskWriteThreshold;
	}
	public void setDiskWriteThreshold(Long diskWriteThreshold) {
		this.diskWriteThreshold = diskWriteThreshold;
	}
	
	

}
