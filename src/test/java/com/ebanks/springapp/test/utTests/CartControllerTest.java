package com.ebanks.springapp.test.utTests;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/*
 *  This class holds the unit tests for Spring MVC Store project.
 *  TODO: Need to finish CART Controller.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("classpath:servlet-context.xml")
public class CartControllerTest {

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
	public void testUserCart() throws Exception {
		this.mockMvc.perform(get("/cart").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("cart"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/cart.jsp")).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void testAddCart() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("firstname");
		first.setLastName("lastname");
		first.setAge(17);

		User test = Mockito.mock(User.class);

		when(test.getId()).thenReturn(new Long(0));

		this.mockMvc.perform(post("/user/add").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/cart"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/cart.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("cartList",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("firstname")),
								hasProperty("lastName", is("lastname")), hasProperty("age", is(17))))));

		verify(userService, times(1)).addUser(first);
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testRemoveCart() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("firstname");
		first.setLastName("lastname");
		first.setAge(17);

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);

		int id = 1;
		String stringID = String.valueOf(id);

		this.mockMvc.perform(get("/cart/remove/" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/cart"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/cart.jsp")).andExpect(status().isOk());

		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testEditCart() throws Exception {
		int id = 1;
		String stringID = String.valueOf(id);

		this.mockMvc.perform(get("/cart/edit/" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/cart.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("cart", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("firstname")),
								hasProperty("lastName", is("lastname")), hasProperty("age", is(17))))));
	}
}
