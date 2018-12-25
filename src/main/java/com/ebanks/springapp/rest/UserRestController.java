package com.ebanks.springapp.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The UserController for handling REST request.
 */
@RestController
@RequestMapping("/rest/api/user")
public class UserRestController {

	/** The Constant logger for user controller. */
	private static final Logger USER_CONTROLLER_LOGGER = Logger.getLogger(UserRestController.class);

	/** The Constant PERSON. */
	private static final String USER = "user";

	/** The Constant LIST_USERS_MODEL. */
	private static final String LIST_USERS_MODEL = "listUser";

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Instantiates a new user controller.
	 *
	 * @param userService           the user service
	 * @param bCryptPasswordEncoder the b crypt password encoder
	 */
	@Autowired
	private UserRestController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService the new user service
	 * 
	 * @Autowired(required = true)
	 * @Qualifier(value = "userService") public final void setUserService(final
	 *                  UserService userService) { this.userService = userService; }
	 */

	/**
	 * Retrieves a list of people.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/userList", produces = { "application/json" })
	@ApiOperation(value = "Returns list of users", notes = "Returns a complete list of users.", response = User.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of appointments", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "User is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<List<User>> listUsers() {
		USER_CONTROLLER_LOGGER.info("Retrieving list of people");

		USER_CONTROLLER_LOGGER.info("list person: " + this.userService.listUsers());

		return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
	}

	/**
	 * Retrieves a list people by last name in ascending order.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsByLastNameASC")
	@ApiOperation(value = "Returns list of users by last name ascending", notes = "Returns a complete list of users by last name ascending.", response = User.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of appointments", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "User is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public String listPersonsByLastNameASC(final Model model) {
		model.addAttribute(LIST_USERS_MODEL, this.userService.listUsersOrderbyLastNameASC());
		return "personByLastName";
	}

	/**
	 * Adds a person.
	 *
	 * @param user the user
	 * @return the page view
	 */
	@PostMapping(value = "/user/add")
	public ResponseEntity<?> addPerson(@RequestBody User user) {
		USER_CONTROLLER_LOGGER.info("person Id: " + user.getId());

		if (user.getId() == 0) {
			// Encrypts the user's password.
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

			// adds new person. Person Id starts at 0 for a new Person object.
			this.userService.addUser(user);
		} else {
			// Encrypts the user's password.
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

			// Calls update method if person exists
			this.userService.updateUser(user);
		}

		// return "redirect:/persons";
		return new ResponseEntity<>("User has been added", HttpStatus.OK);
	}

	/**
	 * Removes a person.
	 *
	 * @param id the id
	 * @return the page view
	 */
	@GetMapping(value = "/removeUser/{id}")
	public ResponseEntity<?> removePerson(@RequestBody User user) {

		this.userService.removeUser(user);
		return new ResponseEntity<>("User has been removed", HttpStatus.OK);
	}

	/**
	 * Removes a person.
	 *
	 * @param id the id
	 * @return the page view
	 */
	@GetMapping(value = "/remove/{id}")
	public ResponseEntity<?> removePerson(@PathVariable("id") final int id) {

		this.userService.removeUserById(id);
		return new ResponseEntity<>("User has been removed", HttpStatus.OK);
	}

	/**
	 * Edits a person.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/edit/{id}")
	public ResponseEntity<?> editPerson(@PathVariable("id") final int id, final Model model) {
		model.addAttribute(USER, this.userService.getUserById(id));
		model.addAttribute(LIST_USERS_MODEL, this.userService.listUsers());
		return new ResponseEntity<>("User has been edited", HttpStatus.OK);
	}

	/**
	 * Retrieves a list of people that are above the legal age (18).
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsAboveLegalAgeASC")
	public String listPersonsAboveLegalAgeASC(final Model model) {
		model.addAttribute(LIST_USERS_MODEL, this.userService.listUsersAboveOrEqualToLegalAge());
		return USER;
	}

	/**
	 * Retrieves a list of people that are owners.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsWithOwnership")
	public String listPersonsWithOwnerShip(final Model model) {
		model.addAttribute(LIST_USERS_MODEL, this.userService.getUsersByOwnership());
		return USER;
	}

	/**
	 * List persons.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsWithOutOwnership")
	public String listPersonsWithOutOwnerShip(final Model model) {
		model.addAttribute(LIST_USERS_MODEL, this.userService.getUsersByWithOutOwnership());
		return USER;
	}

	/**
	 * Retrieves a list of people by distinct address.
	 *
	 * @param model the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsByDistinctAddress")
	public String listPersonsByDistinctAddress(final Model model) {
		model.addAttribute(USER, new User());
		model.addAttribute(LIST_USERS_MODEL, this.userService.getUsersByDistinctAddress());
		return USER;
	}

	/**
	 * Retrieves a list of people by a specified address.
	 *
	 * @param address the address
	 * @param model   the model
	 * @return the page view
	 */
	@GetMapping(value = "/personsBySpecificAddress{address}")
	public String listPersonsBySpecificAddress(@PathVariable("address") final String address, final Model model) {
		model.addAttribute(LIST_USERS_MODEL, this.userService.getUsersBySpecificAddress(address));
		return USER;
	}
}