package com.ebanks.springapps.repositories;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ebanks.springapp.model.elasticsearch.UserEs;
/*
@Repository("userRepositoryElasticSearch")
public interface UserRepositoryElasticSearch extends ElasticsearchRepository<UserEs, Long> {

	    public UserEs findByEmail(String email);

	    public UserEs findOne(long id);

	    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
	    public UserEs findByAuthorsNameUsingCustomQuery(String email);
}*/