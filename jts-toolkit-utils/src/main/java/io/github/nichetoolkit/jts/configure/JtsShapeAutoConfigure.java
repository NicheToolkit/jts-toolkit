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
 * <code>JtsShapeAutoConfigure</code>
 * <p>The jts shape auto configure class.</p>
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
@ConditionalOnProperty(value = "nichetoolkit.jts.shape.enabled", havingValue = "true")
public class JtsShapeAutoConfigure {
    /**
     * <code>JtsShapeAutoConfigure</code>
     * <p>Instantiates a new jts shape auto configure.</p>
     */
    public JtsShapeAutoConfigure() {
        log.debug("The auto configuration for [jts-shape] initiated");
    }

    /**
     * <code>shapeFactory</code>
     * <p>The shape factory method.</p>
     * @param properties {@link io.github.nichetoolkit.jts.configure.JtsShapeProperties} <p>The properties parameter is <code>JtsShapeProperties</code> type.</p>
     * @see  io.github.nichetoolkit.jts.configure.JtsShapeProperties
     * @see  io.github.nichetoolkit.jts.shape.simple.SimpleShapeFactory
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @return  {@link io.github.nichetoolkit.jts.shape.simple.SimpleShapeFactory} <p>The shape factory return object is <code>SimpleShapeFactory</code> type.</p>
     */
    @Bean
    @ConditionalOnMissingBean(ShapeFactory.class)
    public SimpleShapeFactory shapeFactory(JtsShapeProperties properties) {
        SimpleShapeFactory shapeFactory = new SimpleShapeFactory(new SimpleShapeReader(), new SimpleShapeWriter());
        ShapefileUtils.initShapefile(shapeFactory, properties);
        return shapeFactory;
    }


}
