package com.ebanks.springapp.model;

import java.sql.Date;

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
@Table(name = "CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")

	// Declaring columns of Person table for usage with Hibernate
	private int id;
	private int userId;
	private int productId;
	private Date lastmodified;

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
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the lastmodified
	 */
	public Date getLastmodified() {
		return lastmodified;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastmodified the new lastmodified
	 */
	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + productId + ", lastmodified=" + lastmodified
				+ "]";
	}

}