package org.mickael.librarymsreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.mickael.librarymsreservation")
public class LibraryMsReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMsReservationApplication.class, args);
	}

}
