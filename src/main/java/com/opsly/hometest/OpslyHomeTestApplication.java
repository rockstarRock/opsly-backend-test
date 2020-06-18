package com.opsly.hometest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class OpslyHomeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpslyHomeTestApplication.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(9);
		executor.setThreadNamePrefix("SocialMedia-");
		executor.initialize();
		return executor;
	}
}
