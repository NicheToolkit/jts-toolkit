package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * <code>JtsParser</code>
 * <p>The jts parser class.</p>
 * @param <S>  {@link org.locationtech.jts.geom.Geometry} <p>The generic parameter is <code>Geometry</code> type.</p>
 * @see  org.locationtech.jts.geom.Geometry
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class JtsParser<S extends Geometry> {
    /**
     * <code>geometryFactory</code>
     * {@link org.locationtech.jts.geom.GeometryFactory} <p>The <code>geometryFactory</code> field.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    protected final GeometryFactory geometryFactory;

    /**
     * <code>JtsParser</code>
     * <p>Instantiates a new jts parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public JtsParser(GeometryFactory geometryFactory) {
        super();
        this.geometryFactory = geometryFactory;
    }

    /**
     * <code>parse</code>
     * <p>The parse method.</p>
     * @param node {@link com.fasterxml.jackson.databind.JsonNode} <p>The node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  io.github.nichetoolkit.jts.error.JtsParserErrorException
     * @return S <p>The parse return object is <code>S</code> type.</p>
     * @throws JtsParserErrorException {@link io.github.nichetoolkit.jts.error.JtsParserErrorException} <p>The jts parser error exception is <code>JtsParserErrorException</code> type.</p>
     */
    abstract public S parse(JsonNode node) throws JtsParserErrorException;

}
