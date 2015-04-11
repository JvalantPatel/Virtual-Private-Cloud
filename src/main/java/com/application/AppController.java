package com.application;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mainPage(Model model) {
		model.addAttribute("userForm", new User());
		return "index";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancel() {
		return "index";
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		return "index";
	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String login() { return "login"; }
	 */

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * String login() { return "ListVM"; }
	 */

	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getHome(HttpServletRequest request,
			@ModelAttribute("userForm") User user) {
		User userDb = UserDao.getUser(user.getUserName());
		System.out.println("userDb" + userDb);
		if (userDb == null)
			// return new ModelAndView("login").addObject("message",
			// "User Name or Password incorrect..");
			return new ModelAndView("index").addObject("message",
					"User Name or Password incorrect..");
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

		return new ModelAndView("ListVM").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	// public ModelAndView signup(@ModelAttribute("userForm") User user) {
	public ModelAndView signup(@ModelAttribute("user") User user) {
		System.out.println("Registering User...");
		UserDao.addUser(user.getUserName(), user.getPassword());
		/*
		 * return new ModelAndView("login").addObject("message",
		 * "Registration Done!!");
		 */
		return new ModelAndView("index").addObject("message",
				"Registration Done!!");
	}

	@RequestMapping(value = "/createvm", method = RequestMethod.GET)
	public String createvm(Model model) {
		model.addAttribute("VM", new VirtualMachine());
		return "createVM";
	}

	@RequestMapping(value = "/createvmsubmit", method = RequestMethod.POST)
	public ModelAndView createVirtualMachine(HttpServletRequest request,
	// @ModelAttribute("vm") VirtualMachine vm) {
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

}