package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.shape.ShapeFactory;
import io.github.nichetoolkit.jts.shape.ShapefileUtils;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapeFactory;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapeReader;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>JtsShapeAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.jts"})
@ConditionalOnProperty(value = "nichetoolkit.jts.shape.enabled", havingValue = "true")
public class JtsShapeAutoConfigure {
    public JtsShapeAutoConfigure() {
        log.debug("The auto configuration for [jts-shape] initiated");
    }

    @Bean
    @ConditionalOnMissingBean(ShapeFactory.class)
    public SimpleShapeFactory shapeFactory(JtsShapeProperties properties) {
        SimpleShapeFactory shapeFactory = new SimpleShapeFactory(new SimpleShapeReader(), new SimpleShapeWriter());
        ShapefileUtils.initShapefile(shapeFactory, properties);
        return shapeFactory;
    }


}
