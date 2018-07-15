package com.ebanks.springapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.ebanks.springapp.model.User;

/**
 * The Class PersonDAOImpl.
 */
@SuppressWarnings("unchecked")
@Repository
public class AdminUserDAOImpl implements AdminUserDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

	private static final int LEGAL_AGE = 18;
	private static final String FROM_PERSON_TABLE = "from Person";
	private static final String ADDRESS = "address";
	private static final String AGE = "age";
	private static final String LASTNAME = "lastname";
	private static final String OWNERSHIP = "ownership";
	private static final String NONOWNERSHIP = "non-owner";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory the new session factory
	 * 
	 *                       public void setSessionFactory(final SessionFactory
	 *                       sessionFactory) { this.sessionFactory = sessionFactory;
	 *                       }
	 */

	/**
	 * {@inheritDoc}
	 *
	 * @param person the person
	 */
	@Override
	public void addPerson(final User person) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(person);
		LOGGER.info("Person saved successfully, Person Details = {}", person);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param person the person
	 */
	@Override
	public final void updatePerson(final User person) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(person);
		LOGGER.info("Person updated successfully, Person Details = {}", person);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		for (User person : personsList) {
			LOGGER.info("Person List: {}", person);
			LOGGER.info("person.getId: {}", person.getId());
			LOGGER.info("person.getFirstName: {}", person.getFirstName());
			LOGGER.info("person.getLastName: {}", person.getLastName());
			LOGGER.info("person.getAge: {}", person.getAge());
		}

		LOGGER.info("personsList: {}", personsList);

		return personsList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersonsOrderbyLastNameASC() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		Criteria criteria = session.createCriteria(User.class).addOrder(Order.asc(LASTNAME));
		List<User> countList = criteria.list();
		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return countList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersonsOrderbyLastNameDESC() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		Criteria criteria = session.createCriteria(User.class).addOrder(Order.desc(LASTNAME));
		List<User> countList = criteria.list();
		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return countList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersonsAboveOrEqualToLegalAge() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		Criteria criteria = session.createCriteria(User.class).addOrder(Order.asc(AGE));

		criteria.add(Restrictions.ge(AGE, LEGAL_AGE));

		List<User> countList = criteria.list();
		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return countList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPersonsLessThanLegalAge() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		Criteria criteria = session.createCriteria(User.class).addOrder(Order.asc(AGE));

		criteria.add(Restrictions.lt(AGE, LEGAL_AGE));
		List<User> countList = criteria.list();
		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return countList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getPersonListAverageMinMaxAge() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.min(AGE));
		projectionList.add(Projections.max(AGE));
		projectionList.add(Projections.avg(AGE));
		criteria.setProjection(projectionList);
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	public User getPersonById(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User person = session.load(User.class, Integer.valueOf(id));
		LOGGER.info("Person loaded successfully, Person details = {}", person);
		return person;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	public void removePerson(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User person = session.load(User.class, Integer.valueOf(id));
		if (person != null) {
			session.delete(person);
		}
		LOGGER.info("Person deleted successfully, person details = {}", person);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param address the address
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final List<User> getPeopleByAddress(final String address) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();

		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq(ADDRESS, address));

		// ProjectionList columns = Projections.projectionList()
		// .add(Projections.property(ADDRESS));
		// criteria.setProjection(columns);

		List<User> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return personsCriteriaList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final List<User> getPeopleByWithOwnership() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();

		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq(OWNERSHIP, OWNERSHIP));
		List<User> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return personsCriteriaList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final List<User> getPeopleByWithOutOwnership() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();

		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq(OWNERSHIP, NONOWNERSHIP));
		List<User> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return personsCriteriaList;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param addressList the address list
	 */
	@Override
	public final List<Object[]> getAddressListByGivenParameters(final List<String> addressList) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();
		// Gets address by ArrayList of Strings passed in.
		// Criteria criteria = session.createCriteria(Person.class);

		Criteria criteria = session.createCriteria(User.class);

		if (!CollectionUtils.isEmpty(addressList)) {
			for (int i = 1; i < addressList.size(); i++) {
				criteria.add(Restrictions.or(Restrictions.eq(ADDRESS, addressList.get(i)),
						Restrictions.eq(ADDRESS, addressList.get(i))));
			}
		}

		ProjectionList columns = Projections.projectionList().add(Projections.property(ADDRESS));
		criteria.setProjection(Projections.distinct(columns));

		List<Object[]> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}

		return personsCriteriaList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllDistinctAddress() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();

		Criteria criteria = session.createCriteria(User.class);
		ProjectionList columns = Projections.projectionList().add(Projections.property(ADDRESS));
		criteria.setProjection(Projections.distinct(columns));

		List<User> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {}", person);
		}
		return personsCriteriaList;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param address the address
	 */
	@Override
	public List<User> personsBySpecificAddress(final String address) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> personsList = session.createQuery(FROM_PERSON_TABLE).list();

		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq(ADDRESS, ADDRESS));

		List<User> personsCriteriaList = criteria.list();

		for (User person : personsList) {
			LOGGER.info("Person List:: {} ", person);
		}

		return personsCriteriaList;
	}

}