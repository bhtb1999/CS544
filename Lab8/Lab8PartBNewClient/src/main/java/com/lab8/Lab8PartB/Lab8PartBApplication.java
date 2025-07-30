package com.lab8.Lab8PartB;

import com.lab8.Lab8PartB.domain.Account;
import com.lab8.Lab8PartB.domain.AccountEntry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Lab8PartBApplication implements CommandLineRunner {
	private RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(Lab8PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String baseUrl = "http://localhost:8080/account";

//		Account account1 = new Account("111");
//		Account account2 = new Account("222");
//
//		restTemplate.postForEntity(baseUrl, account1, Account.class);
//		restTemplate.postForEntity(baseUrl, account2, Account.class);
//
//		AccountEntry deposit = new AccountEntry(500, "Initial deposit", "111", "");
//		restTemplate.postForEntity(baseUrl + "/deposit/111", deposit, String.class);
//
//		AccountEntry withdraw = new AccountEntry(100, "ATM", "111", "");
//		restTemplate.postForEntity(baseUrl + "/withdraw/111", withdraw, String.class);
//
//		AccountEntry transfer = new AccountEntry(200, "Transfer to 222", "111", "");
//		ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/transfer/111/222", transfer, String.class);
//


		try {
			Account account1 = new Account("111");
			Account account2 = new Account("222");

			restTemplate.postForEntity(baseUrl, account1, Account.class);
			restTemplate.postForEntity(baseUrl, account2, Account.class);

			AccountEntry deposit = new AccountEntry(100, "Small deposit", "111", "");
			restTemplate.postForEntity(baseUrl + "/deposit/111", deposit, String.class);

			AccountEntry transfer = new AccountEntry(500, "Trying to transfer too much", "111", "");
			restTemplate.postForEntity(baseUrl + "/transfer/111/222", transfer, String.class);

		} catch (Exception e) {
			System.out.println("❌ Unexpected error: " + e.getMessage());
		}
	}

}
