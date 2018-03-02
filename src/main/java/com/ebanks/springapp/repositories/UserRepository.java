package com.ebanks.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

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