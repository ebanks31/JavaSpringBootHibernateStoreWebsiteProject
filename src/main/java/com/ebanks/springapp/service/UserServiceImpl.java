package com.ebanks.springapp.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebanks.springapp.dao.UserDAO;
import com.ebanks.springapp.model.Role;
import com.ebanks.springapp.model.User;
import com.ebanks.springapps.repositories.RoleRepository;
import com.ebanks.springapps.repositories.UserRepository;

/**
 * The Class UserServiceImpl. The class is the service layer for the User Controller.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user list. */
	private final Map<String, User> userList = null;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The user DAO. */
	@Autowired
	private UserDAO userDAO;

	/** The role repository. */
	@Autowired
    private RoleRepository roleRepository;

    /** The b crypt password encoder. */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 *  The Hazelcast instance.
	 *
	 * @param userDAO the new user DAO
	 * @Autowired 	private HazelcastInstance hazelcastInstance;
	 */
    /**
     * Instantiates a new user service impl.
     *
     * @param hazelcastInstance the Hazelcast instance

    @Autowired
    public UserServiceImpl(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }     */

	/**
	 * Sets the person DAO.
	 *
	 * @param userDAO the new user DAO
	 */
	public void setUserDAO(final UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<User> listUsers() {
		//Storing userlist in Hazel in-memory cache under a Map
		List<User> userList = this.userDAO.listUsers();
		//Map<String, List<User>> userHazelCastMap = hazelcastInstance.getMap("userMap");
		//userHazelCastMap.put("userList", this.userDAO.listUsers());

		//return userHazelCastMap.get("userList");
		return userList;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param person
	 *            the person
	 */
	@Override
	@Transactional
	public void addUser(final User user) {
		// Adding user to Hazelcast. Each UserId will be unique so this will be the key for the Map.
		//Map<Integer, User> userHazelCastMap = hazelcastInstance.getMap("userMap");
		//userHazelCastMap.put(user.getId(), user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		this.userDAO.addUser(user);
	}

	/**
	 * Register user.
	 *
	 * @param user the user
	 * @throws Exception the exception
	 */
	public void registerUser(final User user) throws Exception {

		// Checks if a user exist. Throws an exception if user is not found/
		// TODO: New to make customer exception instead of having regular Exception
		if (checkIfUserExists(user)) {
			throw new Exception("User is already found");
		}

        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userDAO.addUser(user);

	}

	/**
	 * Check if user exists.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	private boolean checkIfUserExists(final User user) {
		boolean userFound = false;

		// Check if users already exist
		User foundUser = userDAO.getUserById(user.getId());

		if (foundUser.getId() != 0) {
			userFound = true;
		}

		return userFound;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param person
	 *            the person
	 */
	@Override
	@Transactional
	public void updateUser(final User user) {
		this.userDAO.updateUser(user);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id
	 *            the id
	 */
	@Override
	@Transactional
	public void removeUserById(final long id) {
		this.userDAO.removeUserById(id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id
	 *            the id
	 */
	@Override
	@Transactional
	public void removeUser(final User user) {
		this.userDAO.removeUser(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User getUserById(final long id) {
		return this.userDAO.getUserById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> getUsersByOwnership() {
		return this.userDAO.getUsersByOwnership();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> getUsersByWithOutOwnership() {
		return this.userDAO.getUsersByWithOutOwnership();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param address
	 *            the address
	 */
	@Override
	@Transactional
	public final List<User> getUsersByAddress(final String address) {
		return this.userDAO.getUsersByAddress(address);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<Object[]> getUsersByAddressListByGivenParameters(final List<String> addressList) {
		return this.userDAO.getUsersByAddressListByGivenParameters(addressList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> getUsersByDistinctAddress() {
		return this.userDAO.getUsersByDistinctAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> listUsersOrderbyLastNameASC() {
		return this.userDAO.listUsersOrderbyLastNameASC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> listUsersOrderbyLastNameDESC() {
		return this.userDAO.listUsersOrderbyLastNameDESC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> listUsersAboveOrEqualToLegalAge() {
		return this.userDAO.listUsersAboveOrEqualToLegalAge();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> listUsersLessThanLegalAge() {
		return this.userDAO.listUsersLessThanLegalAge();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<User> getUsersByAverageMinMaxAge() {
		return this.userDAO.getUsersByAverageMinMaxAge();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param address
	 *            the address
	 */
	@Override
	public final List<User> getUsersBySpecificAddress(final String address) {
		return this.userDAO.getUsersBySpecificAddress(address);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param email
	 *            the email
	 */
	@Override
	public final User getUserByUserName(final String email) {
		return this.userDAO.getUserByUserName(email);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param user the user
	 * @param password the password
	 */
	@Override
	public final boolean validPassword(final User user, final String password) {

		boolean passwordMatch = false;

		//Checks if the user's password matches the password from parameter.
		if(user != null && StringUtils.isNotBlank(user.getPassword())
				&& user.getPassword().equals(password)) {
			passwordMatch = true;
		}

		return passwordMatch;
	}

	// Spring Data User Repository methods

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public User save(User user) {
        return userRepository.save(user);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void delete(User user) {
    	userRepository.delete(user);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	/*(
	 * {@inheritDoc}
	 */
	@Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
