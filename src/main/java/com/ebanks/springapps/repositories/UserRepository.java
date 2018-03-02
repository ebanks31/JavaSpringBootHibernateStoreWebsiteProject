package com.ebanks.springapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 public User findByEmail(String email);

	 public User findOne(long id);
}