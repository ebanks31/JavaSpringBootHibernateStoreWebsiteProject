package com.ebanks.springapp.service;

import java.util.List;

import com.ebanks.springapp.model.User;

// TODO: Auto-generated Javadoc
/**
 * The interface class for the UserService.
 */
public interface UserService {

    /**
     * Adds the person.
     *
     * @param person the person
     */
    void addUser(User person);

    /**
     * Update person.
     *
     * @param person the person
     */
    void updateUser(User person);

    /**
     * List persons.
     *
     * @return the list
     */
    List<User> listUsers();

    /**
     * Gets the person by id.
     *
     * @param id the id
     * @return the person by id
     */
    User getUserById(long id);

    /**
     * Removes the person.
     *
     * @param id the id
     */
    void removeUserById(long id);

    /**
     * Removes the person.
     *
     * @param user the user
     */
    void removeUser(User user);

    /**
     * Gets the users by address.
     *
     * @param address the address
     * @return the people by address
     */
    List<User> getUsersByAddress(String address);

    /**
     * Gets the users by with ownership.
     *
     * @return the people by with ownership
     */
    List<User> getUsersByOwnership();

    /**
     * Gets the users by without ownership.
     *
     * @return the people by without ownership
     */
    List<User> getUsersByWithOutOwnership();

	/**
	 * Gets the address list.
	 *
	 * @param addressList the address list
	 * @return the address list
	 */
	List<Object[]> getUsersByAddressListByGivenParameters(List<String> addressList);

	/**
	 * Gets users by distinct address.
	 *
	 * @return the distinct address by users
	 */
	List<User> getUsersByDistinctAddress();

	/**
	 * List users that are ordered by last name asc.
	 *
	 * @return the list
	 */
	List<User> listUsersOrderbyLastNameASC();

	/**
	 * List users that are ordered by last name desc.
	 *
	 * @return the list
	 */
	List<User> listUsersOrderbyLastNameDESC();

	/**
	 * List users above or equal to legal age.
	 *
	 * @return the list
	 */
	List<User> listUsersAboveOrEqualToLegalAge();


	/**
	 * List users by less than legal age.
	 *
	 * @return the list
	 */
	List<User> listUsersLessThanLegalAge();

	/**
	 * Gets the users by average min max age.
	 *
	 * @return the person list average min max age
	 */
	List<User> getUsersByAverageMinMaxAge();

	/**
	 * Users by specific address.
	 *
	 * @param address the address
	 * @return the users by specific address
	 */
	List<User> getUsersBySpecificAddress(String address);

	/**
	 * Gets the user by user name.
	 *
	 * @param email the email
	 * @return the user by user name (email)
	 */
	User getUserByUserName(String email);

	/**
	 * Valid the password.
	 *
	 * @param user the user
	 * @param password the password
	 * @return true, if password matches, otherwise false
	 */
	boolean validPassword(User user, String password);

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findUserByEmail(String email);

	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	void saveUser(User user);

	/**
	 * Save.
	 *
	 * @param user the user
	 * @return the user
	 */
	User save(User user);

	/**
	 * Delete.
	 *
	 * @param user the user
	 */
	void delete(User user);

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findOne(long id);

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	Iterable<User> findAll();
}
