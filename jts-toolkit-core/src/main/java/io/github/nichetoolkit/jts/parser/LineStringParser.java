package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>LineStringParser</code>
 * <p>The line string parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LineStringParser extends JtsParser<LineString> {

    /**
     * <code>LineStringParser</code>
     * <p>Instantiates a new line string parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public LineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseLineString</code>
     * <p>The parse line string method.</p>
     * @param root {@link com.fasterxml.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.LineString
     * @return  {@link org.locationtech.jts.geom.LineString} <p>The parse line string return object is <code>LineString</code> type.</p>
     */
    public LineString parseLineString(JsonNode root) {
        Coordinate[] coordinates = PointParser.parseCoordinates(root.get(COORDINATES));
        return geometryFactory.createLineString(coordinates);
    }

    @Override
    public LineString parse(JsonNode node) {
        return parseLineString(node);
    }
}
