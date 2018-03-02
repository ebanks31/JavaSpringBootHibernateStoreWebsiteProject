package com.ebanks.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebanks.springapp.model.Role;
import com.ebanks.springapp.model.User;

/**
 * The Class MyUserDetailsService.
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    /** The user service. */
    @Autowired
    private UserService userService;

	/** The users query. */
	@Value("${spring.queries.users-query}")
	private String usersQuery;

	/** The roles query. */
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

    /**
     * Loads user by username(e-mail).
     *
     * @param email the email
     * @return the user details
     */
    public UserDetails loadUserByUsername(String email) {

        User user = userService.getUserByUserName(email);

        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ email);
        }

        boolean passwordMatch = userService.validPassword(user, email);

        if (!passwordMatch) {
            throw new UsernameNotFoundException(
              "Password does not match "+ email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        //Returns a user with valid security credentials.
        return  new org.springframework.security.core.userdetails.User
          (user.getEmail(),
          user.getPassword().toLowerCase(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked,
          getAuthorities(getStringListRoles(user.getRoles())));
    }

    /**
     * Gets the string list roles.
     *
     * @param roles the roles
     * @return the string list roles
     */
    private List<String> getStringListRoles(Set<Role> roles) {
    	return roles.stream().map (i -> i.getRole()).collect (Collectors.toList());
    }

    /**
     * Gets the authorities from a list of roles.
     *
     * @param roles the roles
     * @return the authorities
     */
    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}