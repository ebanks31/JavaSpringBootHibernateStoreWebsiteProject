package com.ebanks.springapp.test.utTests;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
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

import com.ebanks.springapp.exception.GenericException;
import com.ebanks.springapp.model.User;
import com.ebanks.springapp.service.UserService;

/*
 *  This class holds the unit tests for Spring MVC Store project.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("classpath:servlet-context.xml")
public class RegistrationControllerTest {

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

	@After
	private void cleanUp() {
		reset();
	}

	@Test
	public void testRegistrationUserValid() throws Exception {
		mockUser = new User();
		mockUser.setEmail("test@test.com");
		Mockito.when(userService.findUserByEmail(anyString())).thenReturn(mockUser);

		this.mockMvc.perform(get("/registration", mockUser).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("home"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp")).andExpect(status().isOk())
				.andExpect(content().string(""));

		verify(userService, times(1)).findUserByEmail(anyString());
	}

	@Test
	public void testRegistrationUserNotFound() throws Exception {
		Mockito.when(userService.findUserByEmail(anyString())).thenReturn(null);
		mockUser = new User();
		mockUser.setEmail("test@test.com");

		this.mockMvc.perform(get("/registration", mockUser).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("registration"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/registration.jsp"))
				.andExpect(status().isOk()).andExpect(content().string(""));

		verify(userService, times(1)).findUserByEmail(anyString());
	}

	@Test(expected = GenericException.class)
	public void testRegistrationNull() throws Exception {
		this.mockMvc.perform(get("/registration", mockUser).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.view().name("home"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp")).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

}
