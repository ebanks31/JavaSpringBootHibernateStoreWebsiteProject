package com.ebanks.springapp.test.utTests;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ebanks.springapp.model.Product;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.ProductService;

/*
 *  This class holds the unit tests for Spring MVC Store project.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("classpath:servlet-context.xml")
public class ProductControllerTest {
	private static final String LIST_PRODUCTS_MODEL = "listProducts";

	@Rule
	PowerMockRule powerMockRule = new PowerMockRule();

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@Mock
	User mockUser;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testListProducts() throws Exception {
		Product firstProduct = new Product();
		firstProduct.setId(1);
		firstProduct.setBrand("Test Brand");
		firstProduct.setColor("Blue");
		firstProduct.setName("Chair");

		Product secondProduct = new Product();
		secondProduct.setId(2);
		firstProduct.setBrand("Test Brand2");
		firstProduct.setColor("Black");
		firstProduct.setName("Computer");

		Mockito.when(productService.listProducts()).thenReturn(Arrays.asList(firstProduct, secondProduct));
		assertEquals(2, productService.listProducts().size());
		this.mockMvc.perform(get("/products"))
				// .accept(MediaType.TEXT_PLAIN))
				// .andExpect(MockMvcResultMatchers.view().name("user"))
				// .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"))
				.andExpect(status().isOk()).andExpect(content().string(""))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL, hasSize(2)))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL,
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("brand", is("Test Brand")),
								hasProperty("name", is("Chair")), hasProperty("color", is("Blue"))))))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL,
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("brand", is("Test Brand2")),
								hasProperty("name", is("Computer")), hasProperty("color", is("Black"))))));

		verify(productService, times(1)).listProducts();
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testListProductsNull() throws Exception {
		Mockito.when(productService.listProducts()).thenReturn(null);
		assertEquals(0, productService.listProducts().size());
		this.mockMvc.perform(get("/products"))
				// .accept(MediaType.TEXT_PLAIN))
				// .andExpect(MockMvcResultMatchers.view().name("user"))
				// .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"))
				.andExpect(status().isOk()).andExpect(content().string(""))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL, hasSize(0)));
		verify(productService, times(1)).listProducts();
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testListUsersEmpty() throws Exception {
		Mockito.when(productService.listProducts()).thenReturn(Collections.emptyList());
		assertEquals(0, productService.listProducts().size());
		this.mockMvc.perform(get("/products"))
				// .accept(MediaType.TEXT_PLAIN))
				// .andExpect(MockMvcResultMatchers.view().name("user"))
				// .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"))
				.andExpect(status().isOk()).andExpect(content().string(""))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL, hasSize(0)));

		verify(productService, times(1)).listProducts();
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testListUsersEmptyNewProduct() throws Exception {
		Product firstProduct = new Product();
		Product secondProduct = new Product();

		Mockito.when(productService.listProducts()).thenReturn(Arrays.asList(firstProduct, secondProduct));
		assertEquals(2, productService.listProducts().size());
		this.mockMvc.perform(get("/products"))
				// .accept(MediaType.TEXT_PLAIN))
				// .andExpect(MockMvcResultMatchers.view().name("user"))
				// .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"))
				.andExpect(status().isOk()).andExpect(content().string(""))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL, hasSize(2)));

		verify(productService, times(1)).listProducts();
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testAddProductNewProduct() throws Exception {
		Product firstProduct = new Product();
		firstProduct.setId(1);
		firstProduct.setBrand("Test Brand");
		firstProduct.setColor("Blue");
		firstProduct.setName("Chair");

		Product mockProduct = Mockito.mock(Product.class);

		when(mockProduct.getId()).thenReturn(new Long(0));

		this.mockMvc.perform(post("/products/add").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL,
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("brand", is("Test Brand")),
								hasProperty("name", is("Chair")), hasProperty("color", is("Blue"))))));

		verify(productService, times(1)).addProduct(firstProduct);
		verify(productService, times(0)).updateProduct(firstProduct);

		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testAddProductToExistingProducts() throws Exception {
		Product firstProduct = new Product();
		firstProduct.setId(1);
		firstProduct.setBrand("Test Brand");
		firstProduct.setColor("Blue");
		firstProduct.setName("Chair");

		Product mockProduct = Mockito.mock(Product.class);

		when(mockProduct.getId()).thenReturn(new Long(1));

		this.mockMvc.perform(post("/products/add").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL,
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("brand", is("Test Brand")),
								hasProperty("name", is("Chair")), hasProperty("color", is("Blue"))))));

		verify(productService, times(0)).addProduct(firstProduct);
		verify(productService, times(1)).updateProduct(firstProduct);

		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testRemoveProduct() throws Exception {
		long id = 1;
		String productId = String.valueOf(id);

		this.mockMvc.perform(get("/product/remove" + productId).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/products"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products.jsp"))
				.andExpect(status().isOk());

		verifyNoMoreInteractions(productService);
	}

	@Test
	public void testEditProducts() throws Exception {
		Product firstProduct = new Product();
		firstProduct.setId(1);
		firstProduct.setBrand("Test Brand");
		firstProduct.setColor("Blue");
		firstProduct.setName("Chair");

		Product secondProduct = new Product();
		secondProduct.setId(2);
		firstProduct.setBrand("Test Brand2");
		firstProduct.setColor("Black");
		firstProduct.setName("Computer");

		when(productService.listProducts()).thenReturn(Arrays.asList(firstProduct, secondProduct));

		long id = 1;
		String stringID = String.valueOf(id);
		when(productService.getProductById(id)).thenReturn(firstProduct);

		this.mockMvc.perform(get("/product/edit/" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute(LIST_PRODUCTS_MODEL,
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("brand", is("Test Brand")),
								hasProperty("name", is("Chair")), hasProperty("color", is("Blue"))))));

		verify(productService, times(1)).listProducts();
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void foundURLPath() throws Exception {
		this.mockMvc.perform(get("/products").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
				.andExpect(content().string(""));
	}
}
