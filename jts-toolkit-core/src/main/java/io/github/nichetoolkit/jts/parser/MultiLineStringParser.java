package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiLineStringParser</code>
 * <p>The multi line string parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class MultiLineStringParser extends JtsParser<MultiLineString> {
    /**
     * <code>MultiLineStringParser</code>
     * <p>Instantiates a new multi line string parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public MultiLineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseMultiLineString</code>
     * <p>The parse multi line string method.</p>
     * @param root {@link com.fasterxml.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.MultiLineString
     * @return  {@link org.locationtech.jts.geom.MultiLineString} <p>The parse multi line string return object is <code>MultiLineString</code> type.</p>
     */
    public MultiLineString parseMultiLineString(JsonNode root) {
        LineString[] lineStrings = parseLineStrings(root.get(COORDINATES));
        return geometryFactory.createMultiLineString(lineStrings);
    }

    /**
     * <code>parseLineStrings</code>
     * <p>The parse line strings method.</p>
     * @param array {@link com.fasterxml.jackson.databind.JsonNode} <p>The array parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.LineString
     * @return  {@link org.locationtech.jts.geom.LineString} <p>The parse line strings return object is <code>LineString</code> type.</p>
     */
    private LineString[] parseLineStrings(JsonNode array) {
        LineString[] strings = new LineString[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            Coordinate[] coordinates = PointParser.parseCoordinates(array.get(i));
            strings[i] = geometryFactory.createLineString(coordinates);
        }
        return strings;
    }

    @Override
    public MultiLineString parse(JsonNode node) {
        return parseMultiLineString(node);
    }
}
