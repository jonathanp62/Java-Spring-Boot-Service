package net.jmp.spring.boot.app;

/*
 * (#)TestRedis.java    0.5.0   01/02/2025
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

import java.util.Optional;

import net.jmp.spring.boot.app.classes.Student;
import net.jmp.spring.boot.app.classes.User;

import net.jmp.spring.boot.app.repositories.StudentRepository;
import net.jmp.spring.boot.app.repositories.UserRepository;

//import net.jmp.spring.boot.app.services.RedisStringService;
import net.jmp.spring.boot.app.services.StudentService;
import net.jmp.spring.boot.app.services.UserService;

import static net.jmp.util.logging.LoggerUtils.catching;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

/// The test class for the Redis service bean.
///
/// @version    0.5.0
/// @since      0.5.0
@SpringBootTest
@DisplayName("Redis template/repository and Redisson client")
@Tag("Redis")
final class TestRedis {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("Redis template")
    @Nested
    class TestRedisTemplate {
        @DisplayName("Test string service")
        @Test
        void testRedisStringService() {
            // Getting the context again seems to be the only way to avoid
            // confusing the two instances of the same RedisTemplate bean.

//            final var localContext = new AnnotationConfigApplicationContext(AppConfig.class);
//            @SuppressWarnings("unchecked")
//            final RedisTemplate<String, String> redisStringTemplate = localContext.getBean(RedisTemplate.class);
//            final RedisStringService redisStringService = new RedisStringService(redisStringTemplate);
//
//            redisStringService.setValue("name", "John Doe");
//
//            final String result = redisStringService.getValue("name");
//
//            assertEquals("John Doe", result);
//            assertThat(result).isEqualTo("John Doe");
        }

        @DisplayName("Test user service")
        @Test
        void testRedisUserService() {
            // Getting the context again seems to be the only way to avoid
            // confusing the two instances of the same RedisTemplate bean.

            final String id = "123456789abcedf0";
            final UserService userService = new UserService(userRepository);
            final User user = new User();

            user.setId(id);
            user.setUserName("Jane Doe");
            user.setFirstName("Jane");
            user.setLastName("Doe");
            user.setPassword("secret");

            final User _ = userService.save(user);

            final Optional<User> result = userService.findById("123456789abcedf0");

            assertAll(
                    () -> assertThat(result).isPresent(),
                    () -> assertThat(user).isEqualTo(result.get())
            );

            userService.delete(user);

            assertFalse(userService.existsById(id));

            if (userService.existsById(user.getId())) {
                logger.warn("Object '{}' not deleted", user.getId());
            }
        }

        @DisplayName("Test student service")
        @Test
        void testRedisStudentService() {
            final StudentService studentService = new StudentService(studentRepository);

            final Student student = new Student();

            student.setId("identifier");
            student.setGender(Student.Gender.FEMALE);
            student.setName("Oriole");
            student.setGrade(100);

            final Student result = studentService.save(student);

            assertNotNull(result);
            assertEquals(result, student);
            assertTrue(studentService.existsById(result.getId()));

            assertAll(
                    () -> assertThat(result).isNotNull(),
                    () -> assertThat(result).isEqualTo(student),
                    () -> assertThat(studentService.existsById(result.getId())).isTrue()
            );

            final Optional<Student> fetched = studentService.findById(student.getId());

            assertTrue(fetched.isPresent());

            studentService.delete(student);

            assertFalse(studentService.existsById(result.getId()));
            assertThat(studentService.existsById(result.getId())).isFalse();
        }
    }

    @DisplayName("Test Redisson client")
    @Test
    void testRedisson() {
        try {
            final RBucket<String> bucket = this.redissonClient.getBucket("my-bucket");

            bucket.set("my-bucket-value");

            final String result = bucket.get();

            assertNotNull(result);
            assertEquals("my-bucket-value", result);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals("my-bucket-value", result)
            );

            if (!bucket.delete()) {
                this.logger.warn("Bucket 'my-bucket-value' was not deleted");
            }
        } catch (final Exception e) {
            this.logger.error(catching(e));
        } finally {
            this.redissonClient.shutdown();
        }
    }
}
