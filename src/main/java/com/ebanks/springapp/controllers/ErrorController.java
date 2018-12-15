package com.ebanks.springapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The controllers handles the error responses.
 */
@ControllerAdvice
@EnableWebMvc
public class ErrorController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

	/**
	 * Exception.
	 *
	 * @param throwable the throwable
	 * @param model     the model
	 * @return the string
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model) {
		LOGGER.error("Exception during execution of SpringSecurity application", throwable);
		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
		model.addAttribute("errorMessage", errorMessage);
		LOGGER.info("errorMessage", throwable);

		return "error";
	}
}
