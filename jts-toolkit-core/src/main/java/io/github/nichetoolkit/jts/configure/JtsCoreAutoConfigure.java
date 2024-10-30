package io.github.nichetoolkit.jts.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>JtsCoreAutoConfigure</code>
 * <p>The jts core auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.context.annotation.Configuration
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ImportAutoConfiguration(value = JtsJacksonAutoConfigure.class)
public class JtsCoreAutoConfigure {
    /**
     * <code>JtsCoreAutoConfigure</code>
     * <p>Instantiates a new jts core auto configure.</p>
     */
    public JtsCoreAutoConfigure() {
        log.debug("The auto configuration for [jts-core] initiated");
    }

}

