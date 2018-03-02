package com.ebanks.springapp.consume;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.ebanks.springapp.model.User;

// TODO: This controller still has to be done.
/**
 * The Account Controller is for handling the REST request from the account page.
 */
@Controller
public class ConsumeRestUserController {

	/** The Constant CART_CONTROLLER_LOGGER. */
	private static final Logger ACCOUNT_CONTROLLER_LOGGER = Logger.getLogger(ConsumeRestUserController.class);

	/** The Constant CART. */
	private static final String ACCOUNT = "account";
	//TODO: Need to more REST End points for Account Controller.

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Shows the user's cart.
	 *
	 * @param model            the model
	 * @return the page view
	 */
	@GetMapping(value = "/consumeUserList")
	public String getAccount(final Model model) {
		String fooResourceUrl
		  = "http://localhost:8080/rest/api/user/userList";
		ResponseEntity<User[]> response
		  = restTemplate.getForEntity(fooResourceUrl + "/1", User[].class);

		ACCOUNT_CONTROLLER_LOGGER.info("Header " + response.getHeaders());
		ACCOUNT_CONTROLLER_LOGGER.info("Body " + response.getBody());
		ACCOUNT_CONTROLLER_LOGGER.info("Status Code " + response.getStatusCode());

		return ACCOUNT;
	}

	 @Bean
	 public RestTemplate rest() {
	 return new RestTemplate();
	 }
}