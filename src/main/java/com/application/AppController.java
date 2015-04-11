package com.application;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.application.dao.UserDao;
import com.application.model.User;
import com.application.model.VMStat;
import com.application.model.VirtualMachine;
import com.application.viops.VMOperations;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;

@Controller
public class AppController {

	/*
	 * Services for.. 1)Login 2)Signup 3)CreateVm 4)ListOfVm 5)Stats
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome(HttpServletRequest request,
			@ModelAttribute("userForm") User user) {
		System.out.println("Username is set to -" + user.getUserName());
		request.getSession().setAttribute("username", user.getUserName());
		List<VMStat> vmStats = null;
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("listvm").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userForm") User user) {
		System.out.println("Registering User...");
		UserDao.addUser(user.getUserName(), user.getPassword());
		return new ModelAndView("login").addObject("message",
				"Registration Done!!");
	}
	
	@RequestMapping(value = "/createvm", method = RequestMethod.GET)
	public String createvm() {
		return "createvm";
	}
	
	

	@RequestMapping(value = "/createvmsubmit", method = RequestMethod.POST)
	public ModelAndView createVirtualMachine(HttpServletRequest request,
			@ModelAttribute("vm") VirtualMachine vm) {
		String message = "";
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<VMStat> vmStats = null;
		boolean done = false;
		try {
			done = VMOperations.createVM(vm.getVmName(), vm.getMemoryLimit(),
					vm.getNoOfCpu(), vm.getVmType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (done) {
			UserDao.addUserVM(userName, vm.getVmName());
			/* message = "Virtual Machine is created successfully!!"; */
		} else {
			/* message = "Virtual Machine creation failed !!"; */
		}
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("listvm").addObject("vmList", vmStats);
	}
	
	@RequestMapping(value = "/showstats", method = RequestMethod.GET)
	public ModelAndView showVMStats(HttpServletRequest request) {
		String message = "";
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<VMStat> vmStats = null;
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("listvm").addObject("vmList", vmStats);
	}

}