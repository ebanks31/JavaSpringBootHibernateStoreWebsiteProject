package com.ebanks.springapp.model.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Entity bean with JPA annotations for User table. Hibernate provides JPA
 * implementation
 *
 * @author ebanks
 *
 */
@Document(indexName = "test", type = "books")
public class UserEs {

	/** The id. */
	@Id
	private int id;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The age. */
	private int age;

	/** The email. */
	private String email;
	// TODO: Username is email. Will keep email field here for now and decide if I
	// separate the username from the email.

	/** The address. */
	private String address;

	/** The phone number. */
	private String phoneNumber;

	/** The ownership. */
	private String ownership;

	/** The username. */
	// @NotNull
	// @NotEmpty
	// @ValidEmail
	private String username;

	/** The password. */
	//@NotNull
	// @NotEmpty
	private String password;

	/** The matching password. */
	// @NotNull
	// @NotEmpty
	//@JsonIgnore
	private String matchingPassword;

	/** The boolean indicating whether a user is active. */
	private boolean active;

	/** The display name. */
	private String displayName;

	// TODO: Need to include roles for elastic search

	public UserEs(int id, String firstName, String lastName, int age, String email, String address, String phoneNumber,
			String ownership, String username, String password, String matchingPassword, boolean active,
			String displayName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.ownership = ownership;
		this.username = username;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.active = active;
		this.displayName = displayName;
	}

	/**
	 * Gets the matching password.
	 *
	 * @return the matching password
	 */
	public String getMatchingPassword() {
		return matchingPassword;
	}

	/**
	 * Sets the matching password.
	 *
	 * @param matchingPassword
	 *            the new matching password
	 */
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	/**
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName
	 *            the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Checks if the user is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the user to be active or not.
	 *
	 * @param active
	 *            the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age
	 *            the new age
	 */
	public void setAge(final int age) {
		this.age = age;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param user_id
	 *            the new id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber
	 *            the new phone number
	 */
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the ownership.
	 *
	 * @return the ownership
	 */
	public String getOwnership() {
		return ownership;
	}

	/**
	 * Sets the ownership.
	 *
	 * @param ownership
	 *            the new ownership
	 */
	public void setOwnership(final String ownership) {
		this.ownership = ownership;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id=" + id + ", firstname=" + firstName + " lastname=" + lastName + " age = " + age;
	}
}