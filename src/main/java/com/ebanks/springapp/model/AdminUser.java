package com.ebanks.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations for Person table. Hibernate provides JPA
 * implementation
 * 
 * @author ebanks
 *
 */
@Entity
@Table(name = "AdminUser")
public class AdminUser extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")

	// Declaring columns of Person table for usage with Hibernate
	private long id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	private int age;
	private String email;
	private String address;
	@Column(name = "phonenumber")
	private String phoneNumber;
	private String ownership;

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	@Override
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	@Override
	public void setAge(final int age) {
		this.age = age;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@Override
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	@Override
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	@Override
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	@Override
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	@Override
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the ownership.
	 *
	 * @return the ownership
	 */
	@Override
	public String getOwnership() {
		return ownership;
	}

	/**
	 * Sets the ownership.
	 *
	 * @param ownership the new ownership
	 */
	@Override
	public void setOwnership(final String ownership) {
		this.ownership = ownership;
	}

	@Override
	public final String toString() {
		return "id=" + id + ", name=" + firstName + " " + lastName + " age = " + age;
	}
}