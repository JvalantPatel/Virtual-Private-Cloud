package com.application;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mainPage(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userForm1", new User());
		return "index";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:index";
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		return "redirect:index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getHome(HttpServletRequest request,
			@ModelAttribute("userForm") User user, Model model) {
		ModelAndView m = new ModelAndView();
		User userDb = UserDao.getUser(user.getUserName());
		System.out.println("userDb" + userDb);
		if (userDb == null) {

			m.addObject("userForm", new User());
			m.addObject("userForm1", new User());
			m.setViewName("index");
			;
			m.addObject("message", "User Name or Password is invalid");
			
			return m;
		}
		System.out.println("Username is set to -" + userDb.getUserName());
		request.getSession().setAttribute("username", userDb.getUserName());
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
		model.addAttribute("loggedInUser", user.getUserName());
		model.addAttribute("virtual", new VirtualMachine());
		model.addAttribute("virtual1", new VirtualMachine());
		return new ModelAndView("ListVM").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		System.out.println("Registering User...");
		UserDao.addUser(user.getUserName(), user.getPassword());
		model.addObject("userForm", new User());
		model.addObject("userForm1", new User());
		model.addObject("success", "Sign up Successful");
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/createvm", method = RequestMethod.GET)
	public String createvm(Model model) {
		model.addAttribute("VM", new VirtualMachine());
		return "createVM";
	}

	@RequestMapping(value = "/createvmsubmit", method = RequestMethod.POST)
	public ModelAndView createVirtualMachine(HttpServletRequest request,
			@ModelAttribute("VM") VirtualMachine vm) {
		System.out.println("Inside");
		String message = "";
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<VMStat> vmStats = null;
		boolean done = false;
		try {
			System.out.println("Name: " + vm.getVmName() + "Memory Limit: "
					+ vm.getMemoryLimit() + "No of CPU" + vm.getNoOfCpu()
					+ "Type: " + vm.getVmType());
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

		// return new ModelAndView("listvm").addObject("vmList", vmStats);
		return new ModelAndView("ListVM").addObject("vmList", vmStats);
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

		// return new ModelAndView("listvm").addObject("vmList", vmStats);
		return new ModelAndView("vm-statistics").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/powerOff", method = RequestMethod.POST)
	public ModelAndView powerOff(HttpServletRequest request,
			@ModelAttribute("virtual") VirtualMachine vm) {
		List<VMStat> vmStats = null;
		System.out.println("Inside poweroff");
		System.out.println("VM name is: "+vm.getVmName());
		VMOperations.powerOffVM(vm.getVmName());

		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ModelAndView("ListVM").addObject("vmList", vmStats);

	}

	@RequestMapping(value = "/powerOn", method = RequestMethod.POST)
	public ModelAndView powerOn(HttpServletRequest request,
			@ModelAttribute("virtual1") VirtualMachine vm) {
		List<VMStat> vmStats = null;
		System.out.println("Inside powerOn");
		System.out.println("VM name is: "+vm.getVmName());

		VMOperations.powerOnVM(vm.getVmName());

		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
		//	vmStats = VMOperations.getVMStatistics(vmList);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return new ModelAndView("ListVM").addObject("vmList", vmStats);
	}

}
