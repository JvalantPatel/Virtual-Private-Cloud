package com.application.viops;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.application.model.VMStat;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class VMOperations {
	final static String winTemplateName = "VMTools-Win-VM";
	final static String linTemplateName = "VMTools-Lin-VM";

	public static ServiceInstance getServiceInstance() {
		URL url;
		ServiceInstance serviceInstance = null;
		try {
			url = new URL("https://130.65.132.104/sdk");
			serviceInstance = new ServiceInstance(url, "administrator",
					"12!@qwQW", true);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return serviceInstance;
	}

	public static List<VMStat> getVMStatistics(List<String> vmNames)
			throws InvalidProperty, RuntimeFault, RemoteException {
		List<VMStat> vmStatList = new ArrayList<VMStat>();

		InventoryNavigator inv = new InventoryNavigator(getServiceInstance()
				.getRootFolder());

		for (String vmName : vmNames) {
			VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity(
					"VirtualMachine", vmName);
			if (vm == null)
				continue;

			/*
			 * if (!vm.getRuntime().getPowerState().toString()
			 * .equals("poweredOff")) {
			 */

			VMStat vmStat = new VMStat();
			vmStat.setName(vm.getName());
			// System.out.println(vm.getName());

			vmStat.setvMVersion(vm.getConfig().version);
			// System.out.println("VM Version:"+vm.getConfig().version);

			vmStat.setGuestOS(vm.getSummary().getConfig().guestFullName);
			// System.out.println("Guest OS:"+vm.getSummary().getConfig().guestFullName);

			vmStat.setNumCpu(vm.getConfig().getHardware().numCPU);
			// System.out.println("CPU:"+vm.getConfig().getHardware().numCPU+" vCPU");

			vmStat.setMemoryAllocated(vm.getConfig().getHardware().memoryMB);
			// System.out.println("Memory:"+vm.getConfig().getHardware().memoryMB+" MB");

			vmStat.setiPAddress(vm.getSummary().getGuest().getIpAddress());
			// System.out.println("IP Addresses:"+vm.getSummary().getGuest().getIpAddress());

			vmStat.setState(vm.getGuest().guestState);
			// System.out.println("State:"+vm.getGuest().guestState);

			vmStat.setConsumedMemory(vm.getResourcePool().getSummary()
					.getQuickStats().guestMemoryUsage);
			// System.out.println("consumed memory "
			// +vm.getResourcePool().getSummary().getQuickStats().guestMemoryUsage);

			vmStat.setCpuUsage(vm.getResourcePool().getSummary()
					.getQuickStats().overallCpuUsage);
			// System.out.println("CPU Usage "
			// +vm.getResourcePool().getSummary().getQuickStats().overallCpuUsage);

			vmStat.setCpuAllocated(vm.getResourcePool().getConfig()
					.getCpuAllocation().getLimit());
			// System.out.println("CPU Allocation " +
			// vm.getResourcePool().getConfig().getCpuAllocation().getLimit());

			vmStatList.add(vmStat);
			// }
		}
		return vmStatList;
	}

	public static boolean createVM(String vmName, long memoryLimit,
			int noOfCpu, String vmType) throws Exception {
		String template;
		template = vmType.equalsIgnoreCase("Windows") ? winTemplateName
				: linTemplateName;
		InventoryNavigator inv = new InventoryNavigator(getServiceInstance()
				.getRootFolder());
		VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity(
				"VirtualMachine", template);

		VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();
		cloneSpec.setLocation(new VirtualMachineRelocateSpec());
		cloneSpec.setPowerOn(false);
		cloneSpec.setTemplate(true);

		Task task = vm.cloneVM_Task((Folder) vm.getParent(), vmName, cloneSpec);
		String status = task.waitForTask();

		if (status == Task.SUCCESS) {
			System.out.println("VM got cloned successfully.");

			HostSystem host = (HostSystem) inv.searchManagedEntity(
					"HostSystem", "130.65.132.161");
			VirtualMachine newVM = (VirtualMachine) inv.searchManagedEntity(
					"VirtualMachine", vmName);

			ManagedEntity[] mesAdmin = inv
					.searchManagedEntities("ComputeResource");
			ComputeResource computeResource = (ComputeResource) mesAdmin[0];

			newVM.markAsVirtualMachine(computeResource.getResourcePool(), host);

			VirtualMachineConfigSpec configSpec = new VirtualMachineConfigSpec();
			configSpec.setMemoryMB(memoryLimit);
			configSpec.setNumCPUs(noOfCpu);
			newVM.reconfigVM_Task(configSpec);

			newVM.powerOnVM_Task(host);
			return true;
		} else {
			System.out.println("Failure -: VM cannot be cloned");
			return false;
		}
	}

	public static boolean powerOnVM(String vmName) {
		InventoryNavigator inv = new InventoryNavigator(getServiceInstance()
				.getRootFolder());
		try {
			VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity(
					"VirtualMachine", vmName);
			vm.powerOnVM_Task(null);
			return true;
		} catch (InvalidProperty e) {
			System.out.println("VMOperations: InvalidProperty");
			return false;
		} catch (RuntimeFault e) {
			System.out.println("VMOperations: Runtime Fault");
			return false;
		} catch (RemoteException e) {
			System.out.println("VMOperations: Remote Exception");
			return false;
		}

	}

	public static boolean powerOffVM(String vmName) {
		InventoryNavigator inv = new InventoryNavigator(getServiceInstance()
				.getRootFolder());
		try {
			VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity(
					"VirtualMachine", vmName);
			vm.powerOffVM_Task();
			return true;
		} catch (InvalidProperty e) {
			System.out.println("VMOperations: InvalidProperty");
			return false;
		} catch (RuntimeFault e) {
			System.out.println("VMOperations: Runtime Fault");
			return false;
		} catch (RemoteException e) {
			System.out.println("VMOperations: Remote Exception");
			return false;
		}
	}
}
