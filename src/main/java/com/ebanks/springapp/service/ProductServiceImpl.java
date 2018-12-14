package com.ebanks.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebanks.springapp.dao.ProductDAO;
import com.ebanks.springapp.model.Product;

/**
 * The Class UserServiceImpl. The class is the service layer for the User
 * Controller.
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	/**
	 * The Hazelcast instance.
	 * 
	 * @Autowired private HazelcastInstance hazelcastInstance;
	 */
	/**
	 * Instantiates a new product service impl.
	 *
	 * @param hazelcastInstance the Hazelcast instance
	 * @Autowired public ProductServiceImpl(HazelcastInstance hazelcastInstance) {
	 *            this.hazelcastInstance = hazelcastInstance; }
	 */

	/**
	 * Sets the product DAO.
	 *
	 * @param productDAO the new product dao
	 */
	public void setProductDAO(final ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param product the product
	 */
	@Override
	@Transactional
	public void addProduct(final Product product) {
		// Adding product to Hazelcast. Each UserId will be unique so this will be the
		// key for the Map.
		// Map<Integer, Product> userHazelCastMap =
		// hazelcastInstance.getMap("productMap");
		// userHazelCastMap.put(product.getId(), product);

		this.productDAO.addProduct(product);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void removeProduct(final int id) {
		this.productDAO.removeProduct(id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param product the product
	 */
	@Override
	@Transactional
	public void updateProduct(final Product product) {
		this.productDAO.updateProduct(product);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public Product getProductById(final long id) {
		return this.productDAO.getProductById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Product> listProducts() {
		// Map<String, List<Product>> userHazelCastMap =
		// hazelcastInstance.getMap("userMap");
		// userHazelCastMap.put("userList", this.productDAO.listProducts());
		return this.productDAO.listProducts();
	}
}
