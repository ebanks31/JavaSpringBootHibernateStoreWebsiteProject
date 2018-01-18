package com.ebanks.springapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The HomeController for handling REST request from the home page.
 */
@Controller
public class HomeController {

	/** The Constant HOME_LOGGER. */
	private static final Logger HOME_LOGGER = Logger.getLogger(HomeController.class);

	/** The Constant HOME_PAGE. */
	private static final String HOME_PAGE = "home";

	/** The Constant ABOUT. */
	private static final String ABOUT = "about";

	/**
	 * Shows the home page.
	 *
	 * @param model            the model
	 * @return the page view
	 */
	@GetMapping(value = "/")
	public String home(final Model model) {
		HOME_LOGGER.info("Going to home page");
		return HOME_PAGE;
	}

    /**
     * Index.
     *
     * @return the string
     */
	@GetMapping("/about")
    public String index() {
		HOME_LOGGER.info("Going to about page");
        return ABOUT;
    }
}