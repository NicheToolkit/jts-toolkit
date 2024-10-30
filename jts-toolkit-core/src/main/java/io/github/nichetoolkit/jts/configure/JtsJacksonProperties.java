package io.github.nichetoolkit.jts.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>JtsProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.jts.jackson")
public class JtsJacksonProperties {
    private Boolean enabled = true;
}
