package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParseException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>PointParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PointParser extends JtsParser<Point> {

    public PointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public static Coordinate parseCoordinate(JsonNode array) {
        assert array.isArray() && (array.size() == 2 || array.size() == 3) : "expecting coordinate array with single point [ x, y, |z| ]";
        if (array.size() == 2) {
            return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble());
        }
        return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble(), array.get(2).asDouble());
    }

    public static Coordinate[] parseCoordinates(JsonNode array) {
        Coordinate[] points = new Coordinate[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            points[i] = PointParser.parseCoordinate(array.get(i));
        }
        return points;
    }

    public Point parsePoint(JsonNode node) {
        Coordinate coordinate = parseCoordinate(node.get(COORDINATES));
        return geometryFactory.createPoint(coordinate);
    }

    @Override
    public Point parse(JsonNode node) throws JtsParseException {
        return parsePoint(node);
    }
}
