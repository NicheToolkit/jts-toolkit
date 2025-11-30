package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.rest.resource.RestI18nResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>JtsCoreAutoConfigure</code>
 * <p>The jts core auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk17
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ImportAutoConfiguration(value = JtsJacksonAutoConfigure.class)
public class JtsCoreAutoConfigure {

    private static final String JTS_I18N = "jts-i18n/messages";

    /**
     * <code>JtsCoreAutoConfigure</code>
     * <p>Instantiates a new jts core auto configure.</p>
     */
    public JtsCoreAutoConfigure() {
        log.debug("The auto configuration for [jts-core] initiated");
    }

    /**
     * <code>jtsI18nResources</code>
     * <p>The jts i 18 n resources method.</p>
     * @return {@link io.github.nichetoolkit.rest.resource.RestI18nResources} <p>The jts i 18 n resources return object is <code>RestI18nResources</code> type.</p>
     * @see io.github.nichetoolkit.rest.resource.RestI18nResources
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18nResources jtsI18nResources() {
        return RestI18nResources.of(JTS_I18N);
    }

}

