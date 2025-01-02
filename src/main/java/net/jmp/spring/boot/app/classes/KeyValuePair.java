package net.jmp.spring.boot.app.classes;

/*
 * (#)KeyValuePair.java 0.5.0   01/02/2025
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

import java.io.Serializable;

import java.util.Objects;

import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;

/// A key/value pair class.
///
/// @version    0.5.0
/// @since      0.5.0
@RedisHash
public class KeyValuePair implements Serializable {
    /// The key.
    @Id
    private String key;

    /// The value.
    private String value;

    /// The default constructor.
    public KeyValuePair() {
        super();
    }

    /// A constructor.
    ///
    /// @param  key    java.lang.String
    /// @param  value  java.lang.String
    public KeyValuePair(final String key, final String value) {
        super();

        this.key = key;
        this.value = value;
    }

    /// Get the key.
    ///
    /// @return java.lang.String
    public String getKey() {
        return this.key;
    }

    /// Set the key.
    ///
    /// @param  key java.lang.String
    public void setKey(final String key) {
        this.key = key;
    }

    /// Get the value.
    ///
    /// @return java.lang.String
    public String getValue() {
        return this.value;
    }

    /// Set the value.
    ///
    /// @param  value   java.lang.String
    public void setValue(final String value) {
        this.value = value;
    }

    /// Compares this object against the specified object.
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    ///
    /// @see #hashCode()
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        final KeyValuePair that = (KeyValuePair) o;

        return Objects.equals(this.key, that.key) && Objects.equals(this.value, that.value);
    }

    /// Returns a hash code value for the object.
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(this.key, this.value);
    }

    /// Returns a string representation of the object.
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "KeyValuePair{" +
                "key='" + this.key + '\'' +
                ", value='" + this.value + '\'' +
                '}';
    }
}
