package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>PointParser</code>
 * <p>The point parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PointParser extends JtsParser<Point> {

    /**
     * <code>PointParser</code>
     * <p>Instantiates a new point parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public PointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseCoordinate</code>
     * <p>The parse coordinate method.</p>
     * @param array {@link com.fasterxml.jackson.databind.JsonNode} <p>The array parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Coordinate
     * @return  {@link org.locationtech.jts.geom.Coordinate} <p>The parse coordinate return object is <code>Coordinate</code> type.</p>
     */
    public static Coordinate parseCoordinate(JsonNode array) {
        if (!array.isArray() || (array.size() != 2 && array.size() != 3))
            throw new AssertionError("expecting coordinate array with single point [ x, y, |z| ]");
        if (array.size() == 2) {
            return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble());
        }
        return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble(), array.get(2).asDouble());
    }

    /**
     * <code>parseCoordinates</code>
     * <p>The parse coordinates method.</p>
     * @param array {@link com.fasterxml.jackson.databind.JsonNode} <p>The array parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Coordinate
     * @return  {@link org.locationtech.jts.geom.Coordinate} <p>The parse coordinates return object is <code>Coordinate</code> type.</p>
     */
    public static Coordinate[] parseCoordinates(JsonNode array) {
        Coordinate[] points = new Coordinate[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            points[i] = PointParser.parseCoordinate(array.get(i));
        }
        return points;
    }

    /**
     * <code>parsePoint</code>
     * <p>The parse point method.</p>
     * @param node {@link com.fasterxml.jackson.databind.JsonNode} <p>The node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Point
     * @return  {@link org.locationtech.jts.geom.Point} <p>The parse point return object is <code>Point</code> type.</p>
     */
    public Point parsePoint(JsonNode node) {
        Coordinate coordinate = parseCoordinate(node.get(COORDINATES));
        return geometryFactory.createPoint(coordinate);
    }

    @Override
    public Point parse(JsonNode node) {
        return parsePoint(node);
    }
}
