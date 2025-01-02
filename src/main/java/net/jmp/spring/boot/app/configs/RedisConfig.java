package net.jmp.spring.boot.app.configs;

/*
 * (#)RedisConfig.java  0.5.0   01/02/2025
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

import org.redisson.Redisson;

import org.redisson.api.RedissonClient;

import org.redisson.config.Config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.StringRedisSerializer;

/// The Spring Redis configuration.
///
/// @version    0.5.0
/// @since      0.5.0
@Configuration
@PropertySource("classpath:secrets.properties")
public class RedisConfig {
    /// The Redis host.
    @Value("${redis.host}")
    private String redisHost;

    /// The Redis port.
    @Value("${redis.port}")
    private int redisPort;

    /// The default constructor.
    public RedisConfig() {
        super();
    }

    /// Create and return a Jedis connection factory.
    ///
    /// @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(this.redisHost, this.redisPort);

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    /// Create and return a Redis template.
    ///
    /// @return org.springframework.data.redis.core.RedisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(this.jedisConnectionFactory());
        template.afterPropertiesSet();
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }

    ///  Create and return a Redisson client.
    ///
    /// @return org.redisson.api.RedissonClient
    @Bean
    public RedissonClient redissonClient() {
        final Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://" + this.redisHost + ":" + this.redisPort);

        return Redisson.create(config);
    }
}
