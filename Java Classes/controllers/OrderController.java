package com.ebanks.springapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebanks.springapp.model.Cart;
import com.ebanks.springapp.model.Order;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.OrderService;

/**
 * The HomeController for handling REST request from the home page.
 */
@Controller
public class OrderController {

	/** The Constant ORDER_CONTROLLER_LOGGER. */
	private static final Logger ORDER_CONTROLLER_LOGGER = Logger.getLogger(OrderController.class);

	/** The Constant ORDER. */
	private static final String ORDER = "order";

	/** The order service. */
	@Autowired
	OrderService orderService;

	// TODO: Need to more REST End points for Order Controller.

	/**
	 * Shows the user's order page.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/orders")
	public String getOrders(final Model model) {
		ORDER_CONTROLLER_LOGGER.info("Going to home page");

		return ORDER;
	}

	/**
	 * Shows the user's order page.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/orders/{id}")
	public String getOrdersById(final Model model) {
		ORDER_CONTROLLER_LOGGER.info("Getting order by Id");

		return ORDER;
	}

	/**
	 * Adds an order.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@PostMapping(value = "/orders/add")
	public String addOrder(@ModelAttribute("orderAttribute") Order order) {
		ORDER_CONTROLLER_LOGGER.info("Adding order");

		return ORDER;
	}

	/**
	 * Removes an order.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/orders/remove/{id}")
	public String removeOrder(@PathVariable("id") final int id) {
		ORDER_CONTROLLER_LOGGER.info("Removing order");

		return ORDER;
	}

	/**
	 * Edits an order.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/orders/edit/{id}")
	public String editOrder(@PathVariable("id") final int id, final Model model) {
		ORDER_CONTROLLER_LOGGER.info("Editing order");

		return ORDER;
	}

}