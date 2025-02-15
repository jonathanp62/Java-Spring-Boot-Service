package net.jmp.spring.boot.app.demos;

/*
 * (#)JpaDemo.java  0.4.0   01/01/2025
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

import java.util.List;

import net.jmp.spring.boot.app.ApplicationContextProvider;

import net.jmp.spring.boot.app.entities.Employee;

import net.jmp.spring.boot.app.services.EmployeeService;

import net.jmp.util.extra.demo.Demo;
import net.jmp.util.extra.demo.DemoClass;
import net.jmp.util.extra.demo.DemoVersion;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/// The JDBC template demonstration.
///
/// @version    0.4.0
/// @since      0.4.0
@DemoClass
@DemoVersion(0.4)
public final class JpaDemo implements Demo {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public JpaDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        final EmployeeService employeeService = context.getBean(EmployeeService.class);

        this.employeeService(employeeService);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Uses the JPA employee service.
    ///
    /// @param  employeeService net.jmp.spring.boot.app.services.EmployeeService
    private void employeeService(final EmployeeService employeeService) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(employeeService));
        }

        if (this.logger.isInfoEnabled()) {
            this.employeeServiceCount(employeeService);
            this.employeeServiceFindAll(employeeService);
            this.employeeServiceFindAllSorted(employeeService);
            this.employeeServiceFindAllPaginated(employeeService);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Uses the JPA employee service to count the number of employee objects.
    ///
    /// @param  employeeService net.jmp.spring.boot.app.services.EmployeeService
    private void employeeServiceCount(final EmployeeService employeeService) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(employeeService));
        }

        this.logger.info("Number of employees: {}", employeeService.count());

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Uses the JPA employee service to fetch and log employee objects.
    ///
    /// @param  employeeService net.jmp.spring.boot.app.services.EmployeeService
    private void employeeServiceFindAll(final EmployeeService employeeService) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(employeeService));
        }

        final List<Employee> employees = employeeService.findAll();

        employees.stream()
                .limit(10)
                .forEach(employee -> this.logger.info(employee.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Uses the JPA employee service to fetch and log employee objects.
    ///
    /// @param  employeeService net.jmp.spring.boot.app.services.EmployeeService
    private void employeeServiceFindAllSorted(final EmployeeService employeeService) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(employeeService));
        }

        final Sort sort = Sort.by("lastName", "firstName");
        final List<Employee> employees = employeeService.findAll(sort);

        employees.stream()
                .limit(10)
                .forEach(employee -> this.logger.info(employee.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Uses the JPA employee service to fetch
    /// and log employee objects paginated.
    ///
    /// @param  employeeService net.jmp.spring.boot.app.services.EmployeeService
    private void employeeServiceFindAllPaginated(final EmployeeService employeeService) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(employeeService));
        }

        final Page<Employee> page = employeeService.findAll(0, 10);

        page.get().forEach(employee -> this.logger.info(employee.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
