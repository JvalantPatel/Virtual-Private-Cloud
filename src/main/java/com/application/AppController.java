package com.application;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.application.dao.UserDao;
import com.application.dao.VmStatisticsDao;
import com.application.model.User;
import com.application.model.VMAlarm;
import com.application.model.VMAlarmStatus;
import com.application.model.VMStat;
import com.application.model.VirtualMachine;
import com.application.utility.AppUtility;
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
			m.addObject("message", "User Name or Password is invalid");

			return m;
		}
		System.out.println("Username is set to -" + userDb.getUserName());
		request.getSession().setAttribute("username", userDb.getUserName());
		// List<VMStat> vmStats = null;
		List<VMStat> vmStats = new ArrayList<VMStat>();
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			System.out.println("App Cntroller: Invalid Property Exception");
		} catch (RuntimeFault e) {

			System.out.println("App Cntroller: RuntimeFault");
		} catch (RemoteException e) {
			System.out.println("App Cntroller: RemoteException");

		}

		model.addAttribute("loggedInUser", user.getUserName());
		model.addAttribute("virtual", new VirtualMachine());
		model.addAttribute("virtual1", new VirtualMachine());
		model.addAttribute("vmAlarm", new VMAlarm());
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

		} else {

		}
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {

			System.out.println("AppController: Invalid Property");
		} catch (RuntimeFault e) {
			System.out.println("AppController: Runtime Exception");
		} catch (RemoteException e) {
			System.out.println("AppController: Remote Exception");
		}

		return new ModelAndView("ListVM").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/showstats", method = RequestMethod.GET)
	public ModelAndView showVMStats(HttpServletRequest request, Model model) {
		String message = "";
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<VMStat> vmStats = null;
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			System.out.println("AppController: Invalid Property");

		} catch (RuntimeFault e) {
			System.out.println("AppController: Runtime Exception");

		} catch (RemoteException e) {

			System.out.println("AppController: Remote Exception");
		}
		model.addAttribute("vmAlarm", new VMAlarm());
		return new ModelAndView("vm-statistics").addObject("vmList", vmStats);
	}

	@RequestMapping(value = "/powerOff", method = RequestMethod.POST)
	public ModelAndView powerOff(HttpServletRequest request,
			@ModelAttribute("virtual") VirtualMachine vm) {
		List<VMStat> vmStats = null;
		System.out.println("Inside poweroff");
		System.out.println("VM name is: " + vm.getVmName());
		VMOperations.powerOffVM(vm.getVmName());

		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);

		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			System.out.println("Appcontroller: Invalid Property");
		} catch (RuntimeFault e) {
			System.out.println("Appcontroller: Runtime Fault");
		} catch (RemoteException e) {
			System.out.println("Appcontroller:Remote Exception");
		}

		return new ModelAndView("ListVM").addObject("vmList", vmStats);

	}

	@RequestMapping(value = "/powerOn", method = RequestMethod.POST)
	public ModelAndView powerOn(HttpServletRequest request,
			@ModelAttribute("virtual1") VirtualMachine vm) {
		List<VMStat> vmStats = null;
		System.out.println("Inside powerOn");
		System.out.println("VM name is: " + vm.getVmName());

		VMOperations.powerOnVM(vm.getVmName());

		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		try {
			vmStats = VMOperations.getVMStatistics(vmList);
		} catch (InvalidProperty e) {
			System.out.println("Appcontroller: Invalid Property");
		} catch (RuntimeFault e) {
			System.out.println("Appcontroller: Runtime Fault");
		} catch (RemoteException e) {
			System.out.println("Appcontroller:Remote Exception");
		}

		return new ModelAndView("ListVM").addObject("vmList", vmStats);
	}

	/* Service for getting Alarm page */
	@RequestMapping(value = "/vmThresholdPage", method = RequestMethod.GET)
	public ModelAndView getAlarmPage(HttpServletRequest request, Model model) {
		/*
		 * String userName = (String) request.getSession()
		 * .getAttribute("username"); List<String> vmList =
		 * UserDao.getUserVMs(userName); return new
		 * ModelAndView("alarmPage").addObject("vmList", vmList);
		 */
		String userName = (String) request.getSession()
				.getAttribute("username");
		List<String> vmList = UserDao.getUserVMs(userName);
		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("vmmAlarm", new VMAlarm());
		model.addAttribute("vmAlarm", new VMAlarm());
		modelAndView.setViewName("vmThreshholdPage");
		modelAndView.addObject("vmList", vmList);
		return modelAndView;
	}

	/* AJAX Call - Service for getting Alarm Threshold value for VM */
	// @RequestMapping(value = "/getVMAlarmThreshold", method =
	// RequestMethod.GET)
	@RequestMapping(value = "/getVMAlarmThreshold/{vmName}", method = RequestMethod.GET)
	public @ResponseBody
	VMAlarm getVMAlarmThreshold(@PathVariable String vmName) {
		Map<String, Long> map = VmStatisticsDao
				.getVMPropertyThresholdValues(vmName);
		return AppUtility.convertMapToVMAlarm(map);
	}

	/* AJAX Call - Service for setting Alarm Threshold value for VM */
	@RequestMapping(value = "/setVMAlarmThreshold", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView setVMAlarmThreshold(@ModelAttribute("vmAlarm") VMAlarm vmAlarm) {
		System.out.println("controller: set threshold");
		System.out.println(vmAlarm.getVMName() + " "
				+ vmAlarm.getCpuThreshold() + " " + vmAlarm.getNetThreshold()
				+ " " + vmAlarm.getMemoryThreshold() + " "
				+ vmAlarm.getDiskReadThreshold() + " "
				+ vmAlarm.getDiskWriteThreshold());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("vmThreshholdPage");
		modelView.addObject("response",
				AppUtility.setAlarmThresholdValuesForVM(vmAlarm));
		// return AppUtility.setAlarmThresholdValuesForVM(vmAlarm);
		return modelView;
	}

	/* Service for getting VM Statistics page -- Kibana and VM's Alarm status */

	/*
	 * @RequestMapping(value = "/getVmStatisticsPage", method =
	 * RequestMethod.GET) public ModelAndView getVmStatisticsPage(
	 * 
	 * @ModelAttribute("vmName") String vmName) { Map<String, Long> map =
	 * VmStatisticsDao .getVmPropertyThresholdExceedStatus(vmName);
	 * VMAlarmStatus vmAlarmStatus = AppUtility.convertMapToVMAlarmStatus(map);
	 * return new ModelAndView("getVmStatisticsPage").addObject("vmName",
	 * vmName).addObject("vmAlarmStatus", vmAlarmStatus);
	 * 
	 * }
	 */

	// working with post
	@RequestMapping(value = "/getVmStatisticsPage", method = RequestMethod.POST)
	public ModelAndView getVmStatisticsPage(
			@ModelAttribute("vmAlarm") VMAlarm vmAlarm) {
		String vmName = vmAlarm.getVMName();
		System.out.println("VM Name: " + vmAlarm.getVMName());
		Map<String, Long> map = VmStatisticsDao
				.getVmPropertyThresholdExceedStatus(vmName);
		VMAlarmStatus vmAlarmStatus = AppUtility.convertMapToVMAlarmStatus(map);
		return new ModelAndView("vmPerfGraphs").addObject("vmName", vmName)
				.addObject("vmAlarmStatus", vmAlarmStatus);

	}

	/* AJAX Call - Service for getting VM's Alarm status */
	@RequestMapping(value = "/getVmAlarmStatus/{vmName}", method = RequestMethod.GET)
	/* VMAlarmStatus getVmAlarmStatus(@ModelAttribute("vmName") String vmName) { */
	public @ResponseBody
	VMAlarmStatus getVmAlarmStatus(@PathVariable String vmName) {
		Map<String, Long> map = VmStatisticsDao
				.getVmPropertyThresholdExceedStatus(vmName);
		return AppUtility.convertMapToVMAlarmStatus(map);
	}
}
