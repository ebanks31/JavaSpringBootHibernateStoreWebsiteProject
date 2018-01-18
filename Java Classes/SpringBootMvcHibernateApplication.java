package com.ebanks.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMvcHibernateApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootMvcHibernateApplication.class);
    }

	public static void main(String[] args) {
		//SpringApplication.run(SpringBootMvcHibernateApplication.class, args);
		/*
		SpringApplication application = new SpringApplication(SpringBootMvcHibernateApplication.class);
		application.addListeners(new ApplicationPidFileWriter("./bin/app.pid"));
		application.run();
		*/

        SpringApplication.run(SpringBootMvcHibernateApplication.class, args);

	}
}
