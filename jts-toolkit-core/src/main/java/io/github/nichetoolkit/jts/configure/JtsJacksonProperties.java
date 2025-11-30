package io.github.nichetoolkit.jts.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>JtsJacksonProperties</code>
 * <p>The jts jackson properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Setter
 * @see lombok.Getter
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk17
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.jts.jackson")
public class JtsJacksonProperties {
    private Boolean enabled = true;
}
