package net.jmp.spring.boot.app.configs;

/*
 * (#)MongoConfig.java  0.2.0   01/01/2025
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

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.data.mongodb.core.MongoTemplate;

/// The Spring MongoDB configuration.
///
/// @version    0.2.0
/// @since      0.2.0
@Configuration
@PropertySource("classpath:secrets.properties")
public class MongoConfig {
    /// The MongoDB user ID.
    @Value("${mongodb.uri.userid}")
    private String mongoDbUserId;

    /// The MongoDB password.
    @Value("${mongodb.uri.password}")
    private String mongoDbPassword;

    /// The MongoDB domain.
    @Value("${mongodb.uri.domain}")
    private String mongoDbDomain;

    /// The default constructor.
    public MongoConfig() {
        super();
    }

    /// Create and return a MongoDB client.
    ///
    /// @return com.mongodb.client.MongoClient
    @Bean
    public MongoClient mongoClient() {
        String mongoDbUri = "mongodb+srv://{uri.userid}:{uri.password}@{uri.domain}/?retryWrites=true&w=majority";

        mongoDbUri = mongoDbUri.replace("{uri.userid}", this.mongoDbUserId);
        mongoDbUri = mongoDbUri.replace("{uri.password}", this.mongoDbPassword);
        mongoDbUri = mongoDbUri.replace("{uri.domain}", this.mongoDbDomain);

        return MongoClients.create(mongoDbUri);
    }

    /// Create a MongoDB template.
    ///
    /// @return org.springframework.data.mongodb.core.MongoTemplate
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(this.mongoClient(), "training");
    }
}
