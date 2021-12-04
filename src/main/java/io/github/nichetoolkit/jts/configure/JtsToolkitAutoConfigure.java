package io.github.nichetoolkit.jts.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>JtsScrapAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ConditionalOnProperty(value = "nichetoolkit.jts.enabled", havingValue = "true")
public class JtsToolkitAutoConfigure {
    public JtsToolkitAutoConfigure() {
        log.debug("================= jts-toolkit-auto-configure initiated ！ ===================");
    }

}
