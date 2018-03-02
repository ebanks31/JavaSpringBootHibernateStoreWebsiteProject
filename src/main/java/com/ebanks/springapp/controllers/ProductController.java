package com.ebanks.springapp.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebanks.springapp.model.Cart;
import com.ebanks.springapp.model.Product;
import com.ebanks.springapp.service.ProductService;

/**
 * The PersonController for handling REST request.
 */
@Controller
public class ProductController {
	private static final Logger PRODUCT_CONTROLLER_LOGGER = Logger.getLogger(ProductController.class);
	private static final String PRODUCT = "products";
	private static final String LIST_PRODUCTS_MODEL = "listProducts";

	/** The product service. */
	@Autowired
	private ProductService productService;

	/**
	 * Sets the user service.
	 *
	 * @param productService the new product service

	@Autowired(required = true)
	@Qualifier(value = "productService")
	public final void setProductService(final ProductService productService) {
		this.productService = productService;
	}	 */

	/**
	 * Retrieves a list of products.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/products")
	public String listProducts(final Model model) {
		model.addAttribute(LIST_PRODUCTS_MODEL,
				productService.listProducts());
		PRODUCT_CONTROLLER_LOGGER.info("Retrieving list of products");

		PRODUCT_CONTROLLER_LOGGER.info("list product: " + this.productService.listProducts());
		return PRODUCT;
	}

	/**
	 * Adds a product.
	 *
	 * @param product the product
	 *
	 * @return the page view
	 */
	@PostMapping(value = "/product/add")
	public String addPerson(@ModelAttribute("productAttribute") Product product) {
		PRODUCT_CONTROLLER_LOGGER.info("product Id: " + product.getId());

		if (product.getId() == 0) {
			// adds new person
			this.productService.addProduct(product);
		} else {
			// Calls update method if person exists
			this.productService.updateProduct(product);
		}

		//return "redirect:/persons";
		return "addProduct";

	}
	/**
	 * Removes a person.
	 *
	 * @param id
	 *            the id
	 * @return the page view
	 */
	@GetMapping(value = "/product/remove/{id}")
	public String removeProduct(@PathVariable("id") final int id) {

		this.productService.removeProduct(id);
		return "redirect:/products";
	}

	/**
	 * Edits a person.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@GetMapping(value = "/product/edit/{id}")
	public String editPerson(@PathVariable("id") final int id, final Model model) {
		model.addAttribute(PRODUCT,
				this.productService.getProductById(id));
		model.addAttribute(LIST_PRODUCTS_MODEL,
				this.productService.listProducts());
		return PRODUCT;
	}

	/**
	 * Adds a product to cart.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@PostMapping(value = "/product/cart/add")
	public String addProductToCart(@ModelAttribute("orderAttribute") Product product,
			@ModelAttribute("cartAttribute") Cart cart) {
		PRODUCT_CONTROLLER_LOGGER.info("Adding order to cart");

		return PRODUCT;
	}

	/**
	 * Adds a list of products to cart.
	 *
	 * @param model
	 *            the model
	 * @return the page view
	 */
	@PostMapping(value = "/products/cart/add")
	public String addProductListToCart(@ModelAttribute("productListAttribute") List<Product> productList,
			@ModelAttribute("cartAttribute") Cart cart) {
		PRODUCT_CONTROLLER_LOGGER.info("Adding order to cart");

		return PRODUCT;
	}
}