package net.jmp.spring.boot.app.configs;

/*
 * (#)ServicesConfig.java   0.1.0   12/31/2024
 *
 * @author   Jonathan Parker
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

import net.jmp.spring.boot.app.services.HelloWorldService;
import net.jmp.spring.boot.app.services.HelloWorldServiceImpl;
import net.jmp.spring.boot.app.services.StringService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/// The Spring services configuration.
///
/// @version    0.1.0
/// @since      0.1.0
@Configuration
public class ServicesConfig {
    /// The default constructor.
    public ServicesConfig() {
        super();
    }

    /// The hello world service.
    ///
    /// @return net.jmp.spring.boot.app.services.HelloWorldService
    @Bean
    public HelloWorldService helloWorldService() {
        return new HelloWorldServiceImpl();
    }

    /// The string service.
    ///
    /// @return net.jmp.spring.boot.app.services.StringService
    @Bean
    public StringService stringService() {
        return new StringService();
    }
}
