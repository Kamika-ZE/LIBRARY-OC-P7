package org.mickael.librarymsbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.mickael.librarymsbook")
public class LibraryMsBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMsBookApplication.class, args);
	}

}
