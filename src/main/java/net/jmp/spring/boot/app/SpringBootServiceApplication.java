package net.jmp.spring.boot.app;

/*
 * (#)SpringBootServiceApplication.java	0.1.0   12/31/2024
 *
 * @author    Jonathan Parker
 *
 * MIT License
 *
 * Copyright (c) 2024 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/// The Spring Boot application class.
///
/// @version    0.1.0
/// @since      0.1.0
@SpringBootApplication
public class SpringBootServiceApplication {
	/// The logger.
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/// The default constructor.
	public SpringBootServiceApplication() {
		super();
	}

	///
	/// This calls {@link SpringApplication#run(Class, String[])}
	/// to start the application.
	///
	/// @param	args	java.lang.String[]
	public static void main(final String[] args) {
		SpringApplication.run(SpringBootServiceApplication.class, args);
	}

	/// Provides a Spring Boot {@link CommandLineRunner} that prints the names
	/// of all the beans in the application context.
	///
	/// @param	context	org.springframework.context.ApplicationContext
	/// @return			org.springframework.boot.CommandLineRunner
	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext context) {
		return args -> {
			this.logger.info("Spring Boot Service Application Beans:");

			final String[] beanNames = context.getBeanDefinitionNames();

			Arrays.sort(beanNames);

			for (final String beanName : beanNames) {
				this.logger.info(beanName);
			}

			context.getBean(Main.class).run();
		};
	}
}
