package net.jmp.spring.boot.app.services;

/*
 * (#)UserService.java  0.5.0   01/02/2025
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

import net.jmp.spring.boot.app.classes.User;

import net.jmp.spring.boot.app.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

/// The Redis user service.
///
/// @version    0.5.0
/// @since      0.5.0
@Service
public class UserService {
    /// The repository.
    private final UserRepository userRepository;

    /// The constructor.
    ///
    /// @param  userRepository   net.jmp.spring.boot.app.repositories.UserRepository
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /// Sace the user.
    ///
    /// @param  user    net.jmp.spring.java.app.classes.User
    /// @return         net.jmp.spring.java.app.classes.User
    public User save(final User user) {
        return this.userRepository.save(user);
    }

    /// Find a user by identifier.
    ///
    /// @param  id  java.lang.String
    /// @return     java.util.Optional<net.jmp.spring.boot.app.classes.User>
    public Optional<User> findById(final String id) {
        return this.userRepository.findById(id);
    }

    /// Return true if the user exists.
    ///
    /// @param  id  java.lang.String
    /// @return     boolean
    public boolean existsById(final String id) {
        return this.userRepository.existsById(id);
    }

    /// Delete a user based on identifier.
    ///
    /// @param  id  java.lang.String
    public void deleteById(final String id) {
        this.userRepository.deleteById(id);
    }

    /// Delete a user.
    ///
    /// @param  user    net.jmp.spring.boot.app.classes.User
    public void delete(final User user) {
        this.userRepository.delete(user);
    }
}
