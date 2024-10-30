package io.github.nichetoolkit.jts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * <code>JtsTestApplication</code>
 * <p>The jts test application class.</p>
 * @see  org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 * @see  org.springframework.boot.autoconfigure.SpringBootApplication
 * @see  org.springframework.context.annotation.ComponentScan
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "io.github.nichetoolkit")
public class JtsTestApplication extends SpringBootServletInitializer {

    /**
     * <code>main</code>
     * <p>The entry point of application.</p>
     * @param args {@link java.lang.String} <p>The input arguments.</p>
     * @see  java.lang.String
     */
    public static void main(String[] args) {
        SpringApplication.run(JtsTestApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JtsTestApplication.class);
    }

}
