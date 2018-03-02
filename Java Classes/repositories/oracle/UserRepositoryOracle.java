package com.ebanks.springapp.repositories.oracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.User;

/**
 * The Interface UserRepositoryOracle.
 */
@Repository("userRepositoryOracle")
public interface UserRepositoryOracle extends JpaRepository<User, Long> {

 	/**
 	 * Find by user by email.
 	 *
 	 * @param email the email
 	 * @return the user
 	 */
 	public User findByEmail(String email);

	 /**
 	 * Find user by id
 	 *
 	 * @param id the id
 	 * @return the user
 	 */
 	public User findOne(long id);
}