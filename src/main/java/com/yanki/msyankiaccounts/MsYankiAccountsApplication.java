package com.yanki.msyankiaccounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsYankiAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsYankiAccountsApplication.class, args);
	}

}
