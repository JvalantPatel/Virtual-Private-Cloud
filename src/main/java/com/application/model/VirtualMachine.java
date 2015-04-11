package com.application.model;

public class VirtualMachine {

	private String vmName;
	private long memoryLimit;
	private int noOfCpu;
	private String vmType;

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public long getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(long memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public int getNoOfCpu() {
		return noOfCpu;
	}

	public void setNoOfCpu(int noOfCpu) {
		this.noOfCpu = noOfCpu;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

}
