package com.appmod.release.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.appmod.release.domain.User;
import com.appmod.release.service.CustomUserDetailsService;
@Controller
public class LoginController {
	
	 @Autowired
	    private CustomUserDetailsService userService;

	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView login() {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("login");
	        return modelAndView;
	    }

	    @RequestMapping(value = "/create/admin", method = RequestMethod.GET)
	    public ModelAndView createAdmin() {
	        ModelAndView modelAndView = new ModelAndView();
	        User user = new User();
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("signup-admin");
	        return modelAndView;
	    }
	    
	    @RequestMapping(value = "/create/user", method = RequestMethod.GET)
	    public ModelAndView createUser() {
	        ModelAndView modelAndView = new ModelAndView();
	        User user = new User();
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("signup-user");
	        return modelAndView;
	    }
	    
	    @RequestMapping(value = "/create/admin", method = RequestMethod.POST)
	    public ModelAndView createNewAdmin(@Valid User user, BindingResult bindingResult) {
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByEmail(user.getEmail());
	        if (userExists != null) {
	            bindingResult
	                    .rejectValue("email", "error.user",
	                            "There is already an Admin registered with the username provided");
	        }
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("signup-admin");
	        } else {
	            userService.saveUser(user, "ADMIN");
	            modelAndView.addObject("successMessage", "Admin has been registered successfully");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("login");

	        }
	        return modelAndView;
	    }
	    
	    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
	    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByEmail(user.getEmail());
	        if (userExists != null) {
	            bindingResult
	                    .rejectValue("email", "error.user",
	                            "There is already a user registered with the username provided");
	        }
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("signup-user");
	        } else {
	            userService.saveUser(user, "USER");
	            modelAndView.addObject("successMessage", "User has been registered successfully");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("login");

	        }
	        return modelAndView;
	    }

	    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	    public ModelAndView dashboard() {
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("currentUser", user);
	        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	        modelAndView.setViewName("dashboard");
	        return modelAndView;
	    }
	    
	    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	    public ModelAndView home() {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("home");
	        return modelAndView;
	    }

}
