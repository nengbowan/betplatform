package com.mingben.betplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BetplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetplatformApplication.class, args);
	}
}
