package net.jmp.spring.boot.app.configs;

/*
 * (#)JdbcConfig.java   0.3.0   01/01/2025
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

import javax.sql.DataSource;

import net.jmp.spring.boot.app.repositories.DepartmentRepository;
import net.jmp.spring.boot.app.repositories.DepartmentRepositoryImpl;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.config.BeanDefinition;

import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Role;

import org.springframework.jdbc.core.JdbcTemplate;

/// The Spring JDBC configuration.
///
/// @version    0.3.0
/// @since      0.3.0
@Configuration
@PropertySource("classpath:secrets.properties")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class JdbcConfig {
    /// The MySQL user ID.
    @Value("${mysql.connection.username}")
    private String mysqlUserName;

    /// The MySQL password.
    @Value("${mysql.connection.password}")
    private String mysqlPassword;

    /// The MySQL driver.
    @Value("${mysql.connection.driver}")
    private String mysqlDriver;

    /// The MySQL URL.
    @Value("${mysql.connection.url}")
    private String mysqlUrl;

    /// The default constructor.
    public JdbcConfig() {
        super();
    }

    /// Create and return a MySQL JDBC data source.
    ///
    /// @return javax.sql.DataSource
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DataSource jdbcDataSource() {
        final var dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.url(this.mysqlUrl);
        dataSourceBuilder.username(this.mysqlUserName);
        dataSourceBuilder.password(this.mysqlPassword);
        dataSourceBuilder.driverClassName(this.mysqlDriver);

        return dataSourceBuilder.build();
    }

    /// Create and return a JDBC template.
    ///
    /// @return org.springframework.jdbc.core.JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(this.jdbcDataSource());
    }

    /// Create and return the department repository.
    ///
    /// @return net.jmp.spring.boot.app.repositories.DepartmentRepository
    /// @since  0.7.0
    @Bean
    public DepartmentRepository departmentRepository() {
        return new DepartmentRepositoryImpl(this.jdbcTemplate());
    }
}
