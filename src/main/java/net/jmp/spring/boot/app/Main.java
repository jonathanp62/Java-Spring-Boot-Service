package net.jmp.spring.boot.app;

/*
 * (#)Main.java 0.1.0   12/31/2024
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

import com.google.gson.Gson;

import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.function.Consumer;

import net.jmp.spring.boot.app.classes.Config;

import net.jmp.util.extra.demo.*;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/// The main application class.
///
/// @version    0.1.0
/// @since      0.1.0
@Component
public class Main implements Runnable {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public Main() {
        super();
    }

    /// The run method.
    @Override
    public void run() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        this.greeting();

        try {
            this.runDemos(this.loadConfiguration());
        } catch (final Exception e) {
            this.logger.error(catching(e));
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Log the greeting.
    private void greeting() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isDebugEnabled()) { // Covers trace, too
            this.logger.debug("{} {}", Name.NAME_STRING, Version.VERSION_STRING);
        } else if (this.logger.isInfoEnabled() || this.logger.isWarnEnabled()) {
            this.logger.info("{} {}", Name.NAME_STRING, Version.VERSION_STRING);
        } else {    // Error or off
            System.out.format("%s %s%n", Name.NAME_STRING, Version.VERSION_STRING);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Load the application configuration
    ///
    /// @return net.jmp.spring.boot.app.classes.Config
    /// @throws java.io.IOException When an I/O error occurs reading the configuration file
    private Config loadConfiguration() throws IOException {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        Config config = null;

        final String appConfigFileName = System.getProperty("app.configurationFile", "config/config.json");
        final Gson gson = new Gson();

        try (final JsonReader reader = new JsonReader(new FileReader(appConfigFileName))) {
            config = gson.fromJson(reader, Config.class);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(config));
        }

        return config;
    }

    /// Run the demonstration classes.
    ///
    /// @param  config  net.jmp.spring.boot.app.classes.Config
    private void runDemos(final Config config) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(config));
        }

        final Consumer<String> demoRunner = className -> {
            try {
                final double version = DemoUtils.getDemoClassVersion(className);

                if (version > 0) {
                    if (config.getVersion() >= version) {
                        DemoUtils.runDemoClassDemo(className);
                    }
                } else {
                    DemoUtils.runDemoClassDemo(className);
                }
            } catch (final DemoUtilException due) {
                this.logger.error(catching(due));
            }
        };

        config.getDemosAsStream()
                .map(demo -> config.getPackageName() + "." + demo)
                .forEach(demoRunner);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
