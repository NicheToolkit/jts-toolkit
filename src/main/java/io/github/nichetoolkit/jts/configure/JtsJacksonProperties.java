package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.shape.ShapeConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <p>JtsProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.jts")
public class JtsJacksonProperties {
    private Boolean enabled = false;
    @NestedConfigurationProperty
    private Jackson jackson = new Jackson();

    public JtsJacksonProperties() {
    }

    public static class Jackson {
        private Boolean enabled = false;

        public Jackson() {
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Jackson getJackson() {
        return jackson;
    }

    public void setJackson(Jackson jackson) {
        this.jackson = jackson;
    }
}
