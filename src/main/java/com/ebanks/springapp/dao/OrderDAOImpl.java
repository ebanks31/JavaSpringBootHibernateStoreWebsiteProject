package com.ebanks.springapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.Order;

/**
 * The Class PersonDAOImpl.
 */
@SuppressWarnings("unchecked")
@Repository
public class OrderDAOImpl implements OrderDAO {

	private static final Logger ORDER_LOGGER = LoggerFactory.getLogger(OrderDAOImpl.class);

	private static final int LEGAL_AGE = 18;
	private static final String FROM_ORDER_TABLE = "from Order";
	private static final String ADDRESS = "address";
	private static final String AGE = "age";
	private static final String LASTNAME = "lastname";
	private static final String OWNERSHIP = "ownership";
	private static final String NONOWNERSHIP = "non-owner";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 *
	 * @param person the person
	 */
	@Override
	public void addOrder(final Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		ORDER_LOGGER.info("Order saved successfully, Order Details = {}", order);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param person the person
	 */
	@Override
	public final void updateOrder(final Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(order);
		ORDER_LOGGER.info("Order updated successfully, Order Details = {}", order);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	public void removeOrder(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = (Order) session.load(Order.class, new Integer(id));

		if (order != null) {
			session.delete(order);
		}

		ORDER_LOGGER.info("Order deleted successfully, Order details = {}", order);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createQuery(FROM_ORDER_TABLE).list();
		for (Order order : orderList) {
			ORDER_LOGGER.info("Order List:: {}", order);
			ORDER_LOGGER.info("Product.getId: {}", order.getId());
		}

		return orderList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Order getOrderById(final int orderId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Order.class);
		crit.add(Restrictions.eq("id", orderId));
		crit.setMaxResults(1);

		Order order = (Order) crit.uniqueResult();

		ORDER_LOGGER.info("order.getId(): {}", order.getId());

		return (Order) crit.uniqueResult();
	}

}