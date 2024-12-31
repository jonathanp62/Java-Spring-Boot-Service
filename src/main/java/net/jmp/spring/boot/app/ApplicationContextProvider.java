package net.jmp.spring.boot.app;

/*
 * (#)ApplicationContextProvider.java   0.1.0   12/31/2024
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

import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.stereotype.Component;

/// The application context provider.
///
/// @version    0.1.0
/// @since      0.1.0
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    /// The application context.
    private static ApplicationContext applicationContext;

    /// The default constructor.
    public ApplicationContextProvider() {
        super();
    }

    /// Sets the application context.
    ///
    /// @param  applicationContext  org.springframework.context.ApplicationContext
    /// @throws                     org.springframework.beans.BeansException    If an error occurs.
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /// Returns the application context.
    ///
    /// @return org.springframework.context.ApplicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
