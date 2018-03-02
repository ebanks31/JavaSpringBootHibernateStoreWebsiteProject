package com.ebanks.springapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.Role;

/**
 * The Interface RoleRepository.
 */
@Repository(value = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{

	/**
	 * Find role by role name.
	 *
	 * @param role the role
	 * @return the role
	 */
	Role findByRole(String role);

}