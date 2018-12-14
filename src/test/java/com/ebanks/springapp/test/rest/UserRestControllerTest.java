package com.ebanks.springapp.test.rest;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ebanks.springapp.SpringBootMvcHibernateApplication;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/*
 *  This class holds the unit tests for Spring MVC Store project.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootMvcHibernateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRestControllerTest {

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
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		second.setId(2);
		second.setFirstName("Fred");
		second.setLastName("Taylor");
		second.setAge(24);

		Mockito.when(userService.listUsers()).thenReturn(Arrays.asList(first, second));
		assertEquals(2, userService.listUsers().size());
		verify(userService, times(1)).listUsers();

		this.mockMvc.perform(get("/rest/api/user/userList").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"));

		verify(userService, times(1)).listUsers();
		verifyNoMoreInteractions(userService);
	}

	/*
	 * @Test public void testSetUserService() throws Exception { // TODO }
	 */
	@Test
	public void testListUsersNull() throws Exception {

		Mockito.when(userService.listUsers()).thenReturn(null);
		assertEquals(2, userService.listUsers().size());
		verify(userService, times(1)).listUsers();

		this.mockMvc.perform(get("/rest/api/user/userList").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"));

		verify(userService, times(1)).listUsers();
		verifyNoMoreInteractions(userService);
	}

	/*
	 * @Test public void testSetUserService() throws Exception { // TODO }
	 */
	@Test
	public void testListUsersEmptyList() throws Exception {

		Mockito.when(userService.listUsers()).thenReturn(Collections.emptyList());
		assertEquals(2, userService.listUsers().size());
		verify(userService, times(1)).listUsers();

		this.mockMvc.perform(get("/rest/api/user/userList").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"));

		verify(userService, times(1)).listUsers();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testListUsersByLastNameASC() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);

		when(userService.listUsersOrderbyLastNameASC()).thenReturn(Arrays.asList(first, second));

		this.mockMvc.perform(get("/users").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))))
				.andExpect(model().attribute("todos",
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("firstName", is("Fred")),
								hasProperty("lastName", is("Taylor")), hasProperty("age", is(24))))));

		verify(userService, times(1)).listUsersOrderbyLastNameASC();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testAddUser() throws Exception {
		User firstUser = new User();
		firstUser.setId(1);
		firstUser.setFirstName("Eroc");
		firstUser.setLastName("Banks");
		firstUser.setAge(17);

		User userMock = Mockito.mock(User.class);

		when(userMock.getId()).thenReturn(new Long(0));

		this.mockMvc.perform(post("/user/add").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))));

		verify(userService, times(1)).addUser(firstUser);
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testRemoveUser() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);

		int id = 1;
		String stringID = String.valueOf(id);

		this.mockMvc.perform(get("/remove" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk());

		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testEditUser() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);

		when(userService.listUsers()).thenReturn(Arrays.asList(first, second));

		int id = 1;
		String stringID = String.valueOf(id);
		when(userService.getUserById(id)).thenReturn(first);

		this.mockMvc.perform(get("/edit" + stringID).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))));

		verify(userService, times(1)).listUsers();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void notFoundURLPath() throws Exception {
		this.mockMvc.perform(get("/notfound").accept(MediaType.TEXT_PLAIN)).andExpect(status().is4xxClientError());
	}

	@Test
	public void foundURLPath() throws Exception {
		this.mockMvc.perform(get("/users").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void foundURLPathValidWithUserObjects() throws Exception {

		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);

		when(userService.listUsersOrderbyLastNameASC()).thenReturn(Arrays.asList(first, second));

		this.mockMvc.perform(get("/users").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("firstName", is("Fred")),
								hasProperty("lastName", is("Taylor")), hasProperty("age", is(24))))));

		verify(userService, times(1)).listUsersOrderbyLastNameASC();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testListUsersByDistinctAddressModel() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);
		first.setAddress("234 Hello Dr.");

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);
		first.setAddress("234 Hello Dr.");

		User third = new User();
		first.setId(3);
		first.setFirstName("Ryan");
		first.setLastName("Matthews");
		first.setAge(22);
		first.setAddress("235 Hello Dr.");

		when(userService.getUsersByDistinctAddress()).thenReturn(Arrays.asList(first, second));

		this.mockMvc.perform(get("/users").accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("firstName", is("Fred")),
								hasProperty("lastName", is("Taylor")), hasProperty("age", is(24))))));

		verify(userService, times(1)).getUsersByDistinctAddress();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testListUsersBySpecificAddress() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);
		first.setAddress("234 Hello Dr.");

		User second = new User();
		first.setId(2);
		first.setFirstName("Fred");
		first.setLastName("Taylor");
		first.setAge(24);
		first.setAddress("234 Hello Dr.");

		User third = new User();
		first.setId(2);
		first.setFirstName("Ryan");
		first.setLastName("Matthews");
		first.setAge(22);
		first.setAddress("235 Hello Dr.");

		String address = "234 Hello Dr.";

		when(userService.getUsersBySpecificAddress(address)).thenReturn(Arrays.asList(first, second));

		this.mockMvc.perform(get("/users" + address).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("user"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp")).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(model().attribute("user", hasSize(2)))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Eroc")),
								hasProperty("lastName", is("Banks")), hasProperty("age", is(17))))))
				.andExpect(model().attribute("user",
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("firstName", is("Fred")),
								hasProperty("lastName", is("Taylor")), hasProperty("age", is(24))))));

		verify(userService, times(1)).getUsersBySpecificAddress(address);
		verifyNoMoreInteractions(userService);
	}

	/*
	 * @Test public void testSetUserService() throws Exception { // TODO }
	 */
	@Ignore("Ignoring test due to sonar violations")
	@Test
	public void testListUsersTest() throws Exception {
		User first = new User();
		first.setId(1);
		first.setFirstName("Eroc");
		first.setLastName("Banks");
		first.setAge(17);

		User second = new User();
		second.setId(2);
		second.setFirstName("Fred");
		second.setLastName("Taylor");
		second.setAge(24);

		Mockito.when(userService.listUsers()).thenReturn(Arrays.asList(first, second));
		assertEquals(2, userService.listUsers().size());
		verify(userService, times(1)).listUsers();

		this.mockMvc.perform(get("/rest/api/user/userList"))
				// .accept(MediaType.TEXT_PLAIN))
				// .andExpect(MockMvcResultMatchers.view().name("user"))
				// .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/user.jsp"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string("")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andDo(print())
				.andExpect(jsonPath("$[0].id").exists()).andExpect(jsonPath("$[0].fn").value("Eroc"))
				.andExpect(jsonPath("$[0].ln").value("Banks")).andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].fn").value("Fred")).andExpect(jsonPath("$[0].ln").value("Taylor"));

		verify(userService, times(1)).listUsers();
		verifyNoMoreInteractions(userService);
	}
}
