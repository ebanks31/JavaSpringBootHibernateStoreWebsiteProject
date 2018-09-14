package com.ebanks.springapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/**
 * The Home Controller is for handling the REST request from the home page.
 */
@Controller
public class HomeController {

	/** The Constant HOME_LOGGER. */
	private static final Logger HOME_LOGGER = Logger.getLogger(HomeController.class);

	/** The Constant HOME_PAGE. */
	private static final String HOME_PAGE = "home";

	/** The Constant ABOUT. */
	private static final String ABOUT = "about";

	/** The userservice. */
	@Autowired
	private UserService userService;

	/**
	 * Shows the home page.
	 *
	 * @return the page view
	 * @GetMapping(value = "/") public String home(final Model model) {
	 *                   HOME_LOGGER.info("Going to home page"); return HOME_PAGE; }
	 */

	@GetMapping(value = { "/", "/login" })
	public ModelAndView login() {
		HOME_LOGGER.info("Going to home/login page");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * Home.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("home");
		HOME_LOGGER.info("Going to home page");

		return modelAndView;
	}

	/*
	 * @GetMapping(value = {"/", "/login"}) public ModelAndView login(@RequestParam
	 * String error){ HOME_LOGGER.info("Going to home/login page"); ModelAndView
	 * modelAndView = new ModelAndView(); modelAndView.setViewName("login"); return
	 * modelAndView; }
	 */

	/**
	 * The about page controller.
	 *
	 * @return the string
	 */
	@GetMapping("/about")
	public String index() {
		HOME_LOGGER.info("Going to about page");
		return ABOUT;
	}
}