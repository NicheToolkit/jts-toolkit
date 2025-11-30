package io.github.nichetoolkit.jts.parser;

import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiLineStringParser</code>
 * <p>The multi line string parser class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.jts.JtsParser
 * @since Jdk17
 */
public class MultiLineStringParser extends JtsParser<MultiLineString> {
    /**
     * <code>MultiLineStringParser</code>
     * <p>Instantiates a new multi line string parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see org.locationtech.jts.geom.GeometryFactory
     */
    public MultiLineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseMultiLineString</code>
     * <p>The parse multi line string method.</p>
     * @param root {@link tools.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @return {@link org.locationtech.jts.geom.MultiLineString} <p>The parse multi line string return object is <code>MultiLineString</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see org.locationtech.jts.geom.MultiLineString
     */
    public MultiLineString parseMultiLineString(JsonNode root) {
        LineString[] lineStrings = parseLineStrings(root.get(COORDINATES));
        return geometryFactory.createMultiLineString(lineStrings);
    }

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
