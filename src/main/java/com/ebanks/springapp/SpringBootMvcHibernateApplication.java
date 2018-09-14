package com.ebanks.springapp;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main application class for starting this project using Spring Boot.
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.ebanks.springapps.repositories")
@EnableCaching
//@ComponentScan("com.ebanks.springapp.config")
//@EnableElasticsearchRepositories(basePackages = "com.ebanks.springapp.repositories")
@EntityScan("com.ebanks.springapp.model")
public class SpringBootMvcHibernateApplication extends SpringBootServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.web.support.SpringBootServletInitializer#configure(
	 * org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootMvcHibernateApplication.class);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// SpringApplication.run(SpringBootMvcHibernateApplication.class, args);
		/*
		 * SpringApplication application = new
		 * SpringApplication(SpringBootMvcHibernateApplication.class);
		 * application.addListeners(new ApplicationPidFileWriter("./bin/app.pid"));
		 * application.run();
		 */

		SpringApplication.run(SpringBootMvcHibernateApplication.class, args);

	}

	/**
	 * Session factory.
	 *
	 * @param hemf the hemf
	 * @return the session factory
	 */
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}
}
