package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.HashMap;
import java.util.Map;

import static io.github.nichetoolkit.jts.JtsGeojson.*;


/**
 * <code>GeometryParser</code>
 * <p>The geometry parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class GeometryParser extends JtsParser<Geometry> {

    /**
     * <code>parsers</code>
     * {@link java.util.Map} <p>The <code>parsers</code> field.</p>
     * @see  java.util.Map
     */
    private final Map<String, JtsParser<?>> parsers;

    /**
     * <code>GeometryParser</code>
     * <p>Instantiates a new geometry parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public GeometryParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
        parsers = new HashMap<>();
        parsers.put(POINT, new PointParser(geometryFactory));
        parsers.put(MULTI_POINT, new MultiPointParser(geometryFactory));
        parsers.put(LINE_STRING, new LineStringParser(geometryFactory));
        parsers.put(MULTI_LINE_STRING, new MultiLineStringParser(geometryFactory));
        parsers.put(POLYGON, new PolygonParser(geometryFactory));
        parsers.put(MULTI_POLYGON, new MultiPolygonParser(geometryFactory));
        parsers.put(GEOMETRY_COLLECTION, new GeometryCollectionParser(geometryFactory, this));
    }

    @Override
    public Geometry parse(JsonNode node) throws JtsParserErrorException {
        String typeName = node.get(TYPE).asText();
        JtsParser<?> parser = parsers.get(typeName);
        if (parser != null) {
            return parser.parse(node);
        } else {
            throw new JtsParserErrorException("Invalid geometry type: " + typeName);
        }
    }
}
