package net.jmp.spring.boot.app.services;

/*
 * (#)KeyValuePairService.java  0.5.0   01/02/2025
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

import net.jmp.spring.boot.app.classes.KeyValuePair;

import net.jmp.spring.boot.app.repositories.KeyValuePairRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

/// The Redis string value service.
///
/// @version    0.5.0
/// @since      0.5.0
@Service
public class KeyValuePairService {
    /// The repository.
    private final KeyValuePairRepository keyValuePairRepository;

    /// The constructor.
    ///
    /// @param  keyValuePairRepository  net.jmp.spring.boot.app.repositories.KeyValuePairRepository
    public KeyValuePairService(final KeyValuePairRepository keyValuePairRepository) {
        this.keyValuePairRepository = keyValuePairRepository;
    }

    /// Save the key value pair.
    ///
    /// @param  keyValuePair    net.jmp.spring.boot.app.classes.KeyValuePair
    /// @return                 net.jmp.spring.boot.app.classes.KeyValuePair
    public KeyValuePair save(final KeyValuePair keyValuePair) {
        return this.keyValuePairRepository.save(keyValuePair);
    }

    /// Find a key value pair by identifier.
    ///
    /// @param  id  java.lang.String
    /// @return     java.util.Optional<net.jmp.spring.boot.app.classes.KeyValuePair>
    public Optional<KeyValuePair> findById(final String id) {
        return this.keyValuePairRepository.findById(id);
    }

    /// Return true if the key value pair exists.
    ///
    /// @param  id  java.lang.String
    /// @return     boolean
    public boolean existsById(final String id) {
        return this.keyValuePairRepository.existsById(id);
    }

    /// Delete a key value pair based on identifier.
    ///
    /// @param  id  java.lang.String
    public void deleteById(final String id) {
        this.keyValuePairRepository.deleteById(id);
    }

    /// Delete a key value pair.
    ///
    /// @param  keyValuePair    net.jmp.spring.boot.app.classes.KeyValuePair
    public void delete(final KeyValuePair keyValuePair) {
        this.keyValuePairRepository.delete(keyValuePair);
    }
}
