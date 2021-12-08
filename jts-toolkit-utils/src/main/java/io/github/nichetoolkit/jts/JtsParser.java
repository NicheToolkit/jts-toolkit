package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * <p>JtsParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class JtsParser<S extends Geometry> {
    protected final GeometryFactory geometryFactory;

    public JtsParser(GeometryFactory geometryFactory) {
        super();
        this.geometryFactory = geometryFactory;
    }

    abstract public S parse(JsonNode node) throws JtsParserErrorException;

}
