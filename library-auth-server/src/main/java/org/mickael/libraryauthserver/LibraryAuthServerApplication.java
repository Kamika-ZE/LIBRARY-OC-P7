package org.mickael.libraryauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class LibraryAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAuthServerApplication.class, args);
	}

}
