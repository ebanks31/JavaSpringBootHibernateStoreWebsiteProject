package com.ebanks.springapp.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

/**
 * The class handles the configuration for HazelCast.g
 */
@Configuration
public class HazelcastConfiguration {

	/**
	 * Hazel cast config.
	 *
	 * @return the config
	 */
	@Bean
	public Config hazelCastConfig() {
		return new Config().setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("testCache")
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(20));
	}

}