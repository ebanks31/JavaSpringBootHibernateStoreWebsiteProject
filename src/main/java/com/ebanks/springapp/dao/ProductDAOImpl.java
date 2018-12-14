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

import com.ebanks.springapp.model.Product;

/**
 * The Class PersonDAOImpl.
 */
@SuppressWarnings("unchecked")
@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger PRODUCT_LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);

	private static final int LEGAL_AGE = 18;
	private static final String FROM_PRODUCT_TABLE = "from Product";
	private static final String PRODUCT_ID = "id";
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
	 *                       }(/
	 * 
	 *                       /** {@inheritDoc}
	 *
	 * @param person         the person
	 */
	@Override
	public void addProduct(final Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
		PRODUCT_LOGGER.info("Product saved successfully, Product Details = {}", product);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param person the person
	 */
	@Override
	public final void updateProduct(final Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
		PRODUCT_LOGGER.info("Product updated successfully, Product Details = {}", product);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	public void removeProduct(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = session.load(Product.class, Integer.valueOf(id));

		if (product != null) {
			session.delete(product);
		}

		PRODUCT_LOGGER.info("Product deleted successfully, Product details = {}", product);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listProducts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productList = session.createQuery(FROM_PRODUCT_TABLE).list();
		for (Product product : productList) {
			PRODUCT_LOGGER.info("Product List::{}", product);
			PRODUCT_LOGGER.info("product.getId: {}", product.getId());
			PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());
			PRODUCT_LOGGER.info("product.getLastName: {}", product.getBrand());
			PRODUCT_LOGGER.info("product.getColor: {}", product.getColor());
			PRODUCT_LOGGER.info("product.getWeight: {}", product.getWeight());
			PRODUCT_LOGGER.info("product.getCost: {}", product.getCost());
		}

		return productList;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Product getProductById(final long productId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("id", productId));
		crit.setMaxResults(1);

		Product product = (Product) crit.uniqueResult();

		PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());

		return (Product) crit.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByName(final String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("name", name));
		crit.setMaxResults(1);

		Product product = (Product) crit.uniqueResult();

		PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());

		return (Product) crit.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByBrand(final String brand) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("brand", brand));
		List<Product> productsByBrand = criteria.list();

		for (Product product : productsByBrand) {
			PRODUCT_LOGGER.info("Product List::{}", product);
			PRODUCT_LOGGER.info("product.getId: {}", product.getId());
			PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());
			PRODUCT_LOGGER.info("product.getLastName: {}", product.getBrand());
			PRODUCT_LOGGER.info("product.getColor: {}", product.getColor());
			PRODUCT_LOGGER.info("product.getWeight: {}", product.getWeight());
			PRODUCT_LOGGER.info("product.getCost: {}", product.getCost());
		}

		return productsByBrand;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByCost(final String cost) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("cost", cost));
		List<Product> productsByCost = criteria.list();

		for (Product product : productsByCost) {
			PRODUCT_LOGGER.info("Product List::{}", product);
			PRODUCT_LOGGER.info("product.getId: {}", product.getId());
			PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());
			PRODUCT_LOGGER.info("product.getLastName: {}", product.getBrand());
			PRODUCT_LOGGER.info("product.getColor: {}", product.getColor());
			PRODUCT_LOGGER.info("product.getWeight: {}", product.getWeight());
			PRODUCT_LOGGER.info("product.getCost: {}", product.getCost());
		}

		return productsByCost;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByWeight(final double weight) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("weight", weight));
		List<Product> productsByWeight = criteria.list();

		for (Product product : productsByWeight) {
			PRODUCT_LOGGER.info("Product List::{}", product);
			PRODUCT_LOGGER.info("product.getId: {}", product.getId());
			PRODUCT_LOGGER.info("product.getFirstName: {}", product.getName());
			PRODUCT_LOGGER.info("product.getLastName: {}", product.getBrand());
			PRODUCT_LOGGER.info("product.getColor: {}", product.getColor());
			PRODUCT_LOGGER.info("product.getWeight: {}", product.getWeight());
			PRODUCT_LOGGER.info("product.getCost: {}", product.getCost());
		}

		return productsByWeight;
	}

}