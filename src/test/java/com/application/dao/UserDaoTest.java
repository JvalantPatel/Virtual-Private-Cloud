package com.application.dao;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;

import com.application.model.User;
import com.application.model.VMStat;
import com.application.viops.VMOperations;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;

public class UserDaoTest {

	@Test
	public void testGetUser() throws InvalidProperty, RuntimeFault, RemoteException {
		List<String> vmList = UserDao.getUserVMs("Raina");
		//UserDao.addUser("Raina", "raina");
		List<VMStat> vmStats = VMOperations.getVMStatistics(vmList);
		for(VMStat vm: vmStats) {
			System.out.println(vm.getName());
			System.out.println(vm.getState());
		}
	}

}
