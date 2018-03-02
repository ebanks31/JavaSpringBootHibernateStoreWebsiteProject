package com.ebanks.springapp.repositories.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.User;

/**
 * The Interface UserRepositoryMySQL.
 */
@Repository("userRepositoryMySQL")
public interface UserRepositoryMySQL extends JpaRepository<User, Long> {

 	/**
 	 * Find user by email.
 	 *
 	 * @param email the email
 	 * @return the user
 	 */
 	public User findByEmail(String email);

	 /**
 	 * Find user by id.
 	 *
 	 * @param id the id
 	 * @return the user
 	 */
 	public User findOne(long id);
}