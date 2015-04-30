package com.application.utility;

import java.util.Map;

import com.application.dao.VmStatisticsDao;
import com.application.model.VMAlarm;
import com.application.model.VMAlarmStatus;

public class AppUtility {
	
	public static VMAlarm convertMapToVMAlarm(Map<String, Long> map){
		VMAlarm vmAlarm = new VMAlarm();
		vmAlarm.setCpuThreshold(setDefaultValueIfNull(map.get("CPU")));
		vmAlarm.setMemoryThreshold(setDefaultValueIfNull(map.get("MEMORY")));
		vmAlarm.setNetThreshold(setDefaultValueIfNull(map.get("NET")));
		vmAlarm.setDiskReadThreshold(setDefaultValueIfNull(map.get("DISKREAD")));
		vmAlarm.setDiskWriteThreshold(setDefaultValueIfNull(map.get("DISKWRITE")));
		return vmAlarm;
	}
	
	public static boolean setAlarmThresholdValuesForVM(VMAlarm vmAlarm){
		String vm = vmAlarm.getVMName();
		try{
			if(isThresholdValid(vmAlarm.getCpuThreshold()))
				VmStatisticsDao.setVmPropertyThreshold(vm, "CPU", Integer.parseInt(vmAlarm.getCpuThreshold().toString()));
			
			if(isThresholdValid(vmAlarm.getMemoryThreshold()))
				VmStatisticsDao.setVmPropertyThreshold(vm, "MEMORY", Integer.parseInt(vmAlarm.getMemoryThreshold().toString()));
			
			if(isThresholdValid(vmAlarm.getNetThreshold()))
				VmStatisticsDao.setVmPropertyThreshold(vm, "NET", Integer.parseInt(vmAlarm.getNetThreshold().toString()));
			
			if(isThresholdValid(vmAlarm.getDiskReadThreshold()))
				VmStatisticsDao.setVmPropertyThreshold(vm, "DISKREAD", Integer.parseInt(vmAlarm.getDiskReadThreshold().toString()));
			
			if(isThresholdValid(vmAlarm.getDiskWriteThreshold()))
				VmStatisticsDao.setVmPropertyThreshold(vm, "DISKWRITE", Integer.parseInt(vmAlarm.getDiskWriteThreshold().toString()));
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public static VMAlarmStatus convertMapToVMAlarmStatus(Map<String, Long> map) {
		// TODO Auto-generated method stub
		VMAlarmStatus vmAlarmStatus = new VMAlarmStatus();
		vmAlarmStatus.setCpuAlarm(setDefaultIntValueIfNull(map.get("CPU")));
		vmAlarmStatus.setMemoryAlarm(setDefaultIntValueIfNull(map.get("MEMORY")));
		vmAlarmStatus.setNetAlarm(setDefaultIntValueIfNull(map.get("NET")));
		vmAlarmStatus.setDiskReadAlarm(setDefaultIntValueIfNull(map.get("DISKREAD")));
		vmAlarmStatus.setDiskWriteAlarm(setDefaultIntValueIfNull(map.get("DISKWRITE")));
		return vmAlarmStatus;
	}
	
	public static Long setDefaultValueIfNull(Long value){		
		return (value==0 || value==null)? (long) -1: value;
	}
	
	public static boolean isThresholdValid(Long value){		
		return (value<=0 || value==null )? false: true;
	}
	
	public static int setDefaultIntValueIfNull(Long value){		
		return (value==0 || value==null)? (int) -1: Integer.parseInt(value.toString());
	}

}
