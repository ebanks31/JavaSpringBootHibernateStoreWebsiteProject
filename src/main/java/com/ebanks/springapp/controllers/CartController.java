package com.ebanks.springapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ebanks.springapp.model.Cart;

/**
 * The CartController for handling REST request from the cart page.
 */
@Controller
public class CartController {

	/** The Constant CART_CONTROLLER_LOGGER. */
	private static final Logger CART_CONTROLLER_LOGGER = Logger.getLogger(CartController.class);

	/** The Constant CART. */
	private static final String CART = "cart";
	// TODO: Need to more REST End points for Cart Controller.

	/**
	 * Shows the user's cart.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/cart")
	public String home(final Model model) {
		CART_CONTROLLER_LOGGER.info("Going to cart page");

		return CART;
	}

	/**
	 * Shows the user's order page.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/cart/{id}")
	public String getCartById(final Model model) {
		CART_CONTROLLER_LOGGER.info("Getting order by Id");

		return CART;
	}

	/**
	 * Adds an order.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@PostMapping(value = "/cart/add")
	public String addCart(@ModelAttribute("cartAttribute") Cart cart) {
		CART_CONTROLLER_LOGGER.info("Adding order");

		return CART;
	}

	/**
	 * Removes an order.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/cart/remove/{id}")
	public String removeCart(@PathVariable("id") final int id) {
		CART_CONTROLLER_LOGGER.info("Removing order");

		return CART;
	}

	/**
	 * Edits an order.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/cart/edit/{id}")
	public String editOrder(@PathVariable("id") final int id, final Model model) {
		CART_CONTROLLER_LOGGER.info("Editing order");

		return CART;
	}
}