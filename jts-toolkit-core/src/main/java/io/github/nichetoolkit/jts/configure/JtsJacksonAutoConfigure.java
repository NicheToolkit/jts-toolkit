package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.JtsModule;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <code>JtsJacksonAutoConfigure</code>
 * <p>The jts jackson auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.context.annotation.Configuration
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
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
     * @return  {@link io.github.nichetoolkit.jts.JtsModule} <p>The jts module return object is <code>JtsModule</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsModule
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(JtsModule.class)
    public JtsModule jtsModule() {
        return new JtsModule();
    }

    /**
     * <code>jackson2ObjectMapperBuilderCustomizer</code>
     * <p>The jackson 2 object mapper builder customizer method.</p>
     * @param jtsModule {@link io.github.nichetoolkit.jts.JtsModule} <p>The jts module parameter is <code>JtsModule</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsModule
     * @see  org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.core.annotation.Order
     * @return  {@link org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer} <p>The jackson 2 object mapper builder customizer return object is <code>Jackson2ObjectMapperBuilderCustomizer</code> type.</p>
     */
    @Bean
    @Order(1)
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(JtsModule jtsModule) {
        return jacksonBuilder -> {
            ObjectMapperHolder.objectMapper().registerModule(jtsModule);
            jacksonBuilder.configure(ObjectMapperHolder.objectMapper());
            jacksonBuilder.modules(jtsModule);
        };
    }
}
