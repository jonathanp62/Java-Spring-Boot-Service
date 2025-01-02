package net.jmp.spring.boot.app.demos;

/*
 * (#)RedisTemplateDemo.java    0.5.0   01/02/2025
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

import net.jmp.spring.boot.app.ApplicationContextProvider;

import net.jmp.spring.boot.app.classes.Student;
import net.jmp.spring.boot.app.classes.User;

import net.jmp.spring.boot.app.repositories.StudentRepository;
import net.jmp.spring.boot.app.repositories.UserRepository;

//import net.jmp.spring.boot.app.services.RedisStringService;
import net.jmp.spring.boot.app.services.UserService;
import net.jmp.spring.boot.app.services.StudentService;

import net.jmp.util.extra.demo.Demo;
import net.jmp.util.extra.demo.DemoClass;
import net.jmp.util.extra.demo.DemoVersion;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.data.redis.core.RedisTemplate;

/// The Redis template and repository demonstration.
///
/// @version    0.5.0
/// @since      0.5.0
@DemoClass
@DemoVersion(0.5)
public final class RedisTemplateDemo implements Demo {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public RedisTemplateDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ApplicationContext context = ApplicationContextProvider.getApplicationContext();

//        this.stringService(context);
        this.userService(context);
        this.studentRepository(context);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Work with the Redis string service to store and fetch objects.
    ///
    /// @param  context org.springframework.context.ApplicationContext
    private void stringService(final ApplicationContext context) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(context));
        }

//        @SuppressWarnings("unchecked")
//        final RedisTemplate<String, String> redisTemplate = context.getBean(RedisTemplate.class);
//        final RedisStringService redisStringService = new RedisStringService(redisTemplate);
//
//        redisStringService.setValue("name", "John Doe");
//
//        if (this.logger.isInfoEnabled()) {
//            this.logger.info(redisStringService.getValue("name"));
//        }
//
//        if (redisStringService.deleteValue("name")) {
//            this.logger.info("Object 'name' deleted");
//        } else {
//            this.logger.warn("Object 'name' not deleted");
//        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Use the Redis repository to store and fetch objects
    /// from the user repository.
    ///
    /// @param  context org.springframework.context.ApplicationContext
    private void userService(final ApplicationContext context) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(context));
        }

        final UserRepository repository = context.getBean(UserRepository.class);
        final UserService userService = new UserService(repository);
        final String id = "123456789abcedf0";
        final User user = new User();

        user.setId(id);
        user.setUserName("John Doe");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("secret");

        final User _ = userService.save(user);

        final Optional<User> john = userService.findById(id);

        assert john.isPresent();

        if (this.logger.isInfoEnabled()) {
            this.logger.info(john.toString());
        }

        userService.deleteById(id);

        if (userService.existsById(id)) {
            this.logger.warn("User '{}' not deleted", id);
        } else {
            this.logger.info("User '{}' deleted", id);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Use the Redis repository to store and fetch objects
    /// from the student repository.
    ///
    /// @param  context org.springframework.context.ApplicationContext
    private void studentRepository(final ApplicationContext context) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(context));
        }

        final StudentRepository repository = context.getBean(StudentRepository.class);
        final StudentService studentService = new StudentService(repository);

        final Student student = new Student();

        student.setId("identifier");
        student.setGender(Student.Gender.FEMALE);
        student.setName("Oriole");
        student.setGrade(100);

        final Student result = studentService.save(student);

        assert result != null;
        assert result.equals(student);
        assert studentService.existsById(result.getId());

        final Optional<Student> fetched = studentService.findById(student.getId());

        assert fetched.isPresent();

        if (this.logger.isInfoEnabled()) {
            this.logger.info(fetched.toString());
        }

        studentService.delete(student);

        assert !studentService.existsById(result.getId());

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
