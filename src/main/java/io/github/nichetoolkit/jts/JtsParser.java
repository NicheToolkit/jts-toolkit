package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.error.JtsParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * <p>BaseParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 11:04 2020/9/22
 */
public abstract class JtsParser<S extends Geometry> {
    protected GeometryFactory geometryFactory;

    public JtsParser(GeometryFactory geometryFactory) {
        super();
        this.geometryFactory = geometryFactory;
    }

    abstract public S parse(JsonNode node) throws JtsParseException;

}
