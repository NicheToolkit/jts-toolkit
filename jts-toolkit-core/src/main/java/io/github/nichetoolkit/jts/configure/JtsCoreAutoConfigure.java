package io.github.nichetoolkit.jts.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>JtsCoreAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ImportAutoConfiguration(value = JtsJacksonAutoConfigure.class)
public class JtsCoreAutoConfigure {
    public JtsCoreAutoConfigure() {
        log.debug("The auto configuration for [jts-core] initiated");
    }

}

