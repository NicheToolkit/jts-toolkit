package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.HashMap;
import java.util.Map;

import static io.github.nichetoolkit.jts.JtsGeojson.*;


/**
 * <p>GeometryParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class GeometryParser extends JtsParser<Geometry> {

    private Map<String, JtsParser> parsers;

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
    public Geometry parse(JsonNode node) throws JtsParseException {
        String typeName = node.get(TYPE).asText();
        JtsParser parser = parsers.get(typeName);
        if (parser != null) {
            return parser.parse(node);
        } else {
            throw new JtsParseException("Invalid geometry type: " + typeName);
        }
    }
}
