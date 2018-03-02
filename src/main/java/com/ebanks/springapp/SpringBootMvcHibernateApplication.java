package com.ebanks.springapp;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.ebanks.springapps.repositories")
//@ComponentScan("com.ebanks.springapp.config")
//@EnableElasticsearchRepositories(basePackages = "com.ebanks.springapp.repositories")
@EntityScan("com.ebanks.springapp.model")
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

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }
}
