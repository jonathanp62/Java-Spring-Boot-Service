package net.jmp.spring.boot.app;

/*
 * (#)MainCommandLineRunner.java    0.5.0   01/02/2025
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

import java.util.Arrays;

import org.redisson.api.RedissonClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class MainCommandLineRunner implements CommandLineRunner {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The application context.
    private final ApplicationContext context;

    /// A constructor.
    ///
    /// @param  context org.springframework.context.ApplicationContext
    MainCommandLineRunner(final ApplicationContext context) {
        super();

        this.context = context;
    }

    /// The run method.
    ///
    /// @param  args    java.lang.String[]
    @Override
    public void run(final String... args) throws Exception {
        this.logger.info("Spring Boot Service Application Beans:");

        final String[] beanNames = context.getBeanDefinitionNames();

        Arrays.sort(beanNames);

        for (final String beanName : beanNames) {
            this.logger.info(beanName);
        }

        context.getBean(Main.class).run();
        context.getBean(RedissonClient.class).shutdown();
    }
}
