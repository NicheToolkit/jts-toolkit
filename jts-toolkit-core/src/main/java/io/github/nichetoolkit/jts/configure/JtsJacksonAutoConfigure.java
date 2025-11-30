package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.JtsModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <code>JtsJacksonAutoConfigure</code>
 * <p>The jts jackson auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk17
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ConditionalOnProperty(value = "nichetoolkit.jts.jackson.enabled", havingValue = "true")
public class JtsJacksonAutoConfigure {

    /**
     * <code>JtsJacksonAutoConfigure</code>
     * <p>Instantiates a new jts jackson auto configure.</p>
     */
    public JtsJacksonAutoConfigure() {
        log.debug("The auto configuration for [jts-jackson] initiated");
    }

    /**
     * <code>jtsModule</code>
     * <p>The jts module method.</p>
     * @return {@link io.github.nichetoolkit.jts.JtsModule} <p>The jts module return object is <code>JtsModule</code> type.</p>
     * @see io.github.nichetoolkit.jts.JtsModule
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(JtsModule.class)
    public JtsModule jtsModule() {
        return new JtsModule();
    }

    /**
     * <code>jsonMapperBuilderCustomizer</code>
     * <p>The json mapper builder customizer method.</p>
     * @param jtsModule {@link io.github.nichetoolkit.jts.JtsModule} <p>The jts module parameter is <code>JtsModule</code> type.</p>
     * @return {@link org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer} <p>The json mapper builder customizer return object is <code>JsonMapperBuilderCustomizer</code> type.</p>
     * @see io.github.nichetoolkit.jts.JtsModule
     * @see org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     */
    @Bean
    @Order(1)
    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer(JtsModule jtsModule) {
        return jacksonBuilder -> {
            jacksonBuilder.addModule(jtsModule);
        };
    }
}
