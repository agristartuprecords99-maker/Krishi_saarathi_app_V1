package com.example.First_S_B;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for Krishi Saarathi Backend
 *
 * This class serves as the entry point for the Spring Boot application.
 * The @SpringBootApplication annotation combines three annotations:
 * - @Configuration: Tags the class as a source of bean definitions
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings
 * - @ComponentScan: Tells Spring to look for other components in the com.example.First_S_B package
 *
 * @author Krishi Saarathi Development Team
 * @version 1.0.0
 */
@SpringBootApplication
public class FirstSBApplication {

	/**
	 * Main method that serves as the entry point for the Spring Boot application
	 *
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(FirstSBApplication.class, args);

		// Print success message to console
		System.out.println("\n" +
				"🌾========================================🌾\n" +
				"   KRISHI SAARATHI BACKEND STARTED!     \n" +
				"🌾========================================🌾\n" +
				"🚀 Server running on: http://localhost:8080/api/v1\n" +
				"📊 Database: PostgreSQL (krishi_saarathi)\n" +
				"🔧 Environment: Development\n" +
				"📱 Ready for Flutter app connection!\n");
	}
}
