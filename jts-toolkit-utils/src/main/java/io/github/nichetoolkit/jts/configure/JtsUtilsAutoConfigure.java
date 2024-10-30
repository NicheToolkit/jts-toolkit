package io.github.nichetoolkit.jts.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>JtsUtilsAutoConfigure</code>
 * <p>The jts utils auto configure class.</p>
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
@ImportAutoConfiguration(value = JtsShapeAutoConfigure.class)
public class JtsUtilsAutoConfigure {
    /**
     * <code>JtsUtilsAutoConfigure</code>
     * <p>Instantiates a new jts utils auto configure.</p>
     */
    public JtsUtilsAutoConfigure() {
        log.debug("The auto configuration for [jts-utils] initiated");
    }

}
