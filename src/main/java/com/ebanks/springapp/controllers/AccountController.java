package com.ebanks.springapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ebanks.springapp.model.Cart;

// TODO: This controller still has to be done.
/**
 * The Account Controller is for handling the REST request from the account page.
 */
@Controller
public class AccountController {

	/** The Constant CART_CONTROLLER_LOGGER. */
	private static final Logger ACCOUNT_CONTROLLER_LOGGER = Logger.getLogger(AccountController.class);

	/** The Constant CART. */
	private static final String ACCOUNT = "account";
	//TODO: Need to more REST End points for Account Controller.

	/**
	 * Shows the user's cart.
	 *
	 * @param model            the model
	 * @return the page view
	 */
	@GetMapping(value = "/account")
	public String getAccount(final Model model) {
		ACCOUNT_CONTROLLER_LOGGER.info("Going to the account page");

		return ACCOUNT;
	}

}