package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParseException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>LineStringParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LineStringParser extends JtsParser<LineString> {

    public LineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public LineString parseLineString(JsonNode root) {
        Coordinate[] coordinates = PointParser.parseCoordinates(root.get(COORDINATES));
        return geometryFactory.createLineString(coordinates);
    }

    @Override
    public LineString parse(JsonNode node) throws JtsParseException {
        return parseLineString(node);
    }
}
