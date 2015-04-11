package com.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

 @RequestMapping(value = "/hello", method = RequestMethod.GET)
 public ModelAndView hello() {
	 System.out.println("In controller...");
  return new ModelAndView("hello").addObject("name", "Jvalant");
 }

}