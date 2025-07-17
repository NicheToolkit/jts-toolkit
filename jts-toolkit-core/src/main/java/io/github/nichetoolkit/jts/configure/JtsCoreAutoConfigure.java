package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.rest.RestI18n;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * <code>JtsCoreAutoConfigure</code>
 * <p>The jts core auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ImportAutoConfiguration(value = JtsJacksonAutoConfigure.class)
public class JtsCoreAutoConfigure {

    /**
     * <code>JTS_I18N</code>
     * {@link java.lang.String} <p>The constant <code>JTS_I18N</code> field.</p>
     * @see java.lang.String
     */
    private static final String JTS_I18N = "jts-i18n";

    /**
     * <code>JtsCoreAutoConfigure</code>
     * <p>Instantiates a new jts core auto configure.</p>
     */
    public JtsCoreAutoConfigure() {
        log.debug("The auto configuration for [jts-core] initiated");
    }

    /**
     * <code>jtsI18nBasename</code>
     * <p>The jts i 18 n basename method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18n} <p>The jts i 18 n basename return object is <code>RestI18n</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestI18n
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18n jtsI18nBasename() {
        return () -> Collections.singleton(JTS_I18N);
    }

}

