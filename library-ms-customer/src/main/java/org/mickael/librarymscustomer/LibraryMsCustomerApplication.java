package org.mickael.librarymscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.mickael.librarymscustomer")
public class LibraryMsCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMsCustomerApplication.class, args);
	}

}
