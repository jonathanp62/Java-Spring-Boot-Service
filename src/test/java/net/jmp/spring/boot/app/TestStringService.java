package net.jmp.spring.boot.app;

/*
 * (#)TestStringService.java    0.1.0   12/31/2024
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

import net.jmp.spring.boot.app.services.StringService;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

/// The test class for the string service.
///
/// @version    0.1.0
/// @since      0.1.0
@SpringBootTest
@DisplayName("String Service")
@Tag("Service")
class TestStringService {
    @Autowired
    private StringService stringService;

    @DisplayName("String service helper methods")
    @Nested
    class TestHelperMethods {
        @DisplayName("Test is string legal?")
        @Test
        void testIsStringLegal() {
            assertTrue(stringService.isStringLegal("JohnDoe"));
            assertFalse(stringService.isStringLegal("John Doe"));
            assertTrue(stringService.isStringLegal("123456789abcedf0-_$"));
            assertFalse(stringService.isStringLegal("~`!@#%^&*()+={}[]\\|;:'\"<>,.?/\""));

            assertAll(
                    () -> assertFalse(stringService.isStringLegal(null)),
                    () -> assertFalse(stringService.isStringLegal("")),
                    () -> assertFalse(stringService.isStringLegal(" ")),
                    () -> assertTrue(stringService.isStringLegal("JohnDoe")),
                    () -> assertFalse(stringService.isStringLegal("John Doe")),
                    () -> assertTrue(stringService.isStringLegal("123456789abcedf0-_$")),
                    () -> assertFalse(stringService.isStringLegal("~`!@#%^&*()+={}[]\\|;:'\"<>,.?/\""))
            );

            assertAll(
                    () -> assertThat(stringService.isStringLegal(null)).isFalse(),
                    () -> assertThat(stringService.isStringLegal("")).isFalse(),
                    () -> assertThat(stringService.isStringLegal(" ")).isFalse(),
                    () -> assertThat(stringService.isStringLegal("JohnDoe")).isTrue(),
                    () -> assertThat(stringService.isStringLegal("John Doe")).isFalse(),
                    () -> assertThat(stringService.isStringLegal("123456789abcedf0-_$")).isTrue(),
                    () -> assertThat(stringService.isStringLegal("~`!@#%^&*()+={}[]\\|;:'\"<>,.?/\"")).isFalse()
            );
        }

        @DisplayName("Test is string legal? with a null value")
        @ParameterizedTest
        @NullSource
        void testIsStringLegalNull(final String string) {
            assertThat(stringService.isStringLegal(string)).isFalse();
        }

        @DisplayName("Test is string legal? with an empty value")
        @ParameterizedTest
        @EmptySource
        void testIsStringLegalempty(final String string) {
            assertThat(stringService.isStringLegal(string)).isFalse();
        }

        @DisplayName("Test is string legal? with false values")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "John Doe", "~`!@#%^&*()+={}[]\\|;:'\"<>,.?/"})
        void testIsStringLegalFalse(final String string) {
            assertThat(stringService.isStringLegal(string)).isFalse();
        }

        @DisplayName("Test is string legal? with true values")
        @ParameterizedTest
        @ValueSource(strings = {"JohnDoe", "123456789abcedf0-_$"})
        void testIsStringLegalTrue(final String string) {
            assertThat(stringService.isStringLegal(string)).isTrue();
        }

        @DisplayName("Test remove illegal characters")
        @Test
        void testRemoveIllegalCharacters() {
            final String result1 = stringService.removeIllegalCharacters("Key / Value");
            final String result2 = stringService.removeIllegalCharacters("Key-Value");
            final String result3 = stringService.removeIllegalCharacters("~`!@#%^&*()+={}[]\\|;:'\"<>,.?/");

            assertEquals("KeyValue", result1);
            assertEquals("Key-Value", result2);
            assertEquals("", result3);

            assertAll(
                    () -> assertThat(stringService.removeIllegalCharacters(null)).isNull(),
                    () -> assertThat(stringService.removeIllegalCharacters("")).isEmpty(),
                    () -> assertThat(stringService.removeIllegalCharacters(" ")).isBlank(),
                    () -> assertThat(result1).isEqualTo("KeyValue"),
                    () -> assertThat(result2).isEqualTo("Key-Value"),
                    () -> assertThat(result3).isEmpty()
            );
        }
    }

    @DisplayName("Test sanitize")
    @Test
    void testSanitizeString() {
        assertEquals("JohnDoe", this.stringService.sanitize("John Doe"));

        final String result1 = this.stringService.sanitize("Key / Value");
        final String result2 = this.stringService.sanitize("Key-Value");
        final String result3 = this.stringService.sanitize("~`!@#%^&*()+={}[]\\|;:'\"<>,.?/");

        assertEquals("KeyValue", result1);
        assertEquals("Key-Value", result2);
        assertEquals("", result3);

        assertAll(
                () -> assertThat(this.stringService.sanitize(null)).isNull(),
                () -> assertThat(this.stringService.sanitize("")).isEmpty(),
                () -> assertThat(this.stringService.sanitize(" ")).isBlank(),
                () -> assertThat(result1).isEqualTo("KeyValue"),
                () -> assertThat(result2).isEqualTo("Key-Value"),
                () -> assertThat(result3).isEmpty()
        );
    }
}
