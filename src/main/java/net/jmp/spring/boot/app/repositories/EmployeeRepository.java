package net.jmp.spring.boot.app.repositories;

/*
 * (#)EmployeeRepository.java   0.4.0   01/01/2025
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

import net.jmp.spring.boot.app.entities.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/// An employee repository interface.
///
/// @version    0.4.0
/// @since      0.4.0
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /// Returns a list of employees filtered by the given last name.
    ///
    /// @param  lastName    java.lang.String
    /// @return             java.lang.Iterable<net.jmp.spring.boot.app.entities.Employee>
    @Query(value = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE last_name = ?1", nativeQuery = true)
    public Iterable<Employee> findAllByLastName(final String lastName);

    /// Returns a page of employees limited by the given page criteria.
    ///
    /// @param  pageable    org.springframework.data.domain.Pageable
    /// @return             org.springframework.data.domain.Page<net.jmp.spring.boot.app.entities.Employee>
    public Page<Employee> findAll(final Pageable pageable);
}
