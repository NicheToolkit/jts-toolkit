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
 * <p>JtsJacksonAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ConditionalOnProperty(value = "nichetoolkit.jts.jackson.enabled", havingValue = "true")
public class JtsJacksonAutoConfigure {

    public JtsJacksonAutoConfigure() {
        log.debug("The auto configuration for [jts-jackson] initiated");
    }

    @Bean
    @ConditionalOnMissingBean(JtsModule.class)
    public JtsModule jtsModule() {
        return new JtsModule();
    }

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
