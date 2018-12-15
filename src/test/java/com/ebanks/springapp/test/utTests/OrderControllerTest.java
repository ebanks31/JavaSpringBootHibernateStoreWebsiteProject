package com.ebanks.springapp.test.utTests;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ebanks.springapp.model.Order;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/*
 *  This class holds the unit tests for Spring MVC Store project.
 *  TODO: Need to finish order Controller.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("classpath:servlet-context.xml")
public class OrderControllerTest {

	@Rule
	PowerMockRule powerMockRule = new PowerMockRule();

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@Mock
	User mockUser;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/*
	 * @Test public void testSetUserService() throws Exception { // TODO }
	 */
	@Test
	public void testListUsers() throws Exception {
		this.mockMvc.perform(get("/orders").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("order"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/order.jsp")).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void testAddorder() throws Exception {
		Order firstOrder = new Order();
		firstOrder.setId(1);
		firstOrder.setUserId(1);
		firstOrder.setProductId(2);

		Order test = Mockito.mock(Order.class);

		when(test.getId()).thenReturn(new Integer(0));

		this.mockMvc.perform(post("/order/add").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/order"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/order.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("orderList", hasSize(2)))
				.andExpect(model().attribute("cart", hasItem(allOf(hasProperty("id", is(1)),
						hasProperty("userId", is(1)), hasProperty("productId", is(2))))));

		// verify(userService, times(1)).addUser(first);
		// verifyNoMoreInteractions(userService);
	}

	@Test
	public void testRemoveorder() throws Exception {
		Order firstOrder = new Order();
		firstOrder.setId(1);
		firstOrder.setUserId(1);
		firstOrder.setProductId(2);

		Order secondOrder = new Order();
		firstOrder.setId(3);
		firstOrder.setUserId(4);
		firstOrder.setProductId(5);

		int id = 1;
		String stringID = String.valueOf(id);

		this.mockMvc.perform(get("/order/remove/" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/order"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/order.jsp")).andExpect(status().isOk());

		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testEditorder() throws Exception {
		int id = 1;
		String stringID = String.valueOf(id);

		this.mockMvc.perform(get("/order/edit/" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("order"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/order.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("orderList", hasSize(2)))
				.andExpect(model().attribute("order", hasItem(allOf(hasProperty("id", is(1)),
						hasProperty("userId", is(1)), hasProperty("productId", is(2))))));
	}
}
