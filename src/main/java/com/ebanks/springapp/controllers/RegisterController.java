package com.ebanks.springapp.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ebanks.springapp.exception.GenericException;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/**
 * The PersonController for handling REST request.
 */
@Controller
public class RegisterController {

	/** The Constant REGISTER_CONTROLLER_LOGGER. */
	private static final Logger REGISTER_CONTROLLER_LOGGER = Logger.getLogger(RegisterController.class);

	/** The Constant REGISTRATION. */
	private static final String REGISTRATION = "registration";

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Retrieves a list of people.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/user/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		REGISTER_CONTROLLER_LOGGER.info("User has entered registration page");

		return REGISTRATION;
	}

	/**
	 * Registers the user account.
	 *
	 * @param user    the user
	 * @param result  the result
	 * @param request the request
	 * @param errors  the errors
	 * @return the model and view
	 * @throws GenericException
	 */
	@PostMapping(value = "/registration")
	public ModelAndView registerUserAccount(@Valid User user, BindingResult bindingResult) throws GenericException {
		/*
		 * if (!bindingResult.hasErrors()) { createUserAccount(user, bindingResult);
		 * REGISTER_CONTROLLER_LOGGER.info("User has entered registration page1");
		 * 
		 * } REGISTER_CONTROLLER_LOGGER.info("User has entered registration page2");
		 * 
		 * if (bindingResult.hasErrors()) { return new ModelAndView(REGISTRATION,
		 * "user", user); } else { return new ModelAndView("home", "user", user); }
		 */
		REGISTER_CONTROLLER_LOGGER.info("User has entered registration page");

		REGISTER_CONTROLLER_LOGGER.info("user: " + user);
		if (user == null) {
			REGISTER_CONTROLLER_LOGGER.info("user is null");
			throw new GenericException("User is null");
		}

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			REGISTER_CONTROLLER_LOGGER.info("user has errors");
			REGISTER_CONTROLLER_LOGGER.info("user has errors" + bindingResult.getFieldErrors());

			modelAndView.setViewName(REGISTRATION);
		} else {
			REGISTER_CONTROLLER_LOGGER.info("user does not errors");
			createUserAccount(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName(REGISTRATION);

		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName(REGISTRATION);
		return modelAndView;
	}

	/**
	 * Creates the user account.
	 *
	 * @param user   the user
	 * @param result the binding result
	 */
	private void createUserAccount(User user) {
		try {
			userService.addUser(user);
		} // catch (SQLException e) {
			// REGISTER_CONTROLLER_LOGGER.info(String.format("An SQL exception has occurred:
			// %s", e));}
		catch (Exception e) {
			// TODO: Need to split into separate specific exception
			REGISTER_CONTROLLER_LOGGER.info(String.format("An exception has occurred: %s", e));
		}
	}

}