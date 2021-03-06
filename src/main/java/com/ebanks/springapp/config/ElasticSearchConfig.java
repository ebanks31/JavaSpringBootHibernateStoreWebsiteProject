package com.ebanks.springapp.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * This class keeps track of the Elastic Search configurations.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ebanks.springapp.repositories.elasticsearch")
@ComponentScan(basePackages = { "com.ebanks.springapp.service" })
public class ElasticSearchConfig {

	/** The es host. */
	@Value("${elasticsearch.host}")
	private String esHost;

	/** The es port. */
	@Value("${elasticsearch.port}")
	private int esPort;

	/** The es cluster name. */
	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	/**
	 * Client.
	 *
	 * @return the client
	 * @throws Exception the exception
	 */
	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.settingsBuilder().put("cluster.name", esClusterName).build();

		// https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
		return TransportClient.builder().settings(esSettings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
	}

	/*
	 * @Bean public ElasticsearchOperations elasticsearchTemplate() throws Exception
	 * { return new ElasticsearchTemplate(client()); }
	 */
	/*
	 * @Bean public NodeBuilder nodeBuilder() { return new NodeBuilder(); }
	 * 
	 * //Embedded Elasticsearch Server
	 * 
	 * @Bean public ElasticsearchOperations elasticsearchTemplate() { return new
	 * ElasticsearchTemplate(nodeBuilder().local(true).node().client()); }
	 */

}
